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
public class Geometries extends Intersectable {
    protected List<Intersectable> _intersectables;

    /**
     * constructor of Geometry
     *
     * @param intersectables array of {@link Intersectable} objects
     */
    public Geometries(Intersectable... intersectables) {
        if (bvhIsOn)
            createBoundingBox();
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
        if (bvhIsOn)
            createBoundingBox();
        Collections.addAll(_intersectables, geometries);
    }


    /**
     * @param ray Ray pointing towards the intersection point
     * @return list of geo intersections
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> result = null;

        //gets list of intersections of all elements with the ray
        for (Intersectable item : _intersectables) {
            List<GeoPoint> itemPoints = item.findGeoIntersectionsHelper(ray, maxDistance);
            if (itemPoints != null) {
                if (result == null) {
                    result = new LinkedList<>();
                }
                result.addAll(itemPoints);
            }
        }
        return result;
    }

    @Override
    public void createBoundingBox() {
        if (_intersectables == null)
            return;
        double minX = Double.POSITIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        double minZ = Double.POSITIVE_INFINITY;
        double maxX = Double.NEGATIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;
        double maxZ = Double.NEGATIVE_INFINITY;
        for (Intersectable geo : _intersectables) {
            if (geo.box != null) {
                minX = Math.min(minX, geo.box.minimums.getX());
                minY = Math.min(minY, geo.box.minimums.getY());
                minZ = Math.min(minZ, geo.box.minimums.getZ());
                maxX = Math.max(maxX, geo.box.maximums.getX());
                maxY = Math.max(maxY, geo.box.maximums.getY());
                maxZ = Math.max(maxZ, geo.box.maximums.getZ());
            }
        }
        box = new BoundingBox(new Point(minX, minY, minZ), new Point(maxX, maxY, maxZ));
    }
}