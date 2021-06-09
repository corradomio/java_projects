package jext.math.linear.sparse;

import jext.util.Arrays;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Data extends Coords {

    private static final float[] NO_DATA = new float[0];

    float[] data;

    public Data() {
        super();
        data = NO_DATA;
    }

    public Data(int[] rows, float[] data) {
        super(rows);
        this.data = data;
    }

    public Data(int[] rows, int[] cols, float[] data) {
        super(rows, cols);
        this.data = data;
    }

    //

    float get(Loc l) {
        return get(l.r, l.c);
    }

    float get(int i, int j) {
        int at = find(i, j);
        if (at == -1)
            return 0;
        else
            return data[at];
    }

    // vector

    void add(int i, float v) {
        add(i, 0, v);
    }

    void set(Loc loc, float v) {
        set(loc.r, loc.c, v);
    }

    void set(int i, float v) {
        set(i, 0, v);
    }

    // matrix

    void add(int i, int j, float v) {
        if (v == 0)
            return;
        int at = alloc();
        rows[at] = i;
        cols[at] = j;
        data[at] = v;
    }

    void set(int i, int j, float v) {
        int at = find(i, j);
        if (at == -1)
            add(i, j, v);
        else
            data[at] = v;
    }

    private int find(int i, int j) {
        for (int at=0; at<n; ++at)
            if (rows[at] == i && cols[at] == j)
                return at;
        return -1;
    }

    protected void allocate() {
        super.allocate();
        data = Arrays.copyOf(data, n + 15);
    }

}
