package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Representing complex shapes
 * Composite class to gather other {@link Geometry} based objects
 */
public interface Geometry extends Intersectable {

    /**
     * Getter for normal vector
     *
     * @param point
     * @return
     */
    Vector getNormal(Point point);


    /**
     * @param ray
     * @return
     */
    List<Point> findIntersections(Ray ray);
}


