package jext.util;

import jext.util.function.IntComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.random.RandomGenerator;

/*
    java.util.Arrays (final)
    ------------------------
    Note: it is not possible to extends from Arrays
    Note: the same operations are available for: byte, short, int, long, float, double, char, Object
    Note: in some case, the operation is not available for all primitive types

    public static void sort(int[] a) {
    public static void sort(int[] a, int fromIndex, int toIndex) {
    ...

    public static void parallelSort(int[] a) {
    public static void parallelSort(int[] a, int fromIndex, int toIndex) {
    ...
    public static void parallelPrefix(int[] array, IntBinaryOperator op) {
    public static void parallelPrefix(int[] array, int fromIndex, int toIndex, IntBinaryOperator op) {
    ...


    // Searching

    public static int binarySearch(int[] a, int key) {
    public static int binarySearch(int[] a, int fromIndex, int toIndex, int key) {
    ...

    // Equality Testing

    public static boolean equals(int[] a, int[] a2) {
    public static boolean equals(int[] a, int aFromIndex, int aToIndex, int[] b, int bFromIndex, int bToIndex) {
    ...

    // Filling

    public static void fill(int[] a, int val) {
    public static void fill(int[] a, int fromIndex, int toIndex, int val) {
    ...

    // Cloning

    public static int[] copyOf(int[] original, int newLength) {
    public static int[] copyOfRange(int[] original, int from, int to) {
    ...

    // Misc

    public static int hashCode(int[] a) {
    ...

    public static String toString(int[] a) {
    ...

    public static void setAll(int[] array, IntUnaryOperator generator) {
    public static void parallelSetAll(int[] array, IntUnaryOperator generator) {
    ...

    // Comparison methods

    // Compare boolean
    public static int compare(int[] a, int[] b) {
    ...

    // Mismatch methods

    // Mismatch boolean

    public static int mismatch(int[] a, int[] b) {
    ...

 */

public class ArrayUtils {

    // ----------------------------------------------------------------------
    // copyOf
    // ----------------------------------------------------------------------
    // Simplified version of copyOf(T[] arr, in length)

    public static int[] copyOf(int[] arr) {
        return Arrays.copyOf(arr, arr.length);
    }

    public static long[] copyOf(long[] arr) {
        return Arrays.copyOf(arr, arr.length);
    }

    public static double[] copyOf(double[] arr) {
        return Arrays.copyOf(arr, arr.length);
    }

    // ----------------------------------------------------------------------
    // range
    // ----------------------------------------------------------------------
    // Create the array [0,1,2,...]

    public static int[] range(int length) {
        return range(0, length);
    }

    public static int[] range(int min, int length) {
        int[] a = new int[length];
        for (int i=0; i<length; ++i)
            a[i] = min+i;
        return a;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------
    // Compute the sum of the elements in the array

    public static int sum(int[] arr) {
        int total = 0;
        for (int e : arr) total += e;
        return total;
    }

    public static long sum(long[] arr) {
        long total = 0;
        for (long e : arr) total += e;
        return total;
    }

    public static float sum(float[] arr) {
        float total = 0;
        for (float e : arr) total += e;
        return total;
    }

    public static double sum(double[] arr) {
        double total = 0;
        for (double e : arr) total += e;
        return total;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------
    // Some simple linear find operations

    public static int find(int[] arr, int target) {
        for (int i=0; i<arr.length; ++i)
            if (arr[i] == target) return i;
        return -1;
    }

    public static int find(long[] arr, long target) {
        for (int i=0; i<arr.length; ++i)
            if (arr[i] == target) return i;
        return -1;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------
    // Some in-place operations

    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    // --

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

    // --

    public static void reverse(int[] arr) {
        reverse(arr, 0, arr.length-1);
    }

    public static void reverse(int[] arr, int i, int j) {
        if (i > j) { int t = i; i = j; j = t; }

        for (; i < j; ++i,--j)
            swap(arr, i, j);
    }

    // --

    public static int[] rotate(int[] arr, int shift) {
        // shift > 0, right     [0,1,2,3,4], +1 -> [4,0,1,2,3]
        // shift < 0 left       [0,1,2,3,4], -1 -> [1,2,3,4,0]
        int[] rot = new int[arr.length];
        if (shift > 0) {
            int len = arr.length - shift;
            System.arraycopy(arr, 0, rot, shift, len);
            System.arraycopy(arr, len, rot, 0, shift);
        }
        else {
            int len = arr.length + shift;
            System.arraycopy(arr, -shift, rot, 0, len);
            System.arraycopy(arr, 0, rot, len, -shift);
        }
        return rot;
    }

    // --

    public static int[] moveToEnd(int shift, int[] data) {
        int[] moved = new int[shift + data.length];
        System.arraycopy(data, 0, moved, shift, data.length);
        return moved;
    }

    // ----------------------------------------------------------------------
    // asList
    // ----------------------------------------------------------------------
    // Extends Arrays.asList() for primitive types
    // Safe version of Arrays.asList(T[] arr)

    public static List<Integer> asList(int[] arr) {
        return Arrays.stream(arr).boxed().toList();
    }

    public static List<Long> asList(long[] arr) {
        return Arrays.stream(arr).boxed().toList();
    }

    public static List<Double> asList(double[] arr) {
        return Arrays.stream(arr).boxed().toList();
    }

    public static List<Boolean> asList(boolean[] arr) {
        List<Boolean> list = new ArrayList<>();
        for (int i=0; i<arr.length; ++i)
            list.add(arr[i]);
        return list;
    }

    public static List<Float> asList(float[] arr) {
        List<Float> list = new ArrayList<>();
        for (int i=0; i<arr.length; ++i)
            list.add(arr[i]);
        return list;
    }

    public static <T> List<T> asList(T[] arr) {
        if (arr == null)
            return Collections.emptyList();
        else
            return Arrays.asList(arr);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------
    // Extends Arrays.sort() for primitive types using an external comparator

    public static void sort(int[] arr, IntComparator comparator) {
        IntSort.sort(arr, comparator);
    }

    private static class IntSort {

        public static void sort(int[] arr, IntComparator comparator) {
            quickSort(arr, 0, arr.length-1, comparator);
        }

        private static void quickSort(int[] arr, int begin, int end, IntComparator comparator) {
            if (begin < end) {
                int partitionIndex = partition(arr, begin, end, comparator);
                quickSort(arr, begin, partitionIndex-1, comparator);
                quickSort(arr, partitionIndex+1, end, comparator);
            }
        }

        private static int partition(int[] arr, int begin, int end, IntComparator comparator) {
            int pivot = arr[end];
            int i = (begin-1);

            for (int j = begin; j < end; j++) {
                if (comparator.compare(arr[j], pivot) <= 0) {
                    i++;
                    ArrayUtils.swap(arr, i, j);
                }
            }

            i++;
            ArrayUtils.swap(arr, i, end);
            return i;
        }
    }

}
