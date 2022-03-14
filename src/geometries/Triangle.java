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
     * implementing {@link geometries.Intersectable#findIntersection(Ray)} Ray)}
     *
     * @param ray of triangle
     * @return
*/
    @Override
    public List<Point> findIntersection(Ray ray) {
        return null;
    }
}
