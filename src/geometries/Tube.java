package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * @author Hila Buznach & Hannah Silverberg
 * Class to represent a tube🧪
 */
public class Tube extends Geometry {
    private final Ray _axisRay;     //Ray axis
    private final double _radius;   //Radius of Tube

    /**
     * Constructing a tube
     * @param axisRay axis direction
     * @param radius of tube
     */
    public Tube(Ray axisRay, double radius) {
        _axisRay = axisRay;
        _radius = radius;
    }

    /**
     * @return _axisRay
     */
    public Ray getAxisRay() {
        return _axisRay;
    }

    /**
     * @return _radius
     */
    public double getRadius() {
        return _radius;
    }

    /**
     * implementing {@link Geometry#getNormal(Point)}
     * Getter for normal vector
     * @param point to normalize
     * @return normalized vector
     */
    @Override
    public Vector getNormal(Point point) {
        Vector v = _axisRay.getDir();
        Point p0 = _axisRay.getP0();
        double t = v.dotProduct(point.subtract(p0));
        Point o = p0.add(v.scale(t));
        return point.subtract(o).normalize();
    }

    @Override
    public String toString() {
        return "Tube{" +
                "_axisRay=" + _axisRay +
                ", _radius=" + _radius +
                '}';
    }

    /**
     * implementing {@link Intersectable#findGeoIntersectionsHelper(Ray,double)} }
     * @param ray in Tube
     * @return (nothing because this is a bonus)
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance){
        return null;
    }

}