package jext.jgrapht.edges;

import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.Objects;

public class WeighedDirectedEdge<V> extends DefaultWeightedEdge implements Edge<V>, Weighted, Directed {

    @Override
    public V getSource() {
        return (V) super.getSource();
    }

    @Override
    public V getTarget() {
        return (V) super.getTarget();
    }

    @Override
    public double getWeight() {
        return super.getWeight();
    }

    public Object getOpposite(Object v) {
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
        WeighedDirectedEdge that = (WeighedDirectedEdge) obj;
        return this.getSource().equals(that.getSource())
            && this.getTarget().equals(that.getTarget());
    }

    @Override
    public String toString() {
        return getSource() + "->" + getTarget();
    }

}
