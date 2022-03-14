package primitives;

import java.util.Objects;

/**
 * Class to represent a Point
 */
public class Point {
    Double3 _xyz;   //Representing a point

    /**
     * Point constructor by coordinate values.
     *
     * @param x value for x in axis
     * @param y value for y in axis
     * @param z value for z in axis
     */
    public Point(double x, double y, double z) {
        _xyz = new Double3(x, y, z);
    }

    /**
     * Point constructor by coordinate
     *
     * @param xyz value for axis
     */
    public Point(Double3 xyz) {
        _xyz = xyz;
    }

    public Vector subtract(Point p1) {
        Double3 res = _xyz.subtract(p1._xyz);
        if (Double3.ZERO.equals(res)) {
            throw new IllegalArgumentException("Subtractions results in zero - invalid");
        }
        return new Vector(res);
    }

    /**
     * Adding a vector to point
     *
     * @param vec vector in plane
     * @return
     */
    public Point add(Vector vec) {
        Double3 xyz = _xyz.add(vec._xyz);
        return new Point(xyz);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(_xyz, point._xyz);
    }

    /**
     * The squared distance of a given point
     *
     * @param point in axis
     * @return
     */
    public double distanceSquared(Point point) {
        Double3 temp = _xyz.subtract(point._xyz);
        double xx = temp._d1 * temp._d1;
        double yy = temp._d2 * temp._d2;
        double zz = temp._d3 * temp._d3;
        return (xx + yy + zz);

    }

    /**
     * Calculating the distance (length) of a point
     *
     * @param newP new point in axis
     * @return
     */
    public double distance(Point newP) {
        return Math.sqrt(distanceSquared(newP));
    }

    @Override
    public int hashCode() {
        return Objects.hash(_xyz);
    }

    @Override
    public String toString() {
        return "Point{" +
                "xyz=" + _xyz +
                '}';
    }


}