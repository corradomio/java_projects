package jext.math.linear.dense;

import jext.math.linear.*;

public class DenseBoolVector extends BoolSpace implements BoolVector {

    public DenseBoolVector(Dim dim) {
        this.dim = dim;
        this.data = new boolean[dim.dims[0]];
    }

    public DenseBoolVector(boolean[] v) {
        this.dim = new Dim(v.length);
        this.data = v;
    }

    // ----------------------------------------------------------------------

    @Override
    public BoolVector set(int i, boolean v) {
        data[i] = v;
        return this;
    }

    @Override
    public boolean get(int i) {
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
