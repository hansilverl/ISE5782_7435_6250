package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Plane (defined by a point and the orthogonal vector).
 */
public class Plane implements Geometry {
    final private Vector _normal;
    final private Point _q0;

    /**
     * constructor gets 3 parameters
     *
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point p1, Point p2, Point p3) {
        Vector u = p2.subtract(p1);
        Vector v = p3.subtract(p1);

        Vector norm = u.crossProduct(v);

        _normal = norm.normalize();
        _q0 = p1; //Arbitrary pick to represent plane point
    }

    /**
     * The second constructor, receives a point and a vector.
     */
    public Plane(Point point, Vector vec) {
        _normal = vec.normalize();
        _q0 = point;
    }

    /**
     * getter for normal vector
     */

    public Vector getNormal() {
        return _normal;
    }

    /**
     * implemeneting {@link Geometry#getNormal(Point)}
     *
     * @param point reference point
     * @return normal
     */
    @Override
    public Vector getNormal(Point point) {
        return getNormal(); //Return non-parametric result.
    }
}
