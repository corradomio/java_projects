package jext.math.linear.dense;

import jext.math.util.FloatBinaryOp;
import jext.math.util.FloatUnaryOp;
import jext.math.util.IntBinaryOp;
import jext.math.util.IntUnaryOp;

public class IntLinear {

    // {0,...}
    public static int[] zeros(int n) {
        return new int[n];
    }

    // {1,...}
    public static int[] ones(int n) {
        int[] v = new int[n];
        for (int i=0; i<n; ++i)
            v[i] = 1;
        return v;
    }

    public static int[] zeros(int n, int m) {
        return zeros(n*m);
    }

    // {{1,...},{0,1,...},...}
    public static int[] identity(int n, int m) {
        int[] mat = zeros(n*m);
        int k = Math.min(n, m);
        for (int i=0; i<k; ++i)
            mat[i*n+i] = 1;
        return mat;
    }

    // ----------------------------------------------------------------------
    // dot products
    // ----------------------------------------------------------------------

    // s = u . v            (n).(n)
    public static int dot(int[] u, int[] v) {
        return dot(u, 0, v);
    }

    // s = u . v
    // s = A[i:] . v        (n,m).(m)
    public static int dot(int[] A, int i, int[] v) {
        // i: row
        int m = v.length;
        int s = 0;
        i *= m;
        for (int j=0; j<m; ++i,++j)
            s += A[i]*v[j];
        return s;
    }

    // s = A[i:] . B[:j]    (n,k) (k,m)
    public static int dot(int[] A, int i, int[] B, int j, int k) {
        // i: row
        // j: column
        int m = B.length/k;
        int s = 0;
        i *= k;
        for (int l=0; l<k; ++l,++i,j+=m)
            s += A[i]*B[j];
        return s;
    }

    // r = A . v            (n,m).(m)
    public static void dot(int[] r, int[] A, int[] v) {
        int m = v.length;
        int n = A.length/m;
        for (int l=0,i=0; l<n; ++l, ++i)
            r[l] = dot(A,i,v);
    }

    // R = A . B            (n,k).(k,m)
    public static void dot(int[] R, int[] A, int[] B, int k) {
        int n = A.length/k;
        int m = B.length/k;
        for (int r=0,i=0,l=0; r<n; ++r,++i)
            for (int j=0; j<m; ++l,++j)
                R[l] = dot(A, i, B, j, k);
    }

    // R = u outer v
    public static void outer(int[] R, int[] u, int[] v) {
        int m = u.length;
        int n = v.length;
        int k=0;
        for (int i=0; i<m; ++i)
            for (int j=0; j<n; ++j)
                R[k++] = u[i]*v[j];
    }

    // ----------------------------------------------------------------------
    // Linear combinations
    // ----------------------------------------------------------------------

    // r = s*u + t*v
    // R = s*A + t*B
    public static void linear(int[] r, int s, int[] u, int t, int[] v) {
        // (0,0)    r = 0
        if (s == 0 && t == 0)
            zeros(r);
            // (1,0)    r = u
        else if (s == 1 && t == 0)
            assign(r, u);
            // (0,1)    r = v
        else if (s == 0 && t == 1)
            assign(r, v);
            // (1,1)    r = u + v
        else if (s == 1 && t == 1)
            lin(r, u, v);
            // (0,t)    r = t*v
        else if (s == 0)
            lin(r, t, v);
            // (s,0)    r = s*u
        else if (t == 0)
            lin(r, s, u);
            // (1,t)    r = u + t*v
        else if (s == 1)
            lin(r, u, t, v);
            // (s,1)    r = s*u + t
        else if (t == 1)
            lin(r, v, s, u);
            // (s,t)    r = s*u + t*v
        else
            lin(r,s,u,t,v);
    }

    // r = 0
    private static void zeros(int[] r) {
        int n = r.length;
        for (int i=0; i<n; ++i)
            r[i] = 0;
    }

    // r = u
    private static void assign(int[] r, int[] u) {
        int n = r.length;
        for (int i=0; i<n; ++i)
            r[i] = u[i];
    }

    // r = u + v
    private static void lin(int[] r, int[] u, int[] v) {
        int n = r.length;
        for (int i=0; i<n; ++i)
            r[i] = u[i] + v[i];
    }

    // r = s*u
    private static void lin(int[] r, int s, int[] u) {
        int n = r.length;
        for (int i=0; i<n; ++i)
            r[i] = s*u[i];
    }

    // r = u + t*v
    private static void lin(int[] r, int[] u, int t, int[] v) {
        int n = r.length;
        for (int i=0; i<n; ++i)
            r[i] = u[i] + t*v[i];
    }

    // r = s*u + t*v
    private static void lin(int[] r, int s, int[] u, int t, int[] v) {
        int n = r.length;
        for (int i=0; i<n; ++i)
            r[i] = s*u[i] + t*v[i];
    }

    // ----------------------------------------------------------------------

    // r = s*A.u + t*v
    public static void linear(int[] r, int s, int[] A, int[] u, int t, int[] v) {
        if (s == 1 && t == 0) {
            dot(r, A, u);
        }
        else {
            // r = A.u
            dot(r, A, u);
            // r = s*r + t*v
            linear(r, s, r, t, v);
        }
    }

    // R = s*C.A + t*B
    public static void linear(int[] R, int s, int[] A, int[] B, int t, int[] C, int k) {
        if (s == 1 && t == 0) {
            dot(R, A, B, k);
        }
        else {
            // R = A.B
            dot(R, A, B, k);
            // R = s*R + t*C
            linear(R, s, R, t, C);
        }
    }

    // ----------------------------------------------------------------------
    // Elementwise
    // ----------------------------------------------------------------------

    public static void apply(int[] R, IntUnaryOp f, int[] A) {
        int n = R.length;
        for (int i=0; i<n; ++i)
            R[i] = f.apply(A[i]);
    }

    public static void apply(int[] R, IntBinaryOp f, int[] A, int[] B) {
        int n = R.length;
        for (int i=0; i<n; ++i)
            R[i] = f.apply(A[i], B[i]);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
