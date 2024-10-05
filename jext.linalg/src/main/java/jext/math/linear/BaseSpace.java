package jext.math.linear;

public class BaseSpace {
    public Dim dim;

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
        return dim.dims[idim];
    }

}
