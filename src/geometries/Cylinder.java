package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Class to represent a cylinder
 */
public class Cylinder implements Geometry {
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
