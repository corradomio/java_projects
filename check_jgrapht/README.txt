https://docs.google.com/document/d/1ftu1Uiuh8wlB9M0Ag5fxO9tTVgWA-6HaZoFeLYsKsTQ/edit
https://www.overleaf.com/project/5f0607085a8e780001257438   (vecchio)
https://www.overleaf.com/project/5f5a75bb3f29aa0001f208b9   (nuovo)


Semantica del peso degli archi:
    - similarity        in [0,1]
    - dissimilarity     in [0,1]
    - dissimilarity = 1 - similarity,

Clustering
    - cluster contiene i nodi
        PIU' simili
        MENO dissimili


Caveman generation:
    poiche' dobbiamo avere
    - nodi MENO dissimili nel cluster
    - nodi PIU' dissimili tra cluster
    il cluster viene generato con
    - 20% prob archi interni
    - 90% prob archi esterni

Metriche:

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
        dunnIndex               -> disim
            the distance between two data points is based on an Euclidean/Mathattan Distance.
            Two point are very similar if their distance is very small
        daviesBouldinIndex      ->disim

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
