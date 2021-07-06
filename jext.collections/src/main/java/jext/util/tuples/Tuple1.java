package jext.util.tuples;

import java.util.Objects;

public class Tuple1<T1> {

    public static <T1> Tuple1<T1> of(T1 v1) {
        return new Tuple1<>(v1);
    }

    private T1 value;

    public Tuple1(T1 v1) {
        value = v1;
    }

    public T1 get1() { return value; }

    // ----------------------------------------------------------------------
    // Overrides
    // ----------------------------------------------------------------------

    @Override
    public int hashCode() {
        return Objects.hash(get1());
    }

    @Override
    public boolean equals(Object obj) {
        Tuple1<T1> that = (Tuple1<T1>) obj;
        return get1().equals(that.get1());
    }

    @Override
    public String toString() {
        return String.format("(%s)", get1());
    }
}
