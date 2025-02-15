package jext.optim.domain.vgraph;

import java.util.Set;

/**
 * Simplified version of JGraphT
 *
 * @param <V>
 * @param <E>
 */
public interface Graph<V, E> {

    Set<V> vertexSet();

    boolean addVertex(V v);
    boolean containVertex(V v);
    V getEdgeSource(E e);
    V getEdgeTarget(E e);

    Set<E> edgeSet();

    E getEdge(V sourceVertex, V targetVertex);
    E addEdge(V sourceVertex, V targetVertex);
    boolean addEdge(E e);
    boolean containsEdge(V sourceVertex, V targetVertex);
    boolean containsEdge(E edge);
    Set<E> edgesOf(V vertex);
    Set<E> incomingEdgesOf(V vertex);
    Set<E> outgoingEdgesOf(V vertex);

    float getEdgeWeight(V sourceVertex, V targetVertex);
    void   setEdgeWeight(V sourceVertex, V targetVertex, float weight);
    float getEdgeWeight(E e);
    void   setEdgeWeight(E e, float weight);
}
