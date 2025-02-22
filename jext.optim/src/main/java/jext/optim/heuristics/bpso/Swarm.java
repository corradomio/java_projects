package jext.optim.heuristics.bpso;

import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import jext.math.random.UniformRandomGenerator;
import jext.optim.domain.CandidateFactory;
import jext.optim.domain.FitnessFunction;
import jext.optim.domain.Solution;
import jext.util.concurrent.Parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

import static java.lang.Math.max;

public class Swarm<T> {
    private static final RandomGenerator rng = UniformRandomGenerator.getRandomGenerator();

    private final int swarmSize;
    private final double cognitiveFactor;
    private final double socialFactor;
    private final double stickiness;
    private final CandidateFactory<T> candidateFactory;
    private final FitnessFunction<T> fitnessFunction;

    private boolean decreasingOrder;
    private List<Particle<T>> swarm = new ArrayList<>();
    private Particle<T> pbest;
    private Particle<T> gbest;
    private final int length;
    private final double is, ip, ig;
    private UpdatePolicy<T> updatePolicy;

    public Swarm(
        int swarmSize,
        double cognitiveFactor,
        double socialFactor,
        double stickiness,
        CandidateFactory<T> candidateFactory,
        FitnessFunction<T> fitnessFunction
    ) {
        this.swarmSize = swarmSize;
        this.cognitiveFactor = cognitiveFactor;
        this.socialFactor = socialFactor;
        this.stickiness = stickiness;
        this.candidateFactory = candidateFactory;
        this.fitnessFunction = fitnessFunction;

        this.length = candidateFactory.length();

        double sum = (stickiness + cognitiveFactor + socialFactor);
        this.is = stickiness/sum;
        this.ip = cognitiveFactor/sum;
        this.ig = socialFactor/sum;
    }

    public Swarm<T> setUpdatePolicy(UpdatePolicy<T> updatePolicy) {
        this.updatePolicy = updatePolicy;
        return this;
    }

    public Swarm<T> setDecreasingOrder(boolean decreasingOrder) {
        this.decreasingOrder = decreasingOrder;
        return this;
    }

    public double getStickiness() {
        return stickiness;
    }

    public Particle<T> getGlobalBest() {
        return gbest;
    }

    public Particle<T> getPopulationBest() {
        return pbest;
    }

    public double getIs() {
        return is;
    }

    public double getIp() {
        return ip;
    }

    public double getIg() {
        return ig;
    }

    // ----------------------------------------------------------------------

    public Swarm<T> initialize() {
        // initialize stickiness
        // for (int i=0; i<length; i++)
        //     psticky[i] = rng.nextDouble();

        // initialize swarm
        for (int i=0; i<swarmSize; i++) {
            T candidate = candidateFactory.candidate(rng);
            addCandidate(candidate);
        }

        // initialize pbest, gbest
        gbest = pbest;

        return this;
    }

    void addCandidate(T candidate) {

        double[] psticky = new double[length];
        for (int i=0; i<length; i++)
            psticky[i] = rng.nextDouble();

        Particle<T> particle = new Particle<>(candidate, fitnessFunction, decreasingOrder, psticky);

        addParticle(particle);
    }

    void addParticle(Particle<T> particle) {
        swarm.add(particle);
        if (pbest == null || particle.compareTo(pbest) < 0)
            pbest = particle;
    }

    // ----------------------------------------------------------------------

    public Swarm<T> nextGeneration() {

        updateParticles(rng);
        updateBest();

        return this;
    }

    private void updateParticles(RandomGenerator rng) {
        // List<Particle<T>> generation = new ArrayList<>(swarmSize);
        //
        // for (Particle<T> particle : swarm) {
        //
        //     particle = updatePolicy.updateParticle(particle, rng, this);
        //
        //     generation.add(particle);
        // }

        List<Particle<T>> generation = Parallel.map(swarm,
            particle -> updatePolicy.updateParticle(particle, rng, this)
        );

        swarm = generation;
    }

    private void updateBest() {
        for (Particle<T> particle : swarm) {
            if (particle.compareTo(pbest) < 0)
                pbest = particle;
        }
        if (pbest.compareTo(gbest) < 0)
            gbest = pbest;
    }

    // ----------------------------------------------------------------------

}
