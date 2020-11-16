package jext.dataframe.index;

import jext.dataframe.Index;

public class IntRange implements Index<Integer> {

    private int start;
    private int end;

    public IntRange() {
        start = 0;
        end = 1;
    }

    public IntRange(int n) {
        this(0, n);
    }

    public IntRange(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
