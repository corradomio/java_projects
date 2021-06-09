package jext.math.linear;

import jext.math.linear.dense.DenseVector;
import jext.math.linear.sparse.Data;
import jext.math.linear.sparse.SparseVector;
import jext.util.Arrays;

public class Vectors {

    // -- Dense vectors

    public static DenseVector zeros(int n) {
        return zeros(new Dim(n));
    }

    public static DenseVector zeros(Dim dim) {
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
        return sparse(Arrays.indices(n), v, n);
    }

    public static SparseVector sparse(int[] c, float[] v, int n) {
        return sparse(c, v, new Dim(n));
    }

    public static SparseVector sparse(int[] c, float[] v, Dim dim) {
        Data data = new Data(c, v);
        return new SparseVector(data, dim);
    }

    // -- Print

    public static void print(Vector v) {
        int n = v.length();
        if (n == 0) {
            System.out.println("{ }");
            return;
        }

        System.out.printf("{ %.3f", v.get(0));
        for(int i=1; i<n; ++i)
            System.out.printf(", %.3f", v.get(i));
        System.out.println(" }");
    }
}
