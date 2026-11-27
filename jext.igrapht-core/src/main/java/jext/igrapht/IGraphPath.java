package jext.igrapht;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/** An immutable graph path. */
public final class IGraphPath<V, E> implements GraphPath<V, E> {
    private final Graph<V, E> graph;
    private final V startVertex;
    private final V endVertex;
    private final List<E> edgeList;
    private final double weight;

    public IGraphPath(Graph<V, E> graph, V startVertex, V endVertex, List<E> edgeList, double weight) {
        this.graph = Objects.requireNonNull(graph, "graph");
        this.startVertex = Objects.requireNonNull(startVertex, "startVertex");
        this.endVertex = Objects.requireNonNull(endVertex, "endVertex");
        this.edgeList = Collections.unmodifiableList(List.copyOf(edgeList));
        this.weight = weight;
        if (Double.isNaN(weight) || weight < 0 || Double.isInfinite(weight)) {
            throw new IllegalArgumentException("Invalid path weight");
        }
        V current = startVertex;
        for (E edge : this.edgeList) {
            if (!graph.containsEdge(edge)) {
                throw new IllegalArgumentException("Path contains an edge outside the graph");
            }
            if (graph.getType().isDirected()) {
                if (!current.equals(graph.getEdgeSource(edge))) {
                    throw new IllegalArgumentException("Path contains a disconnected directed edge");
                }
                current = graph.getEdgeTarget(edge);
            } else {
                current = Graphs.getOppositeVertex(graph, edge, current);
            }
        }
        if (!current.equals(endVertex)) throw new IllegalArgumentException("Path does not reach its end vertex");
    }

    @Override public Graph<V, E> getGraph() { return graph; }
    @Override public V getStartVertex() { return startVertex; }
    @Override public V getEndVertex() { return endVertex; }
    @Override public List<E> getEdgeList() { return edgeList; }

    @Override
    public List<V> getVertexList() {
        if (edgeList.isEmpty()) return List.of(startVertex);
        java.util.ArrayList<V> vertices = new java.util.ArrayList<>(edgeList.size() + 1);
        vertices.add(startVertex);
        V current = startVertex;
        for (E edge : edgeList) {
            current = Graphs.getOppositeVertex(graph, edge, current);
            vertices.add(current);
        }
        return Collections.unmodifiableList(vertices);
    }

    @Override public double getWeight() { return weight; }
    @Override public int getLength() { return edgeList.size(); }

    @Override
    public String toString() {
        return getVertexList().toString();
    }

    /** Used only to validate paths without imposing an additional graph dependency. */
    private static final class Graphs {
        static <V, E> V getOppositeVertex(Graph<V, E> graph, E edge, V vertex) {
            if (graph.getEdgeSource(edge).equals(vertex)) return graph.getEdgeTarget(edge);
            if (graph.getEdgeTarget(edge).equals(vertex)) return graph.getEdgeSource(edge);
            throw new IllegalArgumentException("Path edge is not incident to the current vertex");
        }
    }
}
