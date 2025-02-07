package jext.util.arrayfire;

import com.sun.jna.Native;
import com.sun.jna.Pointer;

import java.nio.Buffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public class ArrayFire {
    static {
        Native.register("af.dll");
    }

    public static native int af_create_array(long[] arr, Buffer data, int ndims, long[] dims, int type);
    public static native int af_create_handle(long[] arr, int ndims, long[] dims, int type);
    public static native int af_release_array(long arr);

    public static native int af_get_elements(long[] elems, long arr);
    public static native int af_get_type(int[] type, long arr);
    public static native int af_get_dims(long[] d0, long[] d1, long[] d2, long[] d3, long arr);

    // ----------------------------------------------------------------------

    private static long[] toLongs(int[] dims) {
        long[] ret = new long[dims.length];
        for (int i = 0; i < dims.length; i++) {
            ret[i] = dims[i];
        }
        return ret;
    }

    private static void check(int err) {
        if (err != 0)
            throw new AFException(err);
    }

    // ----------------------------------------------------------------------

    @Override
    protected void finalize() {
        destroy();
    }

    // ----------------------------------------------------------------------

    private long handler = 0;
    private Class etype = void.class;

    public ArrayFire() { };

    // ----------------------------------------------------------------------

    public ArrayFire create(int dim0, Class etype)  {
        return create(new int[]{dim0}, etype);
    }

    public ArrayFire create(int[] dims, Class etype)  {
        long[] h = new long[1];

        destroy();
        check(af_create_handle(h, dims.length, toLongs(dims), AfType.toType(etype)));

        this.handler = h[0];
        this.etype = etype;
        return this;
    }

    public ArrayFire destroy() {
        if (handler != 0) {
            af_release_array(handler);
            handler = 0;
            etype = void.class;
        }
        return this;
    }

    // ----------------------------------------------------------------------

    public ArrayFire create(int[] data) {
        long[] h = new long[1];

        destroy();
        check(af_create_array(h, IntBuffer.wrap(data), 1, new long[]{data.length}, AfType.toType(int.class)));

        this.handler = h[0];
        this.etype = int.class;
        return this;
    }

    public ArrayFire create(long[] data) {
        long[] h = new long[1];

        destroy();
        check(af_create_array(h, LongBuffer.wrap(data), 1, new long[]{data.length}, AfType.toType(long.class)));

        this.handler = h[0];
        this.etype = long.class;
        return this;
    }

    public ArrayFire create(float[] data) {
        long[] h = new long[1];

        destroy();
        check(af_create_array(h, FloatBuffer.wrap(data), 1, new long[]{data.length}, AfType.toType(float.class)));

        this.handler = h[0];
        this.etype = float.class;
        return this;
    }

    public ArrayFire create(double[] data) {
        long[] h = new long[1];

        destroy();
        check(af_create_array(h, DoubleBuffer.wrap(data), 1, new long[]{data.length}, AfType.toType(double.class)));

        this.handler = h[0];
        this.etype = double.class;
        return this;
    }

    // ----------------------------------------------------------------------

    public long size() {
        long[] size = new long[1];
        check(af_get_elements(size, handler));
        return size[0];
    }

    public AfType type() {
        int[] type = new int[1];
        check(af_get_type(type, handler));
        return AfType.fromType(type[0]);
    }

    public long[] dims() {
        long[] d0 = new long[1];
        long[] d1 = new long[1];
        long[] d2 = new long[1];
        long[] d3 = new long[1];

        check(af_get_dims(d0, d1, d2, d3, handler));
        return new long[]{d0[0], d1[0], d2[0], d3[0]};
    }

}
