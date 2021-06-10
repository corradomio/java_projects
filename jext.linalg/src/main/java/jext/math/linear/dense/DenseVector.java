package jext.math.linear.dense;

import jext.math.linear.Dim;
import jext.math.linear.Linalg;
import jext.math.linear.Matrix;
import jext.math.linear.Vector;

import java.util.Arrays;

public class DenseVector extends BaseDense implements Vector {

    public DenseVector(Dim dim) {
        this.dim = dim;
        this.data = new float[dim.dims[0]];
    }

    public DenseVector(float[] v) {
        this.dim = new Dim(v.length);
        this.data = v;
    }

    // ----------------------------------------------------------------------

    @Override
    public Vector set(int i, float v) {
        data[i] = v;
        return this;
    }

    @Override
    public float get(int i) {
        return data[i];
    }

    // ----------------------------------------------------------------------

    @Override
    public float norm() {
        return (float) Math.sqrt(Linear.dot(data, data));
    }

    @Override
    public Vector versor() {
        float s = this.norm();
        DenseVector r = Linalg.vector(dim);
        Linear.linear(r.data, 1/s, this.data, 0, null);
        return r;
    }

    @Override
    public Vector linear(float s, float t, Vector v) {
        DenseVector that = (DenseVector) v;
        DenseVector r = Linalg.vector(dim);
        Linear.linear(r.data, s, this.data, t, that.data);
        return r;
    }

    @Override
    public float dot(Vector v) {
        DenseVector that = (DenseVector) v;
        return Linear.dot(this.data, that.data);
    }

    @Override
    public Matrix outer(Vector v) {
        DenseVector that = (DenseVector) v;
        int r = this.length()*that.length();
        float[] R = new float[r];
        Linear.outer(R, this.data, that.data);
        return Linalg.matrix(R, this.length());
    }

    @Override
    public Matrix diag() {
        return new DiagMatrix(this.data);
    }

    // ----------------------------------------------------------------------

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
