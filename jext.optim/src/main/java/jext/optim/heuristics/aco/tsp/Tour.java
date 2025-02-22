package jext.optim.heuristics.aco.tsp;

import jext.optim.domain.Solution;
import jext.util.ArrayUtils;

import java.util.Arrays;

public class Tour implements Solution<int[]> {
    private final int[] tour;
    private final double length;

    public Tour(int[] tour, double length) {
        this.length = length;
        int pos = ArrayUtils.find(tour, 0);
        this.tour = ArrayUtils.rotate(tour, -pos);
    }

    @Override
    public int[] candidate() {
        return tour;
    }

    @Override
    public double fitness() {
        return this.length;
    }

    @Override
    public String toString() {
        return String.format("%s: %f", Arrays.toString(tour), length);
    }
}
