package jext.jgrapht.edges;

import org.jgrapht.graph.DefaultEdge;

import java.util.Objects;

public class DirectedEdge extends DefaultEdge implements Directed {

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
