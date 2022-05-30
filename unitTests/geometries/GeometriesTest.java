package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import geometries.Intersectable.GeoPoint;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link Geometries}
 *
 * @author Hila Buznach & Hannah Silverberg
 */
class GeometriesTest {

    /**
     * test method for {@link geometries.Geometries#findGeoIntersections(Ray)}
     */
    @Test
    void testfindGeoIntersections() {
        List<GeoPoint> result;
        Geometries geos = new Geometries(
                new Plane(new Point(2, 0, 0), new Vector(-1, 1, 0)),
                new Sphere(new Point(5, 0, 0), 2d),
                new Triangle(new Point(8.5, -1, 0), new Point(7.5, 1.5, 1), new Point(7.5, 1.5, -1))
        );

        // ============ Equivalence Partitions Tests ==============
        // TC01: Typical test
        result = geos.findGeoIntersections(new Ray(new Point(1, 0, 0), new Vector(7, 3, 0)));
        assertNotNull(result, "It is empty!");
        assertEquals(3, result.size(), "Bad intersects");

        // =============== Boundary Values Tests ==================
        // TC11: Empty collection
        result = new Geometries().findGeoIntersections(new Ray(new Point(1, 0, 0), new Vector(1, 0, 0)));
        assertNull(result, "It is not empty!");

        // TC12: No intersection
        result = geos.findGeoIntersections(new Ray(new Point(1, 0, 0), new Vector(1, 3, 0)));
        assertNull(result, "Bad intersects");

        // TC13: one intersection point
        result = geos.findGeoIntersections(new Ray(new Point(1, 0, 0), new Vector(4, 3, 0)));
        assertNotNull(result, "It is empty!");
        assertEquals(1, result.size(), "Bad intersects");

        // TC14: All geometrics intersect
        result = geos.findGeoIntersections(new Ray(new Point(1, 0, 0), new Vector(7, 1, 0)));
        assertNotNull(result, "It is empty!");
        assertEquals(4, result.size(), "Bad intersects");

    }
}
