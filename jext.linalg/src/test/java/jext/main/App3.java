package jext.main;

public class App3 {

    static int locate(int[] values, int v) {
        int b = 0;
        int e = values.length-1;
        int m = -1;

        while (b < e) {
            m = (b+e)/2;
            if (v < values[m])
                e = m-1;
            else if (v > values[m])
                b = m+1;
            else
                return m;
        }
        m = (b+e)/2;
        return m;
    }

    public static void main(String[] args) {
        int[] values;
        values = new int[]{ 1,3,4,5,7,9};
        values = new int[]{0};

        for (int i=0; i<12; ++i) {
            int l = locate(values, i);
            System.out.printf("%d -> v[%d]=%d\n", i, l, values[l]);
        }
    }
}
