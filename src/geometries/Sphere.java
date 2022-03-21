package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Class to represent a sphere
 */
public class Sphere implements Geometry{
    private final Point _center;
    private final double _radius;

    public Sphere(Point center, double radius) {
        _center = center;
        _radius = radius;
    }

    public Point get_center() {
        return _center;
    }

    public double get_radius() {
        return _radius;
    }

    /**``
     * implementing {@link Geometry#getNormal(Point)}
     * Getter for normal vector
     * @param point
     * @return
     */
    @Override
    public Vector getNormal(Point point) {
        return null;
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
        return null;
    }
}
