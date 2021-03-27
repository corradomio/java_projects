package jext.util;

public class Arrays {

    // ----------------------------------------------------------------------
    // coordinate vector lengths
    // ----------------------------------------------------------------------

    public static int[] indices(int n) {
        int[] idxs = new int[n];
        for (int i=0; i<n; ++i)
            idxs[i] = i;
        return idxs;
    }

    public static int lengthOf(int n) {
        int f;
        if (n == 0)
            return n;
        else if (n <  16) f = 4;
        else if (n < 256) f = 16;
        else f = 256;
        int r = n%f;
        return n + ((r != 0) ? f - r : 0);
    }

    public static int lengthSum(int n1, int n2) {
        return lengthOf(n1+n2);
    }

    public static int lengthMin(int n1, int n2) {
        return lengthOf(Math.min(n1, n2));
    }

    public static int lengthMax(int n1, int n2) {
        return lengthOf(Math.max(n1, n2));
    }

    // ----------------------------------------------------------------------

    public static int union(long[] union, long[] s1, int n1, long[] s2, int n2) {
        int k=0,i=0,j=0;
        if (n1 == 0 && n2 == 0)
            return 0;
        if (n1 == 0)
            return copy(union, 0, s2, 0, n2);
        if (n2 == 0)
            return copy(union, 0, s1, 0, n1);

        if (s1[i] < s2[j])
            union[k] = s1[i];
        else
            union[k] = s2[j];

        while (i<n1 || j<n2) {
            if (i<n1 && j<n2) {
                if (s1[i] <= union[k])
                    ++i;
                else if (s2[j] <= union[k])
                    ++j;
                else if (s1[i] < s2[j])
                    union[++k] = s1[i++];
                else
                    union[++k] = s2[j++];
            }
            else if (i<n1)
                union[++k] = s1[i++];
            else if (j<n2)
                union[++k] = s2[j++];
        }
        return k+1;
    }

    public static int intersection(long[] isect, long[] s1, int n1, long[] s2, int n2) {
        int k=0,i=0,j=0;
        if (n1 == 0 || n2 == 0)
            return 0;

        while (i < n1 && j < n2) {
            if (s1[i] < s2[j])
                ++i;
            else if (s2[j] < s1[i])
                ++j;
            else {
                isect[k++] = s1[i];
                ++i;
                ++j;
            }
        }
        return k;
    }

    // ----------------------------------------------------------------------

    public static void zeros(long[] src) {
        for (int i=0; i<src.length; ++i)
            src[i] = 0;
    }

    public static int copy(long[] src, int srcPos, long[] dst, int dstPos, int len) {
        System.arraycopy(src, srcPos, dst, dstPos, len);
        return dstPos+len;
    }

    public static void move(long[] array, int srcPos, int dstPos, int len) {
        // if (p2 > p1) {
        //     for (int i=p1+len-1,j=p2+len-1,k=0; k<len; --i,--j,++k)
        //         array[j] = array[i];
        // }
        // else {
        //     for(int i=p1,j=p2,k=0; k<len; i++,j++,k++)
        //         array[j] = array[i];
        // }
        System.arraycopy(array, srcPos, array, dstPos, len);
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


    public static void zeros(float[] src) {
        for (int i=0; i<src.length; ++i)
            src[i] = 0;
    }

    public static void move(float[] array, int srcPos, int dstPos, int len) {
        System.arraycopy(array, srcPos, array, dstPos, len);
    }

    public static float[] copyOf(float[] a, int n) {
        return java.util.Arrays.copyOf(a, n);
    }

    public static boolean equals(float[] a, float[] b, int n) {
        for(int i=0; i<n; ++i)
            if (a[i] != b[i])
                return false;
        return true;
    }
}
