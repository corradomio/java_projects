package jext.jgrapht.weigts;

import jext.jgrapht.util.LinAlg;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.interfaces.ClusteringAlgorithm;

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
public class ClusterWeights<V, E> extends AbstractClusteringWeights<V, E> {

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ClusterWeights(Graph<V, E> graph, ClusteringAlgorithm.Clustering<V> clustering){
        super(graph, clustering);
        init();
    }

    // ----------------------------------------------------------------------
    // Initialize
    // ----------------------------------------------------------------------

    /**
     * Initialize the data structure with the specified graph and clustering
     */
    protected void init() {
        super.init();
    }

    @Override
    protected void add(E e) {
        double weight = graph.getEdgeWeight(e);
        add(e, weight, weight);
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
    // Louvain Modularity
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
            d[c][c] = div(sclusterWeights[c][c], clusterSizes[c]*(clusterSizes[c]-1));

        for (int ci=0; ci<k; ++ci)
            for (int cj=0; cj<k; ++cj)
                if (ci != cj)
                    d[ci][cj] = div(sclusterWeights[ci][cj], clusterSizes[ci]*clusterSizes[cj]);

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
    //
    // ----------------------------------------------------------------------
}
