package jext.ml;

import java.util.Arrays;
import java.util.Random;

public class Linalg {

    public static float[] alloc(int n) {
        return new float[n];
    }

    public static float[][] alloc(int n, int m) {
        float[][] mat = new float[n][];
        for (int i=0; i<n; ++i)
            mat[i] = new float[m];
        return mat;
    }

    public static void random(float[] a, Random rnd, boolean one) {
        int n = a.length;
        for (int i=0; i<n; ++i)
            a[i] = rnd.nextFloat();

        if (!one) return;
        a[n-1] = 1;

        Arrays.sort(a);

        for (int i=n-1; i>0; --i)
            a[i] = a[i] - a[i-1];
    }

    public static void zero(float[] a) {
        int n = a.length;
        for (int i=0; i<n; ++i)
            a[i] = 0;
    }

    public static float sum(float[] a) {
        int n = a.length;
        float s = 0;
        for (int i=0; i<n; ++i)
            s += a[i];
        return s;
    }

    public static void dir(float[] r, float[] a, float[] b) {
        int n = a.length;
        for (int i=0; i<n; ++i)
            r[i] = a[i]/b[i];
    }

    public static void diff(float[] r, float[] a, float[] b) {
        int n = a.length;
        for (int i=0; i<n; ++i)
            r[i] = a[i]-b[i];
    }

    public static float dot(float[] a, float[] b) {
        int n = a.length;
        float s = 0;
        for (int i=0; i<n;++i)
            s += a[i]*b[i];
        return s;
    }

    public static void colsum(float[] r, float[][] a) {
        int n = a.length;
        int m = n > 0 ? a[0].length : 0;
        zero(r);

        for (int i=0;i<n;++i)
            for (int j=0; j<m; ++j)
                r[j] += a[i][j];
    }
}
