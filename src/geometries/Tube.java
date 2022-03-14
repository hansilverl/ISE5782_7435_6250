package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Class to represent a tube🧪
 */
public class Tube implements Geometry {
    private final Ray _axisRay;
    private final double _radius;

    /**
     * Constructing a tube
     * @param axisRay
     * @param radius
     */
    public Tube(Ray axisRay, double radius) {
        _axisRay = axisRay;
        _radius = radius;
    }

    /**
     * @return _axisRay
     */
    public Ray getAxisRay() {
        return _axisRay;
    }

    /**
     * @return _radius
     */
    public double getRadius() {
        return _radius;
    }

    /**
     * implementing {@link Geometry#getNormal(Point)}
     * Getter for normal vector
     * @param point
     * @return
     */
    @Override
    public Vector getNormal(Point point) {
        return null;
    }

    /**
     * implementing {@link Geometry#findIntersections(Ray)}
     * @param ray in Tube
     * @return
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}