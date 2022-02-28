package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Plane (defined by a point and the orthogonal vector).
 */
public class Plane implements Geometry {
    private Vector normal;
    private Point q0;

    public Plane(Point p1, Point p2, Point p3) {
        Vector u=p2.subtract(p1);
        Vector v=p3.subtract(p1);

        Vector norm=u.crossProduct(v);

        normal=norm.normalize();
        q0=p1; //Arbitrary pick to represent
    }

    // The second constructor, receives a point and a vector.
    public Plane(Point pnt,Vector vec ) {
        normal=vec.normalize();
        q0=pnt;
    }

    //Returns normal
    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point point) {
        return getNormal(); //Return non-parametric result.
    }
}
