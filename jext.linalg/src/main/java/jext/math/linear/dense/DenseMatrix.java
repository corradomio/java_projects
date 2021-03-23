package jext.math.linear.dense;

import jext.math.linear.Dim;
import jext.math.linear.Matrices;
import jext.math.linear.Matrix;
import jext.math.linear.Type;
import jext.math.linear.Vector;
import jext.math.linear.Vectors;

public class DenseMatrix implements Matrix {
    public Dim dim;
    public float[] data;

    public DenseMatrix(float[] mat, int n) {
        this.data = mat;
        this.dim = new Dim(n, mat.length/n);
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
    public Type type() {
        return Type.DENSE;
    }

    @Override
    public Matrix same() {
        return Matrices.zeros(dim);
    }

    // R = s*A + t*B
    @Override
    public Matrix linear(float s, float t, Matrix B) {
        DenseMatrix that = (DenseMatrix) B;
        DenseMatrix res = Matrices.zeros(dim);
        Linear.linear(res.data, s, this.data, t, that.data);
        return res;
    }

    // r = s*A.u + t.v
    @Override
    public Vector linear(float s, Vector u, float t, Vector v) {
        DenseVector du = (DenseVector) u;
        DenseVector dv = (DenseVector) v;
        DenseVector res = Vectors.zeros(u.dim());
        Linear.linear(res.data, s, this.data, du.data, t, dv.data);
        return res;
    }

    @Override
    public Matrix linear(float s, Matrix C, float t, Matrix B) {
        DenseMatrix dc = (DenseMatrix) C;
        DenseMatrix db = (DenseMatrix) B;
        DenseMatrix res = Matrices.zeros(B.dim());
        Linear.linear(res.data, s, dc.data, this.data, t, db.data, C.dim().dim(1));
        return res;
    }

    @Override
    public String toString() {
        return Linear.toString(data, dim.dim(1),"%.03f");
    }
}
