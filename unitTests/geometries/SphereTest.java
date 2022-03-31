package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

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
        Sphere sp=new Sphere(new Point(1,2,3),6d);
        assertEquals(1, sp.getNormal(new Point(1,4,3)).length(), "ERROR: the length of the normalized plane isn't 1");
    }
    
//TODO
    @Test
    void testFindIntersection() {
    }
}