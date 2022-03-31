package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

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
        assertThrows(IllegalArgumentException.class, () -> {
            new Plane(new Point(1, 2, 4), new Point(1, 1, 1), new Point(1, 1, 1));
        }, "ERROR: Non unique points should throw an exception");
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

}