package jext.math.linear.sparse;

import jext.math.linear.sparse.util.Loc;
import jext.util.Arrays;

import java.util.Iterator;

public class Coords implements Iterable<Loc> {
    // (i,j) -> j*FACTOR + i

    public int n;
    public long[] coords;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public Coords() {
        this(0);
    }

    public Coords(int len) {
        len = Arrays.lengthOf(len);
        this.coords = new long[len];
        this.n = 0;
    }

    public Coords(Coords that) {
        this(Arrays.copyOf(that.coords, that.n), that.n);
    }

    public Coords(long[] coords) {
        this(coords, coords.length);
    }

    public Coords(long[] coords, int n) {
        this.coords = coords;
        this.n = n;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private static int  BITS = 27;
    private static long MASK = (1L << BITS) - 1L; // 2^27-1

    protected static long locOf(int i, int j) {
        return ((long) j << BITS) + i;
    }

    protected int rowOf(long loc) {
        return (int)(loc & MASK);
    }

    protected int colOf(long loc) {
        return (int)(loc >> BITS);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    boolean contains(long loc) {
        int at = locate(loc, false);
        return at != -1;
    }
    boolean contains(Loc l) { return contains(l.loc); }

    int add(long loc) {
        int at = locate(loc, true);
        coords[at] = loc;
        return at;
    }
    int add(Loc l) { return add (l.loc); }

    // ----------------------------------------------------------------------

    public Coords union(Coords that) {
        Coords res = new Coords(Arrays.lengthSum(this.n, that.n));
        res.n = Arrays.union(res.coords, this.coords, this.n, that.coords, that.n);
        return res;
    }

    public Coords intersection(Coords that) {
        Coords res = new Coords(Arrays.lengthMin(this.n, that.n));
        res.n = Arrays.intersection(res.coords, this.coords, this.n, that.coords, that.n);
        return res;
    }

    // ----------------------------------------------------------------------

    protected void checkspace() {
        int nlen = Arrays.lengthOf(n+1);
        allocate(nlen);
    }

    protected int locate(long loc, boolean write) {
        int at = locate(loc);
        if (at < n && coords[at] == loc)
            return at;
        if (!write)
            return -1;

        checkspace();

        int rest = n - at;
        if (rest > 0) {
            move(at, at + 1, rest);
        }
        n += 1;
        return at;
    }

    protected void allocate(int nlen) {
        if (coords.length < nlen)
            coords = Arrays.copyOf(coords, nlen);
    }

    protected void move(int  srcPos, int destPos, int length) {
        Arrays.move(coords, srcPos, destPos, length);
    }

    // coords[at] >= loc OR at == n
    private int locate(long loc) {
        int b = 0;
        int e = n-1;
        int m;

        while (b < e) {
            m = (b+e)/2;
            if (loc < coords[m])
                e = m-1;
            else if (loc > coords[m])
                b = m+1;
            else
                return m;
        }
        m = (b+e)/2;
        if (m < n && coords[m] < loc) m++;
        return m;
    }

    // ----------------------------------------------------------------------

    protected class LocIt implements Iterator<Loc>, Iterable<Loc> {
        protected final Loc l = new Loc();
        protected int at;

        @Override
        public boolean hasNext() {
            return at < n;
        }

        @Override
        public Loc next() {
            l.loc = coords[at++];
            return l;
        }

        @Override
        public Iterator<Loc> iterator() {
            return this;
        }
    }

    @Override
    public Iterator<Loc> iterator() {
        return new LocIt();
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------
    // vector:  (i,0)
    // matrix:  (i,j)
    // coords:  (i,j) -> loc = j*100_000_000L + i
    //

    private class RowIt extends LocIt implements Iterable<Loc> {
        private int row;

        RowIt(int row) {
            this.row = row;
            find();
        }

        @Override
        public Loc next() {
            l.loc = coords[at++];
            find();
            return l;
        }

        private void find() {
            while (at<n)
                if (rowOf(coords[at]) != row)
                    ++at;
                else break;
        }

        @Override
        public Iterator<Loc> iterator() {
            return this;
        }
    }

    private class ColIt extends LocIt {
        private int col;

        ColIt(int col) {
            this.col = col;
            find();
        }

        @Override
        public Loc next() {
            l.loc = coords[at++];
            find();
            return l;
        }

        private void find() {
            while (at<n)
                if (colOf(coords[at]) != col)
                    ++at;
                else break;
        }
    }

    public LocIt row(int i) {
        return new RowIt(i);
    }

    public LocIt col(int j) {
        return new ColIt(j);
    }

    // ----------------------------------------------------------------------

    @Override
    public boolean equals(Object obj) {
        Coords that = (Coords) obj;
        return this.n == that.n && Arrays.equals(this.coords, that.coords,n);
    }

    @Override
    public String toString() {
        return ToString.toString(coords, n);
    }

}
