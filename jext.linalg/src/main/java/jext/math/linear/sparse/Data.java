package jext.math.linear.sparse;

import jext.util.Arrays;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SparseData {

    static class Loc {
        int r, c;

        Loc() { }
        Loc(int r) {
            this.r = r;
        }
        Loc(int r, int c) {
            this.r = r;
            this.c = c;
        }
        Loc(Loc l) {
            r = l.r;
            c = l.c;
        }

        @Override
        public boolean equals(Object obj) {
            Loc that = (Loc) obj;
            return this.r == that.r && this.c == that.c;
        }

        @Override
        public int hashCode() {
            if (c == 0)
                return r;
            else
                return c<<27 | r;
        }
    }

    private static final int[] NO_IDXS = new int[0];
    private static final float[] NO_DATA = new float[0];

    private int n;
    private int[] rows;
    private int[] cols;
    private float[] data;

    SparseData() {
        n = 0;
        rows = NO_IDXS;
        cols = NO_IDXS;
        data = NO_DATA;
    }

    SparseData(int[] rows, float[] data, int n) {
        this.n = n;
        this.rows = rows;
        this.cols = new int[rows.length];
        this.data = data;
    }

    SparseData(int[] rows, int[] cols, float[] data, int n) {
        this.n = n;
        this.rows = rows;
        this.cols = cols;
        this.data = data;
    }

    // vector

    void add(int i, float v) {
        add(i, 0, v);
    }

    void set(int i, float v) {
        set(i, 0, v);
    }

    // matrix

    void add(int i, int j, float v) {
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

    private int alloc() {
        n += 1;
        if (n<data.length)
            return n;

        rows = Arrays.copyOf(rows, n+15);
        cols = Arrays.copyOf(cols, n+15);
        data = Arrays.copyOf(data, n+15);
        return n-1;
    }

    // ------------------------------------------------

    private abstract class IterIt implements Iterable<Loc>, Iterator<Loc> {
        protected Loc l;
        protected int at;

        IterIt() {
            at = 0;
        }

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
            while (at < n && rows[at] != l.r)
                ++at;
        }
    }

    public Iterable<Loc> cols(int row) {
        return new RowColsIt(row);
    }

}
