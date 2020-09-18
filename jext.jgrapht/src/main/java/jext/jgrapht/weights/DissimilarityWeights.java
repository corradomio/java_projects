package jext.jgrapht.weights;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ClusteringAlgorithm;

public class DissimilarityWeights<V, E> extends AbstractClusteringWeights<V, E> {

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public DissimilarityWeights(Graph<V, E> graph, ClusteringAlgorithm.Clustering<V> clustering) {
        super(graph, clustering);
    }

    @Override
    protected void add(E e) {
        double dweight = graph.getEdgeWeight(e);
        double sweight = fWeight*maxWeight - dweight;
        add(e, sweight, dweight);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
