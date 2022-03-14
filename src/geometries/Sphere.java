package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Class to represent a sphere
 */
public class Sphere implements Geometry{
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
     * @param ray
     * @return
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
