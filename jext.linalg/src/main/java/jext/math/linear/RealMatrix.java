package jext.math.linear;

public interface RealMatrix extends Matrix {

    // M[i,j] = v
    // cv = M[i,j]
    RealMatrix set(int i, int j, float v);
    float      get(int i, int j);

    // R = s*A + t*B
    RealMatrix linear(float s, float t, RealMatrix B);

    // r = s*A.u + t*v
    RealVector linear(float s, RealVector u, float t, RealVector v);

    // R = s*C.A + t*B
    RealMatrix linear(float s, RealMatrix C, float t, RealMatrix B);

    // R = A.B
    RealMatrix dot(RealMatrix B);
}
