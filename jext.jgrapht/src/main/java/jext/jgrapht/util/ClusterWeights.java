package jext.jgrapht.util;

import org.jgrapht.alg.interfaces.ClusteringAlgorithm;

import java.util.HashMap;
import java.util.Map;

public class ClusterDistances<V> {

    private ClusteringAlgorithm.Clustering<V> clustering;
    private final int k;
    private final int[] clusterSizes;   // n of elements in each cluster
    private final double[][] clusterWeights;
    private final double[] vertexWeights;
    private Map<V, Integer> vidx = new HashMap<>();

    public ClusterDistances(ClusteringAlgorithm.Clustering<V> clustering){
        int order = 0;
        this.clustering = clustering;
        this.k = clustering.getNumberClusters();
        this.clusterSizes = new int[k];
        for (int ci=0; ci<k; ++ci) {
            int clusterSize = clustering.getClusters().get(ci).size();
            this.clusterSizes[ci] = clusterSize;
            order += clusterSize;
        }

        this.clusterWeights = LinAlg.newMatrix(k);
        this.vertexWeights = LinAlg.newVector(order);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public double getInternalDegree(int ci) {
        return clusterWeights[ci][ci];
    }

    public double getExternalDegree(int ci) {
        double extDeg = -clusterWeights[ci][ci];
        for (int cj=0; cj<k; ++cj)
            extDeg += clusterWeights[ci][cj];
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
            modularity += clusterWeights[c][c];

        for (int ci=0; ci<k; ++ci)
            for(int cj=0; cj<k; ++cj)
                if (ci != cj)
                    modularity -= clusterWeights[ci][cj];

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

    private double[] averageDistances() {
        // int k = clustering.getNumberClusters();
        double[] averageDistances = LinAlg.newVector(k);
        for (int i=0; i<k; ++i)
            averageDistances[i] = div(clusterWeights[i][i], clusterSizes[i]*(clusterSizes[i]-1));
        return averageDistances;
    }

    private double[][] betweenSeparation() {
        // int k = clustering.getNumberClusters();
        double[][] betweenSeparation = LinAlg.newMatrix(k);
        for(int i=0; i<k; ++i)
            for(int j=0; j<k; j++)
                if(i != j)
                    betweenSeparation[i][j] = div(clusterWeights[i][i], clusterSizes[i]*clusterSizes[j]);
        return betweenSeparation;
    }

    // ----------------------------------------------------------------------

    /**
     *                min_{i,j} d_a(C_i,C_j)
     *      Dunn_G = ------------------------
     *                   max_h s_a(C_h)
     *
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
     *  m : sum of the edge weights
     *  c_i, c_j: communities
     *  delta: 1 if c_i == c_j else 0
     */
    public double getLouvainModularity() {

    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public void add(V vi, V vj, double weight) {
        int ci = clusterOf(vi);
        int cj = clusterOf(vj);
        clusterWeights[ci][cj] += weight;
        clusterWeights[cj][ci] += weight;

        int i = indexOf(vi);
        int j = indexOf(vj);
        vertexWeights[i] += weight;
        vertexWeights[j] += weight;
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
}
