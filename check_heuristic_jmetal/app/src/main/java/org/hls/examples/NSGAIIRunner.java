package org.hls.examples;

import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAII;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.example.AlgorithmRunner;
import org.uma.jmetal.operator.crossover.CrossoverOperator;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.operator.selection.SelectionOperator;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;
import org.uma.jmetal.util.AbstractAlgorithmRunner;
import org.uma.jmetal.util.comparator.RankingAndCrowdingDistanceComparator;
import org.uma.jmetal.util.errorchecking.JMetalException;
import org.uma.jmetal.operator.mutation.impl.PolynomialMutation;
import org.uma.jmetal.operator.crossover.impl.SBXCrossover;
import org.uma.jmetal.operator.selection.impl.BinaryTournamentSelection;
import org.uma.jmetal.problem.ProblemFactory;

import java.io.IOException;
import java.util.List;

public class NSGAIIRunner extends AbstractAlgorithmRunner {
    // Import code
    public static void main(String[] args) throws JMetalException, IOException {

        String problemName = "org.uma.jmetal.problem.multiobjective.Kursawe";
        Problem<DoubleSolution> problem = ProblemFactory.<DoubleSolution>loadProblem(problemName);

        // Alternatively:
        // Problem<DoubleSolution> problem = new Kursawe()

        // --

        double crossoverProbability = 0.9;
        double crossoverDistributionIndex = 20.0;
        CrossoverOperator<DoubleSolution> crossover = new SBXCrossover(crossoverProbability,
            crossoverDistributionIndex);

        double mutationProbability = 1.0 / problem.numberOfVariables();
        double mutationDistributionIndex = 20.0;
        MutationOperator<DoubleSolution> mutation = new PolynomialMutation(mutationProbability,
            mutationDistributionIndex);

        SelectionOperator<List<DoubleSolution>, DoubleSolution> selection = new BinaryTournamentSelection<>(
            new RankingAndCrowdingDistanceComparator<>());

        // --

        int populationSize = 100;
        NSGAII<DoubleSolution> algorithm =
            new NSGAIIBuilder<>(problem, crossover, mutation, populationSize)
                .setSelectionOperator(selection)
                .setMaxEvaluations(25000)
                .build();

        // --

        AlgorithmRunner algorithmRunner = new AlgorithmRunner.Executor(algorithm).execute();
        List<DoubleSolution> population = algorithm.result();
        printFinalSolutionSet(population);

    }
}
