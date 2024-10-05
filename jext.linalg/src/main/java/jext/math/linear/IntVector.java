package jext.math.linear;

public interface IntVector extends Vector {

    // a[i] = v
    // v = a[i]
    IntVector set(int i, int v);
    int       get(int i);

}
