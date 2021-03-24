package jext.math.linear;

import jext.math.linear.dense.DenseVector;
import jext.math.linear.sparse.SparseVector;

public class Vectors {

    // -- Dense vectors

    public static DenseVector zeros(Dim dim) {
        return zeros(dim.dim(0));
    }

    public static DenseVector zeros(int n) {
        return vector(new float[n]);
    }

    public static DenseVector ones(int n) {
        float[] ones = new float[n];
        for (int i=0; i<n; ++i)
            ones[i] = 1;
        return vector(ones);
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
}
