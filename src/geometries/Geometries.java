package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Composite class to gather other {@link Geometry} based objects
 */
public class Geometries extends Geometry {
    private List<Intersectable> _intersectables;

    /**
     * constructor of Geometry
     *
     * @param intersectables array of {@link Intersectable} objects
     */
    public Geometries(Intersectable... intersectables) {
        _intersectables = new LinkedList<>();
        Collections.addAll(_intersectables, intersectables);
    }

    public Geometries() {
        _intersectables = new LinkedList<>();
    }

    /**
     * @param geometries list to add
     */
    public void add(Intersectable... geometries) {
        Collections.addAll(_intersectables, geometries);
    }


    /**
     * finding intersection between rays
     * implementing {@link Intersectable#findIntersection(Ray)}
     *
     * @param ray of triangle
     * @return intersection point
     */


    @Override
    public List<Point> findIntersection(Ray ray) {
        List<Point> result = null;

        //gets list of intersections of all elements with the ray
        for (Intersectable item : _intersectables) {
            List<Point> itemPoints = item.findIntersection(ray);
            if (itemPoints != null) {
                if (result == null) {
                    result = new LinkedList<>();
                }
                result.addAll(itemPoints);
            }
        }
        return result;
    }

    /**
     * @param ray Ray pointing towards the intersection point
     * @return list of geo intersections
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> result = null;

        //gets list of intersections of all elements with the ray
        for (Intersectable item : _intersectables) {
            List<GeoPoint> itemPoints = item.findGeoIntersections(ray);
            if (itemPoints != null) {
                if (result == null) {
                    result = new LinkedList<>();
                }
                result.addAll(itemPoints);
            }
        }
        return result;
    }

    /**
     * Getter for normal vector
     * Returns the normal of a point
     * implementing {@link Geometry#getNormal(Point)}
     *
     * @param point
     * @return
     */
    @Override
    public Vector getNormal(Point point) {
        return null;
    }

}