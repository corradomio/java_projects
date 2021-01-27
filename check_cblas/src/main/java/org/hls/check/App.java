package org.hls.check;

import jext.dll.openblas.CBLAS;
import jext.dll.openblas.OpenBlas;

public class App {

    public static void main(String[] args) {
        OpenBlas oblas = OpenBlas.INSTANCE;

        System.out.println(oblas.openblas_get_num_threads());

        int n = 100000;
        float[] x = new float[n];
        float[] y = new float[n];

        for (int i=0; i<n; i++)
            x[i] = y[n-1-i] = 1000-i-1;

        float r = oblas.cblas_sdot(n, x, 1, y, 1);
        System.out.println(r);
        r = CBLAS.cblas_sdot(n, x, 1, y, 1);
        System.out.println(r);

        // for (int i=0; i<100; ++i)
        //     System.out.printf("%f, %f\n", x[i], y[i]);
    }
}
