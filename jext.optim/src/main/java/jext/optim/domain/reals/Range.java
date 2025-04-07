package jext.optim.domain.reals;

public class Range {

    public final double min;
    public final double max;

    // ----------------------------------------------------------------------

    public Range() {
        this.min = -1;
        this.max =  1;
    }

    public Range(double min, double max) {
        this.min = min;
        this.max = max;
    }

    // ----------------------------------------------------------------------

    public double clip(double x) {
        if (x < min) return min;
        if (x > max) return max;
        return x;
    }

    public boolean inRange(double x) {
        return min <= x && x <= max;
    }

    public boolean inRange(double x, double step) {
        if (step < 0) step = -step;
        return min <= (x-step) && (x+step) <= max;
    }
}
