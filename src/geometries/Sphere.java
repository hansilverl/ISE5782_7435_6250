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
     * @param ray Ray pointing towards the intersection point
     * @return list og geo intersections
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance) {
       /* u = O - p0
        tm = v * u
        d = sqrt(u^2 - tm^2)   if d >= r there are no intersections
        th = sqrt(r^2 -d^2)
        t1,t2 = tm +/- th, pI = p0 + ti   we only take ti > 0
                */
        Point P0 = ray.getP0();
        Vector v = ray.getDir();


        if (P0.equals(_center) ) {
            Point pnt=_center.add(v.scale(_radius));
            if (pnt.distance(P0)<=maxDistance) {
                GeoPoint ret = new GeoPoint(this, pnt);
                return List.of(ret);
            }
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
            Point pn1=ray.getPoint(t1);
            double distance1 = pn1.distance(P0);
            Point pn2=ray.getPoint(t2);
            double distance2 = pn2.distance(P0);
            if(distance1<=maxDistance && distance2<=maxDistance){
                GeoPoint P1 = new GeoPoint(this,pn1);
                GeoPoint P2 = new GeoPoint(this,pn2);
                return List.of(P1, P2);
            }
            if(distance1<=maxDistance){
                GeoPoint P1 = new GeoPoint(this,pn1);
                return List.of(P1);
            }
            if(distance2<=maxDistance){
                GeoPoint P2 = new GeoPoint(this,pn2);
                return List.of(P2);
            }
        }
        if (t1 > 0) {
            Point pn1=ray.getPoint(t1);
            double distance1 = pn1.distance(P0);
            if(distance1<=maxDistance){
                GeoPoint P1 = new GeoPoint(this,pn1);
                return List.of(P1);
            }
        }
        if (t2 > 0) {
            Point pn2=ray.getPoint(t2);
            double distance2 = pn2.distance(P0);
            if(distance2<=maxDistance){
                GeoPoint P2 = new GeoPoint(this,pn2);
                return List.of(P2);
            }
        }
        return null;
    }
}

