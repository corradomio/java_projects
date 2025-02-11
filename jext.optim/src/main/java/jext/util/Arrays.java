package jext.util;

import java.util.random.RandomGenerator;

public class Arrays {

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
}
