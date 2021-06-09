package jext.math.linear.sparse;

public class Loc {
    public int r, c;

    public Loc() { }
    public Loc(int r) {
        this.r = r;
    }
    public Loc(int r, int c) {
        this.r = r;
        this.c = c;
    }
    public Loc(Loc l) {
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
