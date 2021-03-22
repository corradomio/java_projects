package jext.math.linear;

import jext.math.linear.dense.DenseMatrix;

public class Matrices {

    public static DenseMatrix identity(Dim dim) {
        return identity(dim.dim(0));
    }

    public static DenseMatrix identity(int n) {
        return matrix(Linear.diagonal(n, n, 1));
    }

    public static DenseMatrix matrix(Dim dim) {
        return matrix(dim.dim(0), dim.dim(1));
    }

    public static DenseMatrix matrix(int n, int m) {
        return matrix(Linear.matrix(n, m), n);
    }

    public static DenseMatrix matrix(float[] v) {
        int n = (int)Math.sqrt(v.length);
        return matrix(v, n);
    }

    public static DenseMatrix matrix(float[] v, int n) {
        int m = v.length/n;
        if (n*m != v.length)
            throw new IllegalArgumentException("Matrix dimensions");

        return new DenseMatrix(v, n);
    }
}
