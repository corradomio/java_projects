package jext.math.linear.dense;

import jext.math.linear.Dim;
import jext.math.linear.Type;
import jext.math.linear.Vector;
import jext.math.linear.Vectors;

public class DenseVector implements Vector {
    public Dim dim;
    public float[] data;

    public DenseVector(float[] v) {
        this.data = v;
        this.dim = new Dim(v.length);
    }

    @Override
    public Type type() {
        return Type.DENSE;
    }

    @Override
    public Dim dim() {
        return dim;
    }

    @Override
    public int length() {
        return data.length;
    }

    @Override
    public Vector linear(float s, float t, Vector v) {
        DenseVector that = (DenseVector) v;
        DenseVector r = Vectors.zeros(dim);
        Linear.linear(r.data, s, this.data, t, that.data);
        return r;
    }

    @Override
    public String toString() {
        return Linear.toString(data, "%.03f");
    }
}
