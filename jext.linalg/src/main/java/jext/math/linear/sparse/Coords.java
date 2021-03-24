package jext.math.linear.sparse;

import java.util.Iterator;

public class Coords implements Iterable<Loc> {

    public long[] coords;
    public int n;

    Coords() {
        this.coords = new long[1];
        this.n = 1;
    }

    Coords(Coords that) {
        this.n = that.n;
        this.coords = new long[n];
        System.arraycopy(that.coords, 0, this.coords, 0, this.n);
    }

    Coords(long[] coords, int n) {
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


    Coords union(Coords that) {
        Coords res = new Coords(this);
        for(Loc l : that)
            res.add(l);
        return res;
    }

    Coords intersection(Coords that) {
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

    Coords difference(Coords that) {
        Coords res = new Coords();
        for(Loc l : this)
            if (!that.contains(l))
                res.add(l);
        return res;
    }

    protected int locate(long loc, boolean write) {
        int at = locate(loc);
        if (coords[at] == loc)
            return at;
        if (!write)
            return -1;

        int rest = n - at;
        if (coords[at] < loc) {
            at += 1;
            rest -= 1;
        }
        if (rest > 0) {
            arraycopy(at, at + 1, rest);
        }
        n += 1;
        return at;
    }

    protected void arraycopy(int  srcPos, int destPos, int length) {
        System.arraycopy(coords, srcPos, coords, destPos + 1, length);
    }


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
}
