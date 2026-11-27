package jext.igrapht;

import jext.jgrapht.edges.Directed;
import jext.jgrapht.edges.Weighted;
import org.jgrapht.Graph;

import java.util.function.Supplier;

import static jext.jgrapht.Graphs.edgeSupplier;
import static jext.jgrapht.Graphs.vertexSupplier;

public abstract class Graphs extends org.jgrapht.Graphs {

    public static <V, E> Graph<V, E> newGraph(Class<V> vertexClass, Class<E> edgeClass) {
        boolean directed = Directed.class.isAssignableFrom(edgeClass);
        boolean weighed  = Weighted.class.isAssignableFrom(edgeClass);
        return newGraph(directed, weighed, vertexClass, edgeClass);
    }

    public static <V, E> Graph<V, E> newGraph(
        boolean directed,
        boolean weighted,
        Class<V> vertexClass,
        Class<E> edgeClass) {
        Supplier<V> vertexSupplier = vertexSupplier(vertexClass);
        Supplier<E> edgeSupplier   = edgeSupplier(edgeClass);

        return newGraph(directed, false, false, weighted, true, vertexSupplier, edgeSupplier);
    }

    public static <V, E> Graph<V, E> newGraph(
        boolean directed,
        boolean loop,
        boolean multiple,
        boolean weighted,
        boolean cycles,
        Supplier<V> vertexSupplier,
        Supplier<E> edgeSupplier) {

        return new IGraph<>(directed, vertexSupplier, edgeSupplier);
    }

    public static <V, E> E addEdge(Graph<V, E> g, V sourceVertex, V targetVertex) {
        return addEdgeWithVertices(g, sourceVertex, targetVertex);
    }
}
