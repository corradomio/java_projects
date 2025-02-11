package jext.optim.heuristics.genetics.domain.reals;

import java.util.Arrays;

public class Vector implements Cloneable{

    private final Range range;
    private final double[] data;

    public Vector(int length, Range range) {
        this.data = new double[length];
        this.range = range;
    }

    Vector(double[] data, Range range) {
        this.data = data;
        this.range = range;
    }


    public Vector(double[] data, final Vector template) {
        this.data = data;
        this.range = template.range;
    }

    public Range range() {
        return range;
    }

    public int length() {
        return data.length;
    }

    public double[] data() {
        return data;
    }

    // ----------------------------------------------------------------------

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }

    @Override
    public Vector clone() {
        double[] data = Arrays.copyOf(this.data, this.data.length);
        return new Vector(data, range);
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}
