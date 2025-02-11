package jext.optim.heuristics.genetics.domain.reals;

import java.util.Arrays;

public class Vector implements Cloneable{

    private final Range[] ranges;
    private final double[] data;

    public Vector(int length, Range[] ranges) {
        this.data = new double[length];
        this.ranges = ranges;
    }

    Vector(double[] data, Range[] ranges) {
        this.data = data;
        this.ranges = ranges;
    }


    public Vector(double[] data, final Vector template) {
        this.data = data;
        this.ranges = template.ranges;
    }

    public Range[] ranges() {
        return ranges;
    }

    public int length() {
        return data.length;
    }

    public double[] data() {
        return data;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }

    @Override
    public Vector clone() {
        double[] data = Arrays.copyOf(this.data, this.data.length);
        return new Vector(data, ranges);
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}
