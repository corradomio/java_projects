package jext.dll.igraph;

import com.sun.jna.Native;

public class IGraph {
    static {
        Native.register("igraph-0.8.5");
    }
}
