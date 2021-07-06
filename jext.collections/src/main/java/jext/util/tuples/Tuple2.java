package jext.util.tuples;

import java.util.Objects;

public class Tuple2<T1, T2> extends Tuple1<T1> {

    public static <T1, T2> Tuple2<T1, T2> of(T1 v1, T2 v2) {
        return new Tuple2<>(v1, v2);
    }

    private T2 value;

    public Tuple2(T1 v1, T2 v2) {
        super(v1);
        value = v2;
    }

    public T2 get2() { return value; }

    // ----------------------------------------------------------------------
    // Overrides
    // ----------------------------------------------------------------------

    @Override
    public int hashCode() {
        return Objects.hash(get1(), get2());
    }

    @Override
    public boolean equals(Object obj) {
        Tuple2<T1, T2> that = (Tuple2<T1, T2>) obj;
        return super.equals(obj) && get2().equals(that.get2());
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", get1(), get2());
    }
}
