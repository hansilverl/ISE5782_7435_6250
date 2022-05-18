package renderer;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class CameraTest {
    static final Point ZERO_POINT = new Point(0, 0, 0);

    /**
     * Test method for
     * {@link Camera#constructRay(int, int, int, int)}.
     */
    @Test
    void testConstructRay() {
        Camera camera = new Camera(Point.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)).setVPDistance(10);

        // ============ Equivalence Partitions Tests ==============
        // TC01: 3X3 Corner (0,0)
        assertEquals(new Ray(Point.ZERO, new Vector(-2, -2, 10)),
                camera.setVPSize(6, 6).constructRay(3, 3, 0, 0));

        // TC02: 4X4 Corner (0,0)
        assertEquals(new Ray(Point.ZERO, new Vector(-3, -3, 10)),
                camera.setVPSize(8, 8).constructRay(4, 4, 0, 0));

        // TC03: 4X4 Side (0,1)
        assertEquals(new Ray(Point.ZERO, new Vector(-1, -3, 10)),
                camera.setVPSize(8, 8).constructRay(4, 4, 1, 0));

        // TC04: 4X4 Inside (1,1)
        assertEquals(new Ray(Point.ZERO, new Vector(-1, -1, 10)),
                camera.setVPSize(8, 8).constructRay(4, 4, 1, 1));

        // =============== Boundary Values Tests ==================
        // TC11: 3X3 Center (1,1)
        assertEquals(new Ray(Point.ZERO, new Vector(0, 0, 10)),
               camera.setVPSize(6, 6).constructRay(3, 3, 1, 1));

        // TC12: 3X3 Center of Upper Side (0,1)
        assertEquals(new Ray(Point.ZERO, new Vector(0, -2, 10)),
               camera.setVPSize(6, 6).constructRay(3, 3, 1, 0));

        // TC13: 3X3 Center of Left Side (1,0)
        assertEquals(new Ray(Point.ZERO, new Vector(-2, 0, 10)),
                camera.setVPSize(6, 6).constructRay(3, 3, 0, 1));
    }
}