package jext.math.linear;

public interface Matrix {
    Type type();
    Dim dim();
    int length();

    Matrix same();

    // R = s*A + t*B
    Matrix linear(float s, float t, Matrix B);

    // r = s*u + t*A.v
    Vector linear(float s, Vector u, float t, Vector v);
}
