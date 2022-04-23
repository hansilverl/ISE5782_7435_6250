package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Composite class to gather other {@link Geometry} based objects
 */
public class Geometries implements Geometry {
    List<Intersectable> _intersectables;

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
     * finding intersection between rays
     * implementing {@link Intersectable#findIntersection(Ray)}
     *
     * @param ray of triangle
     * @return intersection point
     */


    @Override
    public List<Point> findIntersection(Ray ray) {
        List<Point> result = null;
        for (Intersectable item : _intersectables) {
            List<Point> itemPointList = item.findIntersection(ray);
            if (itemPointList != null) {
                result = new LinkedList<>();
            }

        }
        return null;
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
