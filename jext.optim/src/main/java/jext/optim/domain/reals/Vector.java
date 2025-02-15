package jext.optim.domain.reals;

import java.util.Arrays;

public class Vector implements Cloneable{

    private final Range range;
    private final float[] data;

    // ----------------------------------------------------------------------

    public Vector(int length, Range range) {
        this.data = new float[length];
        this.range = range;
    }

    Vector(float[] data, Range range) {
        this.data = data;
        this.range = range;
    }

    // ----------------------------------------------------------------------

    public Range range() {
        return range;
    }

    public int length() {
        return data.length;
    }

    public float[] data() {
        return data;
    }

    // ----------------------------------------------------------------------

    public Vector linear(Vector that, float alpha) {
        int n = data.length;

        float[] result = new float[n];
        for (int i = 0; i < n; i++)
            result[i] = range.clip(alpha * this.data[i] + (1-alpha) * that.data[i]);

        return new Vector(result, range);
    }

    public void linear(int i, Vector that, float alpha) {
        data[i] = range.clip(alpha * this.data[i] + (1-alpha) * that.data[i]);
    }

    public float get(int index) {
        return data[index];
    }

    public void set(int index, float value) {
        data[index] = range.clip(value);
    }

    public void set(int start, Vector that, int length) {
        System.arraycopy(that.data, start, this.data, start, length);
    }

    public void add(int index, float step) {
        data[index] = range.clip(data[index] + step);
    }

    // ----------------------------------------------------------------------

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }

    @Override
    public boolean equals(Object obj) {
        Vector that = (Vector) obj;
        return Arrays.equals(data, that.data);
    }

    @Override
    public Vector clone() {
        float[] data = Arrays.copyOf(this.data, this.data.length);
        return new Vector(data, range);
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}
