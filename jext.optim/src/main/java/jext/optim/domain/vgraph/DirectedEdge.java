package jext.optim.domain.vgraph;

public class DirectedEdge<V> extends Edge<V> {

    public DirectedEdge(V sourceVertex, V targetVertex) {
        super(sourceVertex, targetVertex);
    }


    @Override
    public String toString() {
        return String.format("%s->%s]", sourceVertex, targetVertex);
    }

    // private static <V> int hashEdge(V sourceVertex, V targetVertex) {
    //     int hs = sourceVertex.hashCode();
    //     int ht = targetVertex.hashCode();
    //     int hash = hs*31 + ht;
    //     return hash;
    // }

}
