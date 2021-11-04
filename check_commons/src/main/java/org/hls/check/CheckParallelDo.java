package org.hls.check;

import jext.util.concurrent.Parallel;

public class CheckParallelDo {

    public static void main(String[] args) {


        Parallel.invokeAll(() -> {
            System.out.println(1);
        }, () -> {
            System.out.println(2);
        });

    }
}
