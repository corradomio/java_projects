package jext.math.linear.sparse;

import jext.math.linear.sparse.util.Loc;

import java.util.Arrays;

public class Data extends Coords implements Iterable<Loc> {

    float[] data;

    public Data() {
        super();
        this.data = new float[]{0};
    }

    public Data(int[] rows, float[] data) {
        super(coordsOf(rows, new int[rows.length]), data.length);
        if (rows.length != data.length)
            throw new IllegalArgumentException("Different rows or data lengths");
        this.data = data;
    }

    public Data(int[] rows, int[] cols, float[] data) {
        super(coordsOf(rows, cols), rows.length);
        if (rows.length != data.length || cols.length != data.length)
            throw new IllegalArgumentException("Different rows or cols or data lengths");
        this.data = data;
    }

    private static long[] coordsOf(int[] rows, int[] cols) {
        int n = rows.length;
        long[] coords = new long[n];
        for (int i=0; i<n; ++i)
            coords[i] = locOf(rows[i], cols[i]);
        return coords;
    }
    private static long locOf(int i, int j) {
        return j*100_000_000L + i;
    }

    public int length() {
        return data.length;
    }

    public void set(int i, int j, float v) {
        long loc = locOf(i, j);
        set(loc, v);
    }

    public float get(int i, int j) {
        return get(locOf(i, j));
    }

    public float get(Loc l) { return get(l.loc); }
    public float get(long loc) {
        int at = locate(loc, false);
        return at != -1 ? data[at] : 0;
    }

    public void set(long loc, float v) {
        alloc(1);
        int at = locate(loc, true);
        coords[at] = loc;
        data[at] = v;
    }


    private int alloc(int slots) {
        int at = n;
        int len = data.length;
        if (n+slots <= len)
            return at;

        int nlen = len;
        while (n+slots > nlen)
            nlen = (nlen < 16) ? 16 : (nlen <= 256) ? nlen+nlen : nlen+256;

        coords = Arrays.copyOf(coords, nlen);
        data   = Arrays.copyOf(data,   nlen);

        return at;
    }

    protected void arraycopy(int  srcPos, int destPos, int length) {
        super.arraycopy(srcPos, destPos, length);
        System.arraycopy(data, srcPos, data, destPos + 1, length);
    }

    // int locate(long loc, boolean write) {
    //     int at = locate(loc);
    //     if (coords[at] == loc)
    //         return at;
    //     if (!write)
    //         return -1;
    //
    //     int rest = n - at;
    //     if (coords[at] < loc) {
    //         at += 1;
    //         rest -= 1;
    //     }
    //     if (rest > 0) {
    //         System.arraycopy(coords, at, coords, at + 1, rest);
    //         System.arraycopy(data,   at, data,   at + 1, rest);
    //     }
    //     n += 1;
    //     return at;
    // }
    //
    // private int locate(long l) {
    //     int b = 0;
    //     int e = n-1;
    //     int m;
    //
    //     while (b < e) {
    //         m = (b+e)/2;
    //         if (l < coords[m])
    //             e = m-1;
    //         else if (l > coords[m])
    //             b = m+1;
    //         else
    //             return m;
    //     }
    //     m = (b+e)/2;
    //     return m;
    // }
    //
}
