package jext.math.linear;

public interface RealVector extends Vector {

    // a[i] = v
    // v = a[i]
    RealVector set(int i, float v);
    float      get(int i);

    // v = |a|
    float norm();

    // r = u.v
    float  dot(RealVector v);

    // r = s*u + t*v
    RealVector linear(float s, float t, RealVector v);

}
