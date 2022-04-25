package primitives;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link Ray}
 *
 * @author Hila Buznach & Hannah Silverberg
 */
class RayTest {

    @Test
    void testFindClosestPoint() {
        //Equivalence test
        Ray ray = new Ray(new Point(0, 1, 0), new Vector(1, 1, 1));
        List<Point> list = new LinkedList<>();
        list.add(new Point(12, 34, 56));
        list.add(new Point(5, 5, 5));
        list.add(new Point(-200, -5, 2));

        assertEquals(new Point(5, 5, 5), ray.findClosestPoint(list), "ERROR: findClosestPoint does not work correctly");

        //BVA test

        //Empty list test
        assertNull(ray.findClosestPoint(new LinkedList<>()), "ERROR: findClosestPoint does not work correctly for empty list");

        list.remove(new Point(12, 34, 56));

        //First point is the closest
        assertEquals(new Point(5, 5, 5), ray.findClosestPoint(list), "ERROR: findClosestPoint does not work correctly when closest is at the beginning ");

        list.add(new Point(2, 2, 2));

        //Last point is the closest
        assertEquals(new Point(2, 2, 2), ray.findClosestPoint(list), "ERROR: findClosestPoint does not work correctly when closest is at the end ");
    }
}