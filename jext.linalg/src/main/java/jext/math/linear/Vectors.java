package jext.math.linear;

import jext.math.linear.dense.DenseVector;
import jext.math.linear.dense.Linear;

public class Vectors {

    public static DenseVector zeros(Dim dim) {
        return zeros(dim.dim(0));
    }

    public static DenseVector zeros(int n) {
        return vector(Linear.vect(n));
    }

    public static DenseVector ones(int n) {
        return vector(Linear.vect(n, 1));
    }

    public static DenseVector vector(float[] v) {
        return new DenseVector(v);
    }
}
