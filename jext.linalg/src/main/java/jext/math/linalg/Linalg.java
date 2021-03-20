package jext.math.linalg;

public class Linalg {

    public static float[] zeros(int n) {
        return new float[n];
    }

    public static float[] ones(int n) {
        float[] v = zeros(n);
        for (int i=0; i<n; ++i)
            v[i] = 1;
        return v;
    }

    // zeros[n,m]
    public static float[][] zeros(int n, int m) {
        float[][] mat = new float[n][];
        for (int i=0; i<n; ++i)
            mat[i] = new float[m];
        return mat;
    }

    // identity[n]
    public static float[][] ones(int n, int m) {
        float[][] mat = zeros(n, m);
        int l = Math.min(n, m);
        for (int i=0; i<l; ++i)
            mat[i][i] = 1;
        return mat;
    }

}
