package primitives;

import static primitives.Util.isZero;

/**
 * Class to represent a vector
 */
public class Vector extends Point {

    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (isZero(x) && isZero(y) && isZero(z)) {
            throw new IllegalArgumentException("Zero vector invalid!");
        }
    }

    public Vector(Double3 xyz) {
        super(xyz);
    }

    public double lengthSquared() {
        Double3 xyz = _xyz.product(_xyz);
        return xyz._d1 + xyz._d2 + xyz._d3;
    }

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public double dotProduct(Vector vec) {
        Double3 xyz = _xyz.product(vec._xyz);

        return (xyz._d1 + xyz._d2 + xyz._d3);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    public Vector crossProduct(Vector vec) {
        Double3 u = _xyz;
        Double3 v = vec._xyz;

        return new Vector(
                u._d2 * v._d3 - u._d3 * v._d2,
                u._d3 * v._d1 - u._d1 * v._d3,
                u._d1 * v._d2 - u._d2 * v._d1);
    }

    public Vector add(Vector vec) {
        Double3 coordinate = _xyz.add(vec._xyz);
        if (Double3.ZERO.equals(coordinate))
            throw new IllegalArgumentException("Zero vector invalid");
        return new Vector(coordinate);
    }

    /**
     * @param vector Vector
     * @return new Vector(u-v)
     */
    public Vector subtract(Vector vector) {
        Double3 coordinate = _xyz.subtract(vector._xyz);
        if (Double3.ZERO.equals(coordinate))
            throw new IllegalArgumentException("Zero vector invalid");
        return new Vector(coordinate);
    }

    @Override
    public String toString() {
        return "Vector{" +
                "_xyz=" + _xyz +
                '}';
    }

    /**
     * Multiplying a vector by scalar
     *
     * @param scaleFactor
     * @return
     */
    public Vector scale(double scaleFactor) {

        if (isZero(scaleFactor))
            throw new IllegalArgumentException("Zero vector invalid!");

        return new Vector(_xyz.scale(scaleFactor));
    }

    public Vector normalize() {
        double len = length();
        return this.scale(1d / len);
    }
}