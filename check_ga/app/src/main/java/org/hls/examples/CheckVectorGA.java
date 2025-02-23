package org.hls.examples;

import jext.optim.domain.FitnessFunction;
import jext.optim.heuristics.ga.GeneticAlgorithm;
import jext.optim.heuristics.ga.Population;
import jext.optim.domain.reals.Range;
import jext.optim.domain.reals.Vector;
import jext.optim.domain.reals.VectorFactory;
import jext.optim.heuristics.ga.filter.DropDuplicates;
import jext.optim.heuristics.ga.operator.reals.LineCrossover;
import jext.optim.heuristics.ga.operator.reals.NormalMutation;
import jext.optim.heuristics.ga.selection.TournamentSelection;
import jext.optim.heuristics.ga.stopping.FixedElapsedTime;
import jext.optim.heuristics.ga.stopping.FixedGenerationCount;
import jext.optim.heuristics.ga.stopping.Conditions;
import jext.optim.heuristics.ga.stopping.NeverStop;
import jext.optim.heuristics.ga.stopping.Patience;

import static java.lang.Math.PI;
import static java.lang.Math.sin;

public class CheckVectorGA {

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

        GeneticAlgorithm<Vector> solver = new GeneticAlgorithm<Vector>(
            new LineCrossover(0.1), .1,
            new NormalMutation(0.1, 0.01), .1,
            new TournamentSelection<>(TOURNAMENT_ARITY),
            new DropDuplicates<>()
        );

        // ------------------------------------------------------------------

        System.out.println("-- Maximize --");

        Population<Vector> bestPop = solver.evolve(true,
            pop,
            new Conditions(new NeverStop()
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

        Population<Vector> worstPop = solver.evolve(false,
            pop,
            new Conditions(new NeverStop()
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
