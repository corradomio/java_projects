package jext.optim.heuristics.aco;

import jext.util.ArrayUtils;

public class Tour {
    public final int[] tour;
    public final float length;

    public Tour(int[] tour, float length) {
        this.length = length;
        int pos = ArrayUtils.find(tour, 0);
        this.tour = ArrayUtils.rotate(tour, -pos);
    }

    public float getTourLength() {
        return this.length;
    }
}
