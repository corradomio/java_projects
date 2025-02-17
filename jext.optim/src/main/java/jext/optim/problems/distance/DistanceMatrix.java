package jext.optim.problems.distance;

import jext.optim.problems.Distances;

public class DistanceMatrix implements Distances {

    private double[][] distances;

    public DistanceMatrix(double[][] distances) {
        this.distances = distances;
    }

    @Override
    public int size() {
        return distances.length;
    }

    @Override
    public double distance(int i, int j) {
        return distances[i][j];
    }

    public double[][] getMatrix() {
        return distances;
    }

}
