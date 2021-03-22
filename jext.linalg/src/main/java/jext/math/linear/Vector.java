package jext.math.linear;

public interface Vector {

    Type type();
    Dim dim();
    int length();

    // r = s*u + t*v
    Vector linear(float s, float t, Vector v);
}
