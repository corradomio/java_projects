package jext.dataframe.index;

import jext.dataframe.Index;

public class IntegerIndex implements Index<Integer> {

    private int[] index;
    private int n;

    public IntegerIndex() {
        index = new int[0];
        n = 0;
    }

    public IntegerIndex(int[] index) {
        this.index = index;
        this.n = index.length;
    }

    public void add(int idx) {
        if (n == this.index.length)
            realloc();
        this.index[n++] = idx;
    }

    private void realloc() {
        int c = index.length + 1024;
    }
}
