package jext.math.linear;

public interface BoolMatrix extends Matrix {

    // M[i,j] = v
    // cv = M[i,j]
    BoolMatrix set(int i, int j, boolean v);
    boolean    get(int i, int j);

}
