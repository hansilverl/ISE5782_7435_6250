package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import geometries.Polygon;

import java.util.List;

/**
 * Class to represent a triangle
 */
public class Triangle extends Polygon implements Geometry {
    /**
     * @param p1 point of a triangle
     * @param p2 point of a triangle
     * @param p3 point of a triangle
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    /**
     * Getter for normal vector
     * Returns the normal of a point
     * implementing {@link Geometry#getNormal(Point)}
     *
     * @param point
     * @return
     */
    @Override
    public Vector getNormal(Point point) {
        return super.getNormal(point);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane +
                '}';
    }

    /**
     * finding intersection between rays
     * implementing {@link geometries.Intersectable#findIntersection(Ray)} Ray)}
     *
     * @param ray of triangle
     * @return
     */
    @Override
    public List<Point> findIntersection(Ray ray) {
        return null;
    }
}
