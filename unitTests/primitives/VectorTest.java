package primitives;

import org.junit.jupiter.api.Test;

import static primitives.Util.isZero;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for primitives.Vector
 *
 * @Authors Hila Buznach & Hannah Silverberg
 */
class VectorTest {

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}
     */
    @Test
    void testConstructorZero() {
        assertThrows(IllegalArgumentException.class, () -> {
                    new Vector(0, 0, 0);
                },
                "ERROR: zero vector should have thrown an exception");
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}
     */
    @Test
    void testLengthSquared() {
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}
     */
    @Test
    void testLength() {
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}
     */
    @Test
    void testDotProduct() {
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}
     */
    @Test
    void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001, "crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)), "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-product of co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),
                "crossProduct() for parallel vectors does not throw an exception");

    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}
     */
    @Test
    void testAdd() {
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}
     */
    @Test
    void testScale() {
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}
     */
    @Test
    void testNormalize() {
    }
}