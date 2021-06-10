package jext.math.linear.sparse;

import jext.util.Arrays;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class Coords implements Iterable<Loc> {

    private static final int[] NO_IDXS = new int[0];
    public int n;
    public int[] rows;
    public int[] cols;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public Coords() {
        this(NO_IDXS, NO_IDXS);
    }

    public Coords(int[] rows) {
        this(rows, new int[rows.length]);
    }

    public Coords(int[] rows, int[] cols) {
        this.n = rows.length;
        this.rows = rows;
        this.cols = cols;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public int length() {
        return n;
    }

    // ----------------------------------------------------------------------
    // Iterators
    // ----------------------------------------------------------------------

    private abstract class IterIt implements Iterable<Loc>, Iterator<Loc> {
        protected Loc l = new Loc();
        protected int at;

        IterIt() { }

        @Override
        public boolean hasNext() {
            return at < n;
        }

        @Override
        public Iterator<Loc> iterator() {
            return this;
        }

    }

    // ----------------------------------------------------------------------

    private class AllIt extends IterIt {

        @Override
        public Loc next() {
            l.r = rows[at];
            l.c = cols[at];
            ++at;
            return l;
        }
    }

    public Iterator<Loc> iterator() {
        return new AllIt();
    }

    // ----------------------------------------------------------------------

    private class RowsIt extends IterIt {
        private Loc t = new Loc();
        private Set<Loc> visited = new HashSet<>();

        @Override
        public Loc next() {
            l.r = rows[at];
            find();
            return l;
        }

        private void find() {
            visited.add(new Loc(l));
            while (at < n) {
                t.r = rows[at];
                if (visited.contains(t))
                    ++at;
                else break;
            }
        }
    }

    public Iterable<Loc> rows() {
        return new RowsIt();
    }

    // ----------------------------------------------------------------------

    private class ColsIt extends IterIt {
        private Loc t = new Loc();
        private Set<Loc> visited = new HashSet<>();

        @Override
        public Loc next() {
            l.c = cols[at];
            find();
            return l;
        }

        private void find() {
            visited.add(new Loc(l));
            while (at < n) {
                t.c = cols[at];
                if (visited.contains(t))
                    ++at;
                else break;
            }
        }
    }

    public Iterable<Loc> cols() {
        return new ColsIt();
    }

    // ----------------------------------------------------------------------

    private class ColRowsIt extends IterIt {

        ColRowsIt(int col) {
            l.c = col;
            while(at < n && cols[at] != l.c)
                ++at;
        }

        @Override
        public Loc next() {
            l.r = rows[at];
            find();
            return l;
        }

        private void find() {
            ++at;
            while (at < n && cols[at] != l.c)
                ++at;
        }
    }

    public Iterable<Loc> rows(int col) {
        return new ColRowsIt(col);
    }

    // ----------------------------------------------------------------------

    private class RowColsIt extends IterIt {

        RowColsIt(int row) {
            l.r = row;
            while(at < n && rows[at] != l.r)
                ++at;
        }

        @Override
        public Loc next() {
            l.c = cols[at];
            find();
            return l;
        }

        private void find() {
            ++at;
            while (at < n && rows[at] != l.r)
                ++at;
        }
    }

    public Iterable<Loc> cols(int row) {
        return new RowColsIt(row);
    }

    // ----------------------------------------------------------------------


    public Coords intersection(Coords that) {
        Coords u = new Coords();
        for (Loc l : this)
            if (-1 != that.find(l))
                u.add(l);
        return u;
    }


    public Coords union(Coords that) {
        Coords u = new Coords();
        for (Loc l : this)
            u.add(l);
        for (Loc l : that)
            if (-1 == this.find(l))
                u.add(l);
        return u;
    }

    private int find(Loc l) {
        for (int at=0; at<n; ++at)
            if (rows[at] == l.r && cols[at] == l.c)
                return at;
        return -1;
    }

    private void add(Loc l) {
        int at = alloc();
        rows[at] = l.r;
        cols[at] = l.c;
    }

    protected int alloc() {
        n += 1;
        if (n > rows.length) {
            allocate();
        }
        return n-1;
    }

    protected void allocate() {
        rows = Arrays.copyOf(rows, n + 15);
        cols = Arrays.copyOf(cols, n + 15);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public String toString() {
        return String.format("Coords[%d]", n);
    }

    @Override
    public int hashCode() {
        return Objects.hash(java.util.Arrays.hashCode(rows), java.util.Arrays.hashCode(cols));
    }

    @Override
    public boolean equals(Object obj) {
        Coords that = (Coords) obj;
        if (this.n != that.n)
            return false;
        return Arrays.equals(this.rows, that.rows, n)
            && Arrays.equals(this.cols, that.cols, n);
    }

}
