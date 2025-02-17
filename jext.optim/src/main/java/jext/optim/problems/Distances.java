package jext.optim.problems;

public interface Distances {

    // n of elements in the matrix
    int size();

    double distance(int i, int j);

    // Not all Distances implementations are able to return the matrix
    double[][] getMatrix();

}
