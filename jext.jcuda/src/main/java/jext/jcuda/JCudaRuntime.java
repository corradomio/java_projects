package jext.jcuda;

import jcuda.driver.CUcontext;
import jcuda.driver.CUdevice;
import jcuda.driver.CUmodule;
import jcuda.driver.JCudaDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static jcuda.driver.JCudaDriver.cuCtxCreate;
import static jcuda.driver.JCudaDriver.cuDeviceGet;
import static jcuda.driver.JCudaDriver.cuInit;
import static jcuda.driver.JCudaDriver.cuModuleLoad;

public class JCudaRuntime {

    private static CUdevice device;
    private static CUcontext context;

    public static void init() {
        JCudaDriver.setExceptionsEnabled(true);
        cuInit(0);
        device = new CUdevice();
        cuDeviceGet(device, 0);
        context = new CUcontext();
        cuCtxCreate(context, 0, device);
    }

    // ----------------------------------------------------------------------

    private static Map<String, CUmodule> nameModuleMap = new HashMap<>();
    private static Map<String, CUmodule> pathModuleMap = new HashMap<>();

    public static String registerModule(String modulePath) throws Exception {
        return registerModule(new File(modulePath));
    }

    public static String registerModule(String name, String modulePath) throws Exception {
        return registerModule(name, new File(modulePath));
    }

    public static String registerModule(File modulePath) throws Exception {
        String path = modulePath.getAbsolutePath().replace("\\", "/");
        int slash = path.lastIndexOf('/');
        int dot = path.lastIndexOf('.');
        String name = path.substring(slash+1, dot);
        return registerModule(name, modulePath);
    }

    public static String registerModule(String name, File modulePath) throws FileNotFoundException, IllegalAccessException {
        if (!modulePath.exists())
            throw new FileNotFoundException(modulePath.toString());
        if (nameModuleMap.containsKey(name))
            throw new IllegalAccessException(name + " already registered");
        String path = modulePath.getAbsolutePath().replace("\\", "/");
        if (pathModuleMap.containsKey(path))
            throw new IllegalAccessException(path + " already registered");

        CUmodule module = new CUmodule();
        cuModuleLoad(module, path);
        nameModuleMap.put(name, module);
        pathModuleMap.put(path, module);

        return name;
    }

    // ----------------------------------------------------------------------

    public static void shutdown() {

    }
}
