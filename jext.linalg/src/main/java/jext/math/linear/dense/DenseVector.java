package jext.math.linear.dense;

import jext.math.linear.Dim;
import jext.math.linear.Type;
import jext.math.linear.Vector;
import jext.math.linear.Vectors;

import java.util.Arrays;

public class DenseVector extends BaseDense implements Vector {

    public DenseVector(float[] v) {
        this.data = v;
        this.dim = new Dim(v.length);
    }

    @Override
    public Vector set(int i, float v) {
        data[i] = v;
        return this;
    }

    @Override
    public float get(int i) {
        return data[i];
    }

    @Override
    public Vector linear(float s, float t, Vector v) {
        DenseVector that = (DenseVector) v;
        DenseVector r = Vectors.zeros(dim);
        Linear.linear(r.data, s, this.data, t, that.data);
        return r;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }

    @Override
    public boolean equals(Object obj) {
        DenseVector that = (DenseVector) obj;
        return this.dim.equals(that.dim)
            && Arrays.equals(this.data, that.data);
    }

    @Override
    public String toString() {
        return ToString.toString(data, "%.03f");
    }
}
