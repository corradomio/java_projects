package jext.math.linear.sparse;

import jext.math.linear.*;

public class SparseRealMatrix extends BaseSparse implements RealMatrix {

    public SparseRealMatrix(Dim dim) {
        this(new Data(), dim);
    }

    public SparseRealMatrix(Data data, Dim dim) {
        this.dim = dim;
        this.data = data;
    }

    // ----------------------------------------------------------------------

    @Override
    public RealMatrix set(int i, int j, float v) {
        data.set(i, j, v);
        return this;
    }

    @Override
    public float get(int i, int j) {
        return data.get(i, j);
    }

    // ----------------------------------------------------------------------

    public Iterable<Loc> rows() {
        return data.rows();
    }

    public Iterable<Loc> cols() {
        return data.cols();
    }

    public Iterable<Loc> rows(int c) {
        return data.rows(c);
    }

    public Iterable<Loc> cols(int r) {
        return data.cols(r);
    }

    // ----------------------------------------------------------------------

    @Override
    public RealMatrix dot(RealMatrix B) {
        SparseRealMatrix that = (SparseRealMatrix) B;
        SparseRealMatrix r = Linalg.sparse(this.dim(0), that.dim(1));
        RealLinear.dot(r.data, this.data, that.data);
        return r;
    }

    @Override
    public RealMatrix linear(float s, float t, RealMatrix B) {
        return null;
    }

    @Override
    public RealVector linear(float s, RealVector u, float t, RealVector v) {
        return null;
    }

    @Override
    public RealMatrix linear(float s, RealMatrix C, float t, RealMatrix B) {
        return null;
    }
}
