package jext.util.arrayfire;

import static jext.util.arrayfire.ArrayFire.check;

public abstract class Math {

    public static ArrayFire add(ArrayFire a, ArrayFire b) {
        long[] out = new long[1];
        check(ArrayFire.add(out, a.arr, b.arr, false));
        return new ArrayFire(out[0]);
    }

    public static ArrayFire sub(ArrayFire a, ArrayFire b) {
        long[] out = new long[1];
        check(ArrayFire.sub(out, a.arr, b.arr, false));
        return new ArrayFire(out[0]);
    }

    public static ArrayFire mul(ArrayFire a, ArrayFire b) {
        long[] out = new long[1];
        check(ArrayFire.sub(out, a.arr, b.arr, false));
        return new ArrayFire(out[0]);
    }

    public static ArrayFire div(ArrayFire a, ArrayFire b) {
        long[] out = new long[1];
        check(ArrayFire.sub(out, a.arr, b.arr, false));
        return new ArrayFire(out[0]);
    }
}
