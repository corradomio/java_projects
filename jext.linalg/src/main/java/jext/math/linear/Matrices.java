package jext.math.linear;

import jext.math.linear.dense.DenseMatrix;
import jext.math.linear.dense.DiagMatrix;
import jext.math.linear.dense.Linear;

public class Matrices {

    public static DenseMatrix identity(Dim dim) {
        return identity(dim.dim(0));
    }

    public static DenseMatrix identity(int n) {
        return matrix(jext.math.linear.dense.Linear.identity(n, n));
    }

    public static DenseMatrix zeros(Dim dim) {
        return zeros(dim.dim(0), dim.dim(1));
    }

    public static DenseMatrix zeros(int n, int m) {
        return matrix(new float[n*m], n);
    }

    // square matrix
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

    public static DiagMatrix diag(float[] v) {
        return new DiagMatrix(v);
    }

    // -- Print

    public static void print(Matrix mat) {
        int m = mat.dim(0);
        int n = mat.dim(1);
        if (m ==0 || n == 0) {
            System.out.println("{{ }}");
            return;
        }
        System.out.println("{");
        for (int i=0; i<m; ++i) {
            System.out.printf("  { %.3f", mat.get(i, 0));
            for (int j=1; j<n; ++j)
                System.out.printf(", %.3f", mat.get(i, j));
            System.out.println(" }");
        }
        System.out.println("}");
    }
}
