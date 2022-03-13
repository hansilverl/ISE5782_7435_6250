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

    Vector vec = new Vector(1, 2, 3);

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}
     */
    @Test
    // =============== Boundary Values Tests ==================
    //test that constructor does not except zero vector
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
        assertEquals(14, vec.lengthSquared()
                , "ERROR: Vector - Length Squaring does not work correctly");
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}
     */
    @Test
    void testLength() {
        assertEquals(Math.sqrt(14), vec.length()
                , "ERROR: Vector - Length calculation does not work correctly");
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}
     * Testing scalar multiplication
     */
    @Test
    void testDotProduct() {
        assertEquals(6, vec.dotProduct(new Vector(1, 1, 1))
                , "ERROR: Vector - Dot product calculation does not work correctly");
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

        // Check that the length of the cross-product is appropriate (orthogonal vectors taken for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001, "crossProduct() wrong result length");

        // Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)), "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // Test zero vector from cross-product of co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),
                "crossProduct() for parallel vectors does not throw an exception");

    }

    /**
     * Test method for {@link primitives.Vector#add(primitives.Vector)}
     */
    @Test
    void testAdd() {

        assertThrows(IllegalArgumentException.class, () -> {
                    vec.add(new Vector(-1, -2, -3));
                },
                "ERROR: zero vector should have thrown an exception");
        //Test that the addition method works properly
        assertEquals(new Vector(1, 1, 1), vec.add(new Vector(0, -1, -2))
                , "ERROR: Vector - Vector addition does not work correctly");

    }


    /**
     * Test method for {@link primitives.Vector#subtract(Vector)}
     */
    @Test
    void testSubtract() {
        assertThrows(IllegalArgumentException.class, () -> {
            vec.subtract(vec);
        }," ");
        assertEquals(new Vector(1, 3, 5), vec.subtract(new Vector(0, -1, -2))
                , "ERROR: Vector - Vector addition does not work correctly");

    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}
     */
    @Test
    void testScale() {

        //Testing if zero scaling works
        assertThrows(IllegalArgumentException.class, () -> {
                    vec.scale(0);
                },
                "ERROR: zero scaling should have thrown an exception");
        //Testing if general case works.
        assertEquals(new Vector(2, 4, 6), vec.scale(2)
                , "ERROR: Vector - Vector scaling does not work correctly");

    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}
     */
    @Test
    void testNormalize() {
        assertEquals(new Vector(1/Math.sqrt(14),Math.sqrt(2)/Math.sqrt(7),3/Math.sqrt(14)),vec.normalize()
                , "ERROR: Vector - Vector scaling does not work correctly");
    }


}