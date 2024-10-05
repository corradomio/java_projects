package jext.math.linear.dense;

import jext.math.linear.*;

public class DenseIntVector extends IntSpace implements IntVector {

    public DenseIntVector(Dim dim) {
        this.dim = dim;
        this.data = new int[dim.dims[0]];
    }

    public DenseIntVector(int[] v) {
        this.dim = new Dim(v.length);
        this.data = v;
    }

    // ----------------------------------------------------------------------

    @Override
    public IntVector set(int i, int v) {
        data[i] = v;
        return this;
    }

    @Override
    public int get(int i) {
        return data[i];
    }

    // ----------------------------------------------------------------------

    @Override
    public Vector versor() {
        return null;
    }

    @Override
    public Matrix outer(Vector v) {
        return null;
    }

    @Override
    public Matrix diag() {
        return null;
    }
}
