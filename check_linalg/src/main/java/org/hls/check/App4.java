package org.hls.check;

import jext.math.linear.sparse.SparseVector;

public class App4 {

    public static void main(String[] args) {
        SparseVector sv = new SparseVector(10);

        System.out.println(sv);

        sv.set(3, 33);
        sv.set(1, 11);
        sv.set(0, 0.5f);
        sv.set(9, 99);

        System.out.println(sv);

        for (int i=0; i<10; ++i) {
            sv.set(i, i*10+i);
        }

        System.out.println(sv);
    }
}
