Uso di Distances invece di double[][]
------------------------------------

    Ant Colony Optimizatopn
    Bee Colony Optimization

    Non si puo' usare Dimensions, perche' servono anche le matrici
    per i feromoni.
    Forse si potrebbe fare con una struttura a grafo cheviene composta
    in base alle necessita'.
    MA DEVE essere una matrice NON DENSA.

    TSP
    VRP
    MDVRP


    https://github.com/markusmkim/GA-MDVRP


    GeneticAlgorithm
        - population
        CrossoverPolicy(Chromosome,Chromosome) -> (Chromosome,Chromosome)
        MutationPolicy(Chromosome) -> Chromosome
        SelectionPolicy(Population) -> (Chromosome,Chromosome)
        FilterPolicy(Population) -> Population
    Population
        - chromosomes
        populationSize
        elitismRate
        foreignerRate
        CandidateFactory() -> Candidate
        FitnessFunction(Candidate) -> double
    Chromosome
        candidate
        fitness

    AntColonyOptimization (== GeneticAlgorithm)
        - colony
        InitializePolicy(colony)

    AntColony (== Population)
        - ants

    Ant (== Chromosome)
