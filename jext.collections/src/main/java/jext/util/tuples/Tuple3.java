package jext.util.tuples;

import java.util.Objects;

public class Tuple3<T1, T2, T3> extends Tuple2<T1, T2> {

    public static <T1, T2, T3> Tuple3<T1, T2, T3> of(T1 v1, T2 v2, T3 v3) {
        return new Tuple3<>(v1, v2, v3);
    }

    private T3 value;

    public Tuple3(T1 v1, T2 v2, T3 v3) {
        super(v1, v2);
        value = v3;
    }

    public T3 get3() { return value; }

    // ----------------------------------------------------------------------
    // Overrides
    // ----------------------------------------------------------------------

    @Override
    public int hashCode() {
        return Objects.hash(get1(), get2(), get3());
    }

    @Override
    public boolean equals(Object obj) {
        Tuple3<T1, T2, T3> that = (Tuple3<T1, T2, T3>) obj;
        return super.equals(obj) && get3().equals(that.get3());
    }
}
