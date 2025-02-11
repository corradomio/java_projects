package jext.optim.heuristics.genetics.util;

import java.util.Arrays;
import java.util.List;

public class QuickSort<T> {

    public static <T extends Comparable> List<T> sort(List<T> list) {
        Comparable[] arr = list.toArray(new Comparable[0]);
        quickSort(arr, 0, arr.length-1);
        return Arrays.asList((T[])arr);
    }

    private static void quickSort(Comparable arr[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);
            quickSort(arr, begin, partitionIndex-1);
            quickSort(arr, partitionIndex+1, end);
        }
    }

    private static int partition(Comparable arr[], int begin, int end) {
        Comparable pivot = arr[end];
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (arr[j].compareTo(pivot) <= 0) {
                i++;

                Comparable swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        Comparable swapTemp = arr[i+1];
        arr[i+1] = arr[end];
        arr[end] = swapTemp;

        return i+1;
    }
}
