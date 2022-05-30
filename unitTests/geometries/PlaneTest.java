package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link Plane}
 *
 * @author Hila Buznach & Hannah Silverberg
 */
class PlaneTest {

    /**
     * Test method for {@link geometries.Plane#Plane(Point, Vector)}.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC: Only constructs vector with proper values
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1, 2, 4), new Point(1, 1, 1), new Point(1, 1, 1)), "ERROR: Non unique points should throw an exception");
    }

    /**
     * Test method for {@link geometries.Plane#Plane(Point, Point, Point)}
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Plane pl = new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point(0, 0, 1)), "Vector not normalized");

        assertEquals(1, pl.getNormal().length(), "ERROR: the length of the normalized plane isn't 1");
    }


    /**
     * Test method for {@link geometries.Plane#findGeoIntersections(Ray)}
     */
    @Test
    void testfindGeoIntersections() {
        Plane plane = new Plane(
                new Point(1, 0, 0),
                new Point(0, 1, 0),
                new Point(0, 0, 1)
        );
        List<GeoPoint> result;

        // ============ Equivalence Partitions Tests ==============

        // TC01: ray intersects the plane (1 intersection point)

        result = plane.findGeoIntersections(new Ray(new Point(0, 1, 1), new Vector(0, 0, -1)));
        assertEquals(1, result.size(), "Wrong amount of points");
        assertEquals(new GeoPoint(plane, new Point(0, 1, 0)), result.get(0), "ERROR: Ray intersects the plane");

        // TC02: Ray and plane don't have an intersection point.
        assertNull(plane.findGeoIntersections(new Ray(new Point(0, 1, 1), new Vector(0, 0, 1))), "ERROR: Ray doesn't intersects the plane");

        // =============== Boundary Values Tests ==================
        // TC03: ray isn't contained in the plane
        assertNull(plane.findGeoIntersections(new Ray(new Point(0, 0, 2), new Vector(-1, -1, 2))), "ERROR: Ray is parallel to the plane and contained in the plane");

        // A few tests in which the ray is parallel to the plane (all will have no intersection points)
        // TC04: ray is contained in the plane
        assertNull(plane.findGeoIntersections(new Ray(new Point(0, 0, 1), new Vector(-1, 1, 2))), "ERROR: Ray is contained in the plane");

        //A few tests in which the ray is orthogonal to the plane
        // TC05: Ray's starting point is before the plane
        result = plane.findGeoIntersections(new Ray(new Point(-1, -1, -1), new Vector(1, 1, 1)));
        assertEquals(1, result.size(), "Wrong amount of points");
        assertEquals(new GeoPoint(plane,new Point(1d / 3, 1d / 3, 1d / 3)), result.get(0), "ERROR: Ray's starting point is before the plane and orthogonal to the plane");

        // TC06: Ray starting point is on the plane
        assertNull(plane.findGeoIntersections(new Ray(new Point(0, 0, 1), new Vector(1, 1, 1))), "ERROR: Ray's starting point is on the plane and is orthogonal to the plane");

        // TC07: Ray starting pont is after the plane
        assertNull(plane.findGeoIntersections(new Ray(new Point(2, 2, 2), new Vector(1, 1, 1))), "ERROR: Ray's starting point is after the plane and orthogonal to the plane");

        // TC08: Ray begins in the plane's reference point
        assertNull(plane.findGeoIntersections(new Ray(plane.get_q0(), new Vector(1, 0, 0))), "ERROR: Ray starting point is at the plane's reference point");
    }
}
