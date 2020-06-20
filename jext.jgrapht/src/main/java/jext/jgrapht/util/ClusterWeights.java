package jext.jgrapht.util;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ClusteringAlgorithm;

import java.util.HashMap;
import java.util.Map;

public class ClusterWeights<V, E> {

    private double maxWeight;
    private Graph<V, E> graph;
    private ClusteringAlgorithm.Clustering<V> clustering;

    private int k;
    private int[] clusterSizes;   // n of elements in each cluster
    private double[][] weightClusters;
    private double[] weightVertices;
    private double graphWeight;
    private Map<V, Integer> vidx = new HashMap<>();

    public ClusterWeights(){
        this(0.);
    }

    public ClusterWeights(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public ClusterWeights<V, E> init(Graph<V, E> g, ClusteringAlgorithm.Clustering<V> clustering) {
        this.graph = g;
        this.clustering = clustering;
        this.k = clustering.getNumberClusters();

        int order = 0;
        this.clusterSizes = new int[k];
        for (int ci=0; ci<k; ++ci) {
            int clusterSize = clustering.getClusters().get(ci).size();
            this.clusterSizes[ci] = clusterSize;
            order += clusterSize;
        }

        this.weightClusters = LinAlg.newMatrix(k);
        this.weightVertices = LinAlg.newVector(order);

        graph.edgeSet().forEach(e -> {
            double weight = weightOf(e);
            V source = graph.getEdgeSource(e);
            V target = graph.getEdgeTarget(e);
            this.add(source, target, weight);
        });

        return this;
    }

    private double weightOf(E e) {
        double weight = graph.getEdgeWeight(e);
        if(maxWeight > 0)
            weight = maxWeight - weight;
        return weight;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public double getInternalDegree(int ci) {
        return weightClusters[ci][ci];
    }

    public double getExternalDegree(int ci) {
        double extDeg = -weightClusters[ci][ci];
        for (int cj=0; cj<k; ++cj)
            extDeg += weightClusters[ci][cj];
        return extDeg;
    }

    // ----------------------------------------------------------------------

    /**
     *
     * 2007 - Survey Graph Clustering, pag 44
     */
    public double getModularity() {
        double modularity = 0.;
        for (int c=0; c<k; ++c)
            modularity += weightClusters[c][c];

        for (int ci=0; ci<k; ++ci)
            for(int cj=0; cj<k; ++cj)
                if (ci != cj)
                    modularity -= weightClusters[ci][cj];

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
     *
     *            1             [         k_i*k_j  ]
     *      Q = ----- SUM_{i,j} [ w_ij - --------- ] delta(c_i,c_j)
     *           2m             [           2m     ]
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
        double m2 = 2*graphWeight;

        for(int ci=0; ci<k; ++ci)
            lmod += m2*weightClusters[ci][ci] - vprod[ci];

        return div(lmod, sq(m2));
    }

    /**
     * Product of
     * @return
     */
    private double[] vprod() {
        double vprod[] = LinAlg.newVector(k);

        graph.edgeSet().forEach(e -> {
            V source = graph.getEdgeSource(e);
            V target = graph.getEdgeTarget(e);

            int vi = indexOf(source);
            int vj = indexOf(target);

            int ci = clusterOf(source);
            int cj = clusterOf(target);

            if (ci == cj) {
                vprod[ci] += weightVertices[vi]*weightVertices[vj];
            }
        });

        return vprod;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public void add(V vi, V vj, double weight) {
        graphWeight += weight;

        int ci = clusterOf(vi);
        int cj = clusterOf(vj);
        weightClusters[ci][cj] += weight;
        weightClusters[cj][ci] += weight;

        int i = indexOf(vi);
        int j = indexOf(vj);
        weightVertices[i] += weight;
        weightVertices[j] += weight;
    }

    private int indexOf(V v) {
        if (!vidx.containsKey(v))
            vidx.put(v, vidx.size());
        return vidx.get(v);
    }

    private int clusterOf(V v) {
        for (int ci=0; ci<k; ++ci)
            if (clustering.getClusters().get(ci).contains(v))
                return ci;
        return -1;
    }

    private static double div(double x, double y) { return y != 0. ? x/y : 0.; }
    private static double sq(double x) { return x*x; }
}
