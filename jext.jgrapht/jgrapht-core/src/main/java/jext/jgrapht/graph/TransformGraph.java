package jext.jgrapht.alg;

import jext.jgrapht.Graphs;
import org.jgrapht.Graph;
import org.jgrapht.graph.AsSubgraph;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Some methods to generate sub graphs using different filters
 */
public class TransformGraph<V, E> {

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

        return new AsSubgraph<>(graph, null, edgeSet);
    }


    /**
     * Create a new graph with same vertices and edges but with the
     * edge weights computed as:
     *
     *      maxWeight - edgeWeight
     *
     */
    public Graph<V, E> invertWeights() {
        return invertWeights(getMaxWeight()*1.001);
    }

    public Graph<V, E> invertWeights(double maxWeight) {
        Graph<V, E> flipped = Graphs.newGraph(graph);
        graph.vertexSet().forEach(flipped::addVertex);

        graph.edgeSet().forEach(e -> {
            V source = graph.getEdgeSource(e);
            V target = graph.getEdgeTarget(e);
            double weight = graph.getEdgeWeight(e);

            E f = flipped.addEdge(source, target);
            flipped.setEdgeWeight(f, maxWeight - weight);
        });

        return flipped;
    }

    public double getMaxWeight() {
        return graph.edgeSet()
                .parallelStream()
                .map(e -> graph.getEdgeWeight(e))
                .max(Double::compare)
                .orElseGet(() -> (double) 0);
    }

}
