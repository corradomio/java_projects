package jext.optim.heuristics.pbil;

import jext.optim.domain.CandidateFactory;
import jext.optim.domain.FitnessFunction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.random.RandomGenerator;

public class Population<T> implements Iterable<Learner<T>> {

    private final int populationSize;
    private final CandidateFactory<T> candidateFactory;
    private final FitnessFunction<T> fitnessFunction;
    private boolean decreasingOrder;

    private List<Learner<T>> learners = new ArrayList<>();
    private Learner<T> fittestLearner;
    private double[][] membersProbability;

    // ----------------------------------------------------------------------

    public Population(final int populationSize,
                      final CandidateFactory<T> candidateFactory,
                      final FitnessFunction<T> fitnessFunction) {
        this.populationSize = populationSize;
        this.candidateFactory = candidateFactory;
        this.fitnessFunction = fitnessFunction;
    }

    // ----------------------------------------------------------------------

    public double[][] getMembersProbility() {
        return membersProbability;
    }

    public void setDecreasingOrder(boolean decreasingOrder) {
        this.decreasingOrder = decreasingOrder;
    }

    public Learner<T> getFittestLearner() {
        return fittestLearner;
    }

    public List<Learner<T>> getLearners() {
        return learners;
    }

    public void setLearners(List<Learner<T>> learners) {
        this.learners = learners;
    }

    // ----------------------------------------------------------------------

    public void initialize(RandomGenerator rng) {

        this.membersProbability =  ProbUtils.randomProbability(rng, candidateFactory.length(), candidateFactory.size());

        for (int i = 0; i < populationSize; i++) {
            T candidate = candidateFactory.candidate(rng);

            Learner<T> learner = new Learner<>(candidate, fitnessFunction, decreasingOrder);

            add(learner);
        }

        this.fittestLearner = learners.get(0);
    }

    public void add(Learner<T> learner) {
        learners.add(learner);
    }

    // ----------------------------------------------------------------------

    public void updateMembersProbability(RandomGenerator rng, PopulationBasedIncrementalLearning<T> solver) {
        UpdatePolicy<T> updatePolicy = solver.getUpdatePolicy();
        double learningRate = solver.getLearningRate();
        double negativeRate = solver.getNegativeRate();
        double mutationRate = solver.getMutationRate();
        double mutationShift = solver.getMutationShift();

        // find best/worst learners
        BestWorst<T> bestWorst = new BestWorst<>(learners.get(0));
        for (Learner<T> learner: learners)
            bestWorst.update(learner);

        // update best learner
        if (bestWorst.best.compareTo(fittestLearner) < 0)
            fittestLearner = bestWorst.best;

        // update membersProbability
        int length = candidateFactory.length();
        int size = candidateFactory.size();

        for (int i=0; i<length; i++) {
            int bestMember  = updatePolicy.getMember(bestWorst.best,  i);
            int worstMember = updatePolicy.getMember(bestWorst.worst, i);
            if (bestMember == worstMember)
                ProbUtils.improveMember(bestMember, membersProbability[i], learningRate);
            else
                ProbUtils.improveMember(bestMember, membersProbability[i], learningRate+negativeRate);
        }

        // mutation
        for (int i=0; i<length; i++) {
            double r = rng.nextDouble();
            if (r < mutationRate) {
                int member = rng.nextInt(size);
                ProbUtils.improveMember(member, membersProbability[member], mutationShift);
            }
        }

    }

    // ----------------------------------------------------------------------

    public Iterator<Learner<T>> iterator() {
        return learners.iterator();
    }

    public void forEach(Consumer<? super Learner<T>> action) {
        learners.forEach(action);
    }


}
