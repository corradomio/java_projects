package jext.jgrapht;

import jext.jgrapht.util.VertexInfo;
import org.jgrapht.Graph;
import org.jgrapht.GraphType;
import org.jgrapht.graph.AsSubgraph;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import org.jgrapht.util.SupplierUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class Graphs extends org.jgrapht.Graphs {

    /**
     * Create a new grah based on properties
     *
     * @param directed if directed
     * @param loop     if can have loops
     * @param multiple if canhave multiple edges
     * @param weighted if is weighted
     * @param <V>      the graph vertex type
     * @param <E>      the graph edge type
     * @return
     */
    public static <V, E> Graph<V, E> newGraph(
            boolean directed,
            boolean loop,
            boolean multiple,
            boolean weighted,
            Supplier<E> edgeSupplier,
            Supplier<V> vertexSupplier) {
        GraphTypeBuilder<V, E> gtb = directed
                ? GraphTypeBuilder.directed()
                : GraphTypeBuilder.undirected();

        if (edgeSupplier == null)
            edgeSupplier = weighted
                    ? (Supplier<E>) SupplierUtil.createDefaultWeightedEdgeSupplier()
                    : (Supplier<E>) SupplierUtil.createDefaultEdgeSupplier();

        return gtb.allowingSelfLoops(loop)
                .allowingMultipleEdges(multiple)
                .weighted(weighted)
                .edgeSupplier(edgeSupplier)
                .vertexSupplier(vertexSupplier)
                .buildGraph();
    }

    public static <V, E> Graph<V, E> newGraph(boolean directed, boolean weighted) {
        return newGraph(directed, false, false, weighted, null, null);
    }

    /**
     * Created a new graph with the same properties of the specified graph
     *
     * @param graph graph used as properties'  template
     * @param <V> ver
     * @param <E>
     * @return
     */
    public static <V, E> Graph<V, E> newGraph(Graph<V, E> graph) {
        GraphType gtype = graph.getType();
        return newGraph(
                gtype.isDirected(),
                gtype.isAllowingSelfLoops(),
                gtype.isAllowingMultipleEdges(),
                gtype.isWeighted(),
                graph.getEdgeSupplier(),
                graph.getVertexSupplier());
    }

    public static <V, E> Set<V> predecessorSetOf(Graph<V, E> g, V vertex) {
        return g.incomingEdgesOf(vertex)
                .stream()
                .map(e -> getOppositeVertex(g, e, vertex))
                .collect(Collectors.toSet());
    }

    public static <V, E> Set<V> successorSetOf(Graph<V, E> g, V vertex) {
        return g.outgoingEdgesOf(vertex)
                .stream()
                .map(e -> getOppositeVertex(g, e, vertex))
                .collect(Collectors.toSet());
    }

    public static <V, E> Set<V> neighborSetOf(Graph<V, E> g, V v, EdgeType edgeType) {
        switch (edgeType) {
            case IN_EDGE:
                return predecessorSetOf(g, v);
            case OUT_EDGE:
                return successorSetOf(g, v);
            default:
                return neighborSetOf(g, v);
        }
    }

    public static <V, E> Map<V, Double> weightedNeighborOf(Graph<V, E> g, V v, EdgeType edgeType) {
        // Map<V, Float> neighbor = new HashMap<>();
        //
        // neighborSetOf(g, v, edgeType)
        //         .forEach(t -> {
        //             E e = g.getEdge(v, t);
        //             neighbor.put(t, (float)g.getEdgeWeight(e));
        //         });
        // return neighbor;
        return neighborSetOf(g, v, edgeType)
                .stream()
                .map(t -> g.getEdge(v, t))
                .collect(Collectors.toMap(e -> getOppositeVertex(g, e, v), g::getEdgeWeight));
    }

    /**
     * Add an edge and the vertices
     *
     * @param g            the graph
     * @param sourceVertex source vertex of the edge
     * @param targetVertex target vertex of the edge
     * @param <V>          the graph vertex type
     * @param <E>          the graph edge type
     * @return the created edge
     */
    public static <V, E> E addEdge(Graph<V, E> g, V sourceVertex, V targetVertex) {
        return addEdgeWithVertices(g, sourceVertex, targetVertex);
    }

    public static <V, E> Set<E> addEdges(Graph<V, E> g, V sourceVertex, Collection<V> targetVertices) {
        Set<E> edges = new HashSet<>();
        for (V targetVertex : targetVertices)
            edges.add(addEdge(g, sourceVertex, targetVertex));
        return edges;
    }

    /**
     * Compute the closure of the vertex
     *
     * @param g           the graph
     * @param startVertex start vertex used to compute the closure
     * @param <V>         the graph vertex type
     * @param <E>         the graph edge type
     * @return the 'closed' closure (it contains startVertex)
     */
    public static <V, E> Set<V> closureOf(Graph<V, E> g, V startVertex) {
        Set<V> visited = new HashSet<>();
        Queue<V> toVisit = new LinkedList<>();

        toVisit.add(startVertex);

        while (!toVisit.isEmpty()) {
            V v = toVisit.remove();
            visited.add(v);

            successorListOf(g, v).forEach(t -> {
                if (!visited.contains(t))
                    toVisit.add(t);
            });
        }

        return visited;
    }

    public static <V, E> Set<V> closureOf(Map<V, VertexInfo<V>> vinfoMap, V startVertex) {
        Set<V> visited = new HashSet<>();
        Queue<V> toVisit = new LinkedList<>();

        toVisit.add(startVertex);

        while (!toVisit.isEmpty()) {
            V v = toVisit.remove();
            if (visited.contains(v))
                continue;
            visited.add(v);
            toVisit.addAll(vinfoMap.get(v).neighbor);
        }
        return visited;
    }

    /**
     * Check if the graph is 'null' (without edges)
     *
     * @param g   the graph
     * @param <V> the graph vertex type
     * @param <E> the graph edge type
     * @return if the graph is null
     */
    public static <V, E> boolean isNull(Graph<V, E> g) {
        return g.edgeSet().isEmpty();
    }

    /**
     * Check if the graph is connected
     *
     * @param g   the graph
     * @param <V> the graph vertex type
     * @param <E> the graph edge type
     * @return if the graph is connected
     */
    public static <V, E> boolean isConnected(Graph<V, E> g) {
        Set<V> vset = g.vertexSet();
        if (vset.isEmpty())
            return true;
        V startVertex = vset.iterator().next();
        Set<V> component = closureOf(g, startVertex);
        return vset.size() == component.size();
    }

    /**
     * Find the vertices of the graph components
     *
     * @param g   the graph
     * @param <V> the graph vertex type
     * @param <E> the graph edge type
     * @return the list of components
     */
    public static <V, E> List<Set<V>> components(Graph<V, E> g) {
        Set<V> vset = new HashSet<>(g.vertexSet());
        List<Set<V>> components = new ArrayList<>();

        while (!vset.isEmpty()) {
            V startVertex = vset.iterator().next();
            Set<V> component = closureOf(g, startVertex);
            components.add(component);
            vset.removeAll(component);
        }
        return components;
    }

    public static <V, E> Graph<V, E> getLargestComponent(Graph<V, E> g) {
        final Set<V>[] selected = new Set[]{Collections.emptySet()};

        components(g).forEach(c -> {
            if (c.size() > selected[0].size())
                selected[0] = c;
        });

        if (selected[0].size() == g.vertexSet().size())
            return g;

        return new AsSubgraph<>(g, selected[0]);
    }

    // ----------------------------------------------------------------------
    // Other
    // ----------------------------------------------------------------------

    public static <V, E> int order(Graph<V, E> g) {
        return g.vertexSet().size();
    }

    public static <V, E> int size(Graph<V, E> g) {
        return g.edgeSet().size();
    }

    public static <V, E> double density(Graph<V, E> g) {
        long nv = g.vertexSet().size();

        if (g.getType().isDirected())
            return (0.+g.edgeSet().size())/(nv*nv);
        else
            return (2.*g.edgeSet().size())/(nv*(nv-1.));
    }

    // ----------------------------------------------------------------------
    // Dump graph
    // ----------------------------------------------------------------------

    public static <V, E> void describe(Graph<V, E> g) {
        System.out.println("Graph: |V|=%d, |E|=%d\n");
    }
}
