package jext.optim.heuristics.bpso;

import jext.math.random.UniformRandomGenerator;
import jext.optim.domain.CandidateFactory;
import jext.optim.domain.FitnessFunction;
import jext.util.concurrent.Parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

public class Swarm<T> implements Cloneable{

    private static final RandomGenerator rng = UniformRandomGenerator.getRandomGenerator();

    private final int swarmSize;
    private final double cognitiveFactor;
    private final double socialFactor;
    private final double stickiness;
    private final CandidateFactory<T> candidateFactory;
    private final FitnessFunction<T> fitnessFunction;

    private final int length;
    private final double is, ip, ig;
    private boolean decreasingOrder;
    private List<Particle<T>> swarm = new ArrayList<>();
    private Particle<T> pbest;
    private Particle<T> gbest;

    // ----------------------------------------------------------------------

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

    public Swarm<T> clone() {
        return new Swarm<>(
            swarmSize,
            cognitiveFactor, socialFactor, stickiness,
            candidateFactory, fitnessFunction
        );
    }

    // ----------------------------------------------------------------------

    public Swarm<T> setDecreasingOrder(boolean decreasingOrder) {
        this.decreasingOrder = decreasingOrder;
        return this;
    }

    // ----------------------------------------------------------------------

    public List<Particle<T>> getSwarm() {
        return swarm;
    }

    public void setSwarm(List<Particle<T>> swarm) {
        this.swarm = swarm;
        updateBest();
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

        // initialize swarm
        for (int i=0; i<swarmSize; i++) {
            T candidate = candidateFactory.candidate(rng);

            double[] psticky = new double[length];
            for (int d=0; d<length; d++)
                psticky[d] = rng.nextDouble();

            Particle<T> particle = new Particle<>(candidate, fitnessFunction, decreasingOrder, psticky);

            addParticle(particle);
        }

        // initialize pbest, gbest
        gbest = pbest;

        return this;
    }

    void addParticle(Particle<T> particle) {
        swarm.add(particle);
        if (pbest == null || particle.compareTo(pbest) < 0)
            pbest = particle;
    }

    // ----------------------------------------------------------------------

    // public Swarm<T> nextGeneration() {
    //
    //     updateParticles(rng);
    //     updateBest();
    //
    //     return this;
    // }

    // private void updateParticles(RandomGenerator rng) {
    //
    //     swarm = Parallel.map(swarm,
    //         particle -> updatePolicy.updateParticle(particle, rng, this)
    //     );
    // }

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
