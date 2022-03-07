package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link Point}
 * }
 */
class PointTest {

    @Test
    void testSubtract() {
    }

    @Test
    void testAdd() {
    }

    @Test
    void testDistanceSquared() {
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
                "LOSER");
    }
}