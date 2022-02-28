package primitives;

import static primitives.Util.isZero;

/**
 * In geometry, this is a fundamental object.
 * There is a size and a direction to it, defining our vector by the endpoint only,
 * The Vector class will derive from the Point class.
 */
public class Vector extends Point {
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (isZero(x) && isZero(y) && isZero(z)) {
            throw new IllegalArgumentException("Zero vector invalid!");
        }
    }

    //Calculating the vector length squared
    public double lengthSquared() {
        return xyz.d1 * xyz.d1 + xyz.d2 * xyz.d2 + xyz.d3 * xyz.d3;
    }

    //Squaring the square of the vector length
    public double length() {
        return Math.sqrt(lengthSquared());
    }


    // Takes two equal-length sequences of numbers , and returns a single number.
    public double dotProduct(Vector vec) {
        double u1 = xyz.d1;
        double u2 = xyz.d2;
        double u3 = xyz.d3;

        double v1 = vec.xyz.d1;
        double v2 = vec.xyz.d2;
        double v3 = vec.xyz.d3;

        return (u1 * v1 + u2 * v2 + u3 * v3);
    }

    //Multiplying two vectors and their product is a new vector
    public Vector crossProduct(Vector vec) {
        double u1 = xyz.d1;
        double u2 = xyz.d2;
        double u3 = xyz.d3;

        double v1 = vec.xyz.d1;
        double v2 = vec.xyz.d2;
        double v3 = vec.xyz.d3;

        return new Vector(
                u2 * v3 - u3 * v2,
                u3 * v1 - u1 * v3,
                u1 * v2 - u2 * v1);
    }

    //Addition and subtraction of vectors
    public Vector add(Vector vec) {
        Double3 coordinate = new Double3(
                xyz.d1 + vec.xyz.d1,
                xyz.d2 + vec.xyz.d2,
                xyz.d3 + vec.xyz.d3);
        if (Double3.ZERO.equals(coordinate))    //
            throw new IllegalArgumentException("Zero vector invalid");
        return new Vector(coordinate.d1, coordinate.d2, coordinate.d3);
    }

    //Scalar multiplication
    public Vector scale(double scaleFactor) {
        if (isZero(scaleFactor))
            throw new IllegalArgumentException("Zero vector invalid!");

        return (new Vector(xyz.d1 * scaleFactor, xyz.d2 * scaleFactor, xyz.d3 * scaleFactor));
    }

    //Normalizing a vector
    public Vector normalize() {
        double len = length();
        return this.scale(1d / len);
    }
}