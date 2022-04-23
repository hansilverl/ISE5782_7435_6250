package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link Tube}
 *
 * @author Hila Buznach & Hannah Silverberg
 */
class TubeTest {

    /**
     * Test method for {@link geometries.Tube#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {

        Tube tube = new Tube(new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), 1d);
        Vector normal = new Vector(0, 1, 0);
        double dotProduct = normal.dotProduct(tube.getAxisRay().getDir());

        assertEquals(0d, dotProduct, "normal is not orthogonal to the tube");

        assertEquals(tube.getNormal(new Point(0, 1, 1)), normal, "ERROR: Normal calculation doesn't work properly!");

    }

    /**
     * Test method for {@link geometries.Tube#findIntersection(Ray)}.
     */
    @Test
    void testFindIntersection() {
        Tube tube = new Tube(new Ray(new Point(0, 1, 1), new Vector(0, 1, 3)), 2.5);
        // ============ Equivalence Partitions Tests ==============
        //TC01: Ray from outside intersect 2 si des(2 point)
        List<Point> result = tube.findIntersection(new Ray(new Point(0, -4, 2), new Vector(0, 8, 0)));
        assertEquals(result.size(), 2, "wrong number of points");
        assertEquals(result, List.of(new Point(0, -1.5, 2), new Point(0, 3.5, 2))
                , "wrong intersect points");

        //TC02: Ray from outside under the base intersect 2 sides(2 points)
        result = tube.findIntersection(new Ray(new Point(0, -4, 0), new Vector(0, 8, 2)));
        assertEquals(result.size(), 2, "wrong number of points");
        assertEquals(result, List.of(new Point(0, 0, 1), new Point(0, 3.5, 1.75)),
                "wrong intersect points");
        //TC03: Ray from outside under the base intersect just the base(1 point)
        assertEquals(tube.findIntersection(new Ray(new Point(0, -1, 0), new Vector(0, 1, 3))),
                List.of(new Point(0, -2 / 3d, 1)), "wrong intersect point");
        //TC04: No intersect points(0 point)
        assertNull(tube.findIntersection(new Ray(new Point(0, 4, 1), new Vector(0, 1, 3)))
                , "wrong intersect point");

        // =============== Boundary Values Tests ==================
        //**** Group: ray from inside
        //TC10: Ray from inside intersect the base(1 point)
        assertEquals(tube.findIntersection(new Ray(new Point(0, 1, 2), new Vector(0, -1, -1))),
                List.of(new Point(0, 0, 1)), "wrong intersect point from inside to base");

        //TC11: Ray from inside intersect the side(1 point)
        assertEquals(tube.findIntersection(new Ray(new Point(0, 1, 2), new Vector(-2.5, 0, 0))),
                List.of(new Point(-2.5, 1, 2)), "wrong intersect point from inside to side");

        //**** Group: ray on tangent
        //TC12: Ray on tangent(0 point)
        assertNull(tube.findIntersection(new Ray(new Point(2.5, 1, 1), new Vector(0, 1, 3))),
                "wrong intersect point");
        //TC13: Ray on ths base(0 point)
        assertNull(tube.findIntersection(new Ray(new Point(2.5, 1, 1), new Vector(2, 0, 0))),
                "wrong intersect point");
    }
}