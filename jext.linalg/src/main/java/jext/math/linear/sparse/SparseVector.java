package jext.math.linear.sparse;

import jext.math.linear.Dim;
import jext.math.linear.Linalg;
import jext.math.linear.Matrix;
import jext.math.linear.Vector;

public class SparseVector extends BaseSparse implements Vector {

    public SparseVector(Dim dim) {
        this.dim = dim;
        this.data = new Data();
    }

    public SparseVector(Data data, Dim dim) {
        this.dim = dim;
        this.data = data;
    }

    // ----------------------------------------------------------------------

    @Override
    public Vector set(int i, float v) {
        data.set(i, 0, v);
        return this;
    }

    @Override
    public float get(int i) {
        return data.get(i, 0);
    }

    // ----------------------------------------------------------------------

    @Override
    public float norm() {
        return (float) Math.sqrt(Linear.dot(data, data));
    }

    @Override
    public Vector versor() {
        float s = this.norm();
        SparseVector r = Linalg.sparse(dim);
        Linear.linear(r.data, 1/s, this.data, 0, null);
        return r;
    }

    @Override
    public Vector linear(float s, float t, Vector v) {
        SparseVector that = (SparseVector) v;
        SparseVector res = Linalg.sparse(dim);
        Linear.linear(res.data, s, this.data, t, that.data);
        return res;
    }

    @Override
    public float dot(Vector v) {
        SparseVector that = (SparseVector) v;
        return Linear.dot(this.data, that.data);
    }

    @Override
    public Matrix outer(Vector v) {
        SparseVector that = (SparseVector) v;
        Data r = new Data();
        Linear.outer(r, this.data, that.data);
        return new SparseMatrix(r, new Dim(this.dim(0), that.dim(0)));
    }

    @Override
    public Matrix diag() {
        return null;
    }

    // ----------------------------------------------------------------------

    @Override
    public boolean equals(Object obj) {
        SparseVector that = (SparseVector) obj;
        return this.dim.equals(that.dim)
            && this.data.equals(that.data);
    }

    @Override
    public String toString() {
        return ToString.toStringAsVector(data);
    }
}
