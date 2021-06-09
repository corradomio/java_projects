package jext.math.linear.dense;

import jext.math.linear.Dim;
import jext.math.linear.Matrices;
import jext.math.linear.Matrix;
import jext.math.linear.Vector;
import jext.math.linear.Vectors;

import java.util.Arrays;

public class DenseMatrix extends BaseDense implements Matrix {

    public DenseMatrix(Dim dim) {
        this.dim = dim;
        this.data = new float[dim.dims[0]*dim.dims[1]];
    }

    public DenseMatrix(float[] mat, int nrows) {
        this.dim = new Dim(nrows, mat.length/nrows);
        this.data = mat;
    }

    // ----------------------------------------------------------------------

    @Override
    public Matrix set(int i, int j, float v) {
        int at = i*dim(1) + j;
        data[at] = v;
        return this;
    }

    @Override
    public float get(int i, int j) {
        int at = i*dim(1) + j;
        return data[at];
    }

    // ----------------------------------------------------------------------

    @Override
    public Matrix dot(Matrix B) {
        DenseMatrix that = (DenseMatrix) B;
        DenseMatrix res = Matrices.zeros(dim.dims[0], B.dim(1));
        Linear.dot(res.data, this.data, that.data, dim(1));
        return res;
    }

    // R = s*A + t*B
    @Override
    public Matrix linear(float s, float t, Matrix B) {
        if (!dim.equals(B.dim()))
            throw new IllegalArgumentException("Invalid dimensions");

        DenseMatrix that = (DenseMatrix) B;
        DenseMatrix res = Matrices.zeros(dim);
        Linear.linear(res.data, s, this.data, t, that.data);
        return res;
    }

    // r = s*A.u + t.v
    @Override
    public Vector linear(float s, Vector u, float t, Vector v) {
        if (dim(1) != u.dim(0) || dim(0) != v.dim(0))
            throw new IllegalArgumentException("Invalid dimensions");

        DenseVector du = (DenseVector) u;
        DenseVector dv = (DenseVector) v;
        DenseVector res = Vectors.zeros(v.dim());
        Linear.linear(res.data, s, this.data, du.data, t, dv.data);
        return res;
    }

    // R = s*C.A + t*B
    @Override
    public Matrix linear(float s, Matrix C, float t, Matrix B) {
        DenseMatrix dc = (DenseMatrix) C;
        DenseMatrix db = (DenseMatrix) B;
        DenseMatrix res = Matrices.zeros(B.dim());
        Linear.linear(res.data, s, dc.data, this.data, t, db.data, C.dim().dims[1]);
        return res;
    }

    // ----------------------------------------------------------------------

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }

    @Override
    public boolean equals(Object obj) {
        DenseMatrix that = (DenseMatrix) obj;
        return this.dim.equals(that.dim)
            && Arrays.equals(this.data, that.data);
    }

    @Override
    public String toString() {
        return ToString.toString(data, dim.dims[1],"%.03f");
    }
}
