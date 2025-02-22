package jext.optim.domain.graph;

public class DirectedEdge<V> extends Edge<V> {

    public DirectedEdge(V sourceVertex, V targetVertex) {
        super(sourceVertex, targetVertex, true);
    }

    @Override
    public String toString() {
        return String.format("%s->%s]", sourceVertex, targetVertex);
    }

}
