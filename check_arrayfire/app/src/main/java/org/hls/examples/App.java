package org.hls.examples;

import jext.util.arrayfire.ArrayFire;
import jext.util.arrayfire.Math;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        ArrayFire zaf = new ArrayFire(int.class).create(new int[]{1,2,3,4});
        ArrayFire iaf = new ArrayFire().createUsing(new double[]{1.1,2,3,4,5});

        // System.out.println(iaf.size());
        // System.out.println(iaf.type());
        //
        // System.out.println(zaf.size());
        // System.out.println(zaf.type());
        // System.out.println(Arrays.toString(zaf.dims()));
        // System.out.println(iaf.getDouble());

        ArrayFire ret = Math.add(iaf, iaf);
        System.out.println(ret.getDouble());
    }
}
