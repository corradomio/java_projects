package jext.optim.domain.graph;

public class DirectGraph<V> extends AbstractGraph<V> {

    @Override
    public Edge<V> newEdge(V sourceVertex, V targetVertex) {
        return new DirectedEdge<>(sourceVertex, targetVertex);
    }

}
