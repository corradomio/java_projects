package org.hls.example;

import org.hls.example.utils.LatLonUtils;

import java.util.List;

public class Distance {

    public static Distance distanceMatrix(List<? extends Coords> locations) {
        int n = locations.size();

        double[][] matrix = new double[n][n];

        for (int i=0; i<n; ++i) {
            Coords ci = locations.get(i);
            for(int j=i+1; j<n; ++j) {
                Coords cj = locations.get(j);
                double lon1 = ci.getLongitude();
                double lat1 = ci.getLatitude();
                double lon2 = cj.getLongitude();
                double lat2 = cj.getLatitude();
                double dist = LatLonUtils.distance(lat1, lon1, lat2, lon2);

                matrix[i][j] = dist;
                matrix[j][i] = dist;
            }
        }

        return new Distance(matrix);
    }


    public final double[][] matrix;

    private Distance(double[][] matrix) {
        this.matrix = matrix;
    }
}
