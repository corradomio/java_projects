package jext.optim.domain.vgraph;

public class DirectGraph<V> extends AbstractGraph<V> {

    @Override
    protected Edge<V> newEdge(V sourceVertex, V targetVertex) {
        return new DirectedEdge<>(sourceVertex, targetVertex);
    }
}
