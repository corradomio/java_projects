package jext.jgrapht;

import jext.jgrapht.util.ContingencyMatrix;
import jext.jgrapht.util.Statistics;
import jext.jgrapht.weights.ClusterWeights;
import jext.jgrapht.weights.ClusteringWeights;
import jext.jgrapht.weights.DissimilarityWeights;
import jext.jgrapht.weights.SimilarityWeights;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ClusteringAlgorithm;

public class ClusteringMetrics<V, E> {

    private Graph<V, E> graph;
    private ClusteringAlgorithm.Clustering<V> clustering;
    private ClusteringWeights<V, E> clusterWeight;
    private WeightType weightType = WeightType.SIMILARITY;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public ClusteringMetrics() {

    }

    public ClusteringMetrics(Graph<V, E> graph, ClusteringAlgorithm.Clustering<V> clustering) {
        this(graph, clustering, WeightType.UNDEFINED);
    }

    public ClusteringMetrics(Graph<V, E> graph, ClusteringAlgorithm.Clustering<V> clustering, WeightType weightType) {
        this.graph = graph;
        this.clustering = clustering;
        this.weightType = weightType;
    }

    public ClusteringMetrics<V, E> setGraph(Graph<V, E> graph) {
        this.graph = graph;
        return this;
    }

    public ClusteringMetrics<V, E> setClustering(ClusteringAlgorithm.Clustering<V> clustering) {
        this.clustering = clustering;
        return this;
    }

    public ClusteringMetrics<V, E> setWeightType(WeightType weightType) {
        this.weightType = weightType;
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

    public ClusteringWeights<V, E> getClusterWeights() {
        if (clusterWeight != null)
            return clusterWeight;

        switch (weightType) {
            case SIMILARITY:
                clusterWeight = new SimilarityWeights<>(graph, clustering);
                break;
            case DISSIMILARITY:
                clusterWeight = new DissimilarityWeights<>(graph, clustering);
                break;
            default:
                clusterWeight = new ClusterWeights<>(graph, clustering);
        }

        return clusterWeight;
    }

    /**
     *
     * 2007 - Survey Graph Clustering, pag 44
     */
    public double getModularity() {
        return getClusterWeights().getModularity();
    }

    public double getLouvainModularity() {
        return getClusterWeights().getLouvainModularity();
    }

    public double getDaviesBouldinIndex() {
        return getClusterWeights().getDaviesBouldinIndex();
    }

    public double getDunnIndex() {
        return getClusterWeights().getDunnIndex();
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public ContingencyMatrix getContingencyMatrix(ClusteringAlgorithm.Clustering<V> other) {
        ContingencyMatrix cm = new ContingencyMatrix();
        cm.init(clustering, other);
        return cm;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

}
