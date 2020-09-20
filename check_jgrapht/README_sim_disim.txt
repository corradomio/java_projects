Caveman Graph
    10 cluster.
    
    intra   STESSO CLUSTER
    inter   TRA CLUSTERS


similarita' -> [0,1]
    0: per nulla simili  (-> differenti)
    1: totalmente simili (uguali)


dissimilarita' -> [0,1]
    0: per nulla dissimili  (-> uguali)
    1: totalmente dissimili 


dissimilarita':  1 - similarita'


Definizione standard di clustering:
    i pesi rappresentano la 'similarita'

    MOLTI archi 'simili' intra cluster   (STESSO CLUSTER)
    POCHI archi 'simili' inter cluster   (TRA CLUSTERS)

    90% degli archi intra cluster   (STESSO CLUSTER)
    20% degli archi inter cluster   (TRA CLUSTERS) 

Se usiamo la 'dissimilarita':

    POCHI archi 'dissimili' intra cluster   (STESSO CLUSTER)
    MOLTI archi 'dissimili' inter cluster   (TRA CLUSTERS)


-----------------------------------------------------

Per poter generare un grafo usando una limitata quantita' di 
memoria e potenza di calcolo, bisogna necessaramente usare
la rappresentazione che usa meno oggetti.

Dato un numero predefinito di vertici e di archi, e' meglio
avere un modello basato sulla similarita' o dissimilarita'?

similarita' -> 
    
    MOLTI archi 'simili' intra cluster
    POCHI archi 'simili' inter cluster


dissimilarita'

    POCHI archi 'dissimili' intra cluster
    MOLTI archi 'dissimili' inter cluster


Grafo da 1000 nodi:

    clique[1000nodi] = ~500_000 archi

Supponendo 10 cluster da 100 nodi l'uno
    
    clique[10nodi] = 4950 archi
    10 clique      = ~50_000 archi
    
    archi non compresi:  ~45_000
    20% non compresi:    ~90_000
    
    Quindi un grafo comprendente tutti gli archi richiesti
    dovrebbe contenere ~140_000 archi
    

Un modello basato sulla 'similarita' permette 

-----------------------------------------------------

Problema:
    generiamo il grafo di un tipo,
    come otteniamo il grafo complementare?
    
    Con grafi di grandi dimensioni, non e' possibile generare il 
    grafo complementare, MA puo' essere fatto matematicamente
    usando opportunamente le formule


-------------------------------------------------------

    Consideriamo il numero di archi mancanti:
    
    cluster di n nodi ->


-------------------------------------------------------

Semantica delle metriche


    https://en.wikipedia.org/wiki/Modularity_(networks)
    https://en.wikipedia.org/wiki/Louvain_modularity
    https://en.wikipedia.org/wiki/Dunn_index
    https://en.wikipedia.org/wiki/Davies%E2%80%93Bouldin_index
    https://en.wikipedia.org/wiki/Silhouette_(clustering)

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

    based on edge weights

        modularity              -> sim
        louvainModularity       -> sim
            it measures the density of links inside communities compared to links between communities.
            maximize ration between weights inside community/total weight
        dunnIndex               -> disim
            the distance between two data points is based on an Euclidean/Mathattan Distance.
            Two point are very similar if their distance is very small
        daviesBouldinIndex      ->disim



