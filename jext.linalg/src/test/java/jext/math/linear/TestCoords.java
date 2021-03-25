package jext.math.linear;

import jext.math.linear.sparse.Coords;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestCoords extends Assertions {

    @Test
    void testCoordsEmpty() {
        Coords c = new Coords();
        assertEquals(c.n, 1);
        assertEquals(c.coords[0], 0);
    }

    @Test
    void testUnion() {
        Coords c1 = new Coords(new long[]{1,3,5,7});
        Coords c2 = new Coords(new long[]{2,4,6,8});
        Coords c3 = new Coords(new long[]{1,2,3,4,5,6,7,8});
        Coords r = c1.union(c2);
        assertEquals(c3, r);
    }
}
