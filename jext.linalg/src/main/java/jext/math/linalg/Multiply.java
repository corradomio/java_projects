package jext.math.linalg;

public class Multiply {

    // ----------------------------------------------------------------------

    // r = u . v
    public static float dot(float[] u, float[] v) {
        int n = u.length;
        float s = 0;
        for (int i=0; i<n; ++i)
            s += u[i]*v[i];
        return s;
    }

    // r = A[i,:].v
    public static float dot(float[] A, int i, float[] v){
        int m = v.length;
        float s = 0;
        for (int j=0; j<m;++i,++j)
            s += A[i]*v[j];
        return s;
    }

    // r = A[i:].B[:,j]
    public static float dot(float[] A, int i, float[] B, int j, int m) {
        float s = 0;
        for (int k=0; k<m; ++k,++i,j+=m)
            s += A[i]*B[j];
        return s;
    }

    // r = u * v
    public static void hprod(float[]r, float[]u, float[] v) {
        int n = r.length;
        for (int i=0; i<n; ++i)
            r[i] = u[i]*v[i];
    }

    // ----------------------------------------------------------------------

    // r = A . v
    public static void dot(float[] r, float[][] A, float[] v) {
        int n = r.length;
        int m = v.length;

        for (int i=0; i<n; ++i) {
            float s = 0;
            for (int j=0; j<n; ++j) {
                s += A[i][j]*v[j];
            }
            r[i] = s;
        }
    }

    // ----------------------------------------------------------------------

    // R = A . B
    public static void dot(float[][] R, float[][] A, float[][] B) {
        int n = R.length;
        int m = n > 0 ? R[0].length : 0;
        int l = A.length > 0 ? A[0].length : 0;

        for (int i=0; i<n; ++i)
        for (int j=0; j<m; ++j) {
            float s = 0;
            for (int k=0; k<l; ++k)
                s += A[i][k]*B[k][j];
            R[i][j] = s;
        }
    }

    // R = A * B
    public static void hprod(float[][] R, float[][] A, float[][] B) {
        int n = R.length;
        int m = n > 0 ? R[0].length : 0;
        for (int i=0; i<n; ++i)
            for (int j=0; j<m; ++j)
                R[i][j] = A[i][j]*B[i][j];
    }

    // ----------------------------------------------------------------------

    // R = A . diag(v)
    public static void dot(float[][] R, float[][] A, float[] v) {
        int n = R.length;
        int m = n > 0 ? R[0].length : 0;
        for (int i=0; i<n; ++i)
            for (int j=0; j<m; ++j)
                R[i][j] = A[i][j]*v[j];
    }

    // diag(r) = A * diag(v)
    public static void hprod(float[] r, float[][] A, float[] v) {
        int n = r.length;
        for (int i=0; i<n; ++i)
            r[i] = A[i][i]*v[i];
    }

}
