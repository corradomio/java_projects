package jext.math.linear;

public interface IntMatrix extends Matrix {

    // M[i,j] = v
    // cv = M[i,j]
    IntMatrix set(int i, int j, int v);
    int       get(int i, int j);

    // R = s*A + t*B
    IntMatrix linear(int s, int t, IntMatrix B);

    // r = s*A.u + t*v
    IntVector linear(int s, IntVector u, int t, IntVector v);

    // R = s*C.A + t*B
    IntMatrix linear(int s, IntMatrix C, int t, IntMatrix B);
}
