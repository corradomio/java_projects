package jext.dataframe.index;

import jext.dataframe.Index;

public class RangeIndex implements Index<Integer> {

    private int start;
    private int stop;
    private int step;

    public RangeIndex() {
        this(0, 1, 1);
    }

    public RangeIndex(int n) {
        this(0, n, 1);
    }

    public RangeIndex(int start, int stop) {
        this(start, stop, 1);
    }

    public RangeIndex(int start, int stop, int sep) {
        this.start = start;
        this.stop = stop;
        this.step = step;
    }

    @Override
    public String toString() {
        return String.format("RangeIndex[start=%d, stop=%d, step=%d", start, stop, step);
    }

}
