package ae.ac.ebtic.tools.vr.data;

import ae.ac.ebtic.tools.vr.util.LatLonUtils;
import jext.problems.Distances;

import java.util.List;

public class Distance implements Distances {

    public static Distance distanceMatrix(List<? extends LonLat> locations) {
        int n = locations.size();

        double[][] matrix = new double[n][n];

        for (int i=0; i<n; ++i) {
            LonLat ci = locations.get(i);
            for(int j=i+1; j<n; ++j) {
                LonLat cj = locations.get(j);
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

    @Override
    public int size() {
        return matrix.length;
    }

    @Override
    public double distance(int i, int j) {
        return matrix[i][j];
    }
}
