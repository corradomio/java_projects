package jext.optim.heuristics.ants;

import java.util.Arrays;

public class NearestNeighborhood {

    private final float[][] distances;
    private final int n;

    NearestNeighborhood(float[][] distances) {
        this.distances = distances;
        this.n = distances.length;
    }

    float findNearestNeighborhoodDistance() {
        int[] tour = new int[n];
        boolean[] visited = new boolean[n];
        float minDistance = Float.MAX_VALUE;

        for (int i=0; i<n; i++) {
            findTour(tour, visited, i);
            float dist = tourDistance(tour);
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
            float dist = Float.MAX_VALUE;
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

    private float tourDistance(int[] tour) {
        float dist = distances[tour[n-1]][tour[0]];
        for(int i=1; i<n; ++i)
            dist += distances[tour[i-1]][tour[i]];

        return dist;
    }
}
