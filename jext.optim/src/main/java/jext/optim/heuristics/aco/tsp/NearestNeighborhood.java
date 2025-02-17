package jext.optim.heuristics.aco.tsp;

import java.util.Arrays;

public class NearestNeighborhood {

    private final double[][] distances;
    private final int n;

    public NearestNeighborhood(double[][] distances) {
        this.distances = distances;
        this.n = distances.length;
    }

    public double findNearestNeighborhoodDistance() {
        int[] tour = new int[n];
        boolean[] visited = new boolean[n];
        double minDistance = Double.MAX_VALUE;

        for (int i=0; i<n; i++) {
            findTour(tour, visited, i);
            double dist = tourDistance(tour);
            if (dist < minDistance)
                minDistance = dist;
        }

        return minDistance;
    }

    private void findTour(int[] tour, boolean[] visited, int s) {
        Arrays.fill(visited, false);
        tour[0] = s;
        visited[s] = true;

        for(int i=1; i<n; ++i) {
            double dist = Double.MAX_VALUE;
            for(int j=0; j<n; ++j) {
                if (!visited[j] && distances[s][j] < dist) {
                    dist = distances[s][j];
                    s = j;
                }
            }
            tour[i] = s;
            visited[s] = true;
        }
    }

    private double tourDistance(int[] tour) {
        double dist = distances[tour[n-1]][tour[0]];
        for(int i=1; i<n; ++i)
            dist += distances[tour[i-1]][tour[i]];

        return dist;
    }
}
