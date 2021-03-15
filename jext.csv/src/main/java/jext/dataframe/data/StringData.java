package jext.dataframe.data;

import jext.dataframe.util.Arrays;

public class StringData implements jext.dataframe.StringData {

    private String[] data;

    // ----------------------------------------------------------------------

    public StringData(String[] data) {
        this.data = data;
    }

    // ----------------------------------------------------------------------

    @Override
    public int size() {
        return data.length;
    }

    @Override
    public String at(int iloc) {
        if (iloc == -1)
            return null;
        return data[iloc];
    }

    @Override
    public String get(int iloc) {
        return data[iloc];
    }

    // ----------------------------------------------------------------------

    @Override
    public String toString() {
        return String.format("array(%s)", Arrays.toString(data));
    }
}
