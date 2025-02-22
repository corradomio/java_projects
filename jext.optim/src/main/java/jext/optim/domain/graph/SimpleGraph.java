package jext.optim.domain.graph;

public class SimpleGraph <V> extends AbstractGraph<V> {

    @Override
    public Edge<V> newEdge(V sourceVertex, V targetVertex) {
        return new UndirectedEdge<>(sourceVertex, targetVertex);
    }

}
