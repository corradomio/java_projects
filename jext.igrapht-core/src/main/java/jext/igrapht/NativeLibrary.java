package jext.igrapht;

import com.sun.jna.Native;
import com.sun.jna.Pointer;

/** Loads libigraph.dll directly through Java Native Access. */
final class NativeLibrary {
    private static com.sun.jna.NativeLibrary nativeLibrary;
    private static Pointer ignoreErrors;
    private static final IGraphLibrary API = load();

    private static IGraphLibrary load() {
        verifyLayout("vector", new IGraphLibrary.RealVector().size(), 24);
        verifyLayout("integer vector", new IGraphLibrary.IntVector().size(), 24);
        verifyLayout("graph", new IGraphLibrary.Graph().size(), 176);
        verifyLayout("vertex selector", new IGraphLibrary.Vs.ByValue().size(), 32);
        verifyLayout("edge selector", new IGraphLibrary.Es.ByValue().size(), 32);

        String explicit = System.getProperty("igraph.library");
        String libraryName = explicit == null || explicit.isBlank() ? "libigraph" : explicit;
        nativeLibrary = com.sun.jna.NativeLibrary.getInstance(libraryName);
        ignoreErrors = nativeLibrary.getFunction("igraph_error_handler_ignore");
        IGraphLibrary api = Native.load(libraryName, IGraphLibrary.class);
        api.igraph_set_error_handler(ignoreErrors);
        int error = api.igraph_setup();
        if (error != IGraphLibrary.SUCCESS) {
            throw new UnsatisfiedLinkError("igraph_setup failed: " + api.igraph_strerror(error));
        }
        return api;
    }

    private static void verifyLayout(String name, int actual, int expected) {
        if (actual != expected) {
            throw new IllegalStateException(
                "Unexpected igraph " + name + " size: " + actual + " (expected " + expected + ")");
        }
    }

    static IGraphLibrary api() {
        return API;
    }

    static void prepareThread() {
        API.igraph_set_error_handler(ignoreErrors);
    }

    private NativeLibrary() {
    }
}
