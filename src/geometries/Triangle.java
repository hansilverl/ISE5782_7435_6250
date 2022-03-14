package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Class to represent a triangle
 */
public class Triangle implements Geometry {

    /**
     * Getter for normal vector
     * Returns the normal of a point
     * implementing {@link Geometry#getNormal(Point)}
     *
     * @param point
     * @return
     */
    @Override
    public Vector getNormal(Point point) {
        return null;
    }


    /**
     * finding intersection between rays
     * implementing {@link Geometry#findIntersections(Ray)}
     *
     * @param ray of triangle
     * @return
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
