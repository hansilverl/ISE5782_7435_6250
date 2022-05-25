package primitives;

import java.util.List;
import java.util.Objects;

import geometries.Intersectable.GeoPoint;

import static primitives.Util.isZero;

/**
 * Class to represent a ray
 *
 * @author Hila Buznach & Hannah Silverberg
 */
public class Ray {

    final Point _p0;   //point value of ray
    final Vector _dir;  //Vector value of ray

    /**
     * Constructing a ray
     *
     * @param p0  start point of ray
     * @param dir vector to represent direction of ray
     */
    public Ray(Point p0, Vector dir) {
        _p0 = p0;
        _dir = dir.normalize();
    }

    /**
     * @return _p0 starting point of ray
     */
    public Point getP0() {
        return _p0;
    }

    /**
     * @return _dir - direction of ray
     */
    public Vector getDir() {
        return _dir;
    }

    /**
     * Find the closest point when given a list of points
     *
     * @param pointList - list of points
     * @return closest point
     */
    public Point findClosestPoint(List<Point> pointList) {
        if (pointList == null || pointList.isEmpty())
            return null;

        Point myPoint = pointList.get(0);

        for (Point point : pointList) {
            if (_p0.distance(myPoint) > _p0.distance(point)) {
                myPoint = point;
            }
        }

        return myPoint;
    }

    /**
     * Find the closest geo point when given a list of points
     *
     * @param geoList - list of geo points
     * @return closest geo point
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoList) {
        if (geoList == null || geoList.isEmpty())
            return null;

        GeoPoint myGeo = geoList.get(0);

        for (GeoPoint geo : geoList) {
            if (_p0.distance(myGeo.point) > _p0.distance(geo.point)) {
                myGeo = geo;
            }
        }

        return myGeo;
    }


    /**
     * @param delta multiplier
     * @return scaled vector
     */
    public Point getPoint(double delta) {
        if (isZero(delta)) {
            return _p0;
        }
        return _p0.add(_dir.scale(delta));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return _p0.equals(ray._p0) && _dir.equals(ray._dir);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_p0, _dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "_p0=" + _p0 +
                ", _dir=" + _dir +
                '}';
    }
}
