package jext.math.linear.dense;

import jext.math.linear.Dim;
import jext.math.linear.Linear;
import jext.math.linear.Matrices;
import jext.math.linear.Matrix;
import jext.math.linear.Type;

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
    public Matrix linear(float s, float t, Matrix B) {
        DenseMatrix that = (DenseMatrix) B;
        DenseMatrix res = Matrices.matrix(dim);
        Linear.linear(that.data, s, data, t, that.data);
        return res;
    }


    @Override
    public String toString() {
        return Linear.toString(data, dim.dim(1),"%.03f");
    }
}
