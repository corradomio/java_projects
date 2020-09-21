package jext.jgrapht;

import jext.jgrapht.util.ContingencyMatrix;
import jext.jgrapht.util.Statistics;
import jext.jgrapht.weights.ClusteringWeightsImpl;
import jext.jgrapht.weights.ClusteringWeights;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ClusteringAlgorithm;


public class ClusteringMetrics<V, E> {

    private Graph<V, E> graph;
    private ClusteringAlgorithm.Clustering<V> clustering;
    // private ClusteringWeights clusterWeight;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public ClusteringMetrics() {

    }

    public ClusteringMetrics(Graph<V, E> graph, ClusteringAlgorithm.Clustering<V> clustering) {
        this.graph = graph;
        this.clustering = clustering;
    }

    public ClusteringMetrics<V, E> setGraph(Graph<V, E> graph) {
        this.graph = graph;
        return this;
    }

    public ClusteringMetrics<V, E> setClustering(ClusteringAlgorithm.Clustering<V> clustering) {
        this.clustering = clustering;
        return this;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public int getNumberClusters() {
        return clustering.getNumberClusters();
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public static class ClusterStatistics extends Statistics {
        public int size;
        public int numClusters;
    }

    public ClusterStatistics getStatistics() {
        ClusterStatistics cs = new ClusterStatistics();
        cs.numClusters = getNumberClusters();

        clustering.getClusters().forEach(cluster ->
                {
                    int csize = cluster.size();
                    cs.size += csize;
                    cs.add(csize);
                });

        return (ClusterStatistics) cs.finish();
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public ClusteringWeights getClusterWeights() {
        // if (clusterWeight == null)
        //     clusterWeight = new ClusteringWeightsImpl<>(graph, clustering);
        // return clusterWeight;
        return new ClusteringWeightsImpl<>(graph, clustering);
    }

    public ContingencyMatrix getContingencyMatrix(ClusteringAlgorithm.Clustering<V> other) {
        // ContingencyMatrix cm = new ContingencyMatrix();
        // cm.init(clustering, other);
        // return cm;
        return new ContingencyMatrix<>(clustering).using(other);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

}
