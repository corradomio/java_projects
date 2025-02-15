package jext.util;

public class IntSort {

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
                ArrayOps.swap(arr, i, j);
            }
        }

        i++;
        ArrayOps.swap(arr, i, end);
        return i;
    }
}
