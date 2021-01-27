package jext.dll.openblas;

import com.sun.jna.FunctionMapper;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import java.lang.reflect.Method;
import java.util.Map;

class NameMapper implements FunctionMapper {

    static NameMapper INSTANCE = new NameMapper();

    static Map<String, String> NAMES = Maps.asMap(
        "getNumThreads", "openblas_get_num_threads"
    );

    @Override
    public String getFunctionName(NativeLibrary library, Method method) {
        String methodName = method.getName();
        return NAMES.getOrDefault(methodName, methodName);
    }
}

public interface OpenBlas extends Library {

    OpenBlas INSTANCE = (OpenBlas) Native.load("libopenblas", OpenBlas.class, Maps.asMap(
        OPTION_FUNCTION_MAPPER, NameMapper.INSTANCE
    ));

    int openblas_get_num_threads();

    float  cblas_sdot(int n, float[] x, int incx, float[] y, int incy);
}
