package jext.dataframe.index;

import java.util.Iterator;

public class RangeIndex implements jext.dataframe.IntegerIndex {

    private int start;
    private int stop;
    private int step;

    // ----------------------------------------------------------------------

    public RangeIndex() {
        this(0, 1, 1);
    }

    public RangeIndex(int n) {
        this(0, n, 1);
    }

    public RangeIndex(int start, int stop) {
        this(start, stop, 1);
    }

    public RangeIndex(int start, int stop, int step) {
        this.start = start;
        this.stop = stop;
        this.step = step;
    }

    // ----------------------------------------------------------------------

    @Override
    public int size() {
        return (stop - start)/step;
    }

    @Override
    public int at(Integer loc) {
        int iloc = loc;
        if (iloc < start || iloc >= stop)
            return -1;
        return (iloc - start)/step;
    }

    @Override
    public int get(int loc) {
        return (loc - start)/step;
    }

    // ----------------------------------------------------------------------

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int loc = start;

            @Override
            public boolean hasNext() {
                return loc < stop;
            }

            @Override
            public Integer next() {
                int loc = this.loc;
                this.loc += step;
                return loc;
            }
        };
    }

    // ----------------------------------------------------------------------

    @Override
    public String toString() {
        return String.format("RangeIndex[start=%d, stop=%d, step=%d]", start, stop, step);
    }

}
