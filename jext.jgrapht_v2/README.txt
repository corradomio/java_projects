Graph & Edge
------------

    A quanto sembra, un edge in JGraphT NON e' un oggetto che rappresenta una
    coppia di vertici, MA e' un oggetto che lo identifica univocamente.
    NON NECCESSARIAMENTE contiene le informazioni relative a 'source', 'target' e 'weight'.

    Infatti, as esempio, e' possibile creare edge sulla base di un Integer.
    In questo caso, per ottenere le informazioni relative ai vertici, si chiedono al grafo
    e NON all'edge:

            V getEdgeSource(E e);
            V getEdgeTarget(E e);
            double getEdgeWeight(E e);

    SE l'edge deriva dalla classe 'IntrusiveEdge' (o 'IntrusiveWeightedEdge')
    allora la libreria automaticamente popola i field 'source', 'target' e 'weight'.
    In questo caso, sono messi a disposizione i metodi 'protected':

            V getSource()
            V getTarget()
            double getWeight()

    La generazione AUTOMATICA di vertices ed edges viene fatta mediante l'implementazione
    di

            interface Supplier<T> {
                T get();
            }

    Questo oggetto ha il compito di creare un'istanza di T la quale deve essere univocamente
    identificabile. SE non si reimplementa 'Object::equals(Object)' il confronto viene fatto
    in base all'indirizzo di memoria.

        -- Vertices ---------------------------------------------------------

        Set<V> vertexSet();

        V addVertex();
        boolean addVertex(V v);
        boolean containsVertex(V v);
        int degreeOf(V vertex);
        int inDegreeOf(V vertex);
        int outDegreeOf(V vertex);

        boolean removeAllVertices(Collection<? extends V> vertices);
        boolean removeVertex(V v);


        -- Edges ------------------------------------------------------------

        Set<E> edgeSet();
        E getEdge(V sourceVertex, V targetVertex);
        E addEdge(V sourceVertex, V targetVertex);
        boolean addEdge(V sourceVertex, V targetVertex, E e);

        boolean containsEdge(V sourceVertex, V targetVertex);
        boolean containsEdge(E e);

        boolean removeAllEdges(Collection<? extends E> edges);
        Set<E> removeAllEdges(V sourceVertex, V targetVertex);
        E removeEdge(V sourceVertex, V targetVertex);
        boolean removeEdge(E e);

        Set<E> edgesOf(V vertex);
        Set<E> incomingEdgesOf(V vertex);
        Set<E> outgoingEdgesOf(V vertex);

        V getEdgeSource(E e);
        V getEdgeTarget(E e);
        double getEdgeWeight(E e);

        void setEdgeWeight(E e, double weight);
        void setEdgeWeight(V sourceVertex, V targetVertex, double weight)

        -- Done -------------------------------------------------------------


Edge Hierarchy
--------------

    IntrusiveEdge                       (fields: source, target)
        DefaultEdge                         (methods: getSource(),getTarget())
            DirectedEdge                jext.jgrapht
            Edge                        jext.jgrapht
        IntrusiveWeightedEdge           (fields: + weight)
            DefaultWeightedEdge             (methods: + getWeight())
                WeightedDirectedEdge    jext.jgrapht
                WeightedEdge            jext.jgrapht

    Edge<V>,
    Directed,
    Weighted



Graph Hierarchy
---------------





JGrapT library
--------------

org.jgrapht.
    alg
        interfaces
            ... interfaces of all algorithms
            ... each subpackage will implement the general algorithm
                with a specific implementation
        ... algorithms
    event
    generate
        ... generators
    graph
        ... creators/modifiers
    traverse
        ... traverse algorithms
    util
        ... utilities

    Graphs
        - to create & modify a graph
    GraphMetrics
        -- diameter
        -- radius
        -- girth
        -- number of triangles


Metriche
--------

    order -> n of vertices
    size  -> n of edges


Semantica delle metriche
------------------------


    https://en.wikipedia.org/wiki/Modularity_(networks)
    https://en.wikipedia.org/wiki/Louvain_modularity
    https://en.wikipedia.org/wiki/Dunn_index
    https://en.wikipedia.org/wiki/Davies%E2%80%93Bouldin_index
    https://en.wikipedia.org/wiki/Silhouette_(clustering)

    based on edge weights

        modularity              -> sim
        louvainModularity       -> sim
            it measures the density of links inside communities compared to links between communities.
            maximize ration between weights inside community/total weight


    Note:   (euclidean) distance == dissimilarity

        dunnIndex               -> disim
            the distance between two data points is based on an Euclidean/Mathattan Distance.
            Two point are very similar if their distance is very small
        daviesBouldinIndex      -> disim

        silhuette                > sim

    based on vertex counts

        unbalancingIndex
        purity
        giniIndex
        entropy
        randIndex
        adjustedRandIndex
        fowlkesMallowsIndex
        jaccardCoefficient
        normalizedGamma


