package jext.math.linear;

public interface Vector {

    Type type();
    Dim  dim();
    int  dim(int idim);
    int  length();

    Vector set(int i, float v);
    float  get(int i);

    float norm();
    // versor
    Vector versor();

    // r = s*u + t*v
    Vector linear(float s, float t, Vector v);
    float  dot(Vector v);
    Matrix outer(Vector v);
    Matrix diag();
}
