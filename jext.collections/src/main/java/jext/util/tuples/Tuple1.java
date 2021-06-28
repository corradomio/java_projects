package jext.util.tuples;

public class Tuple1<T1> {

    private T1 value;

    public Tuple1(T1 v1) {
        value = v1;
    }

    public T1 get1() { return value; }
}
