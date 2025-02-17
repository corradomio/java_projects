package org.hls.examples;

import jext.optim.heuristics.genetics.domain.bitset.BitSet;

import java.util.Random;

public class Check3 {

    public static void main(String[] args) {
        Random rand = new Random();
        int N = 16;
        BitSet b1 = new BitSet(N);
        BitSet b2 = new BitSet(N);

        for (int i = 0; i < N; i++) {
            if (rand.nextBoolean())
                b1.set(i);
            if (rand.nextBoolean())
                b2.set(i);
        }

        System.out.printf("                b1: %s\n", b1);
        System.out.printf("                b2: %s\n", b2);
        System.out.printf("b1 union b2       : %s\n", b1.union(b2));
        System.out.printf("b1 intersection b2: %s\n", b1.intersection(b2));
        System.out.printf("b1 difference b2  : %s\n", b1.difference(b2));
        System.out.printf("b2 difference b1  : %s\n", b2.difference(b1));
        System.out.printf("b1 symdiff b2  : %s\n", b1.symdiff(b2));

    }
}
