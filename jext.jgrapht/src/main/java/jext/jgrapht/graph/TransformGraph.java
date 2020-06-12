package jext.jgrapht.graph;

import org.jgrapht.Graph;
import org.jgrapht.graph.AsSubgraph;
import org.jgrapht.graph.builder.GraphBuilder;

import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Some methods to generate sub graphs using different filters
 */
public class TransformGraph<V, E> {

    private Random rnd = new Random();
    private Graph<V, E> graph;

    public TransformGraph(Graph<V, E> graph) {
        this.graph = graph;
    }

    /**
     * Extract edges with a weight >= than 'threshold'
     *
     * @param threshold threshold to use to filter the weighed edges
     * @return a subgraph than original graph
     */
    public Graph<V, E> upperThresholdGraph(double threshold) {
        Set<E> edgeSet = graph.edgeSet()
                .parallelStream()
                .filter(e -> graph.getEdgeWeight(e) >= threshold)
                .collect(Collectors.toSet());

        return new AsSubgraph<>(graph, null, edgeSet);
    }

    /**
     * Extract edges with a weight < than 'threshold'
     *
     * @param threshold threshold to use to filter the weighed edges
     * @return a subgraph than original graph
     */
    public  Graph<V, E> lowerThresholdGraph(double threshold) {
        Set<E> edgeSet = graph.edgeSet()
                .parallelStream()
                .filter(e -> graph.getEdgeWeight(e) < threshold)
                .collect(Collectors.toSet());

        return new AsSubgraph<>(graph, graph.vertexSet(), edgeSet);
    }


    /**
     * Random complement graph
     * @param nEdges n of edges to generate
     * @return a complement graph than original graph
     */
    public Graph<V, E> complementGraph(long nEdges) {
        int n = graph.vertexSet().size();
        V[] vertices = (V[]) new Object[n];
        graph.vertexSet().toArray(vertices);
        Graph<V, E> complement = new GraphBuilder<>(graph).build();
        graph.vertexSet().forEach(complement::addVertex);
        while(complement.edgeSet().size() != nEdges) {
            V source = vertices[rnd.nextInt(n)];
            V target = vertices[rnd.nextInt(n)];
            if (!graph.containsEdge(source, target))
                complement.addEdge(source, target);
        }
        return complement;
    }
}
