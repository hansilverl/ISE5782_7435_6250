package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Random;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Class to represent a camera
 *
 * @author Hila Buznach & Hannah Silverberg
 */
public class Camera {
    private Point p0;
    private Vector vRight;
    private Vector vUp;
    private Vector vTo;
    private double height;
    private double width;
    private double distance;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;

    private int nSS = 64;   //number of rays in beam for supersampling

    private int maxLevelAdaptiveSS = 3;    //maximum level of recursion for adaptive supersampling

    private int threadsCount = 3;

    /**
     * Constructor
     *
     * @param my_p0  value of p0
     * @param my_vUp value of vUp
     * @param my_vTo value of vTo
     */
    public Camera(Point my_p0, Vector my_vTo, Vector my_vUp) {
        p0 = my_p0;
        vUp = my_vUp.normalize();
        vTo = my_vTo.normalize();
        double ans = vUp.dotProduct(vTo);
        try {
            if (ans == 0) {
                vRight = vTo.crossProduct(vUp).normalize();
            }
            //if the vectors aren't vertical, throw exception.
            else vRight = new Vector(0, 0, 0);
        } catch (IllegalArgumentException NotVertical) {
        }
    }

    /**
     * render the image using the image writer, using super sampling in the random method
     */
    public Camera renderImageSuperSampling() {
        checkException();
        // for each pixel
        for (int i = 0; i < imageWriter.getNx(); i++) {
            for (int j = 0; j < imageWriter.getNy(); j++) {
                imageWriter.writePixel(j, i, castBeamSuperSampling(j, i));
            }
        }
        return this;
    }


    /**
     * Casts beam of rays around the center ray of pixel
     *
     * @param j col index
     * @param i row index
     * @return Color for a certain pixel
     */
    private Color castBeamSuperSampling(int j, int i) {
        List<Ray> beam = constructBeamSuperSampling(imageWriter.getNx(), imageWriter.getNy(), j, i);
        Color color = Color.BLACK;
        // calculates the average colour of rays traced
        for (Ray ray : beam) {
            color = color.add(rayTracer.traceRay(ray));
        }
        return color.reduce(nSS);
    }

    /**
     * Using super-sampling according to the random algorithm (we will send rays that are randomly placed inside
     * The pixel is in addition to the original beam.
     *
     * @param nX index of row pixels
     * @param nY index of col pixels
     * @param j  col index
     * @param i  row index
     * @return list of rays in beam
     */
    private List<Ray> constructBeamSuperSampling(int nX, int nY, int j, int i) {
        List<Ray> beam = new LinkedList<>();
        beam.add(constructRay(nX, nY, j, i));
        double ry = height / nY;
        double rx = width / nX;
        double yScale = alignZero((j - nX / 2d) * rx + rx / 2d);
        double xScale = alignZero((i - nY / 2d) * ry + ry / 2d);
        Point pixelCenter = p0.add(vTo.scale(distance)); // center
        if (!isZero(yScale))
            pixelCenter = pixelCenter.add(vRight.scale(yScale));
        if (!isZero(xScale))
            pixelCenter = pixelCenter.add(vUp.scale(-1 * xScale));
        Random rand = new Random();
        // create rays randomly around the center ray
        for (int c = 0; c < nSS; c++) {
            // move randomly in the pixel
            double dxfactor = rand.nextBoolean() ? rand.nextDouble() : -1 *
                    rand.nextDouble();
            double dyfactor = rand.nextBoolean() ? rand.nextDouble() : -1 *
                    rand.nextDouble();
            double dx = rx * dxfactor;
            double dy = ry * dyfactor;
            Point randomPoint = pixelCenter;
            if (!isZero(dx))
                randomPoint = randomPoint.add(vRight.scale(dx));
            if (!isZero(dy))
                randomPoint = randomPoint.add(vUp.scale(-1 * dy));
            beam.add(new Ray(p0, randomPoint.subtract(p0)));
        }
        return beam;

    }


    /**
     * render the image using the image writer, using adaptive super sampling(pretty similar to normal super sampling)
     */
    public Camera renderImageAdaptiveSuperSampling() {
        checkException();
        // for each pixel
        for (int i = 0; i < imageWriter.getNx(); i++) {
            for (int j = 0; j < imageWriter.getNy(); j++) {
                imageWriter.writePixel(j, i, castBeamAdaptiveSuperSampling(j, i));
            }
        }
        return this;
    }

    /**
     * @param j col
     * @param i row
     * @return average colour of pixel
     */
    private Color castBeamAdaptiveSuperSampling(int j, int i) {
        Ray center = constructRay(imageWriter.getNx(), imageWriter.getNy(), j, i);
        Color centerColor = rayTracer.traceRay(center);
        return calcAdaptiveSuperSampling(imageWriter.getNx(), imageWriter.getNy(), j, i, maxLevelAdaptiveSS, centerColor);
    }

    /**
     * Calculating actual colour of each pixel
     * @param nX col
     * @param nY    row
     * @param j col index
     * @param i rox index
     * @param maxLevel of recursion
     * @param centerColor   of pixel
     * @return calculated color of pixels
     */
    private Color calcAdaptiveSuperSampling(int nX, int nY, int j, int i, int maxLevel, Color centerColor) {
        if (maxLevel == 0) {
            //return _rayTracerBase.traceRay(center);
            return centerColor;
        }
        Color color = centerColor;
        // divide pixel into 4 mini-pixels
        Ray[] beam = new Ray[]{constructRay(2 * nX, 2 * nY, 2 * j, 2 * i),
                constructRay(2 * nX, 2 * nY, 2 * j, 2 * i + 1),
                constructRay(2 * nX, 2 * nY, 2 * j + 1, 2 * i),
                constructRay(2 * nX, 2 * nY, 2 * j + 1, 2 * i + 1)};
        for (int ray = 0; ray < 4; ray++) {
            Color currentColor = rayTracer.traceRay(beam[ray]);
            if (!currentColor.equals(centerColor))
                currentColor = calcAdaptiveSuperSampling(2 * nX, 2 * nY,
                        2 * j + ray / 2, 2 * i + ray % 2, maxLevel - 1, currentColor);
            color = color.add(currentColor);
        }
        return color.reduce(5);
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getDistance() {
        return distance;
    }

    /**
     * Set view plane size
     *
     * @param wd -width
     * @param ht -height
     * @return object
     */
    public Camera setVPSize(double wd, double ht) {
        width = wd;
        height = ht;
        return this;
    }

    /**
     * Setting view plane distance
     *
     * @param dist distance to set
     * @return object
     */
    public Camera setVPDistance(double dist) {
        distance = dist;
        return this;
    }

    /**
     * Setting image writer field
     *
     * @param myImageWriter to set
     * @return the object
     */
    public Camera setImageWriter(ImageWriter myImageWriter) {
        imageWriter = myImageWriter;
        return this;
    }

    /**
     * @param Nx x value of n
     * @param Ny y value of n
     * @param j  column
     * @param i  row
     * @return constructed ray
     */
    public Ray constructRay(int Nx, int Ny, int j, int i) {
        Point pCenter = p0.add(vTo.scale(distance));
        double Ry = height / Ny;
        double Rx = width / Nx;

        double Yi = alignZero(-(i - (Ny - 1) / 2.0) * Ry);
        double Xj = alignZero((j - (Nx - 1) / 2.0) * Rx);

        Point intersectionPoint = pCenter;
        if (!isZero(Xj)) {
            intersectionPoint = intersectionPoint.add(vRight.scale(Xj));
        }
        if (!isZero(Yi)) {
            intersectionPoint = intersectionPoint.add(vUp.scale(Yi));
            ;
        }
        Vector dir = intersectionPoint.subtract(p0);
        if (!isZero(dir.length())) {
            Ray r = new Ray(p0, dir);
            return r;
        } else {
            throw new IllegalArgumentException();
        }

    }

    /**
     * Setting ray tracer field
     *
     * @param myRayTracer to set
     * @return the object - camera
     */
    public Camera setRayTracer(RayTracerBasic myRayTracer) {
        rayTracer = myRayTracer;
        return this;
    }

    /**
     * checks that all resources needed for camera exist
     */
    public void checkException() {
        try {
            if (p0 == null) {
                throw new MissingResourceException("missing resource", "p0", "");
            }
            if (vRight == null) {
                throw new MissingResourceException("missing resource", "vRight", "");
            }
            if (vTo == null) {
                throw new MissingResourceException("missing resource", "vTo", "");
            }
            if (vUp == null) {
                throw new MissingResourceException("missing resource", "vUp", "");
            }
            if (height == 0) {
                throw new MissingResourceException("missing resource", "height", "");
            }
            if (width == 0) {
                throw new MissingResourceException("missing resource", "width", "");
            }
            if (distance == 0) {
                throw new MissingResourceException("missing resource", "distance", "");
            }
            if (imageWriter == null) {
                throw new MissingResourceException("missing resource", "imageWriter", "");
            }
            if (rayTracer == null) {
                throw new MissingResourceException("missing resource", RayTracerBase.class.getName(), "");
            }
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
        }
    }

    /**
     * Rendering the image
     *
     * @return the object - camera
     */
    public Camera renderImage() {
        checkException();
        //rendering the image
        int delme = 0;
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++) {
                Color pixelColor = castRay(nX, nY, j, i);
                imageWriter.writePixel(j, i, pixelColor);
            }
        }
        return this;
    }

    /**
     * Casting ray
     *
     * @param nx row number
     * @param ny column number
     * @param i  current row
     * @param j  current number
     */
    private Color castRay(int nx, int ny, int j, int i) {
        Ray ray = constructRay(nx, ny, j, i);
        Color color = rayTracer.traceRay(ray);
        return color;
    }

    /**
     * Casts a ray through a pixel, traces it and returns the color for the pixel
     *
     * @param j col index
     * @param i row index
     * @return Color for a certain pixel
     */
    private Color castRay(int j, int i) {
        Ray ray = constructRay(imageWriter.getNx(), imageWriter.getNy(), j, i);
        return rayTracer.traceRay(ray);
    }

    /**
     * Printing grid
     *
     * @param interval between line
     * @param color    to print
     */
    public void printGrid(int interval, Color color) {
        if (imageWriter == null)
            throw new MissingResourceException("missing resource", "imageWriter", "");
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nX; i += interval) {
            for (int j = 0; j < nY; j++) {
                imageWriter.writePixel(i, j, color);
            }
        }

        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j += interval) {
                imageWriter.writePixel(i, j, color);
            }
        }
    }
    /**
     * renders image using multithreading
     *
     * @return this
     */
    public Camera renderImageMultiThreading() {
        Pixel.initialize(imageWriter.getNy(), imageWriter.getNx(), 1);
        while (threadsCount-- > 0) {
            new Thread(() -> {
                for (Pixel pixel = new Pixel(); pixel.nextPixel(); Pixel.pixelDone())
                    imageWriter.writePixel(pixel.col, pixel.row, castRay(pixel.col, pixel.row));
            }).start();
        }
        Pixel.waitToFinish();
        return this;
    }

    /**
     * renders image using multithreading and adaptive supersampling
     *
     * @return this
     */
    public Camera renderImageMultiThreading_AdaptSS() {
        Pixel.initialize(imageWriter.getNy(), imageWriter.getNx(), 60);
        while (threadsCount-- > 0) {
            new Thread(() -> {
                for (Pixel pixel = new Pixel(); pixel.nextPixel(); Pixel.pixelDone())
                    imageWriter.writePixel(pixel.col, pixel.row, castBeamAdaptiveSuperSampling(pixel.col, pixel.row));
            }).start();
        }
        Pixel.waitToFinish();
        return this;
    }

    /**
     * Writing to image
     */
    public void writeToImage() {
        if (imageWriter == null)
            throw new MissingResourceException("missing resource", "imageWriter", "");
        else
            imageWriter.writeToImage();
    }


}