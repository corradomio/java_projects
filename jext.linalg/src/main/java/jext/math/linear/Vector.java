package jext.math.linear;

public interface Vector {

    Type type();
    Dim dim();
    int length();

    Vector same();

    // r = s*u + t*v
    Vector linear(float s, float t, Vector v);
}
