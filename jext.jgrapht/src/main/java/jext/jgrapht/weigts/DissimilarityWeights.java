package jext.jgrapht.weigts;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ClusteringAlgorithm;

public class DissimilarityWeights<V, E> extends AbstractClusteringWeights<V, E> {

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public DissimilarityWeights(Graph<V, E> graph, ClusteringAlgorithm.Clustering<V> clustering) {
        super(graph, clustering);
    }

    // ----------------------------------------------------------------------
    // Metrics
    // ----------------------------------------------------------------------

    @Override
    public double getModularity() {
        return 0;
    }

    @Override
    public double getLouvainModularity() {
        return 0;
    }

    @Override
    public double getSilhouette(V v) {
        return 0;
    }

    @Override
    public double getDaviesBouldinIndex() {
        return 0;
    }

    @Override
    public double getDunnIndex() {
        return 0;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
