package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Class to represent a sphere
 *
 * @author Hila Buznach & Hannah Silverberg
 */
public class Sphere extends Geometry {
    private final Point _center;    //Center of the sphere
    private final double _radius;   //Radius of a sphere


    /**
     * Sphere constructor
     *
     * @param center value for Sphere center
     * @param radius value for Sphere radius
     */
    public Sphere(Point center, double radius) {
        _center = center;
        _radius = radius;
    }

    /**
     * @return the center of the sphere
     */
    public Point get_center() {
        return _center;
    }

    /**
     * @return the radius of the sphere
     */
    public double get_radius() {
        return _radius;
    }

    /**
     * ``
     * implementing {@link Geometry#getNormal(Point)}
     * Getter for normal vector
     *
     * @param point on sphere
     * @return normalized vector by point
     */
    @Override
    public Vector getNormal(Point point) {
        return point.subtract(_center).normalize();
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "_center=" + _center +
                ", _radius=" + _radius +
                '}';
    }

    /**
     * implementing {@link Intersectable#findIntersection(Ray)} }
     *
     * @param ray given to find intersection
     * @return intersection
     */

    @Override
    public List<Point> findIntersection(Ray ray) {
        /*find intersections formula:
            u = O - p0
            tm = v * u
            d = sqrt(u^2 - tm^2)   if d >= r there are no intersections
            th = sqrt(r^2 -d^2)
            t1,t2 = tm +/- th, pI = p0 + ti   we only take ti > 0
         */
        Point P0 = ray.getP0();
        Vector v = ray.getDir();

        if (P0.equals(_center)) {
            return List.of(_center.add(v.scale(_radius)));
        }

        Vector U = _center.subtract(P0);

        double tm = alignZero(v.dotProduct(U));
        double d = alignZero(Math.sqrt(U.lengthSquared() - tm * tm));

        // no intersections : the ray direction is above the sphere
        if (d >= _radius) {
            return null;
        }

        double th = alignZero(Math.sqrt(_radius * _radius - d * d));
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        if (t1 > 0 && t2 > 0) {
            // Point P1 = P0.add(v.scale(t1));
            // Point P2 = P0.add(v.scale(t2));
            Point P1 = ray.getPoint(t1);
            Point P2 = ray.getPoint(t2);
            return List.of(P1, P2);
        }
        if (t1 > 0) {
            //Point P1 = P0.add(v.scale(t1));
            Point P1 = ray.getPoint(t1);
            return List.of(P1);
        }
        if (t2 > 0) {
            //Point P2 = P0.add(v.scale(t2));
            Point P2 = ray.getPoint(t2);
            return List.of(P2);
        }
        return null;
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        return null;
    }
}

