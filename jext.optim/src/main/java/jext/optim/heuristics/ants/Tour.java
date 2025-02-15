package jext.optim.heuristics.ants;

public class Tour {
    public final int[] tour;
    public final float length;

    public Tour(int[] tour, float length) {
        this.tour = tour;
        this.length = length;
    }

    public float getTourLength() {
        return this.length;
    }
}
