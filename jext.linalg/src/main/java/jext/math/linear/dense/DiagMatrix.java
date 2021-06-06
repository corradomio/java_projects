package jext.math.linear.dense;

import jext.math.linear.Dim;
import jext.math.linear.Matrix;
import jext.math.linear.Type;
import jext.math.linear.Vector;

public class DiagMatrix extends BaseDense implements Matrix {

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
    public Matrix set(int i, int j, float v) {
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
    public Matrix dot(Matrix B) {
        return null;
    }

    // R = s*A + t*B
    @Override
    public Matrix linear(float s, float t, Matrix B) {
        return null;
    }

    // r = s*A.u + t*v
    @Override
    public Vector linear(float s, Vector u, float t, Vector v) {
        return null;
    }

    // R = s*C.A + t*B
    @Override
    public Matrix linear(float s, Matrix C, float t, Matrix B) {
        return null;
    }
}
