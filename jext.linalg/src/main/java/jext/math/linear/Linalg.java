package jext.math.linear;

import jext.math.linear.dense.*;
import jext.math.linear.sparse.Data;
import jext.math.linear.sparse.SparseRealMatrix;
import jext.math.linear.sparse.SparseRealVector;
import jext.math.util.Arrays;

public class Linalg {

    // -- Dense vectors

    public static Vector vector(int n, Class<?> dtype) {
        return vector(new Dim(n), dtype);
    }

    public static Vector vector(Dim dim, Class<?> dtype) {
        if (dtype == float.class) {
            return new DenseRealVector(dim);
        } else if (dtype == int.class) {
            return new DenseIntVector(dim);
        }
        else if (dtype == boolean.class) {
            return new DenseBoolVector(dim);
        }
        else {
            throw new IllegalArgumentException("Unsupported vector type: " + dtype);
        }
    }

    public static Vector ones(int n, Class<?> dtype) {
        if (dtype == float.class)
            return vector(RealLinear.ones(n));
        else if (dtype == int.class)
            return vector(IntLinear.ones(n));
        else
            throw new IllegalArgumentException("Unsupported vector type: " + dtype);
    }

    public static IntVector vector(int[] v) {
        return new DenseIntVector(v);
    }

    public static RealVector vector(float[] v) {
        return new DenseRealVector(v);
    }

    // -- Sparse vectors

    public static SparseRealVector sparse(int n) {
        return sparse(new Dim(n));
    }

    public static SparseRealVector sparse(Dim dim) {
        return new SparseRealVector(dim);
    }

    public static SparseRealVector sparse(float[] v) {
        int n = v.length;
        return sparse(Arrays.indices(n), v);
    }

    public static SparseRealVector sparse(int[] c, float[] v) {
        int n = v.length;
        return new SparseRealVector(new Data(c, v), new Dim(v.length));
    }

    // -- Print

    public static void print(RealVector v) {
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

    public static void print(IntVector v) {
        int n = v.length();
        if (n == 0) {
            System.out.println("{ }");
            return;
        }

        System.out.printf("{ %d", v.get(0));
        for(int i=1; i<n; ++i) {
            System.out.printf(", %d", v.get(i));
        }
        System.out.println(" }");
    }

    public static void print(BoolVector v) {
        int n = v.length();
        if (n == 0) {
            System.out.println("{ }");
            return;
        }

        System.out.printf("{ %s", v.get(0) ? "T" : "F");
        for(int i=1; i<n; ++i) {
            System.out.printf(", %s", v.get(i) ? "T" : "F");
        }
        System.out.println(" }");
    }

    // -- Dense matrices

    public static Matrix identity(Dim dim, Class<?> dtype) {
        return identity(dim.dims[0], dtype);
    }

    public static Matrix identity(int n, Class<?> dtype) {
        if (dtype == float.class)
            return matrix(RealLinear.identity(n, n));
        else if (dtype == int.class)
            return matrix(IntLinear.identity(n, n));
        else
            throw new IllegalArgumentException("Unsupported vector type: " + dtype);
    }

    // ---

    public static Matrix matrix(int n, Class<?> dtype) {
        return matrix(new Dim(n, n), dtype);
    }

    public static Matrix matrix(int n, int m, Class<?> dtype) {
        return matrix(new Dim(n, m), dtype);
    }

    public static Matrix matrix(Dim dim, Class<?> dtype) {
        if (dtype == float.class)
            return new DenseRealMatrix(dim);
        else if (dtype == int.class)
            return new DenseIntMatrix(dim);
        else if (dtype == boolean.class)
            return new DenseBoolMatrix(dim);
        else
            throw new IllegalArgumentException("Unsupported vector type: " + dtype);
    }

    // square matrix
    public static RealMatrix matrix(float[] v) {
        int n = (int)Math.sqrt(v.length);
        return matrix(v, n);
    }

    public static RealMatrix matrix(float[] v, int n) {
        int m = v.length/n;
        if (n*m != v.length)
            throw new IllegalArgumentException("Matrix dimensions");

        return new DenseRealMatrix(v, new Dim(n, m));
    }

    public static RealMatrix matrix(float[][] mat) {
        int n = mat.length;
        int m = n > 0 ? mat[0].length : 0;
        float[] v = new float[n*m];
        for (int i=0,k=0; i<n; ++i)
            for (int j=0; j<m; ++j,++k)
                v[k] = mat[i][j];
        return new DenseRealMatrix(v, new Dim(n, m));
    }

    public static DiagMatrix diag(float[] v) {
        return new DiagMatrix(v);
    }

    // square matrix
    public static IntMatrix matrix(int[] v) {
        int n = (int)Math.sqrt(v.length);
        return matrix(v, n);
    }

    public static IntMatrix matrix(int[] v, int n) {
        int m = v.length/n;
        if (n*m != v.length)
            throw new IllegalArgumentException("Matrix dimensions");

        return new DenseIntMatrix(v, new Dim(n, m));
    }

    public static IntMatrix matrix(int[][] mat) {
        int n = mat.length;
        int m = n > 0 ? mat[0].length : 0;
        int[] v = new int[n*m];
        for (int i=0,k=0; i<n; ++i)
            for (int j=0; j<m; ++j,++k)
                v[k] = mat[i][j];
        return new DenseIntMatrix(v, new Dim(n, m));
    }

    // -- Sparse matrices

    /**
     * Sparse matrix
     *
     *      c[i][0],c[i][1] -> v[i]
     *
     */

    public static SparseRealMatrix sparse(int n, int m) {
        return new SparseRealMatrix(new Dim(n, m));
    }

    public static SparseRealMatrix sparse(int[][] rc, float[] v, int n, int m) {
        int k = rc.length;
        int[] r = new int[k];
        int[] c = new int[k];
        for (int i=0; i<k; ++i) {
            r[i] = rc[i][0];
            c[i] = rc[i][1];
        }

        return sparse(r, c, v, n, m);
    }

    public static SparseRealMatrix sparse(int[] r, int[] c, float[] v, int n, int m) {
        return new SparseRealMatrix(new Data(r, c, v), new Dim(n, m));
    }

    // -- Print

    public static void print(RealMatrix mat) {
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
