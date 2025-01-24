package jext.jgrapht.edges;

import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.Objects;

public class WeighedDirectedEdge extends DefaultWeightedEdge implements Weighted {

    public double getWeight() {
        return super.getWeight();
    }

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
