package jext.math.linear;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestDenseVector extends Assertions {

    @Test
    void testLength() {
        Vector v = Vectors.zeros(10);
        assertEquals(10, v.length());
        assertEquals(new Dim(10), v.dim());
    }

    @Test
    void testSum() {
        Vector v1 = Vectors.vector(new float[]{ 1,0,1,0});
        Vector v2 = Vectors.vector(new float[]{ 0,1,0,1});
        Vector r = v1.linear(1, 1, v2);
        Vector v3 = Vectors.ones(4);

        assertEquals(v3, r);
    }

    @Test
    void testLinear() {
        Vector v1 = Vectors.vector(new float[]{ 2,0,2,0});
        Vector v2 = Vectors.vector(new float[]{ 0,1,0,1});
        Vector r = v1.linear(0.5f, 2, v2);
        Vector v3 = Vectors.vector(new float[]{1,2,1,2});

        assertEquals(v3, r);
    }

    @Test
    void testZero() {
        Vector v1 = Vectors.vector(new float[]{ 2,0,2,0});
        Vector v2 = Vectors.vector(new float[]{ 0,1,0,1});
        Vector r = v1.linear(0,0,v2);
        Vector v3 = Vectors.zeros(v1.dim());

        assertEquals(v3, r);
    }

    @Test
    void testassignment() {
        Vector v1 = Vectors.vector(new float[]{ 2,0,2,0});
        Vector v2 = Vectors.vector(new float[]{ 0,1,0,1});
        Vector r1 = v1.linear(1,0,v2);
        assertEquals(v1, r1);
        Vector r2 = v1.linear(0,1,v2);
        assertEquals(v2, r2);
    }
}
