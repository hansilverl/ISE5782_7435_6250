package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Class to represent a tube🧪
 */
public class Tube implements Geometry {
    private final Ray _axisRay;
    private final double _radius;

    //Constructing a tube
    public Tube(Ray axisRay, double radius) {
        _axisRay = axisRay;
        _radius = radius;
    }

    public Ray getAxisRay() {
        return _axisRay;
    }

    public double getRadius() {
        return _radius;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}