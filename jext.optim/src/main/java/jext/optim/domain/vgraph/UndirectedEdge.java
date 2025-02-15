package jext.optim.domain.vgraph;

public class UndirectedEdge<V> extends Edge<V> {

    public UndirectedEdge(V sourceVertex, V targetVertex) {
        super(sourceVertex, targetVertex);
        if (sourceVertex.hashCode() > targetVertex.hashCode()) {
            V tmp = this.sourceVertex;
            this.sourceVertex = this.targetVertex;
            this.targetVertex = tmp;
        }
    }

    @Override
    public String toString() {
        return String.format("%s--%s", sourceVertex, targetVertex);
    }

}
