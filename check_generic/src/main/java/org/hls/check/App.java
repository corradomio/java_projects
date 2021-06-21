package org.hls.check;

import jext.util.ArrayUtils;

import java.util.Arrays;
import java.util.Optional;

public class App {

    public static void main(String[] args) {
        int[] range = ArrayUtils.range(10);
        range = Arrays.copyOf(range, 15);
        System.out.println(range[0]);
    }
}
