package jext.jgrapht.edges;

import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.Objects;

public class WeightedEdge extends DefaultWeightedEdge implements Weighted {

    public double getWeight() {
        return super.getWeight();
    }

    @Override
    public int hashCode() {
        int h1 = Objects.hash(getSource(), getTarget());
        int h2 = Objects.hash(getTarget(), getSource());
        return Math.min(h1, h2);
    }

    @Override
    public boolean equals(Object obj) {
        WeightedEdge that = (WeightedEdge) obj;
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
