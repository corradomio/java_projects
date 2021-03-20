package jext.math.linalg;

public class Linear {
    
    // r = s*u + t*v
    public static void linear(float[] r, float s, float[] u, float t, float[] v) {
        if (s == 0 && t == 0)
            zeros(r);
        else if (s == 1 && t == 0)
            assign(r, u);
        else if (s == 0 && t == 1)
            assign(r, v);
        else if (s == 1 && t == 1)
            lin(r, u, v);
        else if (s == 0)
            lin(r, t, v);
        else if (t == 0)
            lin(r, s, u);
        else if (s == 1)
            lin(r, u, t, v);
        else if (t == 1)
            lin(r, v, s, u);
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

    // R = s*A + t*B
    public static void linear(float[][] R, float s, float[][] A, float t, float[][] B) {
        if (s == 0 && t == 0)
            zeros(R);
        else if (s == 1 && t == 0)
            assign(R, A);
        else if (s == 0 && t == 1)
            assign(R, B);
        else if (s == 1 && t == 1)
            lin(R, A, B);
        else if (s == 0)
            lin(R, t, B);
        else if (t == 0)
            lin(R, s, A);
        else if (s == 1)
            lin(R, A, t, B);
        else if (t == 1)
            lin(R, B, s, A);
        else
            lin(R,s,A,t,B);
    }

    // R = 0
    private static void zeros(float[][] R) {
        int n = R.length;
        int m = n > 0 ? R[0].length : 0;
        for (int i=0; i<n; ++i)
            for (int j=0; j<m; ++j)
                R[i][j] = 0;
    }

    // R = A
    private static void assign(float[][] R, float[][] A) {
        int n = R.length;
        int m = n > 0 ? R[0].length : 0;
        for (int i=0; i<n; ++i)
            for (int j=0; j<m; ++j)
                R[i][j] = A[i][j];
    }

    // R = A + B
    private static void lin(float[][] R, float[][] A, float[][] B) {
        int n = R.length;
        int m = n > 0 ? R[0].length : 0;
        for (int i=0; i<n; ++i)
            for (int j=0; j<m; ++j)
                R[i][j] = A[i][j] + B[i][j];
    }

    // R = s*A
    private static void lin(float[][] R, float s, float[][] A) {
        int n = R.length;
        int m = n > 0 ? R[0].length : 0;
        for (int i=0; i<n; ++i)
            for (int j=0; j<m; ++j)
                R[i][j] = s*A[i][j];
    }

    // R = A + t*B
    private static void lin(float[][] R, float[][] A, float t, float[][] B) {
        int n = R.length;
        int m = n > 0 ? R[0].length : 0;
        for (int i=0; i<n; ++i)
            for (int j=0; j<m; ++j)
                R[i][j] = A[i][j] + t*B[i][j];
    }

    // R = s*A + t*B
    private static void lin(float[][] R, float s, float[][] A, float t, float[][] B) {
        int n = R.length;
        int m = n > 0 ? R[0].length : 0;
        for (int i=0; i<n; ++i)
            for (int j=0; j<m; ++j)
                R[i][j] = s*A[i][j] + t*B[i][j];
    }

    // ----------------------------------------------------------------------

    // R = s*u + t*B
    public static void linear(float[][] R, float s, float[] u, float t, float[][] B) {
        if (s == 0 && t == 0)
            zeros(R);
        else if (s == 1 && t == 0)
            assign(R, u);
        else if (s == 0 && t == 1)
            assign(R, B);
        else if (s == 1 && t == 1)
            lin(R, u, B);
        else if (s == 0)
            lin(R, t, B);
        else if (t == 0)
            lin(R, s, u);
        else if (s == 1)
            lin(R, 1, u, t, B);
        else if (t == 1)
            lin(R, s, u, 1, B);
        else
            lin(R,s,u,t,B);
    }
    
    private static void assign(float[][] R, float[] u) {
        int n = R.length;
        for (int i=0; i<n; ++i)
            R[i][i] = u[i];
    }

    private static void lin(float[][] R, float s, float[] u) {
        int n = R.length;
        for (int i=0; i<n; ++i)
            R[i][i] = s*u[i];
    }

    private static void lin(float[][] R, float[] u, float[][] B) {
        int n = R.length;
        int m = n > 0 ? R[0].length : 0;
        for (int i=0; i<n; ++i)
            for (int j=0; j<m; ++j)
                if (i == j)
                    R[i][j] = u[i] + B[i][j];
                else
                    R[i][j] = B[i][j];
    }

    private static void lin(float[][] R, float s, float[] u, float t, float[][] B) {
        int n = R.length;
        int m = n > 0 ? R[0].length : 0;
        for (int i=0; i<n; ++i)
            for (int j=0; j<m; ++j)
                if (i == j)
                    R[i][j] = s*u[i] + t*B[i][j];
                else
                    R[i][j] = t*B[i][j];
    }

}
