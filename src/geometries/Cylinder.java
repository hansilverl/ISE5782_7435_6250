package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Class to represent a cylinder
 */
public class Cylinder implements Geometry {
    private final double _height;

    public Cylinder(double height) {
        _height = height;
    }

    public double get_height() {
        return _height;
    }

    /**
     * implementing {@link Geometry#getNormal(Point)}
     * getter for normal vector
     * @param point
     * @return
     */
    @Override
    public Vector getNormal(Point point) {
        return null;
    }
    
    @Override
    public String toString() {
        return "Cylinder{" +
                "_height=" + _height +
                '}';
    }

    /**
     * implementing {@link Intersectable#findIntersection(Ray)}
     * @param ray
     * @return
     */
    @Override
    public List<Point> findIntersection(Ray ray) {
        return null;
    }
}
