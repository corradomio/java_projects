package jext.math.linear.dense;

import jext.math.linear.*;

import java.util.Arrays;

public class DenseRealVector extends RealSpace implements RealVector {

    public DenseRealVector(Dim dim) {
        this.dim = dim;
        this.data = new float[dim.dims[0]];
    }

    public DenseRealVector(float[] v) {
        this.dim = new Dim(v.length);
        this.data = v;
    }

    // ----------------------------------------------------------------------

    @Override
    public RealVector set(int i, float v) {
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
        return (float) Math.sqrt(RealLinear.dot(data, data));
    }

    @Override
    public Vector versor() {
        float s = this.norm();
        DenseRealVector r = (DenseRealVector) Linalg.vector(dim, float.class);
        RealLinear.linear(r.data, 1/s, this.data, 0, null);
        return r;
    }

    @Override
    public RealVector linear(float s, float t, RealVector v) {
        DenseRealVector that = (DenseRealVector) v;
        DenseRealVector r = (DenseRealVector) Linalg.vector(dim, float.class);
        RealLinear.linear(r.data, s, this.data, t, that.data);
        return r;
    }

    @Override
    public float dot(RealVector v) {
        DenseRealVector that = (DenseRealVector) v;
        return RealLinear.dot(this.data, that.data);
    }

    @Override
    public Matrix outer(Vector v) {
        DenseRealVector that = (DenseRealVector) v;
        int r = this.length()*that.length();
        float[] R = new float[r];
        RealLinear.outer(R, this.data, that.data);
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
        DenseRealVector that = (DenseRealVector) obj;
        return this.dim.equals(that.dim)
            && Arrays.equals(this.data, that.data);
    }

    @Override
    public String toString() {
        return ToString.toString(data, "%.03f");
    }
}
