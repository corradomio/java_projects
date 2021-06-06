package jext.math.linear.dense;

public class Linear {

    // {0,...}
    public static float[] zeros(int n) {
        return new float[n];
    }

    // {1,...}
    public static float[] ones(int n) {
        float[] v = new float[n];
        for (int i=0; i<n; ++i)
            v[i] = 1.f;
        return v;
    }

    public static float[] zeros(int n, int m) {
        return zeros(n*m);
    }

    // {{1,...},{0,1,...},...}
    public static float[] identity(int n, int m) {
        float[] mat = zeros(n*m);
        int k = Math.min(n, m);
        for (int i=0; i<k; ++i)
            mat[i*n+i] = 1;
        return mat;
    }

    // ----------------------------------------------------------------------
    // dot products
    // ----------------------------------------------------------------------

    // s = u . v            (n).(n)
    public static float dot(float[] u, float[] v) {
        return dot(u, 0, v);
    }

    // s = u . v
    // s = A[i:] . v        (n,m).(m)
    public static float dot(float[] A, int i, float[] v) {
        // i: row
        int m = v.length;
        float s = 0;
        i *= m;
        for (int j=0; j<m; ++i,++j)
            s += A[i]*v[j];
        return s;
    }

    // s = A[i:] . B[:j]    (n,k) (k,m)
    public static float dot(float[] A, int i, float[] B, int j, int k) {
        // i: row
        // j: column
        int m = B.length/k;
        float s = 0;
        i *= k;
        for (int l=0; l<k; ++l,++i,j+=m)
            s += A[i]*B[j];
        return s;
    }

    // r = A . v            (n,m).(m)
    public static void dot(float[] r, float[] A, float[] v) {
        int m = v.length;
        int n = A.length/m;
        for (int l=0,i=0; l<n; ++l, ++i)
            r[l] = dot(A,i,v);
    }

    // R = A . B            (n,k).(k,m)
    public static void dot(float[] R, float[] A, float[] B, int k) {
        int n = A.length/k;
        int m = B.length/k;
        for (int r=0,i=0,l=0; r<n; ++r,++i)
            for (int j=0; j<m; ++l,++j)
                R[l] = dot(A, i, B, j, k);
    }

    // R = u outer v
    public static void outer(float[] R, float[] u, float[] v) {
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
    public static void linear(float[] r, float s, float[] u, float t, float[] v) {
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
    private static void zeros(float[] r) {
        int n = r.length;
        for (int i=0; i<n; ++i)
            r[i] = 0;
    }

    // r = u
    private static void assign(float[] r, float[] u) {
        int n = r.length;
        for (int i=0; i<n; ++i)
            r[i] = u[i];
    }

    // r = u + v
    private static void lin(float[] r, float[] u, float[] v) {
        int n = r.length;
        for (int i=0; i<n; ++i)
            r[i] = u[i] + v[i];
    }

    // r = s*u
    private static void lin(float[] r, float s, float[] u) {
        int n = r.length;
        for (int i=0; i<n; ++i)
            r[i] = s*u[i];
    }

    // r = u + t*v
    private static void lin(float[] r, float[] u, float t, float[] v) {
        int n = r.length;
        for (int i=0; i<n; ++i)
            r[i] = u[i] + t*v[i];
    }

    // r = s*u + t*v
    private static void lin(float[] r, float s, float[] u, float t, float[] v) {
        int n = r.length;
        for (int i=0; i<n; ++i)
            r[i] = s*u[i] + t*v[i];
    }

    // ----------------------------------------------------------------------

    // r = s*A.u + t*v
    public static void linear(float[] r, float s, float[] A, float[] u, float t, float[] v) {
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
    public static void linear(float[] R, float s, float[] A, float[] B, float t, float[] C, int k) {
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

}
