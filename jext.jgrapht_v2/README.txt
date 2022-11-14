or.jgrapht.
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



order -> n of vertices
size  -> n of edges


Semantica delle metriche


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

        siluette                > sim

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


