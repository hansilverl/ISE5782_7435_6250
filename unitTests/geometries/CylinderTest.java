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
}
