package jext.util.tuples;

public class Tuple2<T1, T2> extends Tuple1<T1> {

    private T2 value;

    public Tuple2(T1 v1, T2 v2) {
        super(v1);
        value = v2;
    }

    public T2 get2() { return value; }
}
