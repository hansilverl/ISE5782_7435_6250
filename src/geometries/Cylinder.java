package geometries;

import primitives.Double3;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Class to represent a cylinder
 *
 * @author Hila Buznach & Hannah Silverberg
 */
public class Cylinder extends Tube {
    private final double _height;


    //Constructor
    public Cylinder(double height, Ray axisRay, double radius) {
        super(axisRay, radius);
        _height = height;
    }

    public double get_height() {
        return _height;
    }

    /**
     * implementing {@link Geometry#getNormal(Point)}
     * getter for normal vector
     *
     * @param point
     * @return
     */
    @Override
    public Vector getNormal(Point point) {
        Point p0 = getAxisRay().getP0();
        Vector opp = getAxisRay().getDir();

        if (p0.equals(point))
            return opp;

        Vector hypo = point.subtract(p0);
        double t = hypo.dotProduct(opp);
        if (isZero(t - _height) || isZero(t))          // point is on the bases of the cylinder
            return opp;                        // axisRay vector

        Point center = p0.add(opp.scale(t));
        return point.subtract(center);
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "_height=" + _height +
                '}';
    }

    /**
     * implementing {@link Intersectable#findIntersection(Ray)}
     *
     * @param ray
     * @return
     */
    @Override
    public List<Point> findIntersection(Ray ray) {
        return null;
    }
}
