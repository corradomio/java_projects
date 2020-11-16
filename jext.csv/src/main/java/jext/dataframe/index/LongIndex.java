package jext.dataframe.index;

import jext.dataframe.Index;

public class LongIndex implements Index<Long> {

    private long[] index;
    private int n;

    public LongIndex() {
        index = new long[0];
        n = 0;
    }

    public LongIndex(long[] index) {
        this.index = index;
        this.n = index.length;
    }
}
