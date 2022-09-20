package jext.jgrapht;

import org.jgrapht.graph.DefaultEdge;

import java.util.Objects;

public class Edge extends DefaultEdge {

    public Object getSource() {
        return super.getSource();
    }

    public Object getTarget() {
        return super.getTarget();
    }

    public Object getOpposite(Object v) {
        if (v.equals(getSource()))
            return getTarget();
        if (v.equals(getTarget()))
            return getSource();
        throw new IllegalArgumentException("no such vertex: " + v);
    }

    @Override
    public int hashCode() {
        int h1 = Objects.hash(getSource(), getTarget());
        int h2 = Objects.hash(getTarget(), getSource());
        return Math.min(h1, h2);
    }

    @Override
    public boolean equals(Object obj) {
        DirectedEdge that = (DirectedEdge) obj;
        return this.getSource().equals(that.getSource())
                && this.getTarget().equals(that.getTarget())
                || this.getSource().equals(that.getTarget())
                && this.getTarget().equals(that.getSource());
    }

}
