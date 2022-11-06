package jext.graph;

public class Limit {

    public static Limit of(long limit) {
        return new Limit(0, limit);
    }

    public static Limit of(long start, long limit) {
        return new Limit(start, limit);
    }

    public long start;
    public long limit;

    public Limit(long s, long c) {
        this.start = s;
        this.limit = c;
    }

    public boolean isAll() {
        return limit == 0;
    }

    public Limit page(long p) {
        start = p*limit;
        return this;
    }

    public Limit next() {
        start += limit;
        return this;
    }
}
