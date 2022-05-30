package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Triangle tr = new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), tr.getNormal(new Point(0, 0, 1)), "Triangle's normal isn't calculated correctly");
        assertEquals(1, tr.getNormal(new Point(1, 2, 3)).length(), "ERROR: the length of the normalized plane isn't 1");
    }

    /**
     * test method for {@link geometries.Triangle#findGeoIntersections(Ray)}
     */
    @Test
    void testFindGeoIntersection() {
        Triangle triangle = new Triangle(
                new Point(1, 0, 0),
                new Point(0, 1, 0),
                new Point(0, 0, 1)
        );

        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray intersects inside the triangle (1 points)
        List<GeoPoint> result = triangle.findGeoIntersections(new Ray(new Point(-1, -1, -2), new Vector(1, 1, 2)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(new GeoPoint(triangle,new Point(0.25, 0.25, 0.5)), result.get(0), "Ray intersects inside the triangle");

        // TC02: Ray intersects outside the triangle against an edge (0 points)
        assertNull(triangle.findGeoIntersections(new Ray(new Point(-1, -2, -2), new Vector(1, 1, 2))), "Ray intersects outside the triangle against an edge");
        // TC03: Ray intersects outside the triangle against a vertex (0 points)
        assertNull(triangle.findGeoIntersections(new Ray(new Point(-2, -2, -2), new Vector(1, 1, 2))), "Ray intersects outside the triangle against a vertex");
    }

}