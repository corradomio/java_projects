package jext.util.tuples;

public class Tuple4<T1, T2, T3, T4> extends Tuple3<T1, T2, T3> {

    private T4 value;

    public Tuple4(T1 v1, T2 v2, T3 v3, T4 v4) {
        super(v1, v2, v3);
        value = v4;
    }

    public T4 get4()  { return value; }
}
