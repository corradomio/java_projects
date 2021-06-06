package jext.math.linear;

import jext.math.linear.dense.DenseVector;
import jext.math.linear.sparse.SparseVector;

public class Vectors {

    // -- Dense vectors

    public static DenseVector zeros(Dim dim) {
        return zeros(dim.dim(0));
    }

    public static DenseVector zeros(int n) {
        return vector(jext.math.linear.dense.Linear.zeros(n));
    }

    public static DenseVector ones(int n) {
        return vector(jext.math.linear.dense.Linear.ones(n));
    }

    public static DenseVector vector(float[] v) {
        return new DenseVector(v);
    }

    // -- Sparse vectors

    public static SparseVector sparse(Dim dim) {
        return sparse(dim.dim(0));
    }

    public static SparseVector sparse(int n) {
        return new SparseVector(n);
    }

    public static SparseVector sparse(float[] v) {
        return new SparseVector(v);
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
