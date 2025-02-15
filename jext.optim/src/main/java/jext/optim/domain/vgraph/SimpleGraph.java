package jext.optim.domain.vgraph;

public class SimpleGraph <V> extends AbstractGraph<V> {

    @Override
    protected Edge<V> newEdge(V sourceVertex, V targetVertex) {
        return new UndirectedEdge<>(sourceVertex, targetVertex);
    }
}
