package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Representing complex shapes
 */
public interface Geometry extends intersectable  {
    /**
     * Getter for normal vector
     * implementing {@link Geometry#getNormal(Point)}
     * @param point
     * @return
     */
    Vector getNormal(Point point);


    /**implementing {@link Geometry#findIntersections(Ray)}
     * @param ray
     * @return
     */
    List<Point> findIntersections(Ray ray);
}


