File:
    <name>.csv
        pesi degli achi del grafo completo.
        ogni record ha un id (0,1,2...)
        il file contiene "u,v,w[u,v]"

    <name>-target.csv
        clustering ground truth, basata sulla
        classificazione originale del dataset
        il file contiene "u, class[u]"

    <name>-stats.csv
        numero di clusters basate sul thresholding

    <name>-stats-clean.csv
        stesso di '<name>-stats.csv'
        nell'implementazione originale era la media di molte simulazioni
        in questo caso abbiamo un'unica simulazione.
        Cmunque le colonne finali contengono le DIFFERENZE tra tresholding
        adiacenti, invece del valore dell'indice

    <name>-result.csv
        risultato delle predizioni. 'c' e' il ground truth

