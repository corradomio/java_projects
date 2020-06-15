package jext.graph;

public interface Graph<V, E> {

    int addVertex(V vertex);
    E newEdge(V source, V target);
    E newEdge(int source, int target);
    int addEdge(E edge);
}
