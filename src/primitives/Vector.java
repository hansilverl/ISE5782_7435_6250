package primitives;

import static primitives.Util.isZero;

/**
 * Class to represent a vector
 * @author Hila Buznach & Hannah Silverberg
 */
public class Vector extends Point {

    /**
     * Vector constructor by coordinate values.
     *
     * @param x value for x in axis
     * @param y value for y in axis
     * @param z value for z in axis
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (isZero(x) && isZero(y) && isZero(z)) {
            throw new IllegalArgumentException("Zero vector invalid!");
        }
    }

    /**
     * Vector constructor by coordinate
     *
     * @param xyz value for axis
     */
    public Vector(Double3 xyz) {
        super(xyz);
    }

    /**
     * squares length
     * @return squared length of vector : x^2 + y^2 +z^2
     */
    public double lengthSquared() {
        Double3 xyz = _xyz.product(_xyz);
        return xyz.d1 + xyz.d2 + xyz.d3;
    }

    /**
     * Calculating length
     * @return sqrt(x^2+y^2+z^2)
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * Calculates the sum of the products of corresponding components
     * @param vec vector in plane
     * @return res = x1*x2 + y1*y2 + z1*z2
     */
    public double dotProduct(Vector vec) {

        Double3 xyz = _xyz.product(vec._xyz);

        return (xyz.d1 + xyz.d2 + xyz.d3);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    /**
     * The cross product of two 3D vectors is another vector in the same 3D vector space.
     * @param vec vector in plane
     * @return The result vector of the cross product
     */
    public Vector crossProduct(Vector vec) {
        Double3 u = _xyz;
        Double3 v = vec._xyz;
            return new Vector(
                    u.d2 * v.d3 - u.d3 * v.d2,
                    u.d3 * v.d1 - u.d1 * v.d3,
                    u.d1 * v.d2 - u.d2 * v.d1);
    }

    /**
     * Addition of two vectors
     * @param vec vector in plane
     * @return addition result
     */
    public Vector add(Vector vec) {
        Double3 coordinate = _xyz.add(vec._xyz);
        if (Double3.ZERO.equals(coordinate))
            throw new IllegalArgumentException("Zero vector invalid");
        return new Vector(coordinate);
    }

    /**
     * @param vector Vector in plane
     * @return u-v
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
     * @param scaleFactor the number we will multiply by
     * @return vector*scaleFactor
     */
    public Vector scale(double scaleFactor) {

        if (isZero(scaleFactor))
            throw new IllegalArgumentException("Zero vector invalid!");

        return new Vector(_xyz.scale(scaleFactor));
    }

    /**
     * converted vector to a unit vectors
     * @return normalized vector
     */
    public Vector normalize() {
        double len = length();
        return this.scale(1d / len);

    }
}