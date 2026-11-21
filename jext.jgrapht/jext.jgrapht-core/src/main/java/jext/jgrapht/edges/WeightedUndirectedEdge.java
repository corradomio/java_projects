package jext.jgrapht.edges;

import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.Objects;

public class WeightedUndirectedEdge<V> extends DefaultWeightedEdge implements Edge<V>, Weighted {

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
        int h1 = Objects.hash(getSource(), getTarget());
        int h2 = Objects.hash(getTarget(), getSource());
        return Math.min(h1, h2);
    }

    @Override
    public boolean equals(Object obj) {
        WeightedUndirectedEdge that = (WeightedUndirectedEdge) obj;
        return this.getSource().equals(that.getSource())
            && this.getTarget().equals(that.getTarget())
            || this.getSource().equals(that.getTarget())
            && this.getTarget().equals(that.getSource());
    }

    @Override
    public String toString() {
        return getSource() + "-" + getTarget();
    }

}
