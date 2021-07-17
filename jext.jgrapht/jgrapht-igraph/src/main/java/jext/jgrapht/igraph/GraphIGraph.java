package jext.jgrapht.igraph;

import org.jgrapht.Graph;
import org.jgrapht.GraphType;
import org.jgrapht.graph.DefaultEdge;

import java.util.Collection;
import java.util.Set;
import java.util.function.Supplier;

public class GraphIGraph implements Graph<Float, DefaultEdge> {

    @Override
    public Set<DefaultEdge> getAllEdges(Float sourceVertex, Float targetVertex) {
        return null;
    }

    @Override
    public DefaultEdge getEdge(Float sourceVertex, Float targetVertex) {
        return null;
    }

    @Override
    public Supplier<Float> getVertexSupplier() {
        return null;
    }

    @Override
    public Supplier<DefaultEdge> getEdgeSupplier() {
        return null;
    }

    @Override
    public DefaultEdge addEdge(Float sourceVertex, Float targetVertex) {
        return null;
    }

    @Override
    public boolean addEdge(Float sourceVertex, Float targetVertex, DefaultEdge defaultEdge) {
        return false;
    }

    @Override
    public Float addVertex() {
        return null;
    }

    @Override
    public boolean addVertex(Float aFloat) {
        return false;
    }

    @Override
    public boolean containsEdge(Float sourceVertex, Float targetVertex) {
        return false;
    }

    @Override
    public boolean containsEdge(DefaultEdge defaultEdge) {
        return false;
    }

    @Override
    public boolean containsVertex(Float aFloat) {
        return false;
    }

    @Override
    public Set<DefaultEdge> edgeSet() {
        return null;
    }

    @Override
    public int degreeOf(Float vertex) {
        return 0;
    }

    @Override
    public Set<DefaultEdge> edgesOf(Float vertex) {
        return null;
    }

    @Override
    public int inDegreeOf(Float vertex) {
        return 0;
    }

    @Override
    public Set<DefaultEdge> incomingEdgesOf(Float vertex) {
        return null;
    }

    @Override
    public int outDegreeOf(Float vertex) {
        return 0;
    }

    @Override
    public Set<DefaultEdge> outgoingEdgesOf(Float vertex) {
        return null;
    }

    @Override
    public boolean removeAllEdges(Collection<? extends DefaultEdge> edges) {
        return false;
    }

    @Override
    public Set<DefaultEdge> removeAllEdges(Float sourceVertex, Float targetVertex) {
        return null;
    }

    @Override
    public boolean removeAllVertices(Collection<? extends Float> vertices) {
        return false;
    }

    @Override
    public DefaultEdge removeEdge(Float sourceVertex, Float targetVertex) {
        return null;
    }

    @Override
    public boolean removeEdge(DefaultEdge defaultEdge) {
        return false;
    }

    @Override
    public boolean removeVertex(Float aFloat) {
        return false;
    }

    @Override
    public Set<Float> vertexSet() {
        return null;
    }

    @Override
    public Float getEdgeSource(DefaultEdge defaultEdge) {
        return null;
    }

    @Override
    public Float getEdgeTarget(DefaultEdge defaultEdge) {
        return null;
    }

    @Override
    public GraphType getType() {
        return null;
    }

    @Override
    public double getEdgeWeight(DefaultEdge defaultEdge) {
        return 0;
    }

    @Override
    public void setEdgeWeight(DefaultEdge defaultEdge, double weight) {

    }
}
