package primitives;

import java.util.Objects;

public class Point {
    final Double3 _xyz;

    public Point(double x, double y, double z) {
        _xyz = new Double3(x, y, z);
    }

    public Point(Double3 xyz){_xyz = xyz;}

    public Vector subtract(Point p1) {
        Double3 res = new Double3(
                _xyz._d1 - p1._xyz._d1, _xyz._d2 - p1._xyz._d2, _xyz._d3 - p1._xyz._d3);
        if (Double3.ZERO.equals(res)) {
            throw new IllegalArgumentException("Subtractions results in zero - invalid");
        }
        return new Vector(res._d1, res._d2, res._d3);
    }

    public Point add(Vector vec) {
        double x = _xyz._d1 + vec._xyz._d1;
        double y = _xyz._d2 + vec._xyz._d2;
        double z = _xyz._d3 + vec._xyz._d3;

        return new Point(x,y,z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(_xyz, point._xyz);
    }

    public double distanceSquared(Point newP){
        Double3 temp = _xyz.subtract(newP._xyz);
        double xx = temp._d1* temp._d1;
        double yy = temp._d2* temp._d2;
        double zz = temp._d3* temp._d3;
        return (xx+yy+zz);
    }

    public double distance(Point newP){
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