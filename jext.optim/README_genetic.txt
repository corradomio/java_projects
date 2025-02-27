Alcune migliorie
----------------

    Invece di avere direttamente l'oggetto Chromosome, servono i seguenti elementi

        T candidate
            un candidato

        CandidateFactory<T>
            generatore di candidati random

        FitnessFunction<T>
            calcola la fitness function sul candidato

    Ora c'e' un problema: massimizzare OPPURE minimizzare la fitness function?
    Poiche' dipende dal significato della funzione, va da se che non c'e' una soluzione.
    Pero' l'oggetto su cui si puo' applicare la regola e' Chromosome che implementa Comparable

        Chromosome<T> implements Comparable<Chromosome<T>>

    Quindi NON SI CONFRONTANO direttamente i valori della fitness function, MA si confrontano
    tra di loro i Chromosome<T>

    -----------------------------------------------------------------------------

    Problema: ci sono diversi punti in cui due cromosomi devono essere confrontati tra loro.
    L'implementazione di default dice:

        Compares two chromosomes based on their fitness. The bigger the fitness, the better the chromosome.

    Quindi il 'trucco' descritto precedentemente risolve tutti i problemi.


RandomGenerator
---------------

    Ci sono molti punti in cui server in RNG.
    L'implementazione corrente, che distribuisce l'RNG ai vari oggetti,
    non sembra molto 'intelligente' e, soprattutto, contrasta con
    l'implementazione di Apache.
    Soluzione: un oggetto 'globale' che puo' essere richiamato da ovunque.
    Da notare che gia' GeneticAlgorithm ha un metodo statico per ricuperare
    l'RNG globale


    Utilizzo:

        - Population<T>: passato a CandidateFactory<T> per creare nuovi candidati
        - GA Operators: ogni operatore fa uso di un RNG


Candidati
---------

    Il candidato DEVE implementare

        "hashCode()"
        "equals(Object obj)"

    perche' fa parte del contratto necessario per metter gli oggetti in HashMap e HashSet
    Il candidato e' un oggetto IMMUTABILE. Quindi MutatePolicy DEVE creare
    un nuovo oggetto.

    Si suppone che 'CandidateFactory<T>' generi candidati 'validi'
    Comunque, non necessariamente gli operatori di mutation/crossover generano
    candidati validi. Potrebbe essere inutilmente complicato assicurarsi che
    un candidato si valido.

    L'approccio e': avere mutation/crossover semplici, e prima della prossima
    generazione, applicare un filtro o una patch ai candidati non validi.


Operatori Genetici
------------------

    L'operatore SelectionPolicy DEVE clonare il candidato
    L'operatore MutationPolicy PUO modificare in-place il candidato
    L'operatore CrossoverPolicy per sua natura crea nuovi candidati

    GLi operatori vengono usati nel seguente ordine

        1) select
        2) crossover    per sua natura crea nuovi oggetti
        3) mutation     DEVE creare un nuovo oggetto!


Creazione della popolazione successiva
--------------------------------------

    Il processo di selezione e' stato esteso nel seguente modo:
    e' possibile aggiungere un FilterPolicy che ha il compito di
    "filtrare" i candidati correnti per rimuovere quelli che, in qualche modo
    non soddisfano dei requisiti.
    Esempi classici sono:

        1) rimozione duplicati
        2) rimozione candidati che non soddisfano i contraints

    Fatto questo, si procede a selezionare i candidati in base
    a elitism, quindi si generano nuovi candidati usando la factory
    fino ad avere la popolazione completa.


Apache Commons Genetic
----------------------

    Fitness
        - double fitness()

    Chromosome implements Comparable<Chromosome>, Fitness           <<<<
        AbstractListChromosome<T>
            AbstractListChromosome<Integer>

    Population extends Iterable<Chromosome>
        - int getPopulationSize();
        - int getPopulationLimit();
        - Population nextGeneration();
        - void addChromosome(Chromosome chromosome)
        - Chromosome getFittestChromosome();


-----------------------------------------------------------------------------
- Domains
-----------------------------------------------------------------------------
Lista di 'domini' (usati in Mathematica)

    Booleans([n1,...nk])
        generic tensor k-dim of binary values

    Assignment[n,m, Row|Column]
        assignment with a single 1 in each row or column
        The 'compact' representation is the same than Partition(n,k)
        where, for each element, it is specified where the 1 is located
            Row     -> column
            Column  -> row
        Nota: puo' essere rappresentato come un Partition

    Subset[n]
        generic subset of a set {0,...n-1}
        it can be represented as Boolean[n]

    Permutation[n]
        generic permutation of [0,...n-1]

    Partition[n,k]
        partitioning of {0,..n-1} in k subsets
        The representation is the array of integers {s1,...s[n-1]}
        where each si specify in which subset (0..k-1) the element is located

    Range([n1,...nk],{min,max})     integer/double/
        generic tensor k-dim of integer/real values in the specified range
        For simplicity, the range is the same for all elements in the tensor

    Component[{c1,...cn},{v1,...vn},{vmin,vmax}]
    Component[{c1,...cn},{{v11,...v1},...n},{{v1min,v1max}, ...}]
        each component has one or more values and there are min/max constraints
