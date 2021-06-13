package org.hls.check;

import jext.ml.Linalg;
import org.apache.commons.math3.distribution.NormalDistribution;

import java.util.Random;

public class App {

    public static void main(String[] args) {
        Random rnd = new Random();
        float[] v = new float[10];
        Linalg.random(v, rnd, true);

        System.out.println(Linalg.sum(v));

        NormalDistribution
    }
}
