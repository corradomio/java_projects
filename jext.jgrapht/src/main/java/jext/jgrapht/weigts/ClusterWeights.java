package jext.jgrapht.util;

import jext.jgrapht.WeightType;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.interfaces.ClusteringAlgorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.lang.Math.max;
import static jext.math.Mathx.div;
import static jext.math.Mathx.sq;

/**
 * Cluster adjacent matrix for simple (not directed) graphs.
 *
 * If vertex 'vi' is in cluster 'ci' and the verted 'vj' is in cluster 'cj',
 * are added TWO edge: [ci,cj] AND [cj,ci]
 */
public class ClusterWeights<V, E> {

    // graph used for the clustering
    private Graph<V, E> graph;
    // graph clustering
    private ClusteringAlgorithm.Clustering<V> clustering;
    // weight semantic
    private WeightMode weightMode;

    // n of clusters
    private int k;
    // n of elements in each cluster
    private int[] clusterSizes;
    // edges' count inter/intra clusters
    private int[][] clusterCounts;
    // edges' weight inter/intra clusters
    private double[][] clusterWeights;
    // vertices's weight (sum of incident edges' weights)
    private double[] vertexWeights;
    // graph weight (sum of all edges' weights)
    private double graphWeight;

    // map vertex -> vindex
    private Map<V, Integer> vidx = new HashMap<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ClusterWeights(Graph<V, E> g, ClusteringAlgorithm.Clustering<V> clustering, WeightType weightType){
        this.graph = g;
        this.clustering = clustering;
        this.weightMode = weightMode;
        init();
    }

    // ----------------------------------------------------------------------
    // Initialize
    // ----------------------------------------------------------------------

    /**
     * Initialize the data structure with the specified graph and clustering
     */
    private void init() {
        if (k != 0) return;

        // directed
        if (this.graph.getType().isDirected())
            throw new IllegalArgumentException("Directed graph not supported");

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

        // vertex -> index
        this.graph.vertexSet().forEach(v -> vidx.put(v, vidx.size()));

        // compute the weights
        this.graph.edgeSet().forEach(this::add);
    }

    private void add(E e) {
        V vi = graph.getEdgeSource(e);
        V vj = graph.getEdgeTarget(e);
        double weight = graph.getEdgeWeight(e);

        graphWeight += weight;

        int ci = clusterOf(vi);
        int cj = clusterOf(vj);
        if (ci > cj){ int t=ci;ci=cj;cj=t;}

        clusterCounts [ci][cj]  += 1;
        clusterWeights[ci][cj] += weight;

        clusterCounts [cj][ci] += 1;
        clusterWeights[cj][ci] += weight;

        int i = vidx.get(vi);
        int j = vidx.get(vj);

        vertexWeights[i] += weight;
        vertexWeights[j] += weight;
    }

    // vertex -> cluster
    private int clusterOf(V v) {
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
        double extWeight = -clusterWeights[c][c];
        for (int cj=0; cj<k; ++cj)
            extWeight += clusterWeights[c][cj];
        return extWeight/2;
    }

    // ----------------------------------------------------------------------

    /**
     *
     * 2007 - Survey Graph Clustering, pag 44
     */
    public double getModularity() {
        double modularity = 0.;

        // sum the internal weights
        for (int c=0; c<k; ++c)
            modularity += getInternalWeight(c);

        // subtract the external weights
        for (int c=0; c<k; ++c)
            modularity -= getExternalWeight(c);

        return modularity;
    }

    // ----------------------------------------------------------------------

    /**
     *               S[i] + S[j]
     *      R[ij] = --------------
     *                 M[ij]
     *
     *      D[i] = max[i!=j] R[ij]
     *
     *            1
     *      DB = --- sum[i=1,k] D[i]
     *            k
     *
     * https://en.wikipedia.org/wiki/Davies%E2%80%93Bouldin_index
     */
    public double getDaviesBouldinIndex() {
        // double[] sa = averageDistances();
        // double[][]da = betweenSeparation();

        double[][] d = distances();
        double sum = 0.;
        for (int ci=0; ci<k; ++ci) {
            double maxj = -Double.MAX_VALUE;
            for (int cj=0; cj<k; ++cj) {
                if (ci == cj) continue;
                double ratio = div(d[ci][ci] + d[cj][cj], d[ci][cj]);
                if (ratio > maxj)
                    maxj = ratio;
            }
            sum += maxj;
        }
        return div(sum, k);
    }

    private double[][] distances() {
        double[][] d = LinAlg.newMatrix(k);
        for (int c=0; c<k; ++c)
            d[c][c] = div(clusterWeights[c][c], clusterSizes[c]*(clusterSizes[c]-1));

        for (int ci=0; ci<k; ++ci)
            for (int cj=0; cj<k; ++cj)
                if (ci != cj)
                    d[ci][cj] = div(clusterWeights[ci][cj], clusterSizes[ci]*clusterSizes[cj]);

        return d;
    }

    // private double[] averageDistances() {
    //     double[] averageDistances = LinAlg.newVector(k);
    //     for (int c=0; c<k; ++c)
    //         averageDistances[c] = div(clusterWeights[c][c], clusterSizes[c]*(clusterSizes[c]-1));
    //     return averageDistances;
    // }

    // private double[][] betweenSeparation() {
    //     double[][] betweenSeparation = LinAlg.newMatrix(k);
    //     for(int ci=0; ci<k; ++ci)
    //         for(int cj=0; cj<k; cj++)
    //             if(ci != cj)
    //                 betweenSeparation[ci][cj] = div(clusterWeights[ci][cj], clusterSizes[ci]*clusterSizes[cj]);
    //     return betweenSeparation;
    // }

    // ----------------------------------------------------------------------

    /**
     *                min_{i,j} d_a(C_i,C_j)
     *      Dunn_G = ------------------------
     *                   max_h s_a(C_h)
     *
     * https://en.wikipedia.org/wiki/Dunn_index
     */
    public double getDunnIndex() {
        // double[] sa = averageDistances();
        // double[][]da = betweenSeparation();

        double[][] d = distances();
        double minda = Double.MAX_VALUE;
        for (int ci=0; ci<k; ++ci) {
            for (int cj = 0; cj<k; ++cj) {
                if (ci == cj) continue;
                double daij = d[ci][cj];
                if (daij > 0 && daij < minda)
                    minda = daij;
            }
        }
        if (minda == Double.MAX_VALUE)
            minda = 0;

        double maxsa = -Double.MAX_VALUE;
        for(int c=0; c<k; ++c) {
            double sai = d[c][c];
            if (sai > maxsa)
                maxsa = sai;
        }

        if (maxsa == -Double.MAX_VALUE)
            minda = 0;

        return div(minda, maxsa);
    }

    // ----------------------------------------------------------------------

    /**
     * Louvain Modularity
     *
     * (https://en.wikipedia.org/wiki/Louvain_modularity)
     *
     *            1             [         k_i*k_j  ]
     *      Q = ----- SUM_{i,j} [ w_ij - --------- ] delta(c_i,c_j)
     *           2m             [           2m     ]
     *
     *
     *            1               [                   ]
     *      Q = ------- SUM_{i,j} [ 2m*w_ij - k_i*k_j ] delta(c_i,c_j)
     *          (2m)^2            [                   ]
     *
     *  w_ij : edge weight
     *  k_i, k_j: sum edge weights attached to the vertices i & j
     *  m : sum of the edge weights (graph weight)
     *  c_i, c_j: communities
     *  delta: 1 if c_i == c_j else 0
     */
    public double getLouvainModularity() {
        double lmod = 0.;
        double[] vprod = vprod();
        double m = graphWeight;

        // clusterWeights[c][c] == 2*sum(edge weights in cluster c)

        for(int c=0; c<k; ++c)
            lmod += m*clusterWeights[c][c] - vprod[c];

        return div(lmod, sq(2*m));
    }

    /**
     * Product of vertices weights when the vertices are in th same cluster
     * @return vector of products for each cluster
     */
    private double[] vprod() {
        double[] vprod = LinAlg.newVector(k);

        graph.edgeSet().forEach(e -> {
            V vi = graph.getEdgeSource(e);
            V vj = graph.getEdgeTarget(e);

            // vertex -> vertexIndex
            int i = vidx.get(vi);
            int j = vidx.get(vj);

            // vertex -> cluster
            int ci = clusterOf(vi);
            int cj = clusterOf(vj);

            if (ci == cj) {
                vprod[ci] += vertexWeights[i]*vertexWeights[j];
            }
        });

        return vprod;
    }

    // ----------------------------------------------------------------------
    // Silhouette
    // ----------------------------------------------------------------------

    /**
     *
     * https://en.wikipedia.org/wiki/Silhouette_(clustering)
     */
    public double getSilhouette(V v) {
        int c = clusterOf(v);
        if (clustering.getClusters().get(c).size() == 1)
            return 0;

        double ai = getMeanWeight(v);
        double bi = getSmallestWeight(v);

        return div(bi - ai, max(bi, ai));
    }

    private double getMeanWeight(V source) {
        int c = clusterOf(source);
        Set<V> cluster = clustering.getClusters().get(c);

        double weight = 0.;
        for (V target : cluster) {
            E e = graph.getEdge(source, target);
            if (e == null) continue;
            weight += graph.getEdgeWeight(e);
        }
        return div(weight, cluster.size()-1);
    }

    private double getSmallestWeight(V source) {
        int ci = clusterOf(source);
        double minWeight = Double.MAX_VALUE;
        Set<V> neighbors = Graphs.neighborSetOf(graph, source);
        for (V target : neighbors) {
            int cj = clusterOf(target);
            if (ci == cj) continue;
            E e = graph.getEdge(source, target);
            double weight = graph.getEdgeWeight(e);
            minWeight = Math.min(weight, minWeight);
        }
        return minWeight;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------
}
