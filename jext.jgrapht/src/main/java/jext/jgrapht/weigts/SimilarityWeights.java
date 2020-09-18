package jext.jgrapht.weigts;

import jext.jgrapht.util.LinAlg;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.interfaces.ClusteringAlgorithm;

import java.util.Set;

import static java.lang.Math.max;
import static jext.math.Mathx.div;
import static jext.math.Mathx.sq;

public class SimilarityWeights<V, E> extends AbstractClusteringWeights<V, E> {

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public SimilarityWeights(Graph<V, E> graph, ClusteringAlgorithm.Clustering<V> clustering) {
        super(graph, clustering);
    }

    // ----------------------------------------------------------------------
    // Metrics
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

    @Override
    public double getDaviesBouldinIndex() {
        return 0;
    }

    @Override
    public double getDunnIndex() {
        return 0;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
