package jext.graph;

public class Limit {

    public long start = 0;
    public long count = 0;

    public Limit(long c) {
        count = c;
    }

    public Limit(long s, long c) {
        start = s;
        count = c;
    }

    public boolean isAll() { return count == 0; }

    public Limit start(long s) {
        start = s;
        return this;
    }

    public Limit page(long p) {
        start = p*count;
        return this;
    }

    public Limit next() {
        start += count;
        return this;
    }
}
