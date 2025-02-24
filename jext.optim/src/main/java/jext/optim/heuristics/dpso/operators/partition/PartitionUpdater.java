package jext.optim.heuristics.dpso.operators.partition;

import jext.optim.domain.partition.Partition;
import jext.optim.heuristics.dpso.Particle;
import jext.optim.heuristics.dpso.Population;
import jext.optim.heuristics.dpso.UpdatePolicy;

import java.util.random.RandomGenerator;

import static java.lang.Math.max;

public class PartitionUpdater implements UpdatePolicy<Partition> {

    @Override
    public boolean compareWith(Particle<Partition> particle, Particle<Partition> reference, int index) {
        return particle.candidate().get(index) == reference.candidate().get(index);
    }

    @Override
    public Particle<Partition> updateParticle(Particle<Partition> original, RandomGenerator rng, Population<Partition> population) {
        Partition candidate = original.candidate().clone();
        int length = candidate.length();
        double[] psticky = original.psticky();

        Particle<Partition> pbest = population.getPopulationBest();
        Particle<Partition> gbest = population.getGlobalBest();
        double stickiness = population.getStickiness();
        double is = population.getIs();
        double ip = population.getIp();
        double ig = population.getIg();

        for (int d=0; d<length; d++) {
            double cognitive = compareWith(original, pbest, d) ? 0 : ip;
            double social    = compareWith(original, gbest, d) ? 0 : ig;
            double pd = is*(1 - psticky[d]) + cognitive + social;

            double r = rng.nextDouble();
            if (r < pd) {
                candidate.flip(d);
                psticky[d] = 1;
            }
            else {
                psticky[d] = max(0, psticky[d] - stickiness);
            }
        }

        return new Particle<>(candidate, original);
    }
}
