package ae.ac.ebtic.tools.vr.data;

import ae.ac.ebtic.tools.vr.util.LatLonUtils;
import jext.optim.problems.Distances;

import java.util.List;

public class Distance implements Distances {

    public static Distance distanceMatrix(List<? extends LonLat> locations) {
        int n = locations.size();

        float[][] matrix = new float[n][n];

        for (int i=0; i<n; ++i) {
            LonLat ci = locations.get(i);
            for(int j=i+1; j<n; ++j) {
                LonLat cj = locations.get(j);
                float lon1 = ci.getLongitude();
                float lat1 = ci.getLatitude();
                float lon2 = cj.getLongitude();
                float lat2 = cj.getLatitude();
                float dist = LatLonUtils.distance(lat1, lon1, lat2, lon2);

                matrix[i][j] = dist;
                matrix[j][i] = dist;
            }
        }

        return new Distance(matrix);
    }


    private final float[][] matrix;

    private Distance(float[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public int size() {
        return matrix.length;
    }

    @Override
    public float[][] getMatrix() {
        return matrix;
    }

    @Override
    public float distance(int i, int j) {
        return matrix[i][j];
    }
}
