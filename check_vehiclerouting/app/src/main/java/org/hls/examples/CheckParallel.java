package org.hls.examples;

import jext.util.concurrent.Parallel;

import java.util.ArrayList;
import java.util.List;

public class CheckParallel {

    private static double sq(double i){ return i*i;}

    public static void main(String[] args) {
        List<Double> li = new ArrayList<>();
        for(int i=0; i<10000; ++i)
            li.add((double)i);

        long start = System.currentTimeMillis();

        List<Double> ri = Parallel.apply(li, i-> (sq(i)*Math.sin(i)));

        long end = System.currentTimeMillis();

        System.out.println(end - start);

        Parallel.shutdown();
    }
}
