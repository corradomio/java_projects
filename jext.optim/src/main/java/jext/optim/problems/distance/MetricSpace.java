package jext.optim.problems.distance;

import jext.optim.problems.Coords;
import jext.optim.problems.Distances;

import java.util.List;

public abstract class MetricSpace implements Distances {

    protected Coords[] coords;

    protected MetricSpace(Coords[] coords) {
        this.coords = coords;
    }

    public MetricSpace(List<? extends Coords> coords) {
        this.coords = coords.toArray(new Coords[0]);
    }

    public Coords get(int i) {
        return coords[i];
    }

    @Override
    public int size() {
        return coords.length;
    }

    @Override
    public double[][] getMatrix() {
        double[][] matrix = new double[coords.length][coords.length];
        for (int i = 0; i < coords.length; i++) {
            for (int j = 0; j < coords.length; j++) {
                matrix[i][j] = distance(coords[i], coords[j]);
            }
        }
        return matrix;
    }

    @Override
    public double distance(int i, int j) {
        return distance(coords[i], coords[j]);
    }

    public Coords[] getCoordinates() {
        return coords;
    }

    protected abstract double distance(Coords li, Coords lj);

}
