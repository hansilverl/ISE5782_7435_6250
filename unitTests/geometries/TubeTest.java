package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;


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
}