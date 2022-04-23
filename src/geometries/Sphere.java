package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Class to represent a sphere
 * @author Hila Buznach & Hannah Silverberg
 */
public class Sphere implements Geometry{
    private final Point _center;    //Center of the sphere
    private final double _radius;   //Radius of a sphere


    /**
     * Sphere constructor
     * @param center value for Sphere center
     * @param radius value for Sphere radius
     */
    public Sphere(Point center, double radius) {
        _center = center;
        _radius = radius;
    }

    /**
     * @return the center of the sphere
     */
    public Point get_center() {
        return _center;
    }

    /**
     * @return the radius of the sphere
     */
    public double get_radius() {
        return _radius;
    }

    /**``
     * implementing {@link Geometry#getNormal(Point)}
     * Getter for normal vector
     * @param point on sphere
     * @return normalized vector by point
     */
    @Override
    public Vector getNormal(Point point) {
        return point.subtract(_center).normalize();
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "_center=" + _center +
                ", _radius=" + _radius +
                '}';
    }

    /**
     * implementing {@link Intersectable#findIntersection(Ray)} }
     * @param ray
     * @return
     */

    @Override
    public List<Point> findIntersection(Ray ray) {
       //TODO: Need help
        return null;
    }
}
