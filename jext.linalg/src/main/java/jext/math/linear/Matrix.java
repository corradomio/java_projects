package jext.math.linear;

public interface Matrix {
    Type type();
    Dim dim();
    int dim(int idim);
    int length();

    Matrix set(int i, int j, float v);
    float  get(int i, int j);

    // R = s*A + t*B
    Matrix linear(float s, float t, Matrix B);

    // r = s*A.u + t*v
    Vector linear(float s, Vector u, float t, Vector v);

    // R = s*C.A + t*B
    Matrix linear(float s, Matrix C, float t, Matrix B);
}
