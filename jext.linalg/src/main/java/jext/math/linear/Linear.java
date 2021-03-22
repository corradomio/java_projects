package jext.math.linear;

import jext.math.linear.function.FloatMatrix;
import jext.math.linear.function.FloatVector;

import java.util.Random;

public class Linear {

    public static float[] vect(int n) {
        return new float[n];
    }

    public static float[] vect(int n, float c) {
        float[] v = vect(n);
        for (int i=0; i<n; ++i)
            v[i] = c;
        return v;
    }

    public static float[] vect(int n, Random rnd) {
        return vect(n, (i) -> rnd.nextFloat());
    }

    public static float[] vect(int n, FloatVector f) {
        float[] v = vect(n);
        for (int i=0; i<n; ++i)
            v[i] = f.eval(i);
        return v;
    }

    // ----------------------------------------------------------------------

    public static float[] matrix(int n, int m) {
        return vect(n*m);
    }

    public static float[] matrix(int n, int m, float c) {
        return vect(n*m, c);
    }

    public static float[] matrix(int n, int m, Random rnd) {
        return matrix(n, m, (i, j)->rnd.nextFloat());
    }

    public static float[] identity(int n) {
        return diagonal(n, n, 1);
    }

    public static float[] diagonal(int n, int m, float c) {
        float[] a = matrix(n, m);
        int l = Math.min(n, m);
        for (int r=0,i=0; r<l; ++r,i+=m+1)
            a[i] = c;
        return a;
    }

    public static float[] diagonal(int n, int m, FloatVector f) {
        float[] a = matrix(n, m);
        int l = Math.min(n, m);
        for (int r=0,i=0; r<l; ++r,i+=m+1)
            a[i] = f.eval(r);
        return a;
    }

    public static float[] matrix(int n, int m, FloatMatrix f) {
        float[] a = matrix(n, m);
        for (int r=0,i=0; r<n; ++r)
            for (int c=0; c<m; ++c, ++i)
                a[i] = f.eval(r, c);
        return a;
    }

    // ----------------------------------------------------------------------

    public static float[] ivect(int n) {
        return vect(n, (i) -> i);
    }

    public static float[] imatrix(int n, int m) {
        return matrix(n, m, (i,j) -> (i+1)*10 + (j+1));
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
        // int n = v.length;
        // float s = 0;
        // for (int i=0; i<n; ++i)
        //     s += u[i]*v[i];
        // return s;
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
    public static float dot(float[]A, int i, float[] B, int j, int k) {
        int m = B.length/k;
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
        int n = C.length/k;
        int m = B.length/k;
        for(int r=0,i=0; r<n;++r)
            for (int c=0; c<m; ++c,++i)
                ;//R[i] = A[i] + dot(C, )
    }
}
