package jext.dataframe.data;

import jext.dataframe.Data;

public class LongData implements Data<Long> {

    private long[] data;
    private int n;

    public LongData() {
        data = new long[0];
        n = 0;
    }

    public LongData(long[] data) {
        this.data = data;
        this.n = data.length;
    }
}
