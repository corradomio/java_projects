package jext.optim.domain.graph;

import java.util.Objects;

public abstract class Edge<V> {
    protected V sourceVertex;
    protected V targetVertex;
    private double weight = 1;

    protected Edge(V sourceVertex, V targetVertex, boolean directed) {
        this.sourceVertex = sourceVertex;
        this.targetVertex = targetVertex;

        if (!directed && sourceVertex.hashCode() > targetVertex.hashCode()) {
            V tmp = this.sourceVertex;
            this.sourceVertex = this.targetVertex;
            this.targetVertex = tmp;
        }
    }

    public V getSourceVertex() {
        return sourceVertex;
    }

    public V getTargetVertex() {
        return targetVertex;
    }

    public double getEdgeWeight() {
        return weight;
    }

    public void setEdgeWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceVertex, targetVertex);
    }

    @Override
    public boolean equals(Object obj) {
        Edge<V> that = (Edge<V>) obj;
        return this.sourceVertex.equals(that.sourceVertex) && this.targetVertex.equals(that.targetVertex);
    }

}
