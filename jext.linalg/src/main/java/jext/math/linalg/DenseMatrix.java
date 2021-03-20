package jext.math.linalg;

public class DenseMatrix extends BaseMatrix {

    public float[][] mat;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public DenseMatrix(float[][] mat) {
        super(mat.length, mat.length > 0 ? mat[0].length : 0);
        this.mat = mat;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    // M[i,j]
    @Override
    public float at(int i, int j) {
        return mat[i][j];
    }

    // M[i,j] = v
    @Override
    public Matrix at(int i, int j, float v) {
        mat[i][j] = v;
        return this;
    }

    // M = v
    @Override
    public Matrix set(float v) {
        for (int i=0; i<n; ++i)
            for (int j=0; j<m; ++j)
                mat[i][j] = v;
        return this;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    // r = M . v
    @Override
    public Vector dot(Vector v) {
        float [] r = Linalg.zeros(m);
        Multiply.dot(r, mat, v.data());
        return Vectors.vect(r);
    }

    // R = A . diag(v)
    // R = A . B
    @Override
    public Matrix dot(Matrix b) {
        // dense = dense . diag
        if (b instanceof DiagonalMatrix) {
            float[][] r = Linalg.zeros(n, m);
            Multiply.dot(r, mat, ((DiagonalMatrix)b).diag);
            return Matrices.matrix(r);
        }
        // dense = dense . dense
        else {
            float[][] r = Linalg.zeros(n, m);
            Multiply.dot(r, mat, ((DenseMatrix)b).mat);
            return Matrices.matrix(r);
        }
    }

    @Override
    public Matrix hprod(Matrix b) {
        // diag = dense* diag
        if (b instanceof DiagonalMatrix) {
            float[] r = Linalg.zeros(b.dim(0));
            Multiply.hprod(r, mat, ((DiagonalMatrix)b).diag);
            return Matrices.diagonal(r);
        }
        // dense = dense * dense
        else {
            float[][] r = Linalg.zeros(n, m);
            Multiply.hprod(r, mat, ((DenseMatrix)b).mat);
            return Matrices.matrix(r);
        }
    }

    @Override
    public Matrix linear(float s, float t, Matrix b) {
        if (b instanceof DiagonalMatrix) {
            float[][] r = Linalg.zeros(n, n);
            Linear.linear(r, t, ((DiagonalMatrix)b).diag, s, mat);
            return Matrices.matrix(r);
        }
        else {
            float[][] r = Linalg.zeros(n, m);
            Linear.linear(r, s, mat, t, ((DenseMatrix)b).mat);
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
        StringBuilder sb = new StringBuilder(String.format("Dense[%d,%d])", n, m)).append("{\n");
        for (int i=0; i<n; ++i) {
            sb.append("  {");
            if (m > 0)
                sb.append(String.format(fmt, mat[i][0]));
            for(int j=1; j<m; ++j)
                sb.append(" ").append(String.format(fmt, mat[i][j]));
            if (i<n-1)
                sb.append("},\n");
            else
                sb.append("}\n");
        }
        return sb.append("}").toString();
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

}
