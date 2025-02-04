package org.hls.examples;

import java.util.List;

public class Distance {

    public static Distance distanceMatrix(List<Location> locations) {
        int u, v, n = locations.size();
        double dist;
        double[][] matrix = new double[n][n];
        Location li, lj;

        for (int i=0; i<n; ++i) {
            li = locations.get(i);
            u = li.id();

            for(int j=i+1; j<n; ++j) {
                lj = locations.get(j);
                v = lj.id();

                dist = li.distance(lj);

                matrix[u][v] = dist;
                matrix[v][u] = dist;
            }
        }

        return new Distance(matrix);
    }


    public final double[][] matrix;

    private Distance(double[][] matrix) {
        this.matrix = matrix;
    }
}
