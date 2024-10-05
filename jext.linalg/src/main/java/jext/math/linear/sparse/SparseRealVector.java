package jext.math.linear.sparse;

import jext.math.linear.*;

public class SparseRealVector extends BaseSparse implements RealVector {

    public SparseRealVector(Dim dim) {
        this.dim = dim;
        this.data = new Data();
    }

    public SparseRealVector(Data data, Dim dim) {
        this.dim = dim;
        this.data = data;
    }

    // ----------------------------------------------------------------------

    @Override
    public RealVector set(int i, float v) {
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
        SparseRealVector r = Linalg.sparse(dim);
        Linear.linear(r.data, 1/s, this.data, 0, null);
        return r;
    }

    @Override
    public RealVector linear(float s, float t, RealVector v) {
        SparseRealVector that = (SparseRealVector) v;
        SparseRealVector res = Linalg.sparse(dim);
        Linear.linear(res.data, s, this.data, t, that.data);
        return res;
    }

    @Override
    public float dot(RealVector v) {
        SparseRealVector that = (SparseRealVector) v;
        return Linear.dot(this.data, that.data);
    }

    @Override
    public Matrix outer(Vector v) {
        SparseRealVector that = (SparseRealVector) v;
        Data r = new Data();
        Linear.outer(r, this.data, that.data);
        return new SparseRealMatrix(r, new Dim(this.dim(0), that.dim(0)));
    }

    @Override
    public Matrix diag() {
        return null;
    }

    // ----------------------------------------------------------------------

    @Override
    public boolean equals(Object obj) {
        SparseRealVector that = (SparseRealVector) obj;

        if (!this.dim.equals(that.dim))
            return false;
        if (this.data.equals(that.data))
            return true;

        Coords u = this.data.union(that.data);
        for (Loc l : u)
            if (this.get(l.r) != that.get(l.r))
                return false;
        return true;
    }

    @Override
    public String toString() {
        return ToString.toStringAsVector(data);
    }
}
