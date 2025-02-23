package jext.optim.heuristics.bpso.operators.partition;

import jext.optim.domain.partition.Partition;
import jext.optim.heuristics.bpso.Particle;
import jext.optim.heuristics.bpso.Population;
import jext.optim.heuristics.bpso.UpdatePolicy;

import java.util.random.RandomGenerator;

import static java.lang.Math.max;

public class PartitionUpdate  implements UpdatePolicy<Partition> {

    @Override
    public boolean compareWith(Particle<Partition> particle, Particle<Partition> reference, int index) {
        return particle.candidate().get(index) == reference.candidate().get(index);
    }

    @Override
    public Particle<Partition> updateParticle(Particle<Partition> particle, RandomGenerator rng, Population<Partition> population) {
        Partition partition = particle.candidate().clone();
        int length = partition.length();
        double[] psticky = particle.psticky();

        Particle<Partition> pbest = population.getPopulationBest();
        Particle<Partition> gbest = population.getGlobalBest();
        double stickiness = population.getStickiness();
        double is = population.getIs();
        double ip = population.getIp();
        double ig = population.getIg();

        for (int d=0; d<length; d++) {
            double cognitive = compareWith(particle, pbest, d) ? 0 : ip;
            double social    = compareWith(particle, gbest, d) ? 0 : ig;
            double pd = is*(1 - psticky[d]) + cognitive + social;

            double r = rng.nextDouble();
            if (r < pd) {
                partition.flip(d);
                psticky[d] = 1;
            }
            else {
                psticky[d] = max(0, psticky[d] - stickiness);
            }
        }

        return new Particle<>(partition, particle);
    }
}
