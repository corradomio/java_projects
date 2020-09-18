package jext.jgrapht.weigts;

import jext.jgrapht.util.LinAlg;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ClusteringAlgorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class AbstractClusteringWeights<V, E> implements ClusteringWeights<V, E> {

    // ----------------------------------------------------------------------
    // Graph & Clustering
    // ----------------------------------------------------------------------

    // graph used for the clustering
    protected Graph<V, E> graph;
    // graph clustering
    protected ClusteringAlgorithm.Clustering<V> clustering;

    // ----------------------------------------------------------------------
    // Clusters info
    // ----------------------------------------------------------------------

    // n of clusters
    protected int k;
    // n of elements in each cluster
    protected int[] clusterSizes;
    // edges' count inter/intra clusters
    protected int[][] clusterCounts;

    // ----------------------------------------------------------------------
    // Clusters weights
    // ----------------------------------------------------------------------

    // edges' weight inter/intra clusters
    protected double[][] clusterWeights;
    // vertices's weight (sum of incident edges' weights)
    protected double[] vertexWeights;
    // graph weight (sum of all edges' weights)
    protected double graphWeight;
    // max edges weight
    protected double maxWeight;

    // map vertex -> vindex
    protected Map<V, Integer> vidx = new HashMap<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public AbstractClusteringWeights(Graph<V, E> graph, ClusteringAlgorithm.Clustering<V> clustering) {
        this.graph = graph;
        this.clustering = clustering;

        init();
    }

    // ----------------------------------------------------------------------
    // Initialization
    // ----------------------------------------------------------------------

    protected void init() {
        if (k != 0) return;

        // directed
        if (this.graph.getType().isDirected())
            throw new IllegalArgumentException("Directed graph not supported");

        // vidx: vertex -> index
        this.graph.vertexSet().forEach(v -> vidx.put(v, vidx.size()));

        // n of clusters
        this.k = this.clustering.getNumberClusters();

        List<Set<V>> clusters = clustering.getClusters();

        // n of elements in each cluster
        this.clusterSizes = new int[k];
        for (int c=0; c<k; ++c)
            this.clusterSizes[c] = clusters.get(c).size();

        // count inter/intra cluster edges
        this.clusterCounts = LinAlg.intMatrix(k);
        // weights inter/intra cluster edge weights
        this.clusterWeights = LinAlg.newMatrix(k);
        // vertex's weights (sum of incident edges' weights)
        this.vertexWeights = LinAlg.newVector(this.graph.vertexSet().size());

        // compute the weights
        this.graph.edgeSet().forEach(this::add);
    }

    private void add(E e) {
        V source = graph.getEdgeSource(e);
        V target = graph.getEdgeTarget(e);
        double weight = graph.getEdgeWeight(e);

        // max weight
        if (weight > maxWeight)
            maxWeight = weight;

        // graph weight
        graphWeight += weight;

        int ci = clusterOf(source);
        int cj = clusterOf(target);

        clusterCounts [ci][cj]  += 1;
        clusterWeights[ci][cj] += weight;

        clusterCounts [cj][ci] += 1;
        clusterWeights[cj][ci] += weight;

        int vi = vidx.get(source);
        int vj = vidx.get(target);

        vertexWeights[vi] += weight;
        vertexWeights[vj] += weight;
    }

    // vertex -> cluster
    protected int clusterOf(V v) {
        List<Set<V>> clusters = clustering.getClusters();
        for (int ci=0; ci<k; ++ci)
            if (clusters.get(ci).contains(v))
                return ci;
        return -1;
    }

    // ----------------------------------------------------------------------
    // Internal/external clusters count/weight
    // ----------------------------------------------------------------------

    public int getClustersCount(int ci, int cj) {
        if (ci == cj)
            return clusterCounts[ci][cj]/2;
        else
            return clusterCounts[ci][cj]/2;
    }

    public int getInternalCount(int c) {
        return clusterCounts[c][c]/2;
    }

    public int getExternalCount(int c) {
        int extCount = -clusterCounts[c][c];
        for (int cj=0; cj<k; ++cj)
            extCount += clusterCounts[c][cj];
        return extCount/2;
    }

    //
    //
    //

    public double getClustersWeight(int ci, int cj) {
        if (ci == cj)
            return clusterWeights[ci][cj]/2;
        else
            return clusterWeights[ci][cj];
    }

    public double getInternalWeight(int c) {
        return clusterWeights[c][c]/2;
    }

    public double getExternalWeight(int c) {
        double eweight = -clusterWeights[c][c];
        for (int cj=0; cj<k; ++cj)
            eweight += clusterWeights[c][cj];
        return eweight/2;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
