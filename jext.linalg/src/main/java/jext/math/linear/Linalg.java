package jext.math.linear;

import jext.math.linear.dense.DenseMatrix;
import jext.math.linear.dense.DenseVector;
import jext.math.linear.dense.DiagMatrix;
import jext.math.linear.sparse.Data;
import jext.math.linear.sparse.SparseMatrix;
import jext.math.linear.sparse.SparseVector;
import jext.util.Arrays;

public class Linalg {

    // -- Dense vectors

    public static DenseVector vector(int n) {
        return vector(new Dim(n));
    }

    public static DenseVector vector(Dim dim) {
        return new DenseVector(dim);
    }

    public static DenseVector ones(int n) {
        return vector(jext.math.linear.dense.Linear.ones(n));
    }

    public static DenseVector vector(float[] v) {
        return new DenseVector(v);
    }

    // -- Sparse vectors

    public static SparseVector sparse(int n) {
        return sparse(new Dim(n));
    }

    public static SparseVector sparse(Dim dim) {
        return new SparseVector(dim);
    }

    public static SparseVector sparse(float[] v) {
        int n = v.length;
        return sparse(Arrays.indices(n), v);
    }

    public static SparseVector sparse(int[] c, float[] v) {
        int n = v.length;
        return new SparseVector(new Data(c, v), new Dim(v.length));
    }

    // -- Print

    public static void print(Vector v) {
        int n = v.length();
        if (n == 0) {
            System.out.println("{ }");
            return;
        }

        System.out.printf("{ %.3f", v.get(0));
        for(int i=1; i<n; ++i) {
            System.out.printf(", %.3f", v.get(i));
        }
        System.out.println(" }");
    }

    // -- Dense matrices

    public static DenseMatrix identity(Dim dim) {
        return identity(dim.dims[0]);
    }

    public static DenseMatrix identity(int n) {
        return matrix(jext.math.linear.dense.Linear.identity(n, n));
    }

    public static DenseMatrix matrix(int n, int m) {
        return matrix(new Dim(n, m));
    }

    public static DenseMatrix matrix(Dim dim) {
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

        return new DenseMatrix(v, new Dim(n, m));
    }

    public static DenseMatrix matrix(float[][] mat) {
        int n = mat.length;
        int m = n > 0 ? mat[0].length : 0;
        float[] v = new float[n*m];
        for (int i=0,k=0; i<n; ++i)
            for (int j=0; j<m; ++j,++k)
                v[k] = mat[i][j];
        return new DenseMatrix(v, new Dim(n, m));
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
        return new SparseMatrix(new Data(r, c, v), new Dim(n, m));
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
                if (mat.get(i, j) == 0)
                    System.out.printf(", 0    ");
                else
                    System.out.printf(", %.3f", mat.get(i, j));
            if (i<m-1)
                System.out.println(" },");
            else
                System.out.println(" }");
        }
        System.out.println("}");
    }


}
