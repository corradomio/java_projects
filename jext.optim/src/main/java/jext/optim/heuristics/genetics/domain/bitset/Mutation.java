package jext.optim.heuristics.genetics.domain.bitset;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.MutationPolicy;

import java.util.random.RandomGenerator;

public class Mutation extends MutationPolicy<BitSet> {

    @Override
    public Chromosome<BitSet> mutate(Chromosome<BitSet> chromosome, RandomGenerator rng) {
        BitSet bs = chromosome.candidate();
        int n = bs.nbits();
        double prob = 1./n;

        for (int i = 0; i < n; i++) {
            if (rng.nextDouble() < prob) {
                bs.flip(i);
            }
        }

        return chromosome;
    }
}
