package jext.optim.domain.vgraph;

public abstract class Edge<V> {
    protected V sourceVertex;
    protected V targetVertex;
    private float weight = 1;

    public Edge(V sourceVertex, V targetVertex) {
        this.sourceVertex = sourceVertex;
        this.targetVertex = targetVertex;
    }

    public V getSourceVertex() {
        return sourceVertex;
    }

    public V getTargetVertex() {
        return targetVertex;
    }

    public float getEdgeWeight() {
        return weight;
    }

    public void setEdgeWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public int hashCode() {
        int hs = sourceVertex.hashCode();
        int ht = targetVertex.hashCode();
        return hs*31 + ht;
    }

    @Override
    public boolean equals(Object obj) {
        Edge<V> that = (Edge<V>) obj;
        return this.sourceVertex.equals(that.sourceVertex) && this.targetVertex.equals(that.targetVertex);
    }

}
