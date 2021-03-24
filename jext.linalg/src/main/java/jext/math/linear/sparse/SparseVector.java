package jext.math.linear.sparse;

import jext.math.linear.Dim;
import jext.math.linear.Type;
import jext.math.linear.Vector;

public class SparseVector implements Vector {

    private Data data;
    private Dim dim;

    public SparseVector(int n) {
        this.dim = new Dim(n);
        this.data = new Data();
    }

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
    public Vector set(int i, float v) {
        data.set(i, 0, v);
        return this;
    }

    @Override
    public float get(int i) {
        return data.get(i, 0);
    }


    @Override
    public Vector linear(float s, float t, Vector v) {
        return null;
    }

    @Override
    public String toString() {
        return ToString.toStringAsVector(data);
    }
}
