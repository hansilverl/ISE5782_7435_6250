package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Polygons
 *
 * @author Dan
 */
public class PolygonTest {

    /**
     * Test method for {@link geometries.Polygon#Polygon(primitives.Point...)}.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }

        // TC02: Wrong vertices order
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(0, 1, 0), new Point(1, 0, 0), new Point(-1, 1, 1)), //
                "Constructed a polygon with wrong order of vertices");

        // TC03: Not in the same plane
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 2, 2)), //
                "Constructed a polygon with vertices that are not in the same plane");

        // TC04: Concave quadrangular
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                        new Point(0.5, 0.25, 0.5)), //
                "Constructed a concave polygon");

        // =============== Boundary Values Tests ==================

        // TC10: Vertex on a side of a quadrangular
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0.5, 0.5)),
                "Constructed a polygon with vertex on a side");

        // TC11: Last point = first point
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1)),
                "Constructed a polygon with vertices on a side");

        // TC12: Co-located points
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 1, 0)),
                "Constructed a polygon with vertices on a side");

    }

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple single test here
        Polygon pl = new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point(0, 0, 1)), "Bad normal to polygon");
    }

    /**
     * test method for {@link geometries.Polygon#findIntersection(Ray)}
     */
    @Test
    void testFindIntersection() {
        Polygon polygon = new Polygon(
                new Point(1, 0, 0),
                new Point(0, 1, 0),
                new Point(0, 0, 1),
                new Point(1, -1, 1)
        );

        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's intersection point is on the inside of the polygon
        List<Point> result = polygon.findIntersection(new Ray(new Point(-1, -1, -2), new Vector(1, 1, 2)));
        assertEquals(1, result.size(), "Wrong amount of points");
        assertEquals(new Point(0.25, 0.25, 0.5), result.get(0), "ERROR: Ray intersects the inside of the polygon");

        // TC02: Ray has one intersection point with the polygon on it's edge
        assertNull(polygon.findIntersection(new Ray(new Point(-1, -2.5, -1), new Vector(1, 1, 2))), "ERROR: Ray intersects polygon's edge");
        // TC03: Ray intersects the polygon at a vertex
        assertNull(polygon.findIntersection(new Ray(new Point(-2, -1, 0), new Vector(1, 1, 2))), "ERROR: Ray intersects Polygon's vertex");
    }
}
