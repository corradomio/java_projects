package jext.dll.openblas;

import com.sun.jna.Native;

public class CBLAS {
    static {
        Native.register("libopenblas");
    }

    public static native int openblas_get_num_threads();

    public static native float  cblas_sdot(int n, float[] x, int incx, float[] y, int incy);
}
