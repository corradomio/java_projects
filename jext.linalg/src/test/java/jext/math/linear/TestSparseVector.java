package jext.math.linear;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestSparseVector extends Assertions {

    @Test
    void testLength() {
        Vector v = Linalg.sparse(10);
        assertEquals(0, v.length());
        assertEquals(new Dim(10), v.dim());
    }

    @Test
    void testSum() {
        RealVector v1 = Linalg.sparse(new float[]{ 1,0,1,0});
        RealVector v2 = Linalg.sparse(new float[]{ 0,1,0,1});
        RealVector r = v1.linear(1, 1, v2);
        RealVector v3 = Linalg.sparse(new float[]{1,1,1,1});

        assertEquals(v3, r);
    }

    @Test
    void testLinear() {
        RealVector v1 = Linalg.sparse(new float[]{ 2,0,2,0});
        RealVector v2 = Linalg.sparse(new float[]{ 0,1,0,1});
        RealVector r = v1.linear(0.5f, 2, v2);
        RealVector v3 = Linalg.sparse(new float[]{1,2,1,2});

        assertEquals(v3, r);
    }

    @Test
    void testZero() {
        RealVector v1 = Linalg.sparse(new float[]{ 2,0,2,0});
        RealVector v2 = Linalg.sparse(new float[]{ 0,1,0,1});
        RealVector r = v1.linear(0,0, v2);
        RealVector v3 = Linalg.sparse(v1.dim());

        assertEquals(v3, r);
    }

    @Test
    void testAssignment() {
        RealVector v1 = Linalg.sparse(new float[]{ 2,0,2,0});
        RealVector v2 = Linalg.sparse(new float[]{ 0,1,0,1});
        RealVector r1 = v1.linear(1,0,v2);
        assertEquals(r1, v1);
        RealVector r2 = v1.linear(0,1,v2);
        assertEquals(v2, r2);
    }
}
