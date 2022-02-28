package primitives;

import java.util.Objects;

/**
 * (a Euclidean point) a fundamental object in geometry -  a point with 3 coordinates.
 * It will contain an object that holds three numbers aka it will have an attribute of type Double3 (it will use composition)
 */
public class Point {
    final Double3 xyz;  //an object that holds three numbers (represents a point)

    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    //Subtracting one point from another - returning a vector.
    public Vector subtract(Point p1) {
        Double3 res = new Double3(
                xyz.d1 - p1.xyz.d1, xyz.d2 - p1.xyz.d2, xyz.d3 - p1.xyz.d3);
        if (Double3.ZERO.equals(res)) {
            throw new IllegalArgumentException("Subtractions results in zero - invalid");
        }
        return new Vector(res.d1, res.d2, res.d3);
    }

    // Adding one point to another
    public Point add(Vector vec) {
        double x = xyz.d1 + vec.xyz.d1;
        double y = xyz.d2 + vec.xyz.d2;
        double z = xyz.d3 + vec.xyz.d3;

        return new Point(x, y, z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(xyz, point.xyz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xyz);
    }

    @Override
    public String toString() {
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }
}