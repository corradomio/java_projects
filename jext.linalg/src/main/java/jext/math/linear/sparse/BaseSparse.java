package jext.math.linear.sparse;

import jext.math.linear.Dim;
import jext.math.linear.Type;

import java.util.Iterator;

public class BaseSparse implements Iterable<Loc> {

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
        return dim.dims[idim];
    }

    // @Override
    public int length() {
        return data.length();
    }

    public Iterator<Loc> iterator() {
        return data.iterator();
    }

}
