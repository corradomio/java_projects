package jext.util;

public class Arrays {

    public static void move(long[] array, int p1, int p2, int len) {
        if (p2 > p1) {
            for (int i=p1+len-1,j=p2+len-1,k=0; k<len; --i,--j,++k)
                array[j] = array[i];
        }
        else {
            for(int i=p1,j=p2,k=0; k<len; i++,j++,k++)
                array[j] = array[i];
        }
    }

    public static float[] copyOf(float[] a, int n) {
        return java.util.Arrays.copyOf(a, n);
    }

    public static long[] copyOf(long[] a, int n) {
        return java.util.Arrays.copyOf(a, n);
    }

    public static boolean equals(long[] a, long[] b, int n) {
        for(int i=0; i<n; ++i)
            if (a[i] != b[i])
                return false;
        return true;
    }
}
