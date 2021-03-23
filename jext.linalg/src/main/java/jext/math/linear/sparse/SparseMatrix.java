package jext.math.linear.sparse;

import jext.math.linear.Dim;
import jext.math.linear.Matrix;
import jext.math.linear.Type;
import jext.math.linear.Vector;

public class SparseMatrix implements Matrix {

    private Data data;
    private Dim dim;

    public SparseMatrix(int[] rows, int[] cols, float[] data, int n, int m) {
        this.dim = new Dim(n, m);
        this.data = new Data(rows, cols, data);
    }

    @Override
    public Type type() {
        return Type.SPARSE;
    }

    @Override
    public Dim dim() {
        return dim;
    }

    @Override
    public int dim(int idim) {
        return dim.dim(idim);
    }

    @Override
    public int length() {
        return data.length();
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
