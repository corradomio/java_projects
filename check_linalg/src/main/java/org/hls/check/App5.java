package org.hls.check;

import jext.math.util.Arrays;

public class App5 {

    public static void main(String[] args) {
        long[] a = new long[]{1,2,3,4,10,12,14};
        long[] b = new long[]{2,3,4,5,6,11,13};
        long[] r = new long[a.length+b.length];
        Arrays.zeros(r);
        Arrays.union(r, a,a.length,b,b.length);
        Arrays.zeros(r);
        Arrays.intersection(r, a, a.length, b, b.length);
        Arrays.zeros(r);
    }
}
