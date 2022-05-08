package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link Cylinder}
 *
 * @author Hila Buznach & Hannah Silverberg
 */

class CylinderTest {

    @Test
    void testGetNormal() {
        //Test when point is on the top of the cylinder)
        Cylinder pl = new Cylinder(1d, new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), 1d);
        assertEquals(new Vector(0, 0, 1d), pl.getNormal(new Point(0, 0.5, 1)), "Error: Problem with cylinder normal calculation");
        // Test where there is a simple single test here (when point is on the base of the cylinder)
        assertEquals(new Vector(0, 0, 1d), pl.getNormal(new Point(0, 0.5, 0)), "Error: Problem with cylinder normal calculation");
        // Test where point is on the surface of the cylinder)
        assertEquals(new Vector(0.0,0.5,0.0), pl.getNormal(new Point(0, 0.5, 0.5)), " Error: Problem with cylinder normal calculation");

        assertNotEquals(pl.getNormal(new Point(0, 1, 0.00001)), pl.getNormal(new Point(0, 0.99999999, 0)), "Error: in boundary test with base !");
        // Testing between surface and top of cylinder)
        assertNotEquals(pl.getNormal(new Point(0, 1, 0.99999)), pl.getNormal(new Point(0, 0.99999999, 1)), "Error in boundary test with top !");

    }

    /**
     * Test method for {@link geometries.Cylinder#findIntersection(Ray)}
     */
//    @Test
//    void testFindIntersection() {
//        Cylinder cylinder = new Cylinder(1d, new Ray(new Point(2, 0, 0), new Vector(0, 0, 1)), 2d);
//
//        // ============ Equivalence Partitions Tests ==============
//
//        //TC01: ray is non-contained and parallel to the cylinder's ray
//
//        List<Point> result = cylinder.findIntersection(new Ray(new Point(5, 0, 0), new Vector(0, 0, 1)));
//        assertNull(result, "Wrong amount of points");
//
//
//        //TC02: ray's starting points is contained and parallel to the cylinder's ray
//
//        result = cylinder.findIntersection(new Ray(new Point(2.5, 0, 1), new Vector(0, 0, 1)));
//        assertEquals(1, result.size(), "Wrong amount of points");
//        assertEquals(List.of(new Point(2.5, 0, 2)), result, "incorrect intersection point");
//
//        //TC03: ray's starting point is out of the cylinder, and parallel to the cylinder's ray and crosses the cylinder
//
//        result = cylinder.findIntersection(new Ray(new Point(2.5, 0, -1), new Vector(0, 0, 1)));
//        assertEquals(2, result.size(), "Wrong amount of points");
//        assertEquals(List.of(new Point(2.5, 0, 0), new Point(2.5, 0, 2)), result, "incorrect intersection point");
//
//        //TC04: ray starting point is out of the cylinder, and crosses the cylinder
//
//        result = cylinder.findIntersection(new Ray(new Point(-2, 0, 0.5), new Vector(1, 0, 0)));
//        assertEquals(2, result.size(), "Wrong amount of points");
//        assertEquals(List.of(new Point(1, 0, 0.5), new Point(3, 0, 0.5)), result, "Incorrect intersection points");
//
//        //TC05: ray has an inner starting point, and crosses the cylinder
//
//        result = cylinder.findIntersection(new Ray(new Point(1.5, 0, 0.5), new Vector(1, 0, 0)));
//        assertEquals(1, result.size(), "Wrong amount of points");
//        assertEquals(List.of(new Point(3, 0, 0.5)), result, "Incorrect intersection points");
//
//        //TC06: ray has an outer starting point,and doesn't cross the cylinder
//
//        result = cylinder.findIntersection(new Ray(new Point(5, 0, 0), new Vector(1, 0, 0)));
//        assertNull(result, "Wrong Amount of points");
//
//        //TC07: ray has an outer starting point, and crosses base and surface
//
//        result = cylinder.findIntersection(new Ray(new Point(1, 0, -1), new Vector(1, 0, 1)));
//        assertEquals(2, result.size(), "Wrong amount of points");
//        assertEquals(List.of(new Point(2, 0, 0), new Point(3, 0, 1)), result, "Incorrect intersection points");
//
//        //TC08: ray has an outer starting point and crosses surface and base
//
//        result = cylinder.findIntersection(new Ray(new Point(4, 0, 2), new Vector(-1, 0, -1)));
//        assertEquals(2, result.size(), "Wrong amount of points");
//        assertEquals(List.of(new Point(2, 0, 0), new Point(3, 0, 1)), result, "Incorrect intersection points");
//
//
//        // =============== Boundary Values Tests ==================
//
//        //TC09: ray's starting point is on the surface of the cylinder
//
//        result = cylinder.findIntersection(new Ray(new Point(3, 0, 0), new Vector(0, 0, 1)));
//        assertNull(result, "Wrong amount of points");
//
//        //TC10: ray is on the base of the cylinder and intersects twice
//
//        result = cylinder.findIntersection(new Ray(new Point(-1, 0, 0), new Vector(1, 0, 0)));
//        assertNull(result, "Wrong amount of points");
//
//        //TC11: ray is on the center of the cylinder
//
//        result = cylinder.findIntersection(new Ray(new Point(2, 0, 0), new Vector(0, 0, 1)));
//        assertEquals(1, result.size(), "Wrong amount of points");
//        assertEquals(List.of(new Point(2, 0, 2)), result, "Incorrect intersection points");
//
//        //TC12: ray is perpendicular to cylinder's ray and starts from the outside of the tube
//
//        result = cylinder.findIntersection(new Ray(new Point(-2, 0, 0.5), new Vector(1, 0, 0)));
//        assertEquals(2, result.size(), "Wrong amount of points");
//        assertEquals(List.of(new Point(1, 0, 0.5), new Point(3, 0, 0.5)), result, "Incorrect intersection points");
//
//        //TC13: ray is perpendicular to cylinder's ray and starts from inside cylinder (not center)
//
//        result = cylinder.findIntersection(new Ray(new Point(1.5, 0, 0.5), new Vector(1, 0, 0)));
//        assertEquals(1, result.size(), "Wrong amount of points");
//        assertEquals(List.of(new Point(3, 0, 0.5)), result, "Incorrect intersection points");
//
//        //TC14 ray is perpendicular to cylinder's ray and starts from the center of cylinder
//
//        result = cylinder.findIntersection(new Ray(new Point(2, 0, 0.5), new Vector(1, 0, 0)));
//        assertEquals(1, result.size(), "Wrong number of points");
//        assertEquals(List.of(new Point(3, 0, 0.5)), result, "Bad intersection points");
//
//        //TC15: ray is perpendicular to cylinder's ray and starts from the surface of the cylinder to it's inside
//
//        result = cylinder.findIntersection(new Ray(new Point(1, 0, 0.5), new Vector(1, 0, 0)));
//        assertEquals(1, result.size(), "Wrong amount of points");
//        assertEquals(List.of(new Point(3, 0, 0.5)), result, "Incorrect intersection points");
//
//        //TC16: ray is perpendicular to cylinder's ray and starts from the surface of cylinder to outside
//
//        result = cylinder.findIntersection(new Ray(new Point(3, 0, 0), new Vector(1, 0, 0)));
//        assertNull(result, "Wrong amount of points");
//
//        //TC17: ray starts from the surface and is directed out of it.
//
//        result = cylinder.findIntersection(new Ray(new Point(3, 0, 0), new Vector(1, 1, 1)));
//        assertNull(result, "Wrong amount of points");
//
//        //TC18: ray starts from the surface and is directed inwardly
//
//        result = cylinder.findIntersection(new Ray(new Point(3, 0, 0.5), new Vector(-1, 0, 0)));
//        assertEquals(1, result.size(), "Wrong amount of points");
//        assertEquals(List.of(new Point(1, 0, 0.5)), result, "Incorrect intersection point");
//
//        //TC19: ray starts at the center
//
//        result = cylinder.findIntersection(new Ray(new Point(2, 0, 0), new Vector(1, 0, 1)));
//        assertEquals(1, result.size(), "Wrong amount of points");
//        assertEquals(List.of(new Point(3, 0, 1)), result, "Incorrect intersection point");
//
//        //TC20: The extension of ray crosses cylinder
//
//        result = cylinder.findIntersection(new Ray(new Point(3, 0, 0), new Vector(1, 0, 0)));
//        assertNull(result, "Wrong amount of points");
//
//        //TC21: ray is on the surface and starts before cylinder
//
//        result = cylinder.findIntersection(new Ray(new Point(3, 0, -1), new Vector(0, 0, 1)));
//        assertNull(result, "Wrong amount of points");
//
//        //TC22: ray is on the surface starts at the base's bottom
//
//        result = cylinder.findIntersection(new Ray(new Point(3, 0, 0), new Vector(0, 0, 1)));
//        assertNull(result, "Wrong amount of points");
//
//        //TC23: ray is on the surface starts on the surface
//
//        result = cylinder.findIntersection(new Ray(new Point(3, 0, 1), new Vector(0, 0, 1)));
//        assertNull(result, "Wrong amount of points");
//
//        //TC24: ray is on the surface and starts at base's top
//
//        result = cylinder.findIntersection(new Ray(new Point(3, 0, 2), new Vector(0, 0, 1)));
//        assertNull(result, "Wrong amount of points");
//    }
}
