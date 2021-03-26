package jext.math.linear.sparse;

import jext.math.linear.sparse.util.Loc;
import jext.util.Arrays;

public class Data extends Coords implements Iterable<Loc> {

    float[] data;

    public Data() {
        super();
        this.data = new float[0];
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

    public int length() {
        return data.length;
    }

    //
    // get & set
    //

    public float get(long loc) {
        int at = locate(loc, false);
        return at != -1 ? data[at] : 0;
    }
    public float get(Loc l) { return get(l.loc); }
    public float get(int i, int j) {
        return get(locOf(i, j));
    }

    public void set(long loc, float v) {
        alloc();
        int at = locate(loc, true);
        coords[at] = loc;
        data[at] = v;
    }
    public void set(Loc l, float v) { set(l.loc, v); }
    public void set(int i, int j, float v) { set(locOf(i, j), v); }

    // ----------------------------------------------------------------------

    protected void allocate(int nlen) {
        super.allocate(nlen);
        if (data.length < nlen)
            data = Arrays.copyOf(data, nlen);
    }

    protected void move(int  srcPos, int destPos, int length) {
        super.move(srcPos, destPos, length);
        Arrays.move(data, srcPos, destPos, length);
    }

    private int alloc() {
        int at = n;
        if (n+1 <= data.length)
            return at;

        int len = Arrays.lengthOf(n+1);
        coords = Arrays.copyOf(coords, len);
        data   = Arrays.copyOf(data,   len);

        return at;
    }

    // ----------------------------------------------------------------------

    @Override
    public boolean equals(Object obj) {
        Data that = (Data) obj;
        Coords coords = this.union(that);
        for (Loc l : coords)
            if (this.get(l) != that.get(l))
                return false;
        return true;
    }
}
