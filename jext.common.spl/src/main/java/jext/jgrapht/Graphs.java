package jext.jgrapht;

import org.jgrapht.Graph;
import org.jgrapht.nio.*;
import org.jgrapht.nio.csv.CSVExporter;
import org.jgrapht.nio.dot.DOTExporter;
import org.jgrapht.nio.dot.DOTImporter;
import org.jgrapht.nio.json.JSONExporter;
import org.jgrapht.nio.json.JSONImporter;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


/*
    public abstract class Graphs {
        E       addEdge(Graph<V, E> g, V sourceVertex, V targetVertex, double weight)
        E       addEdgeWithVertices(Graph<V, E> g, V sourceVertex, V targetVertex)
        boolean addEdgeWithVertices(Graph<V, E> targetGraph, Graph<V, E> sourceGraph, E edge)
        E       addEdgeWithVertices(Graph<V, E> g, V sourceVertex, V targetVertex, double weight)

        boolean addGraph(Graph<? super V, ? super E> destination, Graph<V, E> source)
        void    addGraphReversed(Graph<? super V, ? super E> destination, Graph<V, E> source)

        boolean addAllEdges(Graph<? super V, ? super E> destination, Graph<V, E> source, Collection<? extends E> edges)
        boolean addAllVertices(Graph<? super V, ? super E> destination, Collection<? extends V> vertices)

        List<V> neighborListOf(   Graph<V, E> g, V vertex)
        Set<V>  neighborSetOf(    Graph<V, E> g, V vertex)
        List<V> predecessorListOf(Graph<V, E> g, V vertex)
        List<V> successorListOf(  Graph<V, E> g, V vertex)

        Graph<V, E> undirectedGraph(Graph<V, E> g)

        boolean testIncidence(Graph<V, E> g, E e, V v)

        V getOppositeVertex(Graph<V, E> g, E e, V v)

        boolean removeVertexAndPreserveConnectivity(Graph<V, E> graph, V vertex)
        boolean removeVerticesAndPreserveConnectivity(Graph<V, E> graph, Predicate<V> predicate)
        boolean removeVertexAndPreserveConnectivity(Graph<V, E> graph, Iterable<V> vertices)

        void addOutgoingEdges(Graph<V, E> graph, V source, Iterable<V> targets)
        void addIncomingEdges(Graph<V, E> graph, V target, Iterable<V> sources)

        boolean vertexHasSuccessors(Graph<V, E> graph, V vertex)
        boolean vertexHasPredecessors(Graph<V, E> graph, V vertex)

        VertexToIntegerMapping<V> getVertexToIntegerMapping(Graph<V, E> graph)
    }
 */

public abstract class Graphs extends org.jgrapht.Graphs {

    // ----------------------------------------------------------------------
    // IO
    // ----------------------------------------------------------------------

    /**
     * Add the edge AND the vertices
     *
     * @param g graph
     * @param sourceVertex vertex
     * @param targetVertex vertex
     * @param <V> vertices type
     * @param <E> edges type
     * @return created edge
     */
    public static <V, E> E addEdge(Graph<V, E> g, V sourceVertex, V targetVertex) {
        return addEdgeWithVertices(g, sourceVertex, targetVertex);
    }

    public static <V, E> Graph<V, E> removeVertices(Graph<V, E> g, Collection<V> vertices) {
        for(V v : vertices)
            g.removeVertex(v);
        return g;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    /**
     * Compute the closure of the specified vertex inside the graph.
     * The closure is the set of all vertices reachable from the specified vertex
     *
     * @param g graph
     * @param startVertex vertex
     * @param <V> vertices type
     * @param <E> edges type
     * @return closure of
     */
    public static <V, E> Set<V> closureOf(Graph<V, E> g, V startVertex) {
        Set<V> visited = new HashSet<>();
        Queue<V> toVisit = new LinkedList<>();
        toVisit.add(startVertex);

        while(!toVisit.isEmpty()) {
            V v = toVisit.remove();
            visited.add(v);

            successorListOf(g, v).forEach(t -> {
                if (!visited.contains(t))
                    toVisit.add(t);
            });
        }

        return visited;
    }

    public static <V, E> boolean isIsolated(Graph<V, E> g, V v) {
        // return !(vertexHasPredecessors(g, v) || vertexHasSuccessors(g, v));
        return g.degreeOf(v) == 0;
    }

    // public static <V, E> boolean hasPredecessors(Graph<V, E> g, V v) {
    //     return vertexHasPredecessors(g, v);
    // }

    // public static <V, E> boolean hasSuccessors(Graph<V, E> g, V v) {
    //     return vertexHasSuccessors(g, v);
    // }

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

    // ----------------------------------------------------------------------
    // IO
    // ----------------------------------------------------------------------

    public static <V, E> Graph<V, E> exportGraph(Graph<V, E> g, String path) throws IOException {
        BaseExporter<V, E> exporter;

        if (path.endsWith(".json")) {
            exporter = new JSONExporter<>();
            // ((JSONExporter<V, E>)exporter).setVertexIdProvider(v -> v.toString());
            ((JSONExporter<V, E>)exporter).setVertexAttributeProvider(v -> {
                Map<String, Attribute> attributes = new HashMap<>();
                attributes.put("label", new DefaultAttribute<String>(v.toString(), AttributeType.STRING));
                return attributes;
            });
        }
        else if (path.endsWith(".dot")) {
            exporter = new DOTExporter<>();
            ((DOTExporter<V, E>)exporter).setVertexAttributeProvider(v -> {
                Map<String, Attribute> attributes = new HashMap<>();
                attributes.put("label", new DefaultAttribute<String>(v.toString(), AttributeType.STRING));
                return attributes;
            });
        }
        else if (path.endsWith(".csv")) {
            exporter = new CSVExporter<>();
        }
        else {
            throw new IOException("Unsupported file format");
        }

        ((GraphExporter<V, E>)exporter).exportGraph(g, new File(path));
        return g;
    }

    public static <V, E> Graph<V, E> importGraph(Graph<V, E> g, String path) throws IOException {

        GraphImporter<V, E> importer;

        if (path.endsWith(".json")) {
            importer = new JSONImporter<>();
            ((JSONImporter<V, E>)importer).addVertexAttributeConsumer((a, b) -> {
                System.out.printf("%s %s\n", a.toString(), b.toString());
            });
        }
        else if (path.endsWith(".dot")) {
            importer = new DOTImporter<>();
            ((DOTImporter<V, E>)importer).addVertexAttributeConsumer((a, b) -> {
                System.out.printf("%s %s\n", a.toString(), b.toString());
            });
        }
        else {
            throw new IOException("Unsupported file format");
        }

        importer.importGraph(g, new File(path));
        return g;
    }
}
