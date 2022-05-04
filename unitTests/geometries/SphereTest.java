package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link Sphere}
 *
 * @author Hila Buznach & Hannah Silverberg
 */
class SphereTest {

    /**
     * Test method for {@link geometries.Sphere#Sphere(Point, double)}.
     */
    @Test
    void testGetNormal() {
        Sphere sp = new Sphere(new Point(1, 2, 3), 6d);
        assertEquals(1, sp.getNormal(new Point(1, 4, 3)).length(), "ERROR: the length of the normalized plane isn't 1");
    }

    /**
     * test method for {@link geometries.Sphere#findIntersection(Ray)}
     */
    @Test
    void testFindIntersection() {
        Sphere sphere = new Sphere(new Point(1, 0, 0), 1d);
        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersection(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))),
                "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        List<Point> result = sphere.findIntersection(new Ray(new Point(-1, 0, 0),
                new Vector(3, 1, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");


        // TC03: Ray starts inside the sphere (1 point)
        p1 = new Point(1.911437827766148, 0.411437827766148, 0);

        result = sphere.findIntersection(new Ray(new Point(1.5, 0, 0),
                new Vector(1, 1, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(p1), result, "wrong intersection points");

        // TC04: Ray starts after the sphere (0 points)
        result = sphere.findIntersection(new Ray(new Point(3, 0, 0),
                new Vector(1, 1, 0)));
        assertNull(result, "Wrong number of points");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        p1 = new Point(2, 0, 0);
        result = sphere.findIntersection(new Ray(new Point(1, -1, 0),
                new Vector(1, 1, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(p1), result, "wrong intersections points");

        // TC12: Ray starts at sphere and goes outside (0 points)
        result = sphere.findIntersection(new Ray(new Point(1, 1, 0),
                new Vector(1, 1, 0)));
        assertNull(result, "Wrong number of points");

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        p1 = new Point(1, 1, 0);
        p2 = new Point(1, -1, 0);
        result = sphere.findIntersection(new Ray(new Point(1, -2, 0),
                new Vector(0, -1, 0)));
        assertEquals(2, result.size(), "Wrong number of points");

        assertEquals(List.of(p1, p2), result, "wrong intersections points");

        // TC14: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntersection(new Ray(new Point(1, -1, 0),
                new Vector(0, 2, 1)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(p1), result, "wrong intersection points");

        // TC15: Ray starts inside (1 points)
        result = sphere.findIntersection(new Ray(new Point(1, -0.5, 0),
                new Vector(0, 1.5, 1)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(p1), result, "wrong intersection points");

        // TC16: Ray starts at the center (1 points)
        result = sphere.findIntersection(new Ray(new Point(1, 0, 0),
                new Vector(0, 1, 1)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(p1), result, "wrong intersection points");

        // TC17: Ray starts at sphere and goes outside (0 points)
        result = sphere.findIntersection(new Ray(new Point(1, 1, 1),
                new Vector(0, 0.5, -1)));
        assertNull(result, "Wrong number of points");

        // TC18: Ray starts after sphere (0 points)
        result = sphere.findIntersection(new Ray(new Point(1, 1.5, 0),
                new Vector(0,1,0)));
        assertNull(result, "Wrong number of points");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        result = sphere.findIntersection(new Ray(new Point(1, 1, -1),
                new Vector(0, 0, 1)));
        assertNull(result, "Wrong number of points");

        // TC20: Ray starts at the tangent point
        result = sphere.findIntersection(new Ray(new Point(1, 1, 0),
                new Vector(0, 0, 1)));
        assertNull(result, "Wrong number of points");

        // TC21: Ray starts after the tangent point
        result = sphere.findIntersection(new Ray(new Point(1, 1, 1),
                new Vector(0, 0, 1)));
        assertNull(result, "Wrong number of points");

        // **** Group: Special cases
        // TC22: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        result = sphere.findIntersection(new Ray(new Point(1, 2, -1),
                new Vector(0, 0, 1)));
        assertNull(result, "Wrong number of points");
    }
}
