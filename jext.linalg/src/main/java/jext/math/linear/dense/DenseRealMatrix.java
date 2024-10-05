package jext.math.linear.dense;

import jext.math.linear.*;

import java.util.Arrays;

public class DenseRealMatrix extends RealSpace implements RealMatrix {

    public DenseRealMatrix(Dim dim) {
        this.dim = dim;
        this.data = new float[dim.dims[0]*dim.dims[1]];
    }

    public DenseRealMatrix(float[] mat, Dim dim) {
        this.dim = dim;
        this.data = mat;
    }

    // ----------------------------------------------------------------------

    @Override
    public RealMatrix set(int i, int j, float v) {
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
    public RealMatrix dot(RealMatrix B) {
        DenseRealMatrix that = (DenseRealMatrix) B;
        DenseRealMatrix res = (DenseRealMatrix) Linalg.matrix(dim.dims[0], B.dim(1), float.class);
        Linear.dot(res.data, this.data, that.data, dim(1));
        return res;
    }

    // R = s*A + t*B
    @Override
    public RealMatrix linear(float s, float t, RealMatrix B) {
        if (!dim.equals(B.dim()))
            throw new IllegalArgumentException("Invalid dimensions");

        DenseRealMatrix that = (DenseRealMatrix) B;
        DenseRealMatrix res = (DenseRealMatrix) Linalg.matrix(dim, float.class);
        Linear.linear(res.data, s, this.data, t, that.data);
        return res;
    }

    // r = s*A.u + t.v
    @Override
    public RealVector linear(float s, RealVector u, float t, RealVector v) {
        if (dim(1) != u.dim(0) || dim(0) != v.dim(0))
            throw new IllegalArgumentException("Invalid dimensions");

        DenseRealVector du = (DenseRealVector) u;
        DenseRealVector dv = (DenseRealVector) v;
        DenseRealVector res = (DenseRealVector) Linalg.vector(v.dim(), float.class);
        Linear.linear(res.data, s, this.data, du.data, t, dv.data);
        return res;
    }

    // R = s*C.A + t*B
    @Override
    public RealMatrix linear(float s, RealMatrix C, float t, RealMatrix B) {
        DenseRealMatrix dc = (DenseRealMatrix) C;
        DenseRealMatrix db = (DenseRealMatrix) B;
        DenseRealMatrix res = (DenseRealMatrix) Linalg.matrix(B.dim(), float.class);
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
        DenseRealMatrix that = (DenseRealMatrix) obj;
        return this.dim.equals(that.dim)
            && Arrays.equals(this.data, that.data);
    }

    @Override
    public String toString() {
        return ToString.toString(data, dim.dims[1],"%.03f");
    }
}
