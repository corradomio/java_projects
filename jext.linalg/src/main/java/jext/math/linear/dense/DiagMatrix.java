package jext.math.linear.dense;

import jext.math.linear.*;

public class DiagMatrix extends RealSpace implements RealMatrix {

    public DiagMatrix(float[] data) {
        int n = data.length;
        this.data = data;
        this.dim = new Dim(n, n);
    }

    // ----------------------------------------------------------------------

    @Override
    public Type type() {
        return Type.DIAGONAL;
    }

    @Override
    public RealMatrix set(int i, int j, float v) {
        if (i == j)
            data[i] = v;
        return this;
    }

    @Override
    public float get(int i, int j) {
        return (i == j) ? data[i] : 0;
    }

    // ----------------------------------------------------------------------

    @Override
    public RealMatrix dot(RealMatrix B) {
        return null;
    }

    // R = s*A + t*B
    @Override
    public RealMatrix linear(float s, float t, RealMatrix B) {
        return null;
    }

    // r = s*A.u + t*v
    @Override
    public RealVector linear(float s, RealVector u, float t, RealVector v) {
        return null;
    }

    // R = s*C.A + t*B
    @Override
    public RealMatrix linear(float s, RealMatrix C, float t, RealMatrix B) {
        return null;
    }
}
