package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * @author Hila Buznach & Hannah Silverberg
 * Representing Triangle (defined by 3 points ).
 */
public class Triangle extends Polygon  {
    /**
     * @param p1 point of a triangle
     * @param p2 point of a triangle
     * @param p3 point of a triangle
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }
    //TODO: Do we need to check if p1.length + p2.length >=p3.length ect. ?

    /**
     * Getter for normal vector
     * Returns the normal of a point
     * implementing {@link Geometry#getNormal(Point)}
     *
     * @param point in an axis
     * @return normalized vector
     */
    @Override
    public Vector getNormal(Point point) {
        return super.getNormal(point);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane +
                '}';
    }

    /**
     * finding intersection
     * implementing {@link geometries.Intersectable#findGeoIntersections(Ray)} Ray)}
     *
     * @param ray of triangle
     * @return intersection points
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance) {
        return super.findGeoIntersectionsHelper(ray,maxDistance);
    }
}
