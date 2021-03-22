package jext.math.linear;

public interface Matrix {
    Type type();
    Dim dim();
    int length();

    // R = s*A + t*B
    Matrix linear(float s, float t, Matrix B);

    // r = s*u + t*A.v
    Vector linear(float s, Vector u, float t, Vector v);
}
