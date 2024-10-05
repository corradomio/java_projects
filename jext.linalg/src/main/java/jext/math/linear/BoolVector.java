package jext.math.linear;

public interface BoolVector extends Vector {

    // a[i] = v
    // v = a[i]
    BoolVector set(int i, boolean v);
    boolean    get(int i);
}
