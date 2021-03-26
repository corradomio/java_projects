package jext.math.linear.sparse;

import jext.math.linear.Dim;
import jext.math.linear.Matrix;
import jext.math.linear.Type;
import jext.math.linear.Vector;

public class SparseMatrix extends BaseSparse implements Matrix {

    private Data data;

    public SparseMatrix(int[] rows, int[] cols, float[] data, int n, int m) {
        this.dim = new Dim(n, m);
        this.data = new Data(rows, cols, data);
    }


    @Override
    public Matrix set(int i, int j, float v) {
        data.set(i, j, v);
        return this;
    }

    @Override
    public float get(int i, int j) {
        return data.get(i, j);
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
