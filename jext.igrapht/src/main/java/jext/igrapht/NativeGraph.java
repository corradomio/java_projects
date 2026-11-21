package jext.igrapht;

import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.LongByReference;

import java.lang.ref.Cleaner;
import java.lang.ref.Reference;

/** Owns an igraph_t and its weight vector through direct JNA calls. */
final class NativeGraph implements AutoCloseable {
    private static final Cleaner CLEANER = Cleaner.create();

    private IGraphLibrary.Graph graph;
    private IGraphLibrary.RealVector weights;
    private final Cleaner.Cleanable cleanable;

    NativeGraph(boolean directed) {
        IGraphLibrary api = NativeLibrary.api();
        IGraphLibrary.Graph newGraph = new IGraphLibrary.Graph();
        IGraphLibrary.RealVector newWeights = new IGraphLibrary.RealVector();
        NativeLibrary.prepareThread();
        check(api.igraph_empty(newGraph, 0, directed ? (byte) 1 : (byte) 0));
        int error = api.igraph_vector_init(newWeights, 0);
        if (error != IGraphLibrary.SUCCESS) {
            api.igraph_destroy(newGraph);
            check(error);
        }
        try {
            cleanable = CLEANER.register(this, () -> destroy(api, newGraph, newWeights));
        } catch (Throwable e) {
            destroy(api, newGraph, newWeights);
            throw e;
        }
        graph = newGraph;
        weights = newWeights;
    }

    private static void destroy(IGraphLibrary api, IGraphLibrary.Graph graph,
                                IGraphLibrary.RealVector weights) {
        NativeLibrary.prepareThread();
        api.igraph_vector_destroy(weights);
        api.igraph_destroy(graph);
    }

    private IGraphLibrary.Graph openGraph() {
        if (graph == null) throw new IllegalStateException("The native graph is closed");
        return graph;
    }

    private static void check(int error) {
        if (error == IGraphLibrary.SUCCESS) return;
        String message = NativeLibrary.api().igraph_strerror(error);
        throw new IGraphException(error, message == null ? "igraph error " + error : message);
    }

    private void checkpoint() {
        Reference.reachabilityFence(this);
    }

    synchronized int addVertices(int count) {
        if (count <= 0) throw new IllegalArgumentException("Vertex count must be positive");
        IGraphLibrary api = NativeLibrary.api();
        IGraphLibrary.Graph graph = openGraph();
        NativeLibrary.prepareThread();
        long first = api.igraph_vcount(graph);
        if (first > (long) Integer.MAX_VALUE - count) {
            throw new IllegalStateException("The Java adapter supports at most 2^31-1 vertices");
        }
        check(api.igraph_add_vertices(graph, count, null));
        checkpoint();
        return (int) first;
    }

    synchronized int addEdge(int source, int target, double weight) {
        if (!Double.isFinite(weight) || weight < 0) {
            throw new IllegalArgumentException("Weights must be finite and non-negative");
        }
        IGraphLibrary api = NativeLibrary.api();
        IGraphLibrary.Graph graph = openGraph();
        NativeLibrary.prepareThread();
        long id = api.igraph_ecount(graph);
        if (id >= Integer.MAX_VALUE) {
            throw new IllegalStateException("The Java adapter supports at most 2^31-1 edges");
        }
        check(api.igraph_vector_resize(weights, id + 1));
        int error = api.igraph_add_edge(graph, source, target);
        if (error != IGraphLibrary.SUCCESS) {
            int rollbackError = api.igraph_vector_resize(weights, id);
            checkpoint();
            if (rollbackError != IGraphLibrary.SUCCESS) check(rollbackError);
            check(error);
        }
        api.igraph_vector_set(weights, id, weight);
        checkpoint();
        return (int) id;
    }

    synchronized void removeEdge(int id) {
        IGraphLibrary api = NativeLibrary.api();
        IGraphLibrary.Graph graph = openGraph();
        NativeLibrary.prepareThread();
        long oldSize = api.igraph_vector_size(weights);
        checkEdgeId(id, oldSize);
        check(api.igraph_delete_edges(graph, api.igraph_ess_1(id)));
        for (long i = id; i + 1 < oldSize; i++) {
            api.igraph_vector_set(weights, i, api.igraph_vector_get(weights, i + 1));
        }
        check(api.igraph_vector_resize(weights, oldSize - 1));
        checkpoint();
    }

    synchronized void removeVertex(int id) {
        IGraphLibrary api = NativeLibrary.api();
        IGraphLibrary.Graph graph = openGraph();
        NativeLibrary.prepareThread();
        check(api.igraph_delete_vertices(graph, api.igraph_vss_1(id)));
        checkpoint();
    }

    synchronized void setEdgeWeight(int id, double weight) {
        if (!Double.isFinite(weight) || weight < 0) {
            throw new IllegalArgumentException("Weights must be finite and non-negative");
        }
        IGraphLibrary api = NativeLibrary.api();
        openGraph();
        NativeLibrary.prepareThread();
        checkEdgeId(id, api.igraph_vector_size(weights));
        api.igraph_vector_set(weights, id, weight);
        checkpoint();
    }

    synchronized double getEdgeWeight(int id) {
        IGraphLibrary api = NativeLibrary.api();
        openGraph();
        NativeLibrary.prepareThread();
        checkEdgeId(id, api.igraph_vector_size(weights));
        double result = api.igraph_vector_get(weights, id);
        checkpoint();
        return result;
    }

    private static void checkEdgeId(int id, long size) {
        if (id < 0 || id >= size) {
            throw new IGraphException(4, "Invalid native edge ID: " + id);
        }
    }

    synchronized int degree(int vertex, int mode, boolean loops) {
        IGraphLibrary api = NativeLibrary.api();
        IGraphLibrary.Graph graph = openGraph();
        LongByReference degree = new LongByReference();
        NativeLibrary.prepareThread();
        check(api.igraph_degree_1(graph, degree, vertex, mode + 1,
            loops ? IGraphLibrary.LOOPS_TWICE : IGraphLibrary.LOOPS_ONCE));
        checkpoint();
        if (degree.getValue() > Integer.MAX_VALUE) {
            throw new ArithmeticException("Vertex degree exceeds the Java int range");
        }
        return (int) degree.getValue();
    }

    synchronized boolean isConnected(boolean strong) {
        IGraphLibrary api = NativeLibrary.api();
        IGraphLibrary.Graph graph = openGraph();
        ByteByReference connected = new ByteByReference();
        NativeLibrary.prepareThread();
        int mode = strong && api.igraph_is_directed(graph) != 0
            ? IGraphLibrary.STRONG : IGraphLibrary.WEAK;
        check(api.igraph_is_connected(graph, connected, mode));
        checkpoint();
        return connected.getValue() != 0;
    }

    synchronized int[] shortestPath(int source, int target) {
        IGraphLibrary api = NativeLibrary.api();
        IGraphLibrary.Graph graph = openGraph();
        IGraphLibrary.IntVector edges = new IGraphLibrary.IntVector();
        NativeLibrary.prepareThread();
        check(api.igraph_vector_int_init(edges, 0));
        try {
            int mode = api.igraph_is_directed(graph) != 0 ? IGraphLibrary.OUT : IGraphLibrary.ALL;
            check(api.igraph_get_shortest_path(graph, weights, null, edges, source, target, mode));
            long length = api.igraph_vector_int_size(edges);
            if (length > Integer.MAX_VALUE) {
                throw new IllegalStateException("Shortest path is too large for a Java array");
            }
            int[] result = new int[(int) length];
            for (int i = 0; i < result.length; i++) {
                result[i] = Math.toIntExact(api.igraph_vector_int_get(edges, i));
            }
            return result;
        } finally {
            api.igraph_vector_int_destroy(edges);
            checkpoint();
        }
    }

    @Override
    public synchronized void close() {
        if (graph != null) {
            graph = null;
            weights = null;
            cleanable.clean();
        }
    }
}
