package jext.dataframe.data;

import jext.dataframe.Data;

public class IntegerData implements Data<Integer> {

    private int[] data;
    private int n;

    public IntegerData() {
        data = new int[0];
        n = 0;
    }

    public IntegerData(int[] data) {
        this.data = data;
        this.n = data.length;
    }
}
