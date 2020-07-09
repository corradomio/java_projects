package jext.jgrapht;

import jext.jgrapht.util.ClusterWeights;
import jext.jgrapht.util.ContingencyMatrix;
import jext.jgrapht.util.Statistics;
import jext.math.Mathx;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ClusteringAlgorithm;

import static java.lang.Math.sqrt;
import static jext.math.Mathx.sq;
import static jext.math.Mathx.sum;

public class ClusteringMetrics<V, E> {

    private final Graph<V, E> graph;
    private final ClusteringAlgorithm.Clustering<V> clustering;
    private ClusterWeights<V, E> clusterWeight;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public ClusteringMetrics(Graph<V, E> graph, ClusteringAlgorithm.Clustering<V> clustering) {
        this.graph = graph;
        this.clustering = clustering;
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

    public ClusterWeights<V, E> getClusterWeights() {
        if (clusterWeight != null)
            return clusterWeight;

        clusterWeight = new ClusterWeights<>(graph, clustering);
        return clusterWeight;
    }

    /**
     * n: n vertices
     * k: n clusters
     * nj: n vertices cluster j
     *
     * 2020 - Analysis of a parallel MCMC algorithm for graph coloring with nearly uniform balancing
     * eq (8)
     */
    public double getUnbalancingIndex() {
        int[] i = new int[1];
        int k = getNumberClusters();
        int[] nj = new int[k];

        clustering.getClusters().forEach(cluster -> {
            int csize = cluster.size();
            nj[i[0]] = csize;
            i[0] += 1;
        });

        double n = sum(nj);
        double nk = n/k;

        double ui = 0;
        for (int j=0; j<k; ++j) {
            ui += sq(nj[j] - nk);
        }
        ui = sqrt(ui/k);

        return ui;
    }

    /**
     *
     * 2007 - Survey Graph Clustering, pag 44
     */
    public double getModularity() {
        return getClusterWeights().getModularity();
    }

    public double getDaviesBouldinIndex() {
        return getClusterWeights().getDaviesBouldinIndex();
    }

    public double getDunnIndex() {
        return getClusterWeights().getDunnIndex();
    }

    public double getLouvainModularity() {
        return getClusterWeights().getLouvainModularity();
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
