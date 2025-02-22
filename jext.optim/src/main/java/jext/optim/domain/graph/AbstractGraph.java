package jext.optim.domain.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public abstract class AbstractGraph<V> implements Graph<V, Edge<V>> {

    private final Set<V> vertexSet = new HashSet<>();
    private final Set<Edge<V>> edgeSet = new HashSet<>();
    private final Map<V, Set<Edge<V>>> adjacencyList = new HashMap<>();
    private final Map<Edge<V>, Edge<V>> edgeMap = new HashMap<>();

    protected AbstractGraph() {

    }

    @Override
    public Set<V> vertexSet() {
        return vertexSet;
    }

    @Override
    public boolean addVertex(V v) {
        return vertexSet.add(v);
    }

    @Override
    public boolean containVertex(V v) {
        return vertexSet.contains(v);
    }

    @Override
    public V getEdgeSource(Edge<V> edge) {
        return edge.getSourceVertex();
    }

    @Override
    public V getEdgeTarget(Edge<V> edge) {
        return edge.getTargetVertex();
    }

    @Override
    public Set<Edge<V>> edgeSet() {
        return edgeSet;
    }

    @Override
    public Edge<V> getEdge(V sourceVertex, V targetVertex) {
        Edge<V> edge = newEdge(sourceVertex, targetVertex);
        return edgeMap.get(edge);
    }

    @Override
    public Edge<V> addEdge(V sourceVertex, V targetVertex) {
        if (sourceVertex.equals(targetVertex))
            throw new IllegalArgumentException("sourceVertex and targetVertex are the same");

        addVertex(sourceVertex);
        addVertex(targetVertex);

        Edge<V> edge = newEdge(sourceVertex, targetVertex);
        return addEdge(edge) ? edge : edgeMap.get(edge);
    }

    @Override
    public Edge<V> addEdge(V sourceVertex, V targetVertex, double weight) {
        Edge<V> edge = addEdge(sourceVertex, targetVertex);
        setEdgeWeight(edge, weight);
        return edge;
    }

    @Override
    public boolean addEdge(Edge<V> edge) {
        if (edgeMap.containsKey(edge))
            return false;

        V sourceVertex = edge.getSourceVertex();
        V targetVertex = edge.getTargetVertex();

        edgeMap.put(edge, edge);
        edgeSet.add(edge);
        if (!adjacencyList.containsKey(sourceVertex))
            adjacencyList.put(sourceVertex, new HashSet<>());
        if (!adjacencyList.containsKey(targetVertex))
            adjacencyList.put(targetVertex, new HashSet<>());
        adjacencyList.get(sourceVertex).add(edge);
        adjacencyList.get(targetVertex).add(edge);
        return true;
    }

    @Override
    public boolean containsEdge(Edge<V> edge) {
        return edgeMap.containsKey(edge);
    }

    @Override
    public boolean containsEdge(V sourceVertex, V targetVertex) {
        Edge<V> edge = newEdge(sourceVertex, targetVertex);
        return edgeMap.containsKey(edge);
    }

    @Override
    public Set<Edge<V>> edgesOf(V vertex) {
        return adjacencyList.get(vertex);
    }

    @Override
    public Set<Edge<V>> incomingEdgesOf(V vertex) {
        return adjacencyList.get(vertex);
    }

    @Override
    public Set<Edge<V>> outgoingEdgesOf(V vertex) {
        return adjacencyList.get(vertex);
    }

    @Override
    public double getEdgeWeight(V sourceVertex, V targetVertex) {
        Edge<V> edge = newEdge(sourceVertex, targetVertex);
        return edgeMap.get(edge).getEdgeWeight();
    }

    @Override
    public double getEdgeWeight(Edge<V> edge) {
        return edgeMap.get(edge).getEdgeWeight();
    }

    @Override
    public void setEdgeWeight(Edge<V> edge, double weight) {
        edgeMap.get(edge).setEdgeWeight(weight);
    }

    @Override
    public void setEdgeWeight(V sourceVertex, V targetVertex, double weight) {
        Edge<V> edge = newEdge(sourceVertex, targetVertex);
        edgeMap.get(edge).setEdgeWeight(weight);
    }

    public abstract Edge<V> newEdge(V sourceVertex, V targetVertex);

    @Override
    public int hashCode() {
        return Objects.hash(vertexSet(), edgeSet());
    }

    @Override
    public boolean equals(Object obj) {
        AbstractGraph<V> that = (AbstractGraph<V>) obj;
        return vertexSet().equals(that.vertexSet()) && edgeSet().equals(that.edgeSet());
    }

    @Override
    public String toString() {
        return String.format("%s(|V|=%d, |E|=%d)",getClass().getName(), vertexSet().size(), edgeSet().size());
    }
}
