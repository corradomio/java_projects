package jext.math.linear.sparse;

import jext.math.linear.sparse.util.Loc;
import jext.util.Arrays;

import java.util.Iterator;

public class Coords implements Iterable<Loc> {

    public long[] coords;
    public int n;

    public Coords() {
        this.coords = new long[0];
        this.n = 0;
    }

    public Coords(Coords that) {
        this.n = that.n;
        this.coords = Arrays.copyOf(that.coords, that.n);
    }

    public Coords(long[] coords) {
        this(coords, coords.length);
    }

    public Coords(long[] coords, int n) {
        this.coords = coords;
        this.n = n;
    }

    boolean contains(long loc) {
        int at = locate(loc, false);
        return coords[at] == loc;
    }
    boolean contains(Loc l) { return contains(l.loc); }

    void add(long loc) {
        int at = locate(loc, true);
        coords[at] = loc;
    }
    void add(Loc l) { add (l.loc); }

    // ----------------------------------------------------------------------

    public Coords union(Coords that) {
        Coords res = new Coords();
        for(Loc l : this)
            res.add(l.loc);
        for(Loc l : that)
            res.add(l.loc);
        return res;
    }

    public Coords intersection(Coords that) {
        Coords self = this;
        Coords res = new Coords();
        if (self.n > that.n) {
            self = that;
            that = this;
        }

        for (Loc l : self)
            if (that.contains(l))
                res.add(l);
        return res;
    }

    public Coords difference(Coords that) {
        Coords res = new Coords();
        for(Loc l : this)
            if (!that.contains(l))
                res.add(l);
        return res;
    }

    // ----------------------------------------------------------------------

    protected void checkspace(int add) {
        if (n+add <  coords.length)
            return;

        int nlen = coords.length;;
        while (n+add > nlen)
            nlen = (nlen < 16) ? 16 : (nlen <= 256) ? nlen+nlen : nlen+256;

        allocate(nlen);
    }

    protected int locate(long loc, boolean write) {
        int at = locate(loc);
        if (at < n && coords[at] == loc)
            return at;
        if (!write)
            return -1;

        checkspace(1);

        int rest = n - at;
        if (rest > 0) {
            move(at, at + 1, rest);
        }
        n += 1;
        return at;
    }

    protected void allocate(int nlen) {
        coords = Arrays.copyOf(coords, nlen);
    }

    protected void move(int  srcPos, int destPos, int length) {
        System.arraycopy(coords, srcPos, coords, destPos, length);
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

    private class LocIterator implements Iterator<Loc> {
        private final Loc l = new Loc();
        private int at;

        @Override
        public boolean hasNext() {
            return at < n;
        }

        @Override
        public Loc next() {
            l.loc = coords[at++];
            return l;
        }
    }

    @Override
    public Iterator<Loc> iterator() {
        return new LocIterator();
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
