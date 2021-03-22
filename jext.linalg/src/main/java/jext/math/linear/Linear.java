package jext.math.linear.dense;

import jext.math.linear.function.FloatMatrixFunction;
import jext.math.linear.function.FloatVectorFunction;

import java.util.Random;

public class Linear {

    public static float[] vect(int n) {
        return new float[n];
    }

    public static float[] vect(int n, float c) {
        return vect(n, (i) -> c);
    }

    public static float[] vect(int n, Random rnd) {
        return vect(n, (i) -> rnd.nextFloat());
    }

    public static float[] vect(int n, FloatVectorFunction f) {
        float[] v = vect(n);
        for (int i=0; i<n; ++i)
            v[i] = f.apply(i);
        return v;
    }

    // ----------------------------------------------------------------------

    public static float[] matrix(int n, int m) {
        return vect(n*m);
    }

    public static float[] matrix(int n, int m, float c) {
        return matrix(n, m, (i,j) -> c);
    }

    public static float[] matrix(int n, int m, Random rnd) {
        return matrix(n, m, (i,j)->rnd.nextFloat());
    }

    public static float[] matrix(int n, int m, FloatMatrixFunction f) {
        float[] a = matrix(n, m);
        for (int i=0,k=0; i<n; ++i)
            for (int j=0; j<m; ++j, ++k)
                a[k] = f.apply(i, j);
        return a;
    }

    public static float[] identity(int n) {
        return diagonal(n, n, 1);
    }

    public static float[] diagonal(int n, int m, float c) {
        return diagonal(n, m, (i) -> c);
    }

    public static float[] diagonal(int n, int m, FloatVectorFunction f) {
        float[] a = matrix(n, m);
        int l = Math.min(n, m);
        for (int i=0,k=0; i<l; ++i,k+=m+1)
            a[k] = f.apply(i);
        return a;
    }

    // ----------------------------------------------------------------------

    public static String toString(float[] v, String fmt) {
        return toString(v, 0, v.length, fmt);
    }

    private static String toString(float[] v, int i, int n, String fmt) {
        StringBuilder sb = new StringBuilder("[");
        if (n > 0)
            sb.append(String.format(fmt, v[i++]));
        for (int l=1; l<n; ++l)
            sb.append(", ").append(String.format(fmt, v[i++]));
        return sb.append("]").toString();
    }

    public static String toString(float[] A, int m, String fmt) {
        int n = A.length/m;
        StringBuilder sb = new StringBuilder("[\n");
        if (n > 0)
            sb.append("  ").append(toString(A, 0, m, fmt)).append("\n");
        for (int i=1,j=m; i<n; ++i,j+=m)
            sb.append("  ").append(toString(A, j, m, fmt)).append("\n");
        return sb.append("]").toString();
    }

    // ----------------------------------------------------------------------

    // r = u . v
    public static float dot(float[] u, float[] v) {
        return dot(u, 0, v);
    }

    // r = A[i:] . v
    public static float dot(float[] A, int i, float[] v) {
        int m = v.length;
        float s = 0;
        for (int j=0; j<m; ++i,++j)
            s += A[i]*v[j];
        return s;
    }

    // (n,k) (k,m) -> (n,m)
    // [. . . .]  [. .]
    // [. . . .]  [. .]
    // [. . . .]  [. .]
    //            [. .]


    // r = A[i:] . B[:j]
    public static float dot(float[]A, int i, float[] B, int j, int m) {
        int k=B.length/m;
        float s = 0;
        for (int l=0; l<k; ++l,++i,j+=m)
            s += A[i]*B[j];
        return s;
    }

    // r = u + C . v
    public static void dot(float[] r, float[] u, float[] C, float[] v) {
        int m = u.length;

        for(int i=0,j=0; i<m; i++, j+=m)
            r[i] = u[i] + dot(C, j, v);
    }

    // R = A + C . B
    public static void dot(float[] R, float[] A, float[] C, float[] B, int k) {
        int n = A.length/k;
        int m = B.length/k;
        for(int r=0,i=0,j=0,l=0; r<n; ++r)
            for (int c=0; c<m; ++c, l++,i+=k)
                R[l] = dot(A, i, B, c, k);
    }
}
