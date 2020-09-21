package jext.jgrapht.metrics;

import jext.jgrapht.WeightType;
import jext.jgrapht.util.LinAlg;
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

    // factor used to invert a weight:
    //
    //      invertedWeight = fWeight*maxWeight - edgeWeight
    //
    protected double fWeight = 1.001;
    // max edges weight
    protected double maxWeight;

    // map vertex -> vindex
    protected Map<V, Integer> vidx = new HashMap<>();

    // edges' weight inter/intra clusters
    protected double[][] sclusterWeights;
    // vertices's weight (sum of incident edges' weights)
    protected double[] svertexWeights;
    // graph weight (sum of all edges' weights)
    protected double sgraphWeight;

    // INVERTED edges' weight inter/intra clusters
    protected double[][] dclusterWeights;
    // INVERTED vertices's weight (sum of incident edges' weights)
    protected double[] dvertexWeights;
    // INVERTED graph weight (sum of all edges' weights)
    protected double dgraphWeight;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public AbstractClusteringWeights(Graph<V, E> graph, ClusteringAlgorithm.Clustering<V> clustering) {
        this.graph = graph;
        this.clustering = clustering;

        this.maxWeight = graph.edgeSet()
                .stream()
                .map(graph::getEdgeWeight)
                .max(Double::compareTo).get();
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
        this.sclusterWeights = LinAlg.newMatrix(k);
        // vertex's weights (sum of incident edges' weights)
        this.svertexWeights = LinAlg.newVector(this.graph.vertexSet().size());

        // weights inter/intra cluster edge weights
        this.dclusterWeights = LinAlg.newMatrix(k);
        // vertex's weights (sum of incident edges' weights)
        this.dvertexWeights = LinAlg.newVector(this.graph.vertexSet().size());

        // compute the weights
        this.graph.edgeSet().forEach(this::add);
    }

    protected abstract void add(E e);

    protected void add(E e, double sweight, double dweight) {
        V source = graph.getEdgeSource(e);
        V target = graph.getEdgeTarget(e);

        int ci = clusterOf(source);
        int cj = clusterOf(target);

        clusterCounts [ci][cj] += 1;
        clusterCounts [cj][ci] += 1;

        int vi = vidx.get(source);
        int vj = vidx.get(target);

        // similarity

        sgraphWeight += sweight;

        sclusterWeights[ci][cj] += sweight;
        sclusterWeights[cj][ci] += sweight;

        svertexWeights[vi] += sweight;
        svertexWeights[vj] += sweight;

        // dissimilarity

        dgraphWeight += dweight;

        dclusterWeights[ci][cj] += dweight;
        dclusterWeights[cj][ci] += dweight;

        dvertexWeights[vi] += dweight;
        dvertexWeights[vj] += dweight;
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

    @Override
    public int getGraphSize() {
        return graph.edgeSet().size();
    }

    @Override
    public int getClustersCount(int ci, int cj) {
        if (ci == cj)
            return clusterCounts[ci][cj]/2;
        else
            return clusterCounts[ci][cj];
    }

    @Override
    public int getInternalCount(int c) {
        return clusterCounts[c][c]/2;
    }

    @Override
    public int getExternalCount(int c) {
        int ecount = 0;
        for (int cj=0; cj<k; ++cj)
            if (c != cj)
                ecount += clusterCounts[c][cj];
        return ecount;
    }


    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    public double getGraphWeight(WeightType weightType) {
        if (weightType == WeightType.DISSIMILARITY)
            return dgraphWeight;
        else
            return sgraphWeight;
    }

    @Override
    public double getClustersWeight(int ci, int cj, WeightType weightType) {
        double[][] clusterWeights = getClusterWeights(weightType);
        if (ci == cj)
            return clusterWeights[ci][cj]/2;
        else
            return clusterWeights[ci][cj];
    }

    @Override
    public double getInternalWeight(int c, WeightType weightType) {
        double[][] clusterWeights = getClusterWeights(weightType);
        return clusterWeights[c][c]/2;
    }

    @Override
    public double getExternalWeight(int c, WeightType weightType) {
        double[][] clusterWeights = getClusterWeights(weightType);
        double eweight = 0;
        for (int cj = 0; cj < k; ++cj)
            if (cj != c)
                eweight += clusterWeights[c][cj];
        return eweight;
    }

    private double[][] getClusterWeights(WeightType weightType) {
        if (weightType == WeightType.DISSIMILARITY)
            return dclusterWeights;
        else
            return sclusterWeights;
    }

    // ----------------------------------------------------------------------
    // Metrics similarity
    // ----------------------------------------------------------------------

    /**
     *
     * 2007 - Survey Graph Clustering, pag 44
     */
    public double getModularity() {
        double modularity = 0.;

        // sum the internal weights
        for (int c=0; c<k; ++c)
            modularity += getInternalWeight(c, WeightType.SIMILARITY);

        // subtract the external weights
        for (int c=0; c<k; ++c)
            modularity -= getExternalWeight(c, WeightType.SIMILARITY);

        return modularity;
    }

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
        double m = sgraphWeight;

        // clusterWeights[c][c] == 2*sum(edge weights in cluster c)

        for(int c=0; c<k; ++c)
            lmod += m* sclusterWeights[c][c] - vprod[c];

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
                vprod[ci] += svertexWeights[i]* svertexWeights[j];
            }
        });

        return vprod;
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
    // Metrics dissimilarity == distance
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

    /**
     *                min_{i,j} d_a(C_i,C_j)
     *      Dunn_G = ------------------------
     *                   max_h s_a(C_h)
     *
     * https://en.wikipedia.org/wiki/Dunn_index
     */
    public double getDunnIndex() {
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

    private double[][] distances() {
        double[][] d = LinAlg.newMatrix(k);
        for (int c=0; c<k; ++c)
            d[c][c] = div(dclusterWeights[c][c], clusterSizes[c]*(clusterSizes[c]-1));

        for (int ci=0; ci<k; ++ci)
            for (int cj=0; cj<k; ++cj)
                if (ci != cj)
                    d[ci][cj] = div(dclusterWeights[ci][cj], clusterSizes[ci]*clusterSizes[cj]);

        return d;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
