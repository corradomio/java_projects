package org.hls.examples;

import jext.util.ArrayUtils;

import java.util.Arrays;

public class Check4 {

    public static void main(String[] args) {
        int[] arr;

        arr = ArrayUtils.range(10);
        System.out.println(Arrays.toString(ArrayUtils.rotate(arr, 3)));

        arr = ArrayUtils.range(10);
        System.out.println(Arrays.toString(ArrayUtils.rotate(arr, -3)));
    }
}
