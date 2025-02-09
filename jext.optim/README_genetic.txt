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

    Ci sono multi punti in cui server in RNG.
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

    Il candidato DEVE implementare "hashCode()" perche' viene usato per
    eliminare i duplicati
    Il candidato DEVE implementare "clone()" perche' serve per l'implementazione
    dell'operatore SelectionPolicy. Questo puo' essere ftto implementando le
    interfacce

        'java.lang.Cloneable'
        'jext.lang.Cloneable'

    'jext.lang.Cloneable<T>' e' usata per forzare il metodo 'clone()' ad
    essere 'public', MA non sembra sia necessario. BASTA implementare
    'java.lang.Cloneable' e rendere 'public' il metodo.


Operatori Genetici
------------------

    L'operatore SelectionPolicy DEVE clonare il candidato
    L'operatore MutationPolicy PUO modificare in-place il candidato
    L'operatore CrossoverPolicy per sua natura crea nuovi candidati

    GLi operatori vengono usati nel seguente ordine

        1) select       [clone]
        2) crossover    per sua natura crea nuovi oggetti
        3) mutation     puo' fare la modifica 'inplace'


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
        ListPopulation (abstract)
            ElitisticListPopulation


-----------------------------------------------------------------------------
- Domains
-----------------------------------------------------------------------------
Lista di 'domini'  (usati in Mathematica)

    Booleans([n1,...nk])
        generic tensor k-dim of binary values

    Assignment(n,m, row|col)
        assignment with a single 1 in each row or column

    Subset(n)
        generic subset of a set {0,...n-1}

    Permutation(n)
        generic permutation of [0,...n-1]

    Partition(n,k)
        partitioning of {0,..n-1} in k subsets

    Range([n1,...nk],{min,max})     integer/float/
        generic tensor k-dim of integer/real values in the specified range

    Set<T>  {s1,...sn}
        generic subset of {s1, ...sn}

    List<T> [l1,...ln]
        generic permutation of [l1,...ln]
