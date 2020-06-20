package jext.jgrapht;

import jext.jgrapht.util.ClusterWeights;
import jext.jgrapht.util.ContingencyMatrix;
import jext.jgrapht.util.Statistics;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ClusteringAlgorithm;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.Math.sqrt;

public class ClusteringMetrics<V, E> {

    private final Graph<V, E> graph;
    private final ClusteringAlgorithm.Clustering<V> clustering;
    private double maxWeight;
    private ClusterWeights<V, E> clusterDistances;

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

    /**
     * Used to convert a 'similarity' in a 'dissimilarity measure,or viceversa.
     *
     * @param maxWeight max weight used to invert the edge weight
     */
    public ClusteringMetrics<V, E> invertWeights(double maxWeight) {
        this.clusterDistances = null;
        this.maxWeight = maxWeight;
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

    public ClusterWeights<V, E> getClusterWeights() {
        if (clusterDistances != null)
            return clusterDistances;

        clusterDistances = new ClusterWeights<>();
        clusterDistances.init(graph, clustering);
        return clusterDistances;
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

    private static double sq(double x) { return x*x; }

    private static int sum(int[] v) {
        int s = 0;
        for(int i : v) s += i;
        return s;
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

    // public static class Indices {
    //     public int numClusters;
    //     public List<Integer> clusterSizes;
    //     public double modularity;
    //     public double dunnIndex;
    //     public double daviesBouldinIndex;
    //     public double louvainModularity;
    //
    //     public void print() {
    //         System.out.print("Clustering\n");
    //         System.out.printf("  n clusters: %d\n", numClusters);
    //
    //         System.out.printf("  Clusters: %s\n", clusterSizes.toString());
    //         System.out.printf("  Modularity: %.4f\n", modularity);
    //         System.out.printf("  Dunn Index: %.4f\n", dunnIndex);
    //         System.out.printf("  Davies-Boulding Index: %.4f\n", daviesBouldinIndex);
    //         System.out.printf("  Louvain Modularity: %.4f\n", louvainModularity);
    //     }
    // }

    // public Indices getIndices() {
    //     Indices stat = new Indices();
    //     stat.numClusters = clustering.getNumberClusters();
    //     stat.clusterSizes = clustering.getClusters()
    //             .stream()
    //             .map(Set::size)
    //             .collect(Collectors.toList());
    //     stat.modularity = getModularity();
    //     stat.dunnIndex = getDunnIndex();
    //     stat.daviesBouldinIndex = getDaviesBouldinIndex();
    //     stat.louvainModularity = getLouvainModularity();
    //
    //     return stat;
    // }

    // public static class Comparison {
    //     public int numClusters1;
    //     public int numClusters2;
    //     public double purity;
    //     public double giniIndex;
    //     public double entropy;
    //     public double randIndex;
    //     public double adjustedRandIndex;
    //     public double fowlkesMallowsIndex;
    //     public double jaccardCoefficient;
    //     public double normalizedGamma;
    //
    //     public void print() {
    //         System.out.print("Clustering comparison\n");
    //
    //         System.out.printf("  n clusters 1: %d\n", numClusters1);
    //         System.out.printf("  n clusters 2: %d\n", numClusters2);
    //         System.out.printf("      Purity: %.4f (1 is better)\n", purity);
    //         System.out.printf("  Gini Index: %.4f (0 is better)\n", giniIndex);
    //         System.out.printf("     Entropy: %.4f (0 is better)\n", entropy);
    //
    //         System.out.printf("           Rand Index: %.4f (1 is better)\n", randIndex);
    //         System.out.printf("  Adjusted Rand Index: %.4f (1 is better)\n", adjustedRandIndex);
    //         System.out.printf("Fowlkes Mallows Index: %.4f (1 is better)\n", fowlkesMallowsIndex);
    //         System.out.printf("  Jaccard Coefficient: %.4f (1 is better)\n", jaccardCoefficient);
    //         System.out.printf("     Normalized Gamma: %.4f (1 is better)\n", normalizedGamma);
    //     }
    // }

    // public Comparison getComparison(ClusteringAlgorithm.Clustering<V> other) {
    //     Comparison comp = new Comparison();
    //     comp.numClusters1 = clustering.getNumberClusters();
    //     comp.numClusters2 = other.getNumberClusters();
    //
    //     ContingencyMatrix cm = getContingencyMatrix(other);
    //
    //     comp.purity = cm.getPurity();
    //     comp.giniIndex = cm.getGiniIndex();
    //     comp.entropy = cm.getEntropy();
    //     comp.randIndex = cm.getRandIndex();
    //     comp.adjustedRandIndex = cm.getAdjustedRandIndex();
    //     comp.fowlkesMallowsIndex = cm.getFowlkesMallowsIndex();
    //     comp.jaccardCoefficient = cm.getJaccardCoefficient();
    //     comp.normalizedGamma = cm.getNormalizedGamma();
    //     return comp;
    // }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

}
