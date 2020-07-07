package jext.jgrapht.util;

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

public class ClusterWeights<V, E> {

    // used to invert the edge weight
    // private final double maxWeight;

    // graph used for the clustering
    private Graph<V, E> graph;
    // graph clustering
    private ClusteringAlgorithm.Clustering<V> clustering;
    // direct graph
    private boolean directed;

    // n of clusters
    private int k;
    // n of elements in each cluster
    private int[] clusterSizes;
    // weights inter/intra clusters
    private double[][] weightClusters;
    // vertex's weights (sum of incident edges' weights)
    private double[] weightVertices;
    // graph weight (sum of all edges' weights)
    private double graphWeight;

    // map vertex -> vertexIndex
    private Map<V, Integer> vidx = new HashMap<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ClusterWeights(Graph<V, E> g, ClusteringAlgorithm.Clustering<V> clustering){
        this.graph = g;
        this.clustering = clustering;
    }

    // public ClusterWeights(Graph<V, E> g, ClusteringAlgorithm.Clustering<V> clustering, double maxWeight) {
    //     this.maxWeight = maxWeight;
    //     this.graph = g;
    //     this.clustering = clustering;
    // }

    // ----------------------------------------------------------------------
    // Initialize
    // ----------------------------------------------------------------------

    /**
     * Initialize the data structure with the specified graph and clustering
     */
    private void init() {
        if (k != 0) return;

        // directed
        this.directed = this.graph.getType().isDirected();

        if (directed)
            throw new IllegalArgumentException("Graph directed");

        // n of clusters
        this.k = clustering.getNumberClusters();

        // n of elements in each cluster
        this.clusterSizes = new int[k];
        for (int ci=0; ci<k; ++ci) {
            int clusterSize = clustering.getClusters().get(ci).size();
            this.clusterSizes[ci] = clusterSize;
        }

        // weights inter/intra clusters
        this.weightClusters = LinAlg.newMatrix(k);
        // vertex's weights (sum of incident edges' weights)
        this.weightVertices = LinAlg.newVector(this.graph.vertexSet().size());

        // compute the weights
        graph.edgeSet().forEach(e -> {
            double weight = weightOf(e);
            V source = graph.getEdgeSource(e);
            V target = graph.getEdgeTarget(e);
            this.add(source, target, weight);
        });
    }

    private double weightOf(E e) {
        double weight = graph.getEdgeWeight(e);
        // if(maxWeight > 0)
        //     weight = maxWeight - weight;
        return weight;
    }

    public void add(V vi, V vj, double weight) {
        graphWeight += weight;

        int ci = clusterOf(vi);
        int cj = clusterOf(vj);
        if (ci < cj)
            weightClusters[ci][cj] += weight;
        else
            weightClusters[cj][ci] += weight;

        int i = indexOf(vi);
        int j = indexOf(vj);
        weightVertices[i] += weight;
        weightVertices[j] += weight;
    }

    // vertex -> verexIndex
    private int indexOf(V v) {
        if (!vidx.containsKey(v))
            vidx.put(v, vidx.size());
        return vidx.get(v);
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
    // Internal/external cluster weights
    // ----------------------------------------------------------------------

    public double getInternalWeight(int ci) {
        init();
        return weightClusters[ci][ci];
    }

    public double getExternalWeight(int ci) {
        init();
        double extWeight = -weightClusters[ci][ci];
        for (int cj=0; cj<k; ++cj)
            extWeight += weightClusters[ci][cj];
        return extWeight;
    }

    // ----------------------------------------------------------------------

    /**
     *
     * 2007 - Survey Graph Clustering, pag 44
     */
    public double getModularity() {
        init();
        double modularity = 0.;
        // for (int c=0; c<k; ++c)
        //     modularity += weightClusters[c][c];
        //
        // for (int ci=0; ci<k; ++ci)
        //     for(int cj=0; cj<k; ++cj)
        //         if (ci != cj)
        //             modularity -= weightClusters[ci][cj];

        for (int c=0; c<k; ++c)
            modularity += getInternalWeight(c);

        for (int c=0; c<k; ++c)
            modularity -= getExternalWeight(c);

        return modularity;
    }

    // ----------------------------------------------------------------------

    /**
     *            1                     s_a(Ci) + s_a(C_j)
     *      DB = --- SUM(i=1,k : max_j --------------------
     *            k                        d_a(C_i,C_J)
     *
     * https://en.wikipedia.org/wiki/Davies%E2%80%93Bouldin_index
     */
    public double getDaviesBouldinIndex() {
        init();
        double[] sa = averageDistances();
        double[][]da = betweenSeparation();

        double sum = 0.;
        for (int ci=0; ci<k; ++ci) {
            double maxj = 0;
            for (int cj=0; cj<k; ++cj) {
                if (ci == cj) continue;
                double daij = da[ci][cj];
                double ratio = div(sa[ci] + sa[cj], daij);
                if (ratio > maxj) maxj = ratio;
            }
            sum += maxj;
        }
        return div(sum, k);
    }

    private double[] averageDistances() {
        // int k = clustering.getNumberClusters();
        double[] averageDistances = LinAlg.newVector(k);
        for (int c=0; c<k; ++c)
            averageDistances[c] = div(weightClusters[c][c], clusterSizes[c]*(clusterSizes[c]-1));
        return averageDistances;
    }

    private double[][] betweenSeparation() {
        // int k = clustering.getNumberClusters();
        double[][] betweenSeparation = LinAlg.newMatrix(k);
        for(int ci=0; ci<k; ++ci)
            for(int cj=0; cj<k; cj++)
                if(ci != cj)
                    betweenSeparation[ci][cj] = div(weightClusters[ci][cj], clusterSizes[ci]*clusterSizes[cj]);
        return betweenSeparation;
    }

    // ----------------------------------------------------------------------

    /**
     *                min_{i,j} d_a(C_i,C_j)
     *      Dunn_G = ------------------------
     *                   max_h s_a(C_h)
     *
     * https://en.wikipedia.org/wiki/Dunn_index
     */
    public double getDunnIndex() {
        init();
        // int k = clustering.getNumberClusters();
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
        if (minda == Double.POSITIVE_INFINITY)
            minda = 0;

        double maxsa = -Double.POSITIVE_INFINITY;
        for(int ci=0; ci<k; ++ci) {
            double sai = sa[ci];
            if (sai > maxsa) maxsa = sai;
        }

        if (maxsa == -Double.POSITIVE_INFINITY)
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
     *            1             [                   ]
     *      Q = ----- SUM_{i,j} [ 2m*w_ij - k_i*k_j ] delta(c_i,c_j)
     *          2m^2            [                   ]
     *
     *  w_ij : edge weight
     *  k_i, k_j: sum edge weights attached to the vertices i & j
     *  m : sum of the edge weights (graph weight)
     *  c_i, c_j: communities
     *  delta: 1 if c_i == c_j else 0
     */
    public double getLouvainModularity() {
        init();

        double lmod = 0.;
        double[] vprod = vprod();
        double m2 = 2*graphWeight;

        for(int ci=0; ci<k; ++ci)
            lmod += m2*weightClusters[ci][ci] - vprod[ci];

        return div(lmod, sq(m2));
    }

    /**
     * Product of vertices weights when the vertices are in th same cluster
     * @return vector of products for each cluster
     */
    private double[] vprod() {
        double[] vprod = LinAlg.newVector(k);

        graph.edgeSet().forEach(e -> {
            V source = graph.getEdgeSource(e);
            V target = graph.getEdgeTarget(e);

            // vertex -> vertexIndex
            int vi = indexOf(source);
            int vj = indexOf(target);

            // vertex -> cluster
            int ci = clusterOf(source);
            int cj = clusterOf(target);

            if (ci == cj) {
                vprod[ci] += weightVertices[vi]*weightVertices[vj];
            }
        });

        return vprod;
    }

    // ----------------------------------------------------------------------
    // Silhouette
    // ----------------------------------------------------------------------

    private double getMeanWeight(V source) {
        int c = clusterOf(source);
        Set<V> cluster = clustering.getClusters().get(c);

        double weight = 0.;
        for (V target : cluster) {
            E e = graph.getEdge(source, target);
            if (e == null) continue;
            weight += weightOf(e);
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
            double weight = weightOf(e);
            minWeight = Math.min(weight, minWeight);
        }
        return minWeight;
    }

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

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------
}
