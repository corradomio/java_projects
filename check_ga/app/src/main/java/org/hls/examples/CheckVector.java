package org.hls.examples;

import jext.optim.heuristics.genetics.FitnessFunction;
import jext.optim.heuristics.genetics.GeneticAlgorithm;
import jext.optim.heuristics.genetics.Population;
import jext.optim.heuristics.genetics.domain.permutation.InversionMutation;
import jext.optim.heuristics.genetics.domain.permutation.OrderCrossover;
import jext.optim.heuristics.genetics.domain.permutation.Permutation;
import jext.optim.heuristics.genetics.domain.reals.LineCrossover;
import jext.optim.heuristics.genetics.domain.reals.NormalMutation;
import jext.optim.heuristics.genetics.domain.reals.Range;
import jext.optim.heuristics.genetics.domain.reals.Vector;
import jext.optim.heuristics.genetics.domain.reals.VectorFactory;
import jext.optim.heuristics.genetics.filter.DropDuplicates;
import jext.optim.heuristics.genetics.selection.TournamentSelection;
import jext.optim.heuristics.genetics.stopping.FixedElapsedTime;
import jext.optim.heuristics.genetics.stopping.FixedGenerationCount;
import jext.optim.heuristics.genetics.stopping.MultipleConditions;
import jext.optim.heuristics.genetics.stopping.NeverStop;
import jext.optim.heuristics.genetics.stopping.Patience;
import jext.optim.heuristics.genetics.util.TPrint;

import static java.lang.Math.PI;
import static java.lang.Math.sin;

public class CheckVector {

    static int TOURNAMENT_ARITY = 2;
    static int NUM_GENERATIONS = 1000;
    static int PATIENCE = 100;
    static int PROBLEM_SIZE = 2;
    static int POPULATION_SIZE = 30;

    public static void main(String[] args) {

        VectorFactory cf = new VectorFactory(PROBLEM_SIZE, new Range(-PI, PI));
        FitnessFunction<Vector> ff = new FitnessFunction<Vector>() {
            @Override
            public double fitness(Vector vector) {
                double x = vector.data()[0];
                double y = vector.data()[1];
                return sin(x*y);
            }
        };

        Population<Vector> pop = new Population<>(
            POPULATION_SIZE,0, 0,
            cf, ff
        );

        GeneticAlgorithm<Vector> ga = new GeneticAlgorithm<Vector>(
            new LineCrossover(0.1), .1,
            new NormalMutation(0.1, 0.01), .1,
            new TournamentSelection<>(TOURNAMENT_ARITY),
            new DropDuplicates<>()
        ) {
            @Override
            protected void onGeneration(int g, Population<Vector> population) {
                TPrint.printf("[%7d]: %.6f\n", g, population.getFittestChromosome().fitness());
            }
        };

        // ------------------------------------------------------------------

        System.out.println("-- Maximize --");

        Population<Vector> bestPop = ga.evolve(true,
            pop,
            new MultipleConditions(new NeverStop()
                , new FixedGenerationCount(NUM_GENERATIONS)
                , new Patience(PATIENCE)
                , new FixedElapsedTime(30)
            )
        );

        System.out.println(bestPop.getFittestChromosome());
        System.out.println("--");
        bestPop.forEach(System.out::println);

        // ------------------------------------------------------------------

        System.out.println("-- Minimize --");

        Population<Vector> worstPop = ga.evolve(false,
            pop,
            new MultipleConditions(new NeverStop()
                , new FixedGenerationCount(NUM_GENERATIONS)
                , new Patience(PATIENCE)
                , new FixedElapsedTime(30)
            )
        );

        System.out.println(worstPop.getFittestChromosome());
        System.out.println("--");
        worstPop.forEach(System.out::println);

    }
}
