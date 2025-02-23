package jext.optim.heuristics.bpso.operators.bitset;

import jext.optim.domain.bitset.BitSet;
import jext.optim.heuristics.bpso.Particle;
import jext.optim.heuristics.bpso.Population;
import jext.optim.heuristics.bpso.UpdatePolicy;

import java.util.random.RandomGenerator;

import static java.lang.Math.max;


public class BitSetUpdater implements UpdatePolicy<BitSet> {

    @Override
    public boolean compareWith(Particle<BitSet> particle, Particle<BitSet> reference, int bitIndex) {
        return particle.candidate().get(bitIndex) == reference.candidate().get(bitIndex);
    }

    // @Override
    // public Particle<BitSet> updateParticle(Particle<BitSet> particle, int nIndices, int[] indices) {
    //     BitSet bs = particle.candidate().clone();
    //
    //     for (int bitIndex : indices) {
    //         bs.flip(bitIndex);
    //     }
    //     return new Particle<>(bs, particle);
    // }

    @Override
    public Particle<BitSet> updateParticle(Particle<BitSet> particle, RandomGenerator rng, Population<BitSet> population) {
        BitSet bs = particle.candidate().clone();
        int nbits = bs.length();
        double[] psticky = particle.psticky();

        Particle<BitSet> pbest = population.getPopulationBest();
        Particle<BitSet> gbest = population.getGlobalBest();
        double stickiness = population.getStickiness();
        double is = population.getIs();
        double ip = population.getIp();
        double ig = population.getIg();

        for (int d=0; d<nbits; d++) {
            double cognitive = compareWith(particle, pbest, d) ? 0 : ip;
            double social    = compareWith(particle, gbest, d) ? 0 : ig;
            double pd = is*(1 - psticky[d]) + cognitive + social;

            double r = rng.nextDouble();
            if (r < pd) {
                bs.flip(d);
                psticky[d] = 1;
            }
            else {
                psticky[d] = max(0, psticky[d] - stickiness);
            }
        }

        return new Particle<>(bs, particle);
    }

}
