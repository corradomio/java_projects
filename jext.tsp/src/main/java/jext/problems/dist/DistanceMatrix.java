package jext.problems.dist;

import jext.problems.Distances;

public class DistanceMatrix implements Distances {

    private float[][] distances;

    public DistanceMatrix(float[][] distances) {
        this.distances = distances;
    }

    @Override
    public int size() {
        return distances.length;
    }

    @Override
    public float distance(int i, int j) {
        return distances[i][j];
    }

    public float[][] getMatrix() {
        return distances;
    }

}
