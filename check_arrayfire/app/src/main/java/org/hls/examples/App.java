package org.hls.examples;

import jext.util.arrayfire.ArrayFire;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        ArrayFire zaf = new ArrayFire().create(new int[]{1,2,3,4,5}, int.class);

        ArrayFire iaf = new ArrayFire().create(new double[]{1,2,3,4,5});

        System.out.println(iaf.size());
        System.out.println(iaf.type());


        System.out.println(zaf.size());
        System.out.println(zaf.type());
        System.out.println(Arrays.toString(zaf.dims()));
    }
}
