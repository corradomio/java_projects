package jext.util.arrayfire;

import static jext.util.arrayfire.ArrayFire.check;

public abstract class Compare {

    public static ArrayFire less(ArrayFire a, ArrayFire b) {
        long[] out = new long[1];
        check(ArrayFire.lt(out, a.arr, b.arr, false));
        return new ArrayFire(out[0]);
    }

    public static ArrayFire greater(ArrayFire a, ArrayFire b) {
        long[] out = new long[1];
        check(ArrayFire.gt(out, a.arr, b.arr, false));
        return new ArrayFire(out[0]);
    }

    public static ArrayFire lessEqual(ArrayFire a, ArrayFire b) {
        long[] out = new long[1];
        check(ArrayFire.le(out, a.arr, b.arr, false));
        return new ArrayFire(out[0]);
    }

    public static ArrayFire greaterEqual(ArrayFire a, ArrayFire b) {
        long[] out = new long[1];
        check(ArrayFire.ge(out, a.arr, b.arr, false));
        return new ArrayFire(out[0]);
    }

    public static ArrayFire equal(ArrayFire a, ArrayFire b) {
        long[] out = new long[1];
        check(ArrayFire.eq(out, a.arr, b.arr, false));
        return new ArrayFire(out[0]);
    }

    public static ArrayFire notEqual(ArrayFire a, ArrayFire b) {
        long[] out = new long[1];
        check(ArrayFire.neq(out, a.arr, b.arr, false));
        return new ArrayFire(out[0]);
    }
}
