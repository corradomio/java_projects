package jext.jgrapht;

import jext.jgrapht.util.ContingencyMatrix;
import jext.util.SetUtils;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ClusteringAlgorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ClusteringMetrics<V, E> {

    private final Graph<V, E> graph;
    private final ClusteringAlgorithm.Clustering<V> clustering;
    private double maxWeight;

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

    public double getModularity() {
        int k = clustering.getNumberClusters();
        checkDistances();

        double modularity = 0.;
        for (int c=0; c<k; ++c) {
            modularity += clusterDistances[c][c];
        }

        for (int ci=0; ci<k; ++ci)
            for(int cj=0; cj<k; ++cj)
                if (ci != cj)
                    modularity -= clusterDistances[ci][cj];

        return modularity;
    }

    // ----------------------------------------------------------------------

    /**
     *            1                     s_a(Ci) + s_a(C_j)
     *      DB = --- SUM(i=1,k : max_j --------------------
     *            k                        d_a(C_i,C_J)
     *
     */
    public double getDaviesBouldinIndex() {
        int k = clustering.getNumberClusters();
        checkDistances();

        double[] sa = averageDistances();
        double[][]da = betweenSeparation();

        double sum = 0.;
        for (int ci=0; ci<k; ++ci) {
            double max = 0;
            for (int cj=0; cj<k; ++cj) {
                if (ci == cj) continue;
                double daij = da[ci][cj];
                double ratio = div(sa[ci] + sa[cj], da[ci][cj]);
                if (ratio > max) max = ratio;
            }
            sum += max;
        }
        return div(sum, k);
    }

    /**
     *                min_{i,j} d_a(C_i,C_j)
     *      Dunn_G = ------------------------
     *                   max_h s_a(C_h)
     *
     */
    public double getDunnIndex() {
        int k = clustering.getNumberClusters();
        checkDistances();

        double[] sa = averageDistances();
        double[][]da = betweenSeparation();

        double minda = Double.POSITIVE_INFINITY;
        for (int ci=0; ci<k; ++ci) {
            for (int cj = 0; cj<k; ++cj) {
                if (ci == cj) continue;
                double daij = da[ci][cj];
                if (daij == 0) continue;
                if (daij < minda) minda = daij;
            }
        }

        double maxsa = -Double.POSITIVE_INFINITY;
        for(int ci=0; ci<k; ++ci) {
            double sai = sa[ci];
            if (sai > maxsa) maxsa = sai;
        }

        if (k <= 1)
            return 0.;
        else
            return div(minda, maxsa);
    }

    /**
     * Louvain Modularity
     *
     * (https://en.wikipedia.org/wiki/Louvain_modularity)
     *
     *
     *            1             [         k_i*k_j  ]
     *      Q = ----- SUM_{i,j} [ w_ij - --------- ] delta(c_i,c_j)
     *           2m             [           2m     ]
     *
     *  w_ij : edge weight
     *  k_i, k_j: sum edge weights attached to the vertices i & j
     *  m : sum of the edge weights
     *  c_i, c_j: communities
     *  delta: 1 if c_i == c_j else 0
     */
    public double getLouvainModularity() {
        int n = graph.vertexSet().size();

        // map vertex -> index
        Map<V, Integer> vidx = new HashMap<>();

        int index = 0;
        for (V v : graph.vertexSet())
            vidx.put(v, index++);

        double m = 0;
        double[] k = new double[n];
        for (E e : graph.edgeSet()) {
            V source = graph.getEdgeSource(e);
            V targer = graph.getEdgeTarget(e);
            int i = vidx.get(source);
            int j = vidx.get(targer);

            double weight = weightOf(e);
            m += weight;
            k[i] += weight;
            k[j] += weight;
        }

        double q = 0;
        double f = m > 0 ? 1/(2*m) : 0.;
        for (E e : graph.edgeSet()) {
            V source = graph.getEdgeSource(e);
            V targer = graph.getEdgeTarget(e);
            int ci = clusterOf(source);
            int cj = clusterOf(targer);

            if (ci != cj) continue;

            int i = vidx.get(source);
            int j = vidx.get(targer);
            double weight = weightOf(e);
            q += (weight - f*k[i]*k[j]);
        }

        return f*q;
    }

    private double weightOf(E e) {
        double weight = graph.getEdgeWeight(e);
        if(maxWeight > 0)
            weight = maxWeight - weight;
        return weight;
    }

    // ----------------------------------------------------------------------

    private int[] clusterSizes;   // n of elements in each cluster
    private double[][] clusterDistances;

    private void checkDistances() {
        countClusterSizes();
        evaluateDistances();
    }

    private void countClusterSizes() {
        if (clusterSizes != null) return;

        int k = clustering.getNumberClusters();
        clusterSizes = new int[k];
        for (int ci=0; ci<k; ++ci)
            clusterSizes[ci] = clustering.getClusters().get(ci).size();
    }

    private void evaluateDistances() {
        if (clusterDistances != null) return;

        int k = clustering.getNumberClusters();
        clusterDistances = new double[k][];
        for (int i=0; i<k; ++i)
            clusterDistances[i] = new double[k];

        graph.edgeSet().forEach(e -> {
            double weight = weightOf(e);
            V source = graph.getEdgeSource(e);
            V target = graph.getEdgeTarget(e);
            int ci = clusterOf(source);
            int cj = clusterOf(target);
            clusterDistances[ci][cj] += weight;
        });
    }

    private int clusterOf(V v) {
        return clusterOf(v, clustering);
    }

    private int clusterOf(V v, ClusteringAlgorithm.Clustering<V> clustering) {
        int k = clustering.getNumberClusters();
        for (int i=0; i<k; ++i)
            if (clustering.getClusters().get(i).contains(v))
                return i;
        return -1;
    }

    // ----------------------------------------------------------------------

    private static double div(double x, double y) {
        return y != 0. ? x/y : 0.;
    }

    private double[] averageDistances() {
        int k = clustering.getNumberClusters();
        double[] averageDistances = new double[k];
        for (int i=0; i<k; ++i)
            averageDistances[i] = div(clusterDistances[i][i], clusterSizes[i]*(clusterSizes[i]-1));
        return averageDistances;
    }

    private double[][] betweenSeparation() {
        int k = clustering.getNumberClusters();
        double[][] betweenSeparation = new double[k][];
        for (int i=0; i<k; ++i)
            betweenSeparation[i] = new double[k];
        for(int i=0; i<k; ++i)
            for(int j=0; j<k; j++)
                if(i != j)
                    betweenSeparation[i][j] = div(clusterDistances[i][i], clusterSizes[i]*clusterSizes[j]);
        return betweenSeparation;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public static class Statistics {
        public int numClusters;
        public List<Integer> clusterSizes;
        public double modularity;
        public double dunnIndex;
        public double daviesBouldinIndex;
        public double louvainModularity;

        public void print() {
            System.out.print("Clustering\n");
            System.out.printf("  n clusters: %d\n", numClusters);

            System.out.printf("  Clusters: %s\n", clusterSizes.toString());
            System.out.printf("  Modularity: %.4f\n", modularity);
            System.out.printf("  Dunn Index: %.4f\n", dunnIndex);
            System.out.printf("  Davies-Boulding Index: %.4f\n", daviesBouldinIndex);
            System.out.printf("  Louvain Modularity: %.4f\n", louvainModularity);
        }
    }

    public Statistics getStatistics() {
        Statistics stat = new Statistics();
        stat.numClusters = clustering.getNumberClusters();
        stat.clusterSizes = clustering.getClusters()
                .stream()
                .map(Set::size)
                .collect(Collectors.toList());
        stat.modularity = getModularity();
        stat.dunnIndex = getDunnIndex();
        stat.daviesBouldinIndex = getDaviesBouldinIndex();
        stat.louvainModularity = getLouvainModularity();

        return stat;
    }

    public static class Comparison {
        public int numClusters1;
        public int numClusters2;
        public double purity;
        public double giniIndex;
        public double entropy;
        public double randIndex;
        public double adjustedRandIndex;
        public double fowlkesMallowsIndex;
        public double jaccardCoefficient;
        public double normalizedGamma;

        public void print() {
            System.out.print("Clustering comparison\n");

            System.out.printf("  n clusters 1: %d\n", numClusters1);
            System.out.printf("  n clusters 2: %d\n", numClusters2);
            System.out.printf("      Purity: %.4f (1 is better)\n", purity);
            System.out.printf("  Gini Index: %.4f (0 is better)\n", giniIndex);
            System.out.printf("     Entropy: %.4f (0 is better)\n", entropy);

            System.out.printf("           Rand Index: %.4f (1 is better)\n", randIndex);
            System.out.printf("  Adjusted Rand Index: %.4f (1 is better)\n", adjustedRandIndex);
            System.out.printf("Fowlkes Mallows Index: %.4f (1 is better)\n", fowlkesMallowsIndex);
            System.out.printf("  Jaccard Coefficient: %.4f (1 is better)\n", jaccardCoefficient);
            System.out.printf("     Normalized Gamma: %.4f (1 is better)\n", normalizedGamma);
        }
    }

    public Comparison getComparison(ClusteringAlgorithm.Clustering<V> other) {
        Comparison comp = new Comparison();
        comp.numClusters1 = clustering.getNumberClusters();
        comp.numClusters2 = other.getNumberClusters();
        ContingencyMatrix cm = getContingencyMatrix(other);

        comp.purity = cm.getPurity();
        comp.giniIndex = cm.getGiniIndex();
        comp.entropy = cm.getEntropy();
        comp.randIndex = cm.getRandIndex();
        comp.adjustedRandIndex = cm.getAdjustedRandIndex();
        comp.fowlkesMallowsIndex = cm.getFowlkesMallowsIndex();
        comp.jaccardCoefficient = cm.getJaccardCoefficient();
        comp.normalizedGamma = cm.getNormalizedGamma();
        return comp;
    }


    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public ContingencyMatrix getContingencyMatrix(ClusteringAlgorithm.Clustering<V> other) {
        int nr = clustering.getNumberClusters();
        int nc = other.getNumberClusters();
        ContingencyMatrix cm = new ContingencyMatrix(nr, nc);
        Set<V> v1 = verticesOf(clustering);
        Set<V> v2 = verticesOf(other);
        if (!SetUtils.union(v1, v2).equals(SetUtils.intersection(v1, v2)))
            throw new IllegalArgumentException("Invalid vertex sets");

        for(V v : v1) {
            int ci = clusterOf(v, clustering);
            int cj = clusterOf(v, other);
            cm.add(ci, cj);
        }

        return cm.done();
    }

    private Set<V> verticesOf(ClusteringAlgorithm.Clustering<V> clustering) {
        Set<V> vertices = new HashSet<>();
        clustering.getClusters()
                .forEach(vertices::addAll);
        return vertices;
    }
}
