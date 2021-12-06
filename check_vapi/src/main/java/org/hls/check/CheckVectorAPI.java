package org.hls.check;

import jdk.incubator.vector.DoubleVector;
import jdk.incubator.vector.VectorSpecies;

public class CheckVectorAPI {

    static final VectorSpecies<Double> SPECIES = DoubleVector.SPECIES_PREFERRED;

    static void scalarComputation(double[] a, double[] b, double[] c) {
        int i = 0,n = c.length;
        for(; i< n; i++)
            c[i] = (a[i] * a[i] + b[i] * b[i]) * -1.0;
    }

    static void vectorComputation(double[] a, double[] b, double[] c) {
        int i = 0;
        int upperBound = SPECIES.loopBound(a.length);
        int step = SPECIES.length();
        for (; i < upperBound; i += step) {
            // FloatVector va, vb, vc;
            var va = DoubleVector.fromArray(SPECIES, a, i);
            var vb = DoubleVector.fromArray(SPECIES, b, i);
            var vc = va.mul(va)
                .add(vb.mul(vb))
                .neg();
            vc.intoArray(c, i);
        }
        for (; i < a.length; i++) {
            c[i] = (a[i] * a[i] + b[i] * b[i]) * -1.0f;
        }
    }

    public static void main(String[] args) {
        int N = 100000000;
        double[] a = new double[N];
        double[] b = new double[N];
        double[] c = new double[N];

        for (int i=0; i<10; ++i) {
            long start = System.currentTimeMillis();
            scalarComputation(a, b, c);
            System.out.printf("scalar in %d ms\n", System.currentTimeMillis() - start);
            start = System.currentTimeMillis();
            vectorComputation(a, b, c);
            System.out.printf("vector in %d ms\n", System.currentTimeMillis() - start);
            System.out.println("--");
        }
    }
}
