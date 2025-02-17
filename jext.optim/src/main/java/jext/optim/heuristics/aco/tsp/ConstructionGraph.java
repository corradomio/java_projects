package jext.optim.heuristics.aco.tsp;

import jext.util.ArrayUtils;
import jext.util.MathUtils;

public class ConstructionGraph {

    private final int candidateSize;
    private final double[][] distanceMatrix;
    private final int[][] nearestNeighbours;

    public ConstructionGraph(double[][] distanceMatrix) {
        this.candidateSize = distanceMatrix.length;
        this.distanceMatrix = distanceMatrix;

        this.nearestNeighbours = new int[candidateSize][candidateSize];
    }

    public void initialize() {
        // initialize nearest neighbours
        for (int i=0; i<candidateSize; ++i)
            for (int j=0; j<candidateSize; ++j)
                nearestNeighbours[i][j] = j;

        // sort nearest neighbours
        for (int i=0; i<candidateSize; i++) {
            final int s = i;
            ArrayUtils.sort(nearestNeighbours[s], (a, b) ->
                MathUtils.sign(distanceMatrix[s][a] - distanceMatrix[s][b])
            );
        }
    }

    public double getDistance(int i, int j) {
        return distanceMatrix[i][j];
    }

    public int[] getNearestNeighbours(int i) {
        return nearestNeighbours[i];
    }
}
