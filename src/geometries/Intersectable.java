package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;
import java.util.Objects;

/**
 * common interface for all graphic 3D objects
 * that interface with a specific Ray {@link primitives.Ray}
 */
public abstract class Intersectable {

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
     * Helper Class - class GeoPoint
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point point;

        /**
         * Constructor for GeoPoint
         * @param myGeometry for {@link GeoPoint#geometry}
         * @param myPoint for {@link GeoPoint#point}
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
  public List<GeoPoint> findGeoIntersections(Ray ray)
  {
      return findGeoIntersectionsHelper(ray);
  }

    /**
     * find all geo intersection points {@link Point}
     * that intersect the Shape from a specific Ray {@link Ray}
     *Non-Virtual Interface (NVI)
     * @param ray Ray pointing towards the intersection point
     */
  protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);
}
