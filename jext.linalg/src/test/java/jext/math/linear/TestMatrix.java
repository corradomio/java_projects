package jext.math.linear;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestMatrix extends Assertions {

    @Test
    void testCreate() {
        Matrix m = Matrices.zeros(3,4);
        assertEquals(3, m.dim(0));
        assertEquals(4, m.dim(1));
    }

    @Test
    void testLinear() {
        Matrix m1 = Matrices.matrix(new float[][]{
            {1,0,1,0},
            {0,1,0,1}
        });
        Matrix m2 = Matrices.matrix(new float[][]{
            {0,2,0,2},
            {2,0,2,0}
        });
        Matrix m3 = Matrices.matrix(new float[][]{
            {2,1,2,1},
            {1,2,1,2}
        });

        Matrix r = m1.linear(2, 0.5f, m2);
        assertEquals(m3, r);
    }

    @Test
    void testDotVector() {
        Vector u = Vectors.vector(new float[]{2,4,6,8});
        Vector v = Vectors.vector(new float[]{1,2,3});
        Vector v3 = Vectors.vector(new float[]{2,4,10});
        Matrix m = Matrices.matrix(new float[][]{
            {0.5f,0,0,0},
            {0,0.5f,0,0},
            {0,0,0.5f,0.5f}
        });
        Vector r = m.linear(1,u,1,v);
        assertEquals(v3, r);
    }
}