package jext.math.linalg;

public interface Vector {

    int len();
    Dim dim();

    float  at(int i);
    Vector at(int i, float v);
    Vector set(float v);
    float[] data();

    // r = t*u + s*v
    Vector linear(float s, float t, Vector v);

    float dot(Vector v);

    Vector hprod(Vector v);
}
