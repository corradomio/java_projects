package jext.optim.domain.reals;

public class Range {

    public final float min;
    public final float max;

    // ----------------------------------------------------------------------

    public Range() {
        this.min = -1;
        this.max =  1;
    }

    public Range(double min, double max) {
        this.min = (float) min;
        this.max = (float) max;
    }

    // ----------------------------------------------------------------------

    public float clip(float x) {
        if (x < min) return min;
        if (x > max) return max;
        return x;
    }

    public boolean inRange(float x) {
        return min <= x && x <= max;
    }

    public boolean inRange(float x, float distance) {
        if (distance < 0) distance = -distance;
        return min <= (x-distance) && (x+distance) <= max;
    }
}
