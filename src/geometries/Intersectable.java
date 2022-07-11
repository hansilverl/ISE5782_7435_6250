package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;
import java.util.Objects;

/**
 * common interface for all graphic 3D objects
 * that interface with a specific Ray {@link primitives.Ray}
 */
public abstract class Intersectable {


    protected boolean bvhIsOn = true;  //a field to turn on and off the bvh

    public BoundingBox box; //Boundary box

    /**
     * find all intersection points {@link Point}
     * that intersect the Shape from a specific Ray {@link Ray}
     *
     * @param ray Ray pointing towards the intersection point
     */
    public List<Point> findIntersection(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * @param b boolean value for bvh
     * @return this (using builder pattern)
     */
    public Intersectable setBvhIsOn(boolean b) {
        if (!bvhIsOn && b)//no box has been created
            createBoundingBox();
        bvhIsOn=b;
        return this;
    }


    /**
     * Helper Class - class GeoPoint
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point point;

        /**
         * Constructor for GeoPoint
         *
         * @param myGeometry for {@link GeoPoint#geometry}
         * @param myPoint    for {@link GeoPoint#point}
         */
        public GeoPoint(Geometry myGeometry, Point myPoint) {
            geometry = myGeometry;
            point = myPoint;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(geometry, geoPoint.geometry) && Objects.equals(point, geoPoint.point);
        }

        @Override
        public int hashCode() {
            return Objects.hash(geometry, point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }

    }

    /**
     * find all geo intersection points {@link Point}
     * that intersect the Shape from a specific Ray {@link Ray}
     *
     * @param ray Ray pointing towards the intersection point
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        if (bvhIsOn && ! isIntersectingBoundingBox(ray))    //We'll only calculate intersections if it intersects bounding box
            return null;
        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
    }

    /**
     * @param ray         to check intersections
     * @param maxDistance from light source to object
     * @return intersections
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        return findGeoIntersectionsHelper(ray, maxDistance);
    }

    /**
     * find all geo intersection points {@link Point}
     * that intersect the Shape from a specific Ray {@link Ray}
     * Non-Virtual Interface (NVI)
     *
     * @param ray         to check intersections
     * @param maxDistance from light source to object
     * @return intersections
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance);

    /**
     * class representing boundary box
     */
    public class BoundingBox {
        public Point minimums;  //Borders of box
        public Point maximums;  //Box borders

        public BoundingBox(Point mins, Point maxs) {
            minimums = mins;
            maximums = maxs;
        }

    }
    /**
     * Creates bounding box for objects
     */
    public abstract void createBoundingBox();

    /**
     * Testing if ray intersects
     *
     * @param ray ray to check
     * @return Whether ray intersects box
     * Code taken from scratchapixel.com
     * https://www.scratchapixel.com/lessons/3d-basic-rendering/introductionacceleration-structure/bounding-volume-hierarchy-BVH-part1
     */
    public boolean isIntersectingBoundingBox(Ray ray) {
        if (!bvhIsOn || box == null)    //Intersect as usual
            return true;
        Vector dir = ray.getDir();
        Point p0 = ray.getP0();
        double tMin = (box.minimums.getX() - p0.getX()) / dir.getX();
        double tMax = (box.maximums.getX() - p0.getX()) / dir.getX();
        if (tMin > tMax) {
            double temp = tMin;
            tMin = tMax;
            tMax = temp;
        }
        double tyMin = (box.minimums.getY() - p0.getY()) / dir.getY();
        double tyMax = (box.maximums.getY() - p0.getY()) / dir.getY();
        if (tyMin > tyMax) {
            double temp = tyMin;
            tyMin = tyMax;
            tyMax = temp;
        }
        if ((tMin > tyMax) || (tyMin > tMax))
            return false;
        if (tyMin > tMin)
            tMin = tyMin;
        if (tyMax < tMax)
            tMax = tyMax;
        double tzMin = (box.minimums.getZ() - p0.getZ()) / dir.getZ();
        double tzMax = (box.maximums.getZ() - p0.getZ()) / dir.getZ();
        if (tzMin > tzMax) {
            double temp = tzMin;
            tzMin = tzMax;
            tzMax = temp;
        }
        if ((tMin > tzMax) || (tzMin > tMax))
            return false;
        if (tzMin > tMin)
            tMin = tzMin;
        if (tzMax < tMax)
            tMax = tzMax;
        return true;
    }
}