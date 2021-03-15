package jext.dataframe.data;

import jext.dataframe.util.Arrays;

public class IntegerData implements jext.dataframe.IntegerData {

    private int[] data;

    // ----------------------------------------------------------------------

    public IntegerData(int[] data) {
        this.data = data;
    }

    // ----------------------------------------------------------------------

    @Override
    public int size() {
        return data.length;
    }

    @Override
    public Integer at(int iloc) {
        if (iloc == -1)
            return null;
        return data[iloc];
    }

    @Override
    public int get(int iloc) {
        return data[iloc];
    }

    // ----------------------------------------------------------------------

    @Override
    public String toString() {
        return String.format("array(%s)", Arrays.toString(data));
    }
}
