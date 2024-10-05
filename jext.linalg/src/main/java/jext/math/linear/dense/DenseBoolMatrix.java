package jext.math.linear.dense;

import jext.math.linear.BoolMatrix;
import jext.math.linear.Dim;

public class DenseBoolMatrix extends BoolSpace implements BoolMatrix {

    public DenseBoolMatrix(Dim dim) {
        this.dim = dim;
        this.data = new boolean[dim.dims[0]*dim.dims[1]];
    }

    public DenseBoolMatrix(boolean[] mat, Dim dim) {
        this.dim = dim;
        this.data = mat;
    }

    // ----------------------------------------------------------------------

    @Override
    public BoolMatrix set(int i, int j, boolean v) {
        int at = i*dim(1) + j;
        data[at] = v;
        return this;
    }

    @Override
    public boolean get(int i, int j) {
        int at = i*dim(1) + j;
        return data[at];
    }

    // ----------------------------------------------------------------------

}
