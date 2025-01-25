package jext.igraph;

import com.sun.jna.FunctionMapper;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import jext.igraph.impl.IGraphType;
import jext.util.MapUtils;

import java.lang.reflect.Method;

public class IGraphLibrary {
    static {
        // Native.register("libigraph");
        NativeLibrary library =
            NativeLibrary.getInstance("libigraph",
                MapUtils.asMap(
                    Library.OPTION_CLASSLOADER, IGraphLibrary.class.getClassLoader(),
                    Library.OPTION_FUNCTION_MAPPER, new FunctionMapper() {
                        @Override
                        public String getFunctionName(NativeLibrary library, Method method) {
                            return "igraph_" + method.getName();
                        }
                    }
                )
            );
        Native.register(IGraphLibrary.class, library);
    }

    public static native void version(byte[] dummy, int[] major, int[] minor, int[] patch);

    public static native int empty(IGraphType g, int n, boolean directed);
    public static native int destroy(IGraphType g);

    public static native int vcount(IGraphType g);
    public static native int ecount(IGraphType g);
    public static native boolean is_directed(IGraphType g);

    public static native int edge(IGraphType g, int eid, int[] source, int[] target);
    public static native int add_edge(IGraphType g, int from, int to);


    public static void check(int err) {
        if (err != 0)
            throw new RuntimeException("Error: " + err);
    }

}
