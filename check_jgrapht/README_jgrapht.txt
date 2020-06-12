https://snap.stanford.edu/data/index.html
https://www.timlrx.com/2019/05/05/benchmark-of-popular-graph-network-packages/
https://jgrapht.org/guide/UserOverview


public interface Graph<V, E>
    double DEFAULT_EDGE_WEIGHT = 1.0;

    Supplier<V> getVertexSupplier();
    Supplier<E> getEdgeSupplier();

    Set<E> getAllEdges(V sourceVertex, V targetVertex);
    E getEdge(V sourceVertex, V targetVertex);
    E addEdge(V sourceVertex, V targetVertex);
    boolean addEdge(V sourceVertex, V targetVertex, E e);
    boolean containsEdge(V sourceVertex, V targetVertex);
    boolean containsEdge(E e);
    Set<E> edgeSet();
    Set<E> edgesOf(V vertex);
    Set<E> incomingEdgesOf(V vertex);
    Set<E> outgoingEdgesOf(V vertex);
    boolean removeAllEdges(Collection<? extends E> edges);
    Set<E> removeAllEdges(V sourceVertex, V targetVertex);
    E removeEdge(V sourceVertex, V targetVertex);
    boolean removeEdge(E e);

    double getEdgeWeight(E e);
    void setEdgeWeight(E e, double weight);
    void setEdgeWeight(V sourceVertex, V targetVertex, double weight)

    V getEdgeSource(E e);
    V getEdgeTarget(E e);

    V addVertex();
    boolean addVertex(V v);
    boolean containsVertex(V v);
    int degreeOf(V vertex);
    int inDegreeOf(V vertex);
    int outDegreeOf(V vertex);
    boolean removeAllVertices(Collection<? extends V> vertices);
    boolean removeVertex(V v);
    Set<V> vertexSet();

    GraphType getType();
}

public interface ListenableGraph<V, E> extends Graph<V, E>
    public void addGraphListener(GraphListener<V, E> l);
    public void addVertexSetListener(VertexSetListener<V> l);
    public void removeGraphListener(GraphListener<V, E> l);
    public void removeVertexSetListener(VertexSetListener<V> l);
}
