package jext.util.tuples;

import java.util.Objects;

public class Tuple4<T1, T2, T3, T4> extends Tuple3<T1, T2, T3> {

    public static <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> of(T1 v1, T2 v2, T3 v3, T4 v4) {
        return new Tuple4<>(v1, v2, v3, v4);
    }

    private T4 value;

    public Tuple4(T1 v1, T2 v2, T3 v3, T4 v4) {
        super(v1, v2, v3);
        value = v4;
    }

    public T4 get4()  { return value; }

    // ----------------------------------------------------------------------
    // Overrides
    // ----------------------------------------------------------------------

    @Override
    public int hashCode() {
        return Objects.hash(get1(), get2(), get3(), get4());
    }

    @Override
    public boolean equals(Object obj) {
        Tuple4<T1, T2, T3, T4> that = (Tuple4<T1, T2, T3, T4>) obj;
        return super.equals(obj) && get4().equals(that.get4());
    }
}
