package jext.jgrapht.weights;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ClusteringAlgorithm;

import static java.lang.Math.max;
import static jext.math.Mathx.sq;

public class SimilarityWeights<V, E> extends AbstractClusteringWeights<V, E> {

    // ----------------------------------------------------------------------
    // Inverted weights
    // ----------------------------------------------------------------------

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public SimilarityWeights(Graph<V, E> graph, ClusteringAlgorithm.Clustering<V> clustering) {
        super(graph, clustering);
    }

    @Override
    protected void add(E e) {
        double sweight = graph.getEdgeWeight(e);
        double dweight = fWeight*maxWeight - sweight;
        add(e, sweight, dweight);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
