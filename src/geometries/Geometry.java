package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Representing complex shapes
 */
public interface Geometry {
    Vector getNormal(Point point);
}

