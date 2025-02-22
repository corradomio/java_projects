package jext.jgrapht;

import jext.jgrapht.edges.Directed;
import jext.jgrapht.edges.EdgeType;
import jext.jgrapht.edges.Weighted;
import org.jgrapht.Graph;
import org.jgrapht.GraphType;
import org.jgrapht.graph.AsSubgraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedAcyclicGraph;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import org.jgrapht.util.SupplierUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@SuppressWarnings({"java:S125"})
public abstract class Graphs extends org.jgrapht.Graphs {

    // public static <V, E> boolean addGraph(Graph<? super V, ? super E> destination, Graph<V, E> source)
    // public static <V, E> void    addGraphReversed(Graph<? super V, ? super E> destination, Graph<V, E> source)
    // public static <V, E> Graph<V, E> undirectedGraph(Graph<V, E> g)
    //
    // public static <V, E> E       addEdge(Graph<V, E> g, V sourceVertex, V targetVertex, double weight)
    // public static <V, E> E       addEdgeWithVertices(Graph<V, E> g, V sourceVertex, V targetVertex)
    // public static <V, E> boolean addEdgeWithVertices(Graph<V, E> targetGraph, Graph<V, E> sourceGraph, E edge)
    // public static <V, E> E       addEdgeWithVertices(Graph<V, E> g, V sourceVertex, V targetVertex, double weight)
    // public static <V, E> boolean addAllEdges(Graph<? super V, ? super E> destination, Graph<V, E> source, Collection<? extends E> edges)
    // public static <V, E> void    addOutgoingEdges(Graph<V, E> graph, V source, Iterable<V> targets)
    // public static <V, E> void    addIncomingEdges(Graph<V, E> graph, V target, Iterable<V> sources)
    //
    // public static <V, E> V getOppositeVertex(Graph<V, E> g, E e, V v)
    // public static <V, E> VertexToIntegerMapping<V> getVertexToIntegerMapping(Graph<V, E> graph)
    //
    // public static <V, E> boolean addAllVertices(Graph<? super V, ? super E> destination, Collection<? extends V> vertices)
    // public static <V, E> List<V> neighborListOf(Graph<V, E> g, V vertex)
    // public static <V, E> Set<V>  neighborSetOf(Graph<V, E> g, V vertex)
    // public static <V, E> List<V> predecessorListOf(Graph<V, E> g, V vertex)
    // public static <V, E> List<V> successorListOf(Graph<V, E> g, V vertex)
    // public static <V, E> boolean testIncidence(Graph<V, E> g, E e, V v)
    //
    // public static <V, E> boolean removeVertexAndPreserveConnectivity(Graph<V, E> graph, V vertex)
    // public static <V, E> boolean removeVerticesAndPreserveConnectivity(Graph<V, E> graph, Predicate<V> predicate)
    // public static <V, E> boolean removeVertexAndPreserveConnectivity(Graph<V, E> graph, Iterable<V> vertices)
    // public static <V, E> boolean vertexHasSuccessors(Graph<V, E> graph, V vertex)
    // public static <V, E> boolean vertexHasPredecessors(Graph<V, E> graph, V vertex)

    // ----------------------------------------------------------------------
    // Graph constructors
    // ----------------------------------------------------------------------

    /**
     * Create a new graph.
     * The class is directed or weighted based on the edge class properties
     *
     * @param vertexClass class of the vertex
     * @param edgeClass class of the edge
     * @param <V> vertices type
     * @param <E> edges type
     * @return a new Graph object
     */
    public static <V, E> Graph<V, E> newGraph(Class<V> vertexClass, Class<E> edgeClass) {
        boolean directed = Directed.class.isAssignableFrom(edgeClass);
        boolean weighed  = Weighted.class.isAssignableFrom(edgeClass);

        return newGraph(directed, weighed, vertexClass, edgeClass);
    }

    /**
     * Create a new graph.
     *
     * @param loop      if can have loops
     * @param multiple  if can have multiple edges
     * @param cycles    if can have cycles
     * @param vertexClass vertex class
     * @param edgeClass   edge class
     * @param <V> vertices type
     * @param <E> edges type
     * @return a new Graph object
     */
    public static <V, E> Graph<V, E> newGraph(
        boolean loop,
        boolean multiple,
        boolean cycles,
        Class<V> vertexClass,
        Class<E> edgeClass) {

        boolean directed = Directed.class.isAssignableFrom(edgeClass);
        boolean weighted  = Weighted.class.isAssignableFrom(edgeClass);
        Supplier<V> vertexSupplier = vertexSupplier(vertexClass);
        Supplier<E> edgeSupplier   = edgeSupplier(edgeClass);

        return newGraph(directed, loop, multiple, weighted, cycles, vertexSupplier, edgeSupplier);
    }

    /**
     * Create a new graph.
     *
     * @param directed    if directed
     * @param weighted    if weighted
     * @param vertexClass vertex class
     * @param edgeClass   edge class
     * @param <V> vertices type
     * @param <E> edges type
     * @return a new Graph object
     */
    public static <V, E> Graph<V, E> newGraph(
        boolean directed,
        boolean weighted,
        Class<V> vertexClass,
        Class<E> edgeClass) {
        Supplier<V> vertexSupplier = vertexSupplier(vertexClass);
        Supplier<E> edgeSupplier   = edgeSupplier(edgeClass);

        return newGraph(directed, false, false, weighted, true, vertexSupplier, edgeSupplier);
    }

    /**
     * Create a new graph based on properties
     *
     * @param directed if directed
     * @param vertexSupplier vertex factory
     * @param edgeSupplier edge factory
     * @param <V> vertices type
     * @param <E> edges type
     * @return a new Graph object
     */
    public static <V, E> Graph<V, E> newGraph(
        boolean directed,
        Supplier<V> vertexSupplier,
        Supplier<E> edgeSupplier) {
        return newGraph(directed, false, false, false, true, vertexSupplier, edgeSupplier);
    }

    /**
     * Create a new graph based on properties
     *
     * @param directed if directed
     * @param loop     if can have loops
     * @param multiple if can have multiple edges
     * @param weighted if is weighted
     * @param cycles   if cycles are permitted
     * @param vertexSupplier vertex factory
     * @param edgeSupplier edge factory
     * @param <V> vertices type
     * @param <E> edges type
     * @return a new Graph object
     */
    public static <V, E> Graph<V, E> newGraph(
            boolean directed,
            boolean loop,
            boolean multiple,
            boolean weighted,
            boolean cycles,
            Supplier<V> vertexSupplier,
            Supplier<E> edgeSupplier) {

        GraphTypeBuilder<V, E> gtb = directed
                ? GraphTypeBuilder.directed()
                : GraphTypeBuilder.undirected();

        if (edgeSupplier == null)
            edgeSupplier = weighted
                    ? (Supplier<E>) SupplierUtil.createDefaultWeightedEdgeSupplier()
                    : (Supplier<E>) SupplierUtil.createDefaultEdgeSupplier();

        if (directed && !cycles)
            return new DirectedAcyclicGraph<>(
                vertexSupplier,
                edgeSupplier,
                weighted,
                multiple
            );

        return gtb.allowingSelfLoops(loop)
            .weighted(weighted)
            .allowingMultipleEdges(multiple)
            .vertexSupplier(vertexSupplier)
            .edgeSupplier(edgeSupplier)
            .buildGraph();
    }

    /**
     * Created a new graph with the same properties of the specified graph
     *
     * @param template graph used as properties' template
     * @param <V> vertices type
     * @param <E> edges type
     * @return a new Graph object
     */
    public static <V, E> Graph<V, E> newGraph(Graph<V, E> template) {
        GraphType gtype = template.getType();
        return newGraph(
                gtype.isDirected(),
                gtype.isAllowingSelfLoops(),
                gtype.isAllowingMultipleEdges(),
                gtype.isWeighted(),
                gtype.isAllowingCycles(),
                template.getVertexSupplier(),
                template.getEdgeSupplier()
        );
    }

    // ----------------------------------------------------------------------
    // Vertices
    // ----------------------------------------------------------------------

    /**
     * Create a vertex supplier.
     * Simplify the usage of {@link org.jgrapht.util.SupplierUtil}
     *
     * @param vertexClass
     *      supported classes: int, Integer, long, Long, String, UUID
     *      and a generic class with a valid default constructor
     */
    public static <V> Supplier<V> vertexSupplier(Class<V> vertexClass) {
        if (vertexClass.equals(Integer.class) || vertexClass.equals(int.class))
            return (Supplier<V>) SupplierUtil.createIntegerSupplier();
        if (vertexClass.equals(Long.class) || vertexClass.equals(long.class))
            return (Supplier<V>) SupplierUtil.createLongSupplier();
        if (vertexClass.equals(String.class))
            return (Supplier<V>) SupplierUtil.createStringSupplier();
        if (vertexClass.equals(UUID.class))
            return (Supplier<V>) SupplierUtil.createRandomUUIDStringSupplier();
        else
            return SupplierUtil.createSupplier(vertexClass);
    }

    // ----------------------------------------------------------------------

    /**
     * Add the list of vertices
     *
     * @param g the graph
     * @param vertices list of vertices
     * @param <V> vertices type
     * @param <E> edges type
     */
    public static <V, E> void addVertices(Graph<V, E> g, V ... vertices) {
        for (V v : vertices) {
            g.addVertex(v);
        }
    }

    /**
     * Predecessor vertices of the specified vertex
     *
     * @see {@link org.jgrapht.Graphs::predecessorListOf}
     *
     * @param g the graph
     * @param vertex the selected vertex
     * @param <V> vertices type
     * @param <E> edges type
     * @return a set of vertices
     */
    public static <V, E> Set<V> predecessorSetOf(Graph<V, E> g, V vertex) {
        return g.incomingEdgesOf(vertex)
                .stream()
                .map(e -> getOppositeVertex(g, e, vertex))
                .collect(Collectors.toSet());
    }

    /**
     * Successor vertices of the specified vertex
     *
     * @see {@link org.jgrapht.Graphs::successorListOf}
     *
     * @param g the graph
     * @param vertex the selected vertex
     * @param <V> vertices type
     * @param <E> edges type
     * @return a set of vertices
     */
    public static <V, E> Set<V> successorSetOf(Graph<V, E> g, V vertex) {
        return g.outgoingEdgesOf(vertex)
                .stream()
                .map(e -> getOppositeVertex(g, e, vertex))
                .collect(Collectors.toSet());
    }

    /**
     * Neighbor vertices of the specified vertex, parametrized by edge type
     *
     * @param g the graph
     * @param vertex the selected vertex
     * @param <V> vertices type
     * @param <E> edges type
     * @return a set of vertices
     */
    public static <V, E> Set<V> neighborSetOf(Graph<V, E> g, V vertex, EdgeType edgeType) {
        switch (edgeType) {
            case IN_EDGE:
                return predecessorSetOf(g, vertex);
            case OUT_EDGE:
                return successorSetOf(g, vertex);
            default:
                return neighborSetOf(g, vertex);
        }
    }

    // ----------------------------------------------------------------------
    // Edges
    // ----------------------------------------------------------------------

    /**
     * Creates an edge supplier
     *
     * @param edgeClass
     *      supported classes: DefaultEdge, DefaultWeightedEdge
     * @param <E> edges type
     */
    public static <E> Supplier<E> edgeSupplier(Class<E> edgeClass) {
        if (edgeClass.equals(Integer.class) || edgeClass.equals(int.class))
            return (Supplier<E>) SupplierUtil.createIntegerSupplier();
        if (edgeClass.equals(Long.class) || edgeClass.equals(long.class))
            return (Supplier<E>) SupplierUtil.createIntegerSupplier();
        if (edgeClass.equals(String.class))
            return (Supplier<E>) SupplierUtil.createStringSupplier();
        if (edgeClass.equals(DefaultEdge.class))
            return (Supplier<E>) SupplierUtil.createDefaultEdgeSupplier();
        if (edgeClass.equals(DefaultWeightedEdge.class))
            return (Supplier<E>) SupplierUtil.createDefaultWeightedEdgeSupplier();
        else
            return SupplierUtil.createSupplier(edgeClass);
    }

    // ----------------------------------------------------------------------

    /**
     * Add all edges specified as a list of vertex pairs
     *
     * @param g the graph
     * @param edges a listof vertex pairs
     * @param <V> vertices type
     * @param <E> edges type
     */
    public static <V, E> void addEdges(Graph<V, E> g,V ... edges) {
        for (int i=0; i<edges.length; i+=2) {
            V sourceVertex = edges[i  ];
            V targetVertex = edges[i+1];
            addEdge(g, sourceVertex, targetVertex);
        }
    }

    /**
     * Add the edge and the vertices. Simplified version than
     * {@link org.jgrapht.Graphs::addEdgeWithVertices}
     *
     * @param g the graph
     * @param sourceVertex source vertex of the edge
     * @param targetVertex target vertex of the edge
     * @param <V> vertices type
     * @param <E> edges type
     * @return the created edge or null
     */
    public static <V, E> E addEdge(Graph<V, E> g, V sourceVertex, V targetVertex) {
        return addEdgeWithVertices(g, sourceVertex, targetVertex);
    }

    /**
     * Add all edges between the source vertex and the collection of target vertices
     *
     * @param g the graph
     * @param sourceVertex source vertex of the edge
     * @param targetVertices target vertices of the edge
     * @param <V> vertices type
     * @param <E> edges type
     * @return the set of added edges
     */
    public static <V, E> Set<E> addEdges(Graph<V, E> g, V sourceVertex, Collection<V> targetVertices) {
        Set<E> edges = new HashSet<>();
        for (V targetVertex : targetVertices) {
            E edge = addEdge(g, sourceVertex, targetVertex);
            if (edge != null)
                edges.add(edge);
        }
        return edges;
    }

    // ----------------------------------------------------------------------
    // Closure
    // ----------------------------------------------------------------------

    /**
     * Compute the closure of the vertex
     *
     * @param g the graph
     * @param startVertex start vertex used to compute the closure
     * @param <V> vertices type
     * @param <E> edges type
     * @return the 'closed' closure (it contains startVertex)
     */
    public static <V, E> Set<V> closureOf(Graph<V, E> g, V startVertex) {
        Set<V> visited = new HashSet<>();
        Queue<V> toVisit = new LinkedList<>();

        toVisit.add(startVertex);

        while (!toVisit.isEmpty()) {
            V vertex = toVisit.remove();
            visited.add(vertex);

            successorListOf(g, vertex).forEach(t -> {
                if (!visited.contains(t))
                    toVisit.add(t);
            });
        }

        return visited;
    }

    // ----------------------------------------------------------------------
    // Graph properties
    // ----------------------------------------------------------------------

    /**
     * Check if the graph is 'null' (without edges)
     *
     * @param g   the graph
     * @param <V> vertices type
     * @param <E> edges type
     * @return if the graph is null (without edges)
     */
    public static <V, E> boolean isNull(Graph<V, E> g) {
        return g.edgeSet().isEmpty();
    }

    /**
     * Check if the graph is connected
     *
     * @param g   the graph
     * @param <V> vertices type
     * @param <E> edges type
     * @return if the graph is connected
     */
    public static <V, E> boolean isConnected(Graph<V, E> g) {
        Set<V> vset = g.vertexSet();
        if (vset.isEmpty()) return true;
        V startVertex = vset.iterator().next();
        Set<V> component = closureOf(g, startVertex);
        return vset.size() == component.size();
    }

    public static <V, E> boolean isIsolated(Graph<V, E> g, V v) {
        return g.degreeOf(v) == 0;
    }

    // ----------------------------------------------------------------------
    // Graph components
    // ----------------------------------------------------------------------

    /**
     * Search the isolated vertices.
     * A vertex is isolated if it has no predecessors nor successors
     *
     * @param g graph
     * @param <V> vertices type
     * @param <E> edges type
     * @return set of isolated vertices
     */
    public static <V, E> Set<V> isolatedVertices(Graph<V, E> g) {
        return g.vertexSet().parallelStream()
            .filter( v -> isIsolated(g, v))
            .collect(Collectors.toSet());
    }

    /**
     * Find the vertices of the graph components
     *
     * @param g   the graph
     * @param <V> vertices type
     * @param <E> edges type
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

    /**
     * Return the largest component of the graph
     *
     * @param g the graph
     * @param <V> vertices type
     * @param <E> edges type
     * @return the largest component
     */
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
    // Graph properties
    // ----------------------------------------------------------------------

    public static <V, E> Graph<V, E> union(Graph<V, E> g1, Graph<V, E> g2) {
        Graph<V, E> gu = newGraph(g1);

        g1.vertexSet().forEach(gu::addVertex);
        g2.vertexSet().forEach(gu::addVertex);

        g1.edgeSet().forEach(e -> addEdge(gu, g1, e));
        g2.edgeSet().forEach(e -> addEdge(gu, g2, e));

        return gu;
    }

    private static <V, E> void addEdge(Graph<V, E> a, Graph<V, E> g, E e) {
        V sourceVertex = g.getEdgeSource(e);
        V targetVertex = g.getEdgeTarget(e);
        a.addEdge(sourceVertex, targetVertex);
    }

    // ----------------------------------------------------------------------
    // Graph properties
    // ----------------------------------------------------------------------

    public static <V,E> Graph<V,E> addPath(Graph<V,E> g, V... vertices) {
        if (vertices == null || vertices.length < 2)
            return g;

        for(int i=0; i<vertices.length-1; ++i)
            addEdge(g, vertices[i], vertices[i+1]);

        return g;
    }

    public static <V, E> Graph<V, E> removeVertices(Graph<V, E> g, Collection<V> vertices) {
        for(V v : vertices)
            g.removeVertex(v);
        return g;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
