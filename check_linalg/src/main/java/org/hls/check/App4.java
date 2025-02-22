package org.hls.check;

import jext.math.linear.Linalg;
import jext.math.linear.RealVector;
import jext.math.linear.Vector;

public class App4 {

    public static void main(String[] args) {
        RealVector sv = Linalg.sparse(10);

        System.out.println(sv);

        sv.set(3, 33);
        sv.set(1, 11);
        sv.set(0, 0.5f);
        sv.set(9, 99);

        for (int i=0; i<sv.dim(0); ++i) {
            System.out.printf("%d: %f\n", i, sv.get(i));
        }


        System.out.println(sv);

        for (int i=0; i<10; ++i) {
            sv.set(i, i*10+i);
        }

        System.out.println(sv);
    }
}
