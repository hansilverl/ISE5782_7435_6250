package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

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

    public Camera(Point my_p0, Vector my_vUp, Vector my_vTo) {
        p0 = my_p0;
        vUp = my_vUp;
        vTo = my_vTo;
        double ans = vUp.dotProduct(vTo);
        try {
            if (ans == 0) {
                vRight = vUp.crossProduct(vTo).normalize();
                vUp.normalize();
                vTo.normalize();
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
     * set view plane size
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
     * setting veiw plane distance
     * @param dist distance to set
     * @return object
     */
    public Camera setVPDistance(double dist) {
        distance = dist;
        return this;
    }

    //TODO: dodododododod
    public Object setImageWriter(ImageWriter base_render_test) {
        return this;
    }

    /**
     * @param Nx x value of n
     * @param Ny y value of n
     * @param j column
     * @param i row
     * @return constructed ray
     */
    public Ray constructRay(int Nx, int Ny, int j, int i) {
        Point pCenter = p0.add(vTo.scale(distance));
        double Ry = height / Ny;
        double Rx = width / Nx;
        double Yi = -1 * (i - alignZero((double) (Ny - 1) / 2)) * Ry;
        double Xj = (j - alignZero((double) (Nx - 1) / 2)) * Rx;
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

}