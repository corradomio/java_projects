package jext.math.linear;

import jext.math.linear.dense.DenseMatrix;
import jext.math.linear.dense.DiagMatrix;
import jext.math.linear.dense.Linear;
import jext.math.linear.sparse.SparseMatrix;

public class Matrices {

    // -- Dense matrices

    public static DenseMatrix identity(Dim dim) {
        return identity(dim.dims[0]);
    }

    public static DenseMatrix identity(int n) {
        return matrix(jext.math.linear.dense.Linear.identity(n, n));
    }

    public static DenseMatrix zeros(int n, int m) {
        return zeros(new Dim(n, m));
    }

    public static DenseMatrix zeros(Dim dim) {
        return new DenseMatrix(dim);
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

    // -- Sparse matrices

    /**
     * Sparse matrix
     *
     *      c[i][0],c[i][1] -> v[i]
     *
     * @return
     */

    public static SparseMatrix sparse(int n, int m) {
        return new SparseMatrix(new Dim(n, m));
    }

    public static SparseMatrix sparse(int[][] rc, float[] v, int n, int m) {
        int k = rc.length;
        int[] r = new int[k];
        int[] c = new int[k];
        for (int i=0; i<k; ++i) {
            r[i] = rc[i][0];
            c[i] = rc[i][1];
        }

        return sparse(r, c, v, n, m);
    }

    public static SparseMatrix sparse(int[] r, int[] c, float[] v, int n, int m) {
        return new SparseMatrix(r, c, v, new Dim(n, m));
    }

    // -- Print

    public static void print(Matrix mat) {
        int m = mat.dim(0);
        int n = mat.dim(1);
        if (m ==0 || n == 0) {
            System.out.println("{ }");
            return;
        }
        System.out.println("{");
        for (int i=0; i<m; ++i) {
            System.out.printf("  { %.3f", mat.get(i, 0));
            for (int j=1; j<n; ++j)
                System.out.printf(", %.3f", mat.get(i, j));
            if (i<m-1)
                System.out.println(" },");
            else
                System.out.println(" }");
        }
        System.out.println("}");
    }
}
