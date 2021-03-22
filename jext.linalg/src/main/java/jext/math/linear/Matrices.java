package jext.math.linear;

import jext.math.linear.dense.DenseMatrix;
import jext.math.linear.dense.Linear;

public class Matrices {

    public static DenseMatrix identity(Dim dim) {
        return identity(dim.dim(0));
    }

    public static DenseMatrix identity(int n) {
        return matrix(Linear.diagonal(n, n, 1));
    }

    public static DenseMatrix zeros(Dim dim) {
        return zeros(dim.dim(0), dim.dim(1));
    }

    public static DenseMatrix zeros(int n, int m) {
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

    public static DenseMatrix matrix(float[][] mat) {
        int n = mat.length;
        int m = n > 0 ? mat[0].length : 0;
        float[] data = new float[n*m];
        for (int i=0,k=0; i<n; ++i)
            for (int j=0; j<m; ++j,++k)
                data[k] = mat[i][j];
        return new DenseMatrix(data, n);
    }
}
