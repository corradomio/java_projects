package jext.optim.domain.vgraph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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

        Edge<V> edge = newEdge(sourceVertex, targetVertex);
        return addEdge(edge) ? edge : edgeMap.get(edge);
    }

    @Override
    public boolean addEdge(Edge<V> edge) {
        if (edgeMap.containsKey(edge))
            return false;

        V sourceVertex = edge.getSourceVertex();
        V targetVertex = edge.getTargetVertex();

        edgeMap.put(edge, edge);
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
        return edgeSet.contains(edge);
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
    public float getEdgeWeight(V sourceVertex, V targetVertex) {
        Edge<V> edge = newEdge(sourceVertex, targetVertex);
        return edgeMap.get(edge).getEdgeWeight();
    }

    @Override
    public float getEdgeWeight(Edge<V> edge) {
        return edgeMap.get(edge).getEdgeWeight();
    }

    @Override
    public void setEdgeWeight(Edge<V> edge, float weight) {
        edgeMap.get(edge).setEdgeWeight(weight);
    }

    @Override
    public void setEdgeWeight(V sourceVertex, V targetVertex, float weight) {
        Edge<V> edge = newEdge(sourceVertex, targetVertex);
        edgeMap.get(edge).setEdgeWeight(weight);
    }

    protected abstract Edge<V> newEdge(V sourceVertex, V targetVertex);
}
