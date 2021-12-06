package org.hls.check;

import mikera.vectorz.*;

public class CheckVectorz {

    public static void main(String[] args) {
        int N = 100000000;
        double[] a = new double[N];
        double[] b = new double[N];
        double[] c = new double[N];

        AVector va = Vector.of(a);
        AVector vb = Vector.of(b);
        AVector vc = Vector.of(c);

        long start = System.currentTimeMillis();
        vc.set(va.multiplyCopy(va).addCopy(vb.multiplyCopy(vb)));
        System.out.printf("scalar in %d ms\n", System.currentTimeMillis() - start);

    }
}
