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

    public Vector linear(Vector that, double alpha) {
        int n = data.length;

        double[] result = new double[n];
        for (int i = 0; i < n; i++)
            result[i] = range.clip(alpha * this.data[i] + (1-alpha) * that.data[i]);

        return new Vector(result, range);
    }

    public void linear(int i, Vector that, double alpha) {
        data[i] = range.clip(alpha * this.data[i] + (1-alpha) * that.data[i]);
    }

    public double get(int index) {
        return data[index];
    }

    public void set(int index, double value) {
        data[index] = range.clip(value);
    }

    public void set(int start, Vector that, int length) {
        System.arraycopy(that.data, start, this.data, start, length);
    }

    public void add(int index, double step) {
        data[index] = range.clip(data[index] + step);
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
