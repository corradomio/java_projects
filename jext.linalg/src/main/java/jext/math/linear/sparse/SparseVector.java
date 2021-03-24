package jext.math.linear.sparse;

import jext.math.linear.Dim;
import jext.math.linear.Type;
import jext.math.linear.Vector;
import jext.math.linear.Vectors;

public class SparseVector extends BaseSparse implements Vector {

    public SparseVector(int n) {
        this.dim = new Dim(n);
        this.data = new Data();
    }

    public SparseVector(int[] idxs, float[] data, int n) {
        this.dim = new Dim(n);
        this.data = new Data(idxs, data);
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
        SparseVector that = (SparseVector) v;
        SparseVector res = Vectors.sparse(dim);
        Linear.linear(res.data, s, this.data, t, that.data);
        return res;
    }

    @Override
    public String toString() {
        return ToString.toStringAsVector(data);
    }
}
