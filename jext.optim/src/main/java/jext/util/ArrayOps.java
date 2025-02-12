package jext.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.random.RandomGenerator;

public class ArrayOps {

    public static int[] copyOf(int[] arr) {
        return Arrays.copyOf(arr, arr.length);
    }

    public static long[] copyOf(long[] arr) {
        return Arrays.copyOf(arr, arr.length);
    }

    public static double[] copyOf(double[] arr) {
        return Arrays.copyOf(arr, arr.length);
    }

    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void shuffle(int[] arr, RandomGenerator rng) {
        shuffle(arr, 0, arr.length - 1, rng);
    }

    public static void shuffle(int[] arr, int i, int j, RandomGenerator rng) {
        if (i > j) {
            int t = i;
            i = j;
            j = t;
        }
        int n = j - i + 1;
        for (int h = 1; h < n; h++) {
            int k = rng.nextInt(h);
            swap(arr, i + h, i + k);
        }
    }

    public static void reverse(int[] arr) {
        reverse(arr, 0, arr.length-1);
    }

    public static void reverse(int[] arr, int i, int j) {
        if (i > j) { int t = i; i = j; j = t; }

        for (; i < j; ++i,--j)
            swap(arr, i, j);
    }

    // ----------------------------------------------------------------------

    public static List<Integer> asList(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int i=0; i<arr.length; ++i)
            list.add(arr[i]);
        return list;
    }

    public static List<Long> asList(long[] arr) {
        List<Long> list = new ArrayList<>();
        for (int i=0; i<arr.length; ++i)
            list.add(arr[i]);
        return list;
    }

    public static List<Double> asList(double[] arr) {
        List<Double> list = new ArrayList<>();
        for (int i=0; i<arr.length; ++i)
            list.add(arr[i]);
        return list;
    }

}
