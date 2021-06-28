package jext.util.tuples;

public class Tuple3<T1, T2, T3> extends Tuple2<T1, T2> {

    private T3 value;

    public Tuple3(T1 v1, T2 v2, T3 v3) {
        super(v1, v2);
        value = v3;
    }

    public T3 get3() { return value; }
}
