package jext.math.linalg;

public abstract class BaseMatrix implements Matrix {

    protected Dim dim;
    protected int n;
    protected int m;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    protected BaseMatrix(int n) {
        this(n, n);
    }

    protected BaseMatrix(int n, int m) {
        this.n = n;
        this.m = m;
        this.dim = new Dim(n, m);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    public Dim dim() {
        return dim;
    }

    @Override
    public int dim(int idim) {
        return dim.dim(idim);
    }

    @Override
    public Matrix at(int i, int j, float v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Matrix set(float v) {
        throw new UnsupportedOperationException();
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

}
