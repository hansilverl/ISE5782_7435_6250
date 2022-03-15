package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Plane (defined by a point and the orthogonal vector).
 */
public class Plane implements Geometry {
    
    final private Vector _normal;   //Normalized vector
    final private Point _q0;    //q0 point for plane

    /**
     * constructor gets 3 parameters:
     *
     * @param p1 point in plane
     * @param p2 point in plane
     * @param p3 point in plane
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
     *
     * @param point point in plane
     * @param vec vector in plane
     */
    public Plane(Point point, Vector vec) {
        _normal = vec.normalize();
        _q0 = point;
    }

    /**
     * getter for normal vector
     *
     * @return normal
     */

    public Vector getNormal() {
        return _normal;
    }

    /**
     * implementing {@link Geometry#getNormal(Point)}
     *
     * @param point reference point
     * @return normal
     */
    @Override
    public Vector getNormal(Point point) {
        return getNormal(); //Return non-parametric result.
    }

    /**
     * finding the intersection of rays
     * implementing {@link Intersectable#findIntersection(Ray)} )}
     * @param ray in plane
     * @return
     */

    public List<Point> findIntersection(Ray ray) {
        Point p0 = ray.getP0();
        Vector v = ray.getDir();
        Vector n = _normal;

        double nv = n.dotProduct(v);

        //if the ray is parallel to the point, there will be no intersection point
        if (isZero(nv)) {
            return null;
        }

        Vector Q_P0 = _q0.subtract(p0);
        double t = alignZero(n.dotProduct(Q_P0) / nv);

        //meaning t===0 Origin of the ray lies on the plane
        if (isZero(t)) {
            return null;
        }
        // if t < 0 - the direction is in the opposite
        if (t > 0) {
            Point point = p0.add(v.scale(t));
            return List.of(point);
        }
        return null;
    }
}
