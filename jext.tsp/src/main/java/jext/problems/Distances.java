package jext.problems;

public interface Distances {

    // n of elements in the matrix
    int size();

    float distance(int i, int j);

    float[][] getMatrix();

}
