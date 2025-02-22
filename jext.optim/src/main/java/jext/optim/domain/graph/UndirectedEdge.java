package jext.optim.domain.graph;

public class UndirectedEdge<V> extends Edge<V> {

    public UndirectedEdge(V sourceVertex, V targetVertex) {
        super(sourceVertex, targetVertex, false);
    }

    @Override
    public String toString() {
        return String.format("%s--%s", sourceVertex, targetVertex);
    }

}
