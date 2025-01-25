package jext.jgrapht.graph;

import org.jgrapht.Graph;
import org.jgrapht.GraphType;
import org.jgrapht.graph.DefaultGraphType;

import java.util.Collection;
import java.util.Set;
import java.util.function.Supplier;

public class BaseGraph<V, E> implements Graph<V, E> {

    protected GraphType type = DefaultGraphType.simple();


    @Override
    public Set<E> getAllEdges(V sourceVertex, V targetVertex) {
        return Set.of();
    }

    @Override
    public E getEdge(V sourceVertex, V targetVertex) {
        return null;
    }

    @Override
    public Supplier<V> getVertexSupplier() {
        return null;
    }

    @Override
    public Supplier<E> getEdgeSupplier() {
        return null;
    }

    @Override
    public E addEdge(V sourceVertex, V targetVertex) {
        return null;
    }

    @Override
    public boolean addEdge(V sourceVertex, V targetVertex, E e) {
        return false;
    }

    @Override
    public V addVertex() {
        return null;
    }

    @Override
    public boolean addVertex(V v) {
        return false;
    }

    @Override
    public boolean containsEdge(V sourceVertex, V targetVertex) {
        return false;
    }

    @Override
    public boolean containsEdge(E e) {
        return false;
    }

    @Override
    public boolean containsVertex(V v) {
        return false;
    }

    @Override
    public Set<E> edgeSet() {
        return Set.of();
    }

    @Override
    public int degreeOf(V vertex) {
        return 0;
    }

    @Override
    public Set<E> edgesOf(V vertex) {
        return Set.of();
    }

    @Override
    public int inDegreeOf(V vertex) {
        return 0;
    }

    @Override
    public Set<E> incomingEdgesOf(V vertex) {
        return Set.of();
    }

    @Override
    public int outDegreeOf(V vertex) {
        return 0;
    }

    @Override
    public Set<E> outgoingEdgesOf(V vertex) {
        return Set.of();
    }

    @Override
    public boolean removeAllEdges(Collection<? extends E> edges) {
        return false;
    }

    @Override
    public Set<E> removeAllEdges(V sourceVertex, V targetVertex) {
        return Set.of();
    }

    @Override
    public boolean removeAllVertices(Collection<? extends V> vertices) {
        return false;
    }

    @Override
    public E removeEdge(V sourceVertex, V targetVertex) {
        return null;
    }

    @Override
    public boolean removeEdge(E e) {
        return false;
    }

    @Override
    public boolean removeVertex(V v) {
        return false;
    }

    @Override
    public Set<V> vertexSet() {
        return Set.of();
    }

    @Override
    public V getEdgeSource(E e) {
        return null;
    }

    @Override
    public V getEdgeTarget(E e) {
        return null;
    }

    @Override
    public GraphType getType() {
        return type;
    }

    @Override
    public double getEdgeWeight(E e) {
        return 0;
    }

    @Override
    public void setEdgeWeight(E e, double weight) {

    }
}
