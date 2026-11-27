package jext.igrapht;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.GraphType;
import org.jgrapht.graph.AbstractGraph;
import org.jgrapht.graph.DefaultGraphType;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

/**
 * A JGraphT graph whose topology and optional edge weights are stored in igraph.
 *
 * <p>Vertices and edges retain their Java objects; their integer indices are
 * only an implementation detail. Mutating a graph invalidates outstanding
 * collection iterators. This class is not thread safe.</p>
 */
public class IGraph<V, E> extends AbstractGraph<V, E> implements AutoCloseable {
    private static final int OUT = 0;
    private static final int IN = 1;
    private static final int ALL = 2;

    private final NativeGraph nativeGraph;
    private final GraphType type;
    private final Supplier<V> vertexSupplier;
    private final Supplier<E> edgeSupplier;
    private final List<V> vertices = new ArrayList<>();
    private final List<Edge<E>> edges = new ArrayList<>();
    private final Map<V, Integer> vertexIds = new LinkedHashMap<>();
    private final Map<E, Integer> edgeIds = new LinkedHashMap<>();
    private final Set<V> vertexView = new VertexView();
    private final Set<E> edgeView = new EdgeView();

    public IGraph(boolean directed) {
        this(directed, null, null);
    }

    public IGraph(boolean directed, Supplier<V> vertexSupplier, Supplier<E> edgeSupplier) {
        this(new DefaultGraphType.Builder(directed, !directed).weighted(true).build(),
            vertexSupplier, edgeSupplier, directed);
    }

    /** Creates a weighted graph with the specified graph-type restrictions. */
    public IGraph(GraphType type, Supplier<V> vertexSupplier, Supplier<E> edgeSupplier) {
        this(type, vertexSupplier, edgeSupplier, type.isDirected());
    }

    public IGraph(GraphType type) {
        this(type, null, null);
    }

    private IGraph(GraphType type, Supplier<V> vertexSupplier, Supplier<E> edgeSupplier, boolean directed) {
        this.type = Objects.requireNonNull(type, "type");
        if (type.isMixed() || !type.isDirected() && !type.isUndirected()) {
            throw new IllegalArgumentException("Mixed graphs are not supported");
        }
        if (!type.isAllowingCycles()) {
            throw new IllegalArgumentException("Acyclic-only graph types are not supported");
        }
        if (!type.isModifiable()) {
            throw new IllegalArgumentException("Unmodifiable graph types are not supported");
        }
        this.vertexSupplier = vertexSupplier;
        this.edgeSupplier = edgeSupplier;
        this.nativeGraph = new NativeGraph(directed);
    }

    private static final class Edge<E> {
        final E object;
        int source;
        int target;
        double weight;

        Edge(E object, int source, int target, double weight) {
            this.object = object;
            this.source = source;
            this.target = target;
            this.weight = weight;
        }
    }

    @Override public GraphType getType() { return type; }
    @Override public Supplier<V> getVertexSupplier() { return vertexSupplier; }
    @Override public Supplier<E> getEdgeSupplier() { return edgeSupplier; }
    @Override public Set<V> vertexSet() { return vertexView; }
    @Override public Set<E> edgeSet() { return edgeView; }
    @Override public boolean containsVertex(V vertex) { return vertexIds.containsKey(vertex); }

    @Override
    public V addVertex() {
        if (vertexSupplier == null) throw new UnsupportedOperationException("No vertex supplier");
        V vertex = Objects.requireNonNull(vertexSupplier.get(), "The vertex supplier returned null");
        if (!addVertex(vertex)) {
            throw new IllegalArgumentException("The vertex supplier returned a duplicate vertex");
        }
        return vertex;
    }

    @Override
    public boolean addVertex(V vertex) {
        Objects.requireNonNull(vertex, "vertex");
        if (vertexIds.containsKey(vertex)) return false;
        int id = nativeGraph.addVertices(1);
        vertices.add(vertex);
        vertexIds.put(vertex, id);
        return true;
    }

    @Override
    public boolean addEdge(V sourceVertex, V targetVertex, E edge) {
        Objects.requireNonNull(edge, "edge");
        assertVertex(sourceVertex);
        assertVertex(targetVertex);
        if (containsEdge(edge)) return false;
        if (!type.isAllowingSelfLoops() && Objects.equals(sourceVertex, targetVertex)) {
            throw new IllegalArgumentException("Self-loops are not allowed");
        }
        if (!type.isAllowingMultipleEdges() && containsEdge(sourceVertex, targetVertex)) return false;

        int source = vertexId(sourceVertex);
        int target = vertexId(targetVertex);
        int id = nativeGraph.addEdge(source, target, 1.0);
        edges.add(new Edge<>(edge, source, target, 1.0));
        edgeIds.put(edge, id);
        return true;
    }

    @Override
    public E addEdge(V sourceVertex, V targetVertex) {
        if (edgeSupplier == null) throw new UnsupportedOperationException("No edge supplier");
        E edge = Objects.requireNonNull(edgeSupplier.get(), "The edge supplier returned null");
        return addEdge(sourceVertex, targetVertex, edge) ? edge : null;
    }

    @Override
    public E getEdge(V sourceVertex, V targetVertex) {
        if (!containsVertex(sourceVertex) || !containsVertex(targetVertex)) return null;
        int source = vertexId(sourceVertex);
        int target = vertexId(targetVertex);
        for (Edge<E> edge : edges) {
            if (connects(edge, source, target)) return edge.object;
        }
        return null;
    }

    @Override
    public Set<E> getAllEdges(V sourceVertex, V targetVertex) {
        if (!containsVertex(sourceVertex) || !containsVertex(targetVertex)) return null;
        int source = vertexId(sourceVertex);
        int target = vertexId(targetVertex);
        Set<E> result = new java.util.LinkedHashSet<>();
        for (Edge<E> edge : edges) {
            if (connects(edge, source, target)) result.add(edge.object);
        }
        return Collections.unmodifiableSet(result);
    }

    @Override public V getEdgeSource(E edge) { return vertices.get(edge(edge).source); }
    @Override public V getEdgeTarget(E edge) { return vertices.get(edge(edge).target); }

    @Override
    public double getEdgeWeight(E edge) {
        return nativeGraph.getEdgeWeight(edgeId(edge));
    }

    @Override
    public void setEdgeWeight(E edge, double weight) {
        Objects.requireNonNull(edge, "edge");
        if (!type.isWeighted()) throw new UnsupportedOperationException("Graph is unweighted");
        if (!Double.isFinite(weight) || weight < 0) {
            throw new IllegalArgumentException("Weights must be finite and non-negative");
        }
        nativeGraph.setEdgeWeight(edgeId(edge), weight);
        edge(edge).weight = weight;
    }

    @Override public int degreeOf(V vertex) { return nativeGraph.degree(vertexId(vertex), ALL, type.isAllowingSelfLoops()); }
    @Override public int inDegreeOf(V vertex) { return nativeGraph.degree(vertexId(vertex), IN, type.isAllowingSelfLoops()); }
    @Override public int outDegreeOf(V vertex) { return nativeGraph.degree(vertexId(vertex), OUT, type.isAllowingSelfLoops()); }

    @Override
    public Set<E> edgesOf(V vertex) {
        int id = vertexId(vertex);
        Set<E> result = new java.util.LinkedHashSet<>();
        for (Edge<E> edge : edges) {
            if (edge.source == id || edge.target == id) result.add(edge.object);
        }
        return Collections.unmodifiableSet(result);
    }

    @Override
    public Set<E> incomingEdgesOf(V vertex) {
        if (!type.isDirected()) return edgesOf(vertex);
        int id = vertexId(vertex);
        Set<E> result = new java.util.LinkedHashSet<>();
        for (Edge<E> edge : edges) {
            if (edge.target == id) result.add(edge.object);
        }
        return Collections.unmodifiableSet(result);
    }

    @Override
    public Set<E> outgoingEdgesOf(V vertex) {
        if (!type.isDirected()) return edgesOf(vertex);
        int id = vertexId(vertex);
        Set<E> result = new java.util.LinkedHashSet<>();
        for (Edge<E> edge : edges) {
            if (edge.source == id) result.add(edge.object);
        }
        return Collections.unmodifiableSet(result);
    }

    @Override
    public boolean containsEdge(V sourceVertex, V targetVertex) {
        return containsVertex(sourceVertex) && containsVertex(targetVertex)
            && getEdge(sourceVertex, targetVertex) != null;
    }

    @Override
    public boolean containsEdge(E edge) {
        return edge != null && edgeIds.containsKey(edge);
    }

    @Override
    public E removeEdge(V sourceVertex, V targetVertex) {
        E edge = getEdge(sourceVertex, targetVertex);
        if (edge != null) removeEdge(edge);
        return edge;
    }

    @Override
    public boolean removeEdge(E edge) {
        if (!containsEdge(edge)) return false;
        int removed = edgeId(edge);
        nativeGraph.removeEdge(removed);
        edgeIds.remove(edge);
        edges.remove(removed);
        for (int i = removed; i < edges.size(); i++) {
            edgeIds.put(edges.get(i).object, i);
        }
        return true;
    }

    @Override
    public boolean removeVertex(V vertex) {
        if (!containsVertex(vertex)) return false;
        int removed = vertexId(vertex);
        // Remove incident Java edges first so native edge weights stay aligned.
        for (E edge : new ArrayList<>(edgesOf(vertex))) removeEdge(edge);
        nativeGraph.removeVertex(removed);
        vertices.remove(removed);
        vertexIds.remove(vertex);
        for (int i = removed; i < vertices.size(); i++) vertexIds.put(vertices.get(i), i);
        for (Edge<E> edge : edges) {
            if (edge.source > removed) edge.source--;
            if (edge.target > removed) edge.target--;
        }
        return true;
    }

    public boolean isConnected() {
        return isConnected(false);
    }

    /** For a directed graph, chooses weak or strong connectivity. */
    public boolean isConnected(boolean strong) {
        return nativeGraph.isConnected(strong);
    }

    /** Returns a shortest path using non-negative native edge weights. */
    public GraphPath<V, E> shortestPath(V sourceVertex, V targetVertex) {
        int source = vertexId(sourceVertex);
        int target = vertexId(targetVertex);
        int[] encoded = nativeGraph.shortestPath(source, target);
        if (encoded.length == 0 && source != target) return null;
        List<E> pathEdges = new ArrayList<>(encoded.length);
        double weight = 0;
        for (int edgeId : encoded) {
            Edge<E> edge = edgeAt(edgeId);
            pathEdges.add(edge.object);
            weight += type.isWeighted() ? edge.weight : 1;
        }
        return new IGraphPath<>(this, sourceVertex, targetVertex, pathEdges, weight);
    }

    /** Releases native memory. Native-backed operations fail after closure. */
    @Override
    public void close() {
        nativeGraph.close();
    }

    private void assertVertex(V vertex) {
        Objects.requireNonNull(vertex, "vertex");
        if (!vertexIds.containsKey(vertex)) throw new IllegalArgumentException("No such vertex: " + vertex);
    }

    private int vertexId(V vertex) {
        assertVertex(vertex);
        return vertexIds.get(vertex);
    }

    private int edgeId(E edge) {
        Objects.requireNonNull(edge, "edge");
        Integer id = edgeIds.get(edge);
        if (id == null) throw new IllegalArgumentException("No such edge in graph");
        return id;
    }

    private Edge<E> edge(E edge) {
        return edges.get(edgeId(edge));
    }

    private Edge<E> edgeAt(int id) {
        return edges.get(id);
    }

    private boolean connects(Edge<E> edge, int source, int target) {
        return edge.source == source && edge.target == target
            || !type.isDirected() && edge.source == target && edge.target == source;
    }

    private abstract class CheckedIterator<T> implements Iterator<T> {
        int cursor;
        int last = -1;
        int size;

        CheckedIterator(int size) {
            this.size = size;
        }

        abstract T valueAt(int index);
        abstract void removeAt(int index);
        abstract int currentSize();

        void checkSize() {
            if (size != currentSize()) throw new java.util.ConcurrentModificationException();
        }

        @Override
        public boolean hasNext() {
            checkSize();
            return cursor < size;
        }

        @Override
        public T next() {
            checkSize();
            if (cursor >= size) throw new NoSuchElementException();
            last = cursor;
            return valueAt(cursor++);
        }

        @Override
        public void remove() {
            checkSize();
            if (last < 0) throw new IllegalStateException();
            removeAt(last);
            cursor = last;
            last = -1;
            size--;
        }
    }

    private final class VertexView extends AbstractSet<V> {
        @Override public int size() { return vertices.size(); }
        @Override public boolean contains(Object value) { return vertexIds.containsKey(value); }

        @Override
        public Iterator<V> iterator() {
            return new CheckedIterator<V>(vertices.size()) {
                @Override V valueAt(int index) { return vertices.get(index); }
                @Override void removeAt(int index) { removeVertex(vertices.get(index)); }
                @Override int currentSize() { return vertices.size(); }
            };
        }
    }

    private final class EdgeView extends AbstractSet<E> {
        @Override public int size() { return edges.size(); }
        @Override public boolean contains(Object value) { return containsEdge((E) value); }

        @Override
        public Iterator<E> iterator() {
            return new CheckedIterator<E>(edges.size()) {
                @Override E valueAt(int index) { return edges.get(index).object; }
                @Override void removeAt(int index) { removeEdge(edges.get(index).object); }
                @Override int currentSize() { return edges.size(); }
            };
        }
    }
}
