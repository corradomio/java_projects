package jext.jgrapht;

import org.jgrapht.graph.DefaultEdge;

public class UndirectedEdge<V> extends DefaultEdge {

    public V getSource() {
        return (V) super.getSource();
    }

    public V getTarget() {
        return (V) super.getTarget();
    }

    public V getOpposite(V v) {
        if (v.equals(getSource()))
            return getTarget();
        if (v.equals(getTarget()))
            return getSource();
        throw new IllegalArgumentException("no such vertex: " + v.toString());
    }
}
