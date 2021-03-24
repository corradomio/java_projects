package jext.math.linear.sparse;

import java.util.Arrays;

public class Data {

    int n;
    // int[] rows; // vector index or row index
    // int[] cols; // column index or zeros
    long[] coords;
    float[] data;

    public Data() {
        this.n = 1;
        this.coords = new long[1];
        this.data = new float[]{0};
    }

    public Data(int[] rows, float[] data) {
        if (rows.length != data.length)
            throw new IllegalArgumentException("Different rows or data lengths");
        this.n = data.length;
        this.coords = coordsOf(rows, new int[n]);
        this.data = data;
    }

    public Data(int[] rows, int[] cols, float[] data) {
        if (rows.length != data.length || cols.length != data.length)
            throw new IllegalArgumentException("Different rows or cols or data lengths");
        this.n = rows.length;
        this.coords = coordsOf(rows, cols);
        this.data = data;
    }

    private static long[] coordsOf(int[] rows, int[] cols) {
        int n = rows.length;
        long[] coords = new long[n];
        for (int i=0; i<n; ++i)
            coords[i] = coordsOf(rows[i], cols[i]);
        return coords;
    }
    private static long coordsOf(int r, int c) {
        return c*100_000_000L + r;
    }

    public int length() {
        return data.length;
    }

    public void set(int r, int c, float v) {
        alloc(1);
        long loc = coordsOf(r, c);
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
            nlen = (nlen < 16) ? 16 : (nlen <= 256) ? nlen+nlen : nlen + 256;

        coords = Arrays.copyOf(coords, nlen);
        data   = Arrays.copyOf(data,   nlen);

        return at;
    }

    private int locate(long loc, boolean slot) {
        int at = locate(loc);
        if (coords[at] == loc || !slot)
            return at;
        int rest = n - at;
        if (coords[at] < loc) {
            at += 1;
            rest -= 1;
        }
        if (rest > 0) {
            System.arraycopy(coords, at, coords, at + 1, rest);
            System.arraycopy(data,   at, data,   at + 1, rest);
        }
        if (slot) n += 1;
        return at;
    }

    int locate(long l) {
        int b = 0;
        int e = n-1;
        int m = -1;

        while (b < e) {
            m = (b+e)/2;
            if (l < coords[m])
                e = m-1;
            else if (l > coords[m])
                b = m+1;
            else
                return m;
        }
        m = (b+e)/2;
        return m;
    }
}
