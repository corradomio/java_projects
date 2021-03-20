package jext.math.linalg;

public class DiagonalMatrix extends BaseMatrix {

    public float[] diag;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public DiagonalMatrix(float[] diag) {
        super(diag.length);
        this.diag = diag;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    public float at(int i, int j) {
        return i == j ? diag[i] : 0f;
    }

    @Override
    public Matrix at(int i, int j, float v) {
        if (i != j)
            throw new UnsupportedOperationException();
        diag[i] = v;
        return this;
    }

    @Override
    public Matrix set(float v) {
        for (int i=0; i<n; ++i)
            diag[i] = v;
        return this;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    public Vector dot(Vector v) {
        // vec = diag * vect
        float[] r = Linalg.zeros(n);
        Multiply.hprod(r, diag, v.data());
        return Vectors.vect(r);
    }

    @Override
    public Matrix dot(Matrix b) {
        // diag = diag . diag
        if (b instanceof DiagonalMatrix) {
            float[] r = Linalg.zeros(n);
            Multiply.hprod(r, diag, ((DiagonalMatrix)b).diag);
            return Matrices.diagonal(r);
        }
        // dense = diag . dense
        else {
            float[][] r = Linalg.zeros(n, n);
            Multiply.dot(r, ((DenseMatrix)b).mat, diag);
            return Matrices.matrix(r);
        }
    }

    @Override
    public Matrix hprod(Matrix b) {
        // diag = diag * diag
        if (b instanceof DiagonalMatrix) {
            float[] r = Linalg.zeros(n);
            Multiply.hprod(r, diag, ((DiagonalMatrix)b).diag);
            return Matrices.diagonal(r);
        }
        // diag = diag * dense
        else {
            float[] r = Linalg.zeros(n);
            Multiply.hprod(r, ((DenseMatrix)b).mat, diag);
            return Matrices.diagonal(r);
        }
    }

    @Override
    public Matrix linear(float s, float t, Matrix b) {
        if (b instanceof DiagonalMatrix){
            float[] r = Linalg.zeros(n);
            Linear.linear(r, s, diag, t, ((DiagonalMatrix)b).diag);
            return Matrices.diagonal(r);
        }
        else {
            float[][] r = Linalg.zeros(b.dim(0), b.dim(1));
            Linear.linear(r, s, diag, t, ((DenseMatrix)b).mat);
            return Matrices.matrix(r);
        }
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    public String toString() {
        return toString("%.3f");
    }

    public String toString(String fmt) {
        StringBuilder sb = new StringBuilder(String.format("Diagonal[%d]", n)).append("{");
        if (n > 0)
            sb.append(String.format(fmt, diag[0]));
        for(int i=1; i<n; ++i)
            sb.append(", ").append(String.format(fmt, diag[i]));
        return sb.append("}").toString();
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

}
