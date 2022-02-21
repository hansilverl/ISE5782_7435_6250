package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry {
    private Vector normal;
    private Point q0;

    public Plane(Point p1, Point p2, Point p3) {
        Vector u=p2.subtract(p1);
        Vector v=p3.subtract(p1);

        Vector norm=u.crossProduct(v);

        normal=norm.normalize();
    }

    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point point) {
        return getNormal(); //Return non-parametric result.
    }
}
