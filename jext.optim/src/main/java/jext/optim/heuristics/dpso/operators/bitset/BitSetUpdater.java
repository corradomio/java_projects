package jext.optim.heuristics.dpso.operators.bitset;

import jext.optim.domain.bitset.BitSet;
import jext.optim.heuristics.dpso.Particle;
import jext.optim.heuristics.dpso.Population;
import jext.optim.heuristics.dpso.UpdatePolicy;

import java.util.random.RandomGenerator;

import static java.lang.Math.max;


public class BitSetUpdater implements UpdatePolicy<BitSet> {

    @Override
    public boolean compareWith(Particle<BitSet> particle, Particle<BitSet> reference, int bitIndex) {
        return particle.candidate().get(bitIndex) == reference.candidate().get(bitIndex);
    }

    @Override
    public Particle<BitSet> updateParticle(Particle<BitSet> original, RandomGenerator rng, Population<BitSet> population) {
        BitSet candidate = original.candidate().clone();
        int nbits = candidate.length();
        double[] psticky = original.psticky();

        Particle<BitSet> pbest = population.getPopulationBest();
        Particle<BitSet> gbest = population.getGlobalBest();
        double stickiness = population.getStickiness();
        double is = population.getIs();
        double ip = population.getIp();
        double ig = population.getIg();

        for (int d=0; d<nbits; d++) {
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
