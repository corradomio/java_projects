package jext.util.arrayfire;

import com.sun.jna.FunctionMapper;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import java.lang.reflect.Method;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.util.HashMap;

public class ArrayFire implements AutoCloseable {
    static NativeLibrary AFL;
    static {
        AFL = NativeLibrary.getInstance("af", new HashMap<>(){{
            put(Library.OPTION_FUNCTION_MAPPER, new FunctionMapper() {
                @Override
                public String getFunctionName(NativeLibrary library, Method method) {
                    return "af_" + method.getName();
                }
            });
            put(Library.OPTION_CLASSLOADER, ArrayFire.class.getClassLoader());
        }});
        Native.register(AFL);
    }

    public static native int create_array(long[] arr, Buffer data, int ndims, long[] dims, int type);
    public static native int create_handle(long[] arr, int ndims, long[] dims, int type);
    public static native int release_array(long arr);

    public static native int get_elements(long[] elems, long arr);
    public static native int get_type(int[] type, long arr);
    public static native int get_numdims(int[] ndims, long arr);
    public static native int get_dims(long[] d0, long[] d1, long[] d2, long[] d3, long arr);

    public static native int is_empty(boolean[] test, long arr);
    public static native int is_scalar(boolean[] test, long arr);
    public static native int is_row(boolean[] test, long arr);
    public static native int is_column(boolean[] test, long arr);
    public static native int is_vector(boolean[] test, long arr);
    public static native int is_real(boolean[] test, long arr);
    public static native int is_double(boolean[] test, long arr);
    public static native int is_single(boolean[] test, long arr);
    public static native int is_half(boolean[] test, long arr);
    public static native int is_integer(boolean[] test, long arr);
    public static native int is_bool(boolean[] test, long arr);
    public static native int is_sparse(boolean[] test, long arr);

    public static native int get_scalar(Buffer buffer, long arr);

    // ----------------------------------------------------------------------

    public static native int add(long[] out, long lhs, long rhs, boolean batch);
    public static native int sub(long[] out, long lhs, long rhs, boolean batch);
    public static native int mul(long[] out, long lhs, long rhs, boolean batch);
    public static native int div(long[] out, long lhs, long rhs, boolean batch);

    public static native int lt(long[] out, long lhs, long rhs, boolean batch);
    public static native int gt(long[] out, long lhs, long rhs, boolean batch);
    public static native int le(long[] out, long lhs, long rhs, boolean batch);
    public static native int ge(long[] out, long lhs, long rhs, boolean batch);
    public static native int eq(long[] out, long lhs, long rhs, boolean batch);
    public static native int neq(long[] out, long lhs, long rhs, boolean batch);

    public static native int and(long[] out, long lhs, long rhs, boolean batch);
    public static native int  or(long[] out, long lhs, long rhs, boolean batch);
    public static native int not(long[] out, long lhs);

    public static native int bitand(long[] out, long lhs, long rhs, boolean batch);
    public static native int  bitor(long[] out, long lhs, long rhs, boolean batch);
    public static native int bitxor(long[] out, long lhs, long rhs, boolean batch);
    public static native int bitshiftl(long[] out, long lhs, long rhs, boolean batch);
    public static native int bitshiftr(long[] out, long lhs, long rhs, boolean batch);


    // ----------------------------------------------------------------------

    static long[] toDims(int dim) {
        return new long[] { dim };
    }

    static long[] toLongs(int[] values) {
        long[] ret = new long[values.length];
        for (int i = 0; i < values.length; i++) {
            ret[i] = values[i];
        }
        return ret;
    }

    static byte[] toBytes(boolean[] values) {
        byte[] ret = new byte[values.length];
        for (int i = 0; i < values.length; i++)
            ret[i] = (values[i] ? (byte)1 : 0);
        return ret;
    }

    static void check(int err) {
        if (err != 0)
            throw new AFException(err);
    }

    void checkType(Class etype) {
        if (etype != this.etype)
            throw new AFException("Array type mismatch: required " + etype + " but array of type " + this.etype);
    }

    // ----------------------------------------------------------------------

    long arr = 0;
    private Class etype = float.class;

    // ----------------------------------------------------------------------

    public ArrayFire() { };

    public ArrayFire(Class etype) {
        this.etype = etype;
    };

    public ArrayFire(long arr) {
        this.arr = arr;
        int[] type = new int[1];
        check(get_type(type, arr));
        this.etype = AFType.fromType(type[0]);
    };

    // ----------------------------------------------------------------------

    public ArrayFire create(int dim0)  {
        return create(new int[]{dim0});
    }
    public ArrayFire create(int dim0, int dim1)  {
        return create(new int[]{dim0, dim1});
    }

    public ArrayFire create(int[] dims)  {
        long[] h = new long[1];

        if (dims.length > 4)
            throw new AFException("Too many dims: maximum 4 dims are required");

        destroy();
        check(create_handle(h, dims.length, toLongs(dims), AFType.toType(etype)));

        this.arr = h[0];
        return this;
    }

    public ArrayFire destroy() {
        if (arr != 0) {
            release_array(arr);
            arr = 0;
            etype = float.class;
        }
        return this;
    }

    @Override
    public void close() {
        destroy();
    }

    @Override
    protected void finalize() {
        destroy();
    }

    // ----------------------------------------------------------------------

    public ArrayFire createUsing(boolean[] data) {
        long[] h = new long[1];

        destroy();
        this.etype = boolean.class;
        check(create_array(h, ByteBuffer.wrap(toBytes(data)), 1, toDims(data.length), AFType.toType(etype)));

        this.arr = h[0];
        return this;
    }

    public ArrayFire createUsing(byte[] data) {
        long[] h = new long[1];

        destroy();
        this.etype = byte.class;
        check(create_array(h, ByteBuffer.wrap(data), 1, toDims(data.length), AFType.toType(etype)));

        this.arr = h[0];
        return this;
    }

    public ArrayFire createUsing(int[] data) {
        long[] h = new long[1];

        destroy();
        this.etype = int.class;
        check(create_array(h, IntBuffer.wrap(data), 1, toDims(data.length), AFType.toType(etype)));

        this.arr = h[0];
        return this;
    }

    public ArrayFire createUsing(long[] data) {
        long[] h = new long[1];

        destroy();
        this.etype = long.class;
        check(create_array(h, LongBuffer.wrap(data), 1, toDims(data.length), AFType.toType(etype)));

        this.arr = h[0];
        return this;
    }

    public ArrayFire createUsing(float[] data) {
        long[] h = new long[1];

        destroy();
        this.etype = float.class;
        check(create_array(h, FloatBuffer.wrap(data), 1, toDims(data.length), AFType.toType(etype)));

        this.arr = h[0];
        return this;
    }

    public ArrayFire createUsing(double[] data) {
        long[] h = new long[1];

        destroy();
        this.etype = double.class;
        check(create_array(h, DoubleBuffer.wrap(data), 1, toDims(data.length), AFType.toType(etype)));

        this.arr = h[0];
        return this;
    }

    // ----------------------------------------------------------------------

    public long size() {
        long[] size = new long[1];
        check(get_elements(size, arr));
        return size[0];
    }

    public Class type() {
        int[] type = new int[1];
        check(get_type(type, arr));
        return AFType.fromType(type[0]);
    }

    public long[] dims() {
        int[] ndims = new int[1];
        long[][] d = new long[4][1];

        check(get_numdims(ndims, arr));
        check(get_dims(d[0], d[1], d[2], d[3], arr));
        switch(ndims[0]) {
            case 0:
                return new long[0];
            case 1:
                return new long[]{d[0][0]};
            case 2:
                return new long[]{d[0][0], d[1][0]};
            case 3:
                return new long[]{d[0][0], d[1][0], d[2][0]};
            default:
                return new long[]{d[0][0], d[1][0], d[2][0], d[3][0]};
        }

    }

    // ----------------------------------------------------------------------

    public boolean isEmpty() {
        boolean[] test = new boolean[1];
        check(is_empty(test, arr));
        return test[0];
    }

    public boolean isScalar() {
        boolean[] test = new boolean[1];
        check(is_scalar(test, arr));
        return test[0];
    }

    // ----------------------------------------------------------------------

    public boolean getBoolean() {
        checkType(boolean.class);
        ByteBuffer buf = ByteBuffer.allocate(1);
        check(get_scalar(buf, arr));
        return buf.get(0) != 0;
    }

    public int getInt() {
        checkType(int.class);
        IntBuffer buf = IntBuffer.allocate(1);
        check(get_scalar(buf, arr));
        return buf.get(0);
    }

    public long getLong() {
        checkType(long.class);
        LongBuffer buf = LongBuffer.allocate(1);
        check(get_scalar(buf, arr));
        return buf.get(0);
    }

    public float getFloat() {
        checkType(float.class);
        FloatBuffer buf = FloatBuffer.allocate(1);
        check(get_scalar(buf, arr));
        return buf.get(0);
    }

    public double getDouble() {
        checkType(double.class);
        DoubleBuffer buf = DoubleBuffer.allocate(1);
        check(get_scalar(buf, arr));
        return buf.get(0);
    }

}
