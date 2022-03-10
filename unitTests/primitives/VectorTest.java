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

    @Test
    void testLengthSquared() {
    }

    @Test
    void testLength() {
    }

    @Test
    void testDotProduct() {
    }

    @Test
    void testCrossProduct() {
    }

    @Test
    void testAdd() {
    }

    @Test
    void testScale() {
    }

    @Test
    void testNormalize() {
    }
}