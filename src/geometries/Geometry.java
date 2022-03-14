package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Representing complex shapes
 */
public interface Geometry extends intersectable  {
    /**
     * @param point
     * @return
     */
    Vector getNormal(Point point);
}


