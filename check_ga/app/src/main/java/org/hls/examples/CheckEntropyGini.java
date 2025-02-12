package org.hls.examples;

import jext.optim.heuristics.genetics.util.Entropy;

public class CheckEntropyGini {

    public static void main(String[] args) {
        System.out.println(Entropy.log2(2));
        System.out.println(Entropy.log2(4));
        System.out.println(Entropy.log2(.5));
        System.out.println(Entropy.value(new double[]{1,1,1,1}));
        System.out.println(Entropy.value(new double[]{1,2,3,4}));
    }
}
