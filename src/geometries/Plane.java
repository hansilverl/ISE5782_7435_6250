package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * @author Hila Buznach & Hannah Silverberg
 * Plane (defined by a point and the orthogonal vector).
 */
public class Plane extends Geometry {

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
        if (p1.equals(p2) || p2.equals(p3) || p3.equals(p1))
            throw new IllegalArgumentException("All three vectors must be unique");
        Vector u = p2.subtract(p1);
        Vector v = p3.subtract(p1);

        Vector norm = u.crossProduct(v);

        _normal = norm.normalize();
        _q0 = p1; //Arbitrary pick to represent plane point
        if (bvhIsOn)
            createBoundingBox();
    }

    /**
     * The second constructor, receives a point and a vector.
     *
     * @param point point in plane
     * @param vec   vector in plane
     */
    public Plane(Point point, Vector vec) {
        _normal = vec.normalize();
        _q0 = point;
        if (bvhIsOn)
            createBoundingBox();
    }


    public Point get_q0() {
        return _q0;
    }

    /**
     * getter for normal vector
     *
     * @return normal of plane
     */
    public Vector getNormal() {
        return _normal;
    }

    /**
     * implementing {@link Geometry#getNormal(Point)}
     *
     * @param point reference point
     * @return normal to plane
     */
    @Override
    public Vector getNormal(Point point) {
        return getNormal(); //Return non-parametric result.
    }

    @Override
    public String toString() {
        return "Plane{" +
                "_normal=" + _normal +
                ", _q0=" + _q0 +
                '}';
    }

    /**
     * @param ray Ray pointing towards the intersection point
     * @return Intersection list
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        Point p0 = ray.getP0();
        Vector v = ray.getDir();
        Vector n = _normal;

        if (_q0.equals(p0)) {
            return null;
        }

        Vector Q_P0 = _q0.subtract(p0);
        double t = alignZero(n.dotProduct(Q_P0));

        //meaning t===0 Origin of the ray lies on the plane
        if (isZero(t)) {
            return null;
        }

        double nv = alignZero(n.dotProduct(v));

        //if the ray is parallel to the point, there will be no intersection point
        if (isZero(nv)) {
            return null;
        }

        double m = alignZero(t / nv);

        if (m <= 0) {
            return null;
        }

        if (alignZero(m - maxDistance) > 0) {
            return null;
        }
        GeoPoint geoPoint = new GeoPoint(this, ray.getPoint(m));

        return List.of(geoPoint);
    }

    /**
     * Bound objecs
     */
    @Override
    public void createBoundingBox() {
        box=null;
    }
}
