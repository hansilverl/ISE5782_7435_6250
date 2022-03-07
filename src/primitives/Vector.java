package primitives;

import static primitives.Util.isZero;

public class Vector extends Point {
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (isZero(x)&&isZero(y)&&isZero(z)) {
            throw new IllegalArgumentException("Zero vector invalid!");
        }
    }

    public double lengthSquared() {
        return _xyz._d1 * _xyz._d1 + _xyz._d2 * _xyz._d2 + _xyz._d3 * _xyz._d3;
    }

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public double dotProduct(Vector vec) {
        double u1 = _xyz._d1;
        double u2 = _xyz._d2;
        double u3 = _xyz._d3;

        double v1 = vec._xyz._d1;
        double v2 = vec._xyz._d2;
        double v3 = vec._xyz._d3;

        return (u1 * v1 + u2 * v2 + u3 * v3);
    }


    public Vector crossProduct(Vector vec) {
        double u1 = _xyz._d1;
        double u2 = _xyz._d2;
        double u3 = _xyz._d3;

        double v1 = vec._xyz._d1;
        double v2 = vec._xyz._d2;
        double v3 = vec._xyz._d3;

        return new Vector(
                u2 * v3 - u3 * v2,
                u3 * v1 - u1 * v3,
                u1 * v2 - u2 * v1);
    }

    public Vector add(Vector vec)
    {
        Double3 coordinate =new Double3(
                _xyz._d1 +vec._xyz._d1,
                _xyz._d2 +vec._xyz._d2,
                _xyz._d3 +vec._xyz._d3);
        if (Double3.ZERO.equals(coordinate))
            throw new IllegalArgumentException("Zero vector invalid");
        return new Vector(coordinate._d1,coordinate._d2,coordinate._d3);
    }

    public Vector scale(double scaleFactor)
    {
        if (isZero(scaleFactor ))
            throw new IllegalArgumentException("Zero vector invalid!");

        return (new Vector(_xyz._d1 *scaleFactor, _xyz._d2 *scaleFactor, _xyz._d3 *scaleFactor));
    }

    public Vector normalize() {
        double len=length();
        return this.scale(1d/len);
    }
}