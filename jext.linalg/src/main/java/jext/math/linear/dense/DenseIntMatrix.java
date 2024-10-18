package jext.math.linear.dense;

import jext.math.linear.*;

public class DenseIntMatrix extends IntSpace implements IntMatrix {

    public DenseIntMatrix(Dim dim) {
        this.dim = dim;
        this.data = new int[dim.dims[0]*dim.dims[1]];
    }

    public DenseIntMatrix(int[] mat, Dim dim) {
        this.dim = dim;
        this.data = mat;
    }

    // ----------------------------------------------------------------------

    @Override
    public IntMatrix set(int i, int j, int v) {
        int at = i*dim(1) + j;
        data[at] = v;
        return this;
    }

    @Override
    public int get(int i, int j) {
        int at = i*dim(1) + j;
        return data[at];
    }

    // ----------------------------------------------------------------------

    @Override
    public IntMatrix linear(int s, int t, IntMatrix B) {
        if (!dim.equals(B.dim()))
            throw new IllegalArgumentException("Invalid dimensions");

        DenseIntMatrix that = (DenseIntMatrix) B;
        DenseIntMatrix res = (DenseIntMatrix) Linalg.matrix(dim, int.class);
        IntLinear.linear(res.data, s, this.data, t, that.data);
        return res;
    }

    @Override
    public IntVector linear(int s, IntVector u, int t, IntVector v) {
        return null;
    }

    @Override
    public IntMatrix linear(int s, IntMatrix C, int t, IntMatrix B) {
        return null;
    }

}
