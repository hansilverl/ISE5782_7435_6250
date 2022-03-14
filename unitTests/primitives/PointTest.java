package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link Point}
 *
 * @Authors Hila Buznach & Hannah Silverberg
 */
class PointTest {

    Point p1 = new Point(1, 2, 3);

    /**
     * test method for {@link primitives.Vector#subtract(Point)} (primitives.Point...)}.
     */
    @Test
    void testSubtract() {
        //Test that the subtraction method works properly
        assertEquals(new Vector(1, 1, 1), p1.subtract(new Point(0, 1, 2))
                , "ERROR: Point - Point subtraction does not work correctly");

        //Test that if subtraction results in 0, an exception will be thrown
        assertThrows(IllegalArgumentException.class, () -> {
            p1.subtract(p1);
        }, "ERROR: zero point should have thrown an exception");
    }

    /**
     * test method for {@link primitives.Vector#add(Vector)} (Point)} (primitives.Point...)}.
     */
    @Test
    void testAdd() {
        //Test that the addition method works properly
        assertEquals(new Point(1, 1, 1), p1.add(new Vector(0, -1, -2))
                , "ERROR: Point - Point addition does not work correctly");

    }


    /**
     * Testing the {@link Point#distance(Point)}
     */
    @Test
    void testDistance() {
        Point point3 = new Point(0.5, 0, -100);
        double distance = point3.distance(new Point(0, 0, -100));
        assertEquals(
                0.5,
                distance,
                0.0001,
                "Distance method doesn't work correctly");
    }
}