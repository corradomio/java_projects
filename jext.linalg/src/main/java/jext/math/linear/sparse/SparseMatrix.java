package jext.math.linear.sparse;

import jext.math.linear.Dim;
import jext.math.linear.Linalg;
import jext.math.linear.Matrix;
import jext.math.linear.Vector;

public class SparseMatrix extends BaseSparse implements Matrix {

    public SparseMatrix(Dim dim) {
        this(new Data(), dim);
    }

    public SparseMatrix(Data data, Dim dim) {
        this.dim = dim;
        this.data = data;
    }

    // ----------------------------------------------------------------------

    @Override
    public Matrix set(int i, int j, float v) {
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
    public Matrix dot(Matrix B) {
        SparseMatrix that = (SparseMatrix) B;
        SparseMatrix r = Linalg.sparse(this.dim(0), that.dim(1));
        Linear.dot(r.data, this.data, that.data);
        return r;
    }

    @Override
    public Matrix linear(float s, float t, Matrix B) {
        return null;
    }

    @Override
    public Vector linear(float s, Vector u, float t, Vector v) {
        return null;
    }

    @Override
    public Matrix linear(float s, Matrix C, float t, Matrix B) {
        return null;
    }
}
