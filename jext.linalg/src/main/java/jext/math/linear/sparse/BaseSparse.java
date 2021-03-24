package jext.math.linear.sparse;

import jext.math.linear.Dim;
import jext.math.linear.Type;

public class BaseSparse {

    public Data data;
    public Dim dim;

    // @Override
    public Type type() {
        return Type.SPARSE;
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
        return data.length();
    }

}
