package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.MissingResourceException;

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
     * Rendering the image
     *
     * @return the object - camera
     */
    public Camera renderImage() {
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
            int forhelp=0;
            //rendering the image
            int nX = imageWriter.getNx();
            int nY = imageWriter.getNy();
            for (int i = 0; i < nX; i++) {
                for (int j = 0; j < nY; j++) {
                    if(i==303&&j==303)
                        forhelp = 1;
                    Color pixelColor = castRay(nX, nY, i, j);
                    imageWriter.writePixel(i, j, pixelColor);
                }
            }
            return this;
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
        }
    }

    /**
     * Casting ray
     *
     * @param nx row number
     * @param ny column number
     * @param i  current row
     * @param j  current number
     */
    private Color castRay(int nx, int ny, int i, int j) {
        Ray ray = constructRay(nx, ny, j, i);
        Color color = rayTracer.traceRay(ray);
        imageWriter.writePixel(j, i, color);
        return color;
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
     * Writing to image
     */
    public void writeToImage() {
        if (imageWriter == null)
            throw new MissingResourceException("missing resource", "imageWriter", "");
        else
            imageWriter.writeToImage();
    }
}