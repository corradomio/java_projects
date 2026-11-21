package jext.jgrapht.edges;

import org.jgrapht.graph.DefaultEdge;

import java.util.Objects;

public class DirectedEdge<V> extends DefaultEdge implements Edge<V>, Directed {

    @Override
    public V getSource() {
        return (V) super.getSource();
    }

    @Override
    public V getTarget() {
        return (V) super.getTarget();
    }

    public double getWeight() {
        return 1.;
    }

    public V getOpposite(V v) {
        if (v.equals(getSource()))
            return getTarget();
        if (v.equals(getTarget()))
            return getSource();
        throw new IllegalArgumentException("no such vertex: " + v);
    }

    // ----------------------------------------------------------------------

    @Override
    public int hashCode() {
        return Objects.hash(getSource(), getTarget());
    }

    @Override
    public boolean equals(Object obj) {
        DirectedEdge that = (DirectedEdge) obj;
        return this.getSource().equals(that.getSource())
            && this.getTarget().equals(that.getTarget());
    }

    @Override
    public String toString() {
        return getSource() + "->" + getTarget();
    }

}
