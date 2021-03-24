package jext.math.linear.dense;

import jext.math.linear.Dim;
import jext.math.linear.Type;

public class BaseDense {

    public Dim dim;
    public float[] data;

    // @Override
    public Type type() {
        return Type.DENSE;
    }

    // @Override
    public Dim dim() {
        return dim;
    }

    // @Override
    public int dim(int idim) {
        return dim.dim(idim);
    }

    // @Override
    public int length() {
        return data.length;
    }

}
