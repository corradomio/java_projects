package jext.dataframe.data;

import jext.dataframe.util.Arrays;

public class DoubleData implements jext.dataframe.DoubleData {

    private double[] data;

    // ----------------------------------------------------------------------

    public DoubleData(double[] data) {
        this.data = data;
    }

    // ----------------------------------------------------------------------

    @Override
    public int size() {
        return data.length;
    }

    @Override
    public Double at(int iloc) {
        if (iloc == -1)
            return null;
        return data[iloc];
    }

    @Override
    public double get(int iloc) {
        return data[iloc];
    }

    // ----------------------------------------------------------------------

    @Override
    public String toString() {
        return String.format("array(%s)", Arrays.toString(data));
    }
}