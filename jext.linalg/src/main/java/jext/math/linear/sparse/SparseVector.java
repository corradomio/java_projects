package jext.math.linear.sparse;

import jext.math.linear.Dim;
import jext.math.linear.Type;
import jext.math.linear.Vector;

public class SparseVector implements Vector {

    private Data data;
    private Dim dim;

    public SparseVector(int[] idxs, float[] data, int n) {
        this.dim = new Dim(n);
        this.data = new Data(idxs, data);
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
    public Vector linear(float s, float t, Vector v) {
        return null;
    }
}
