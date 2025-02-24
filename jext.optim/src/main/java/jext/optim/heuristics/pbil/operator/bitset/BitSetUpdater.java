package jext.optim.heuristics.pbil.operator.bitset;

import jext.optim.domain.bitset.BitSet;
import jext.optim.heuristics.pbil.Learner;
import jext.optim.heuristics.pbil.ProbUtils;
import jext.optim.heuristics.pbil.UpdatePolicy;

import java.util.random.RandomGenerator;

public class BitSetUpdater implements UpdatePolicy<BitSet> {

    @Override
    public boolean compareWith(Learner<BitSet> particle, Learner<BitSet> reference, int bitIndex) {
        return particle.candidate().get(bitIndex) == reference.candidate().get(bitIndex);
    }

    @Override
    public Learner<BitSet> updateMembers(Learner<BitSet> original, RandomGenerator rng, double[/*length*/][/*size*/] membersProb) {
        BitSet candidate = original.candidate().clone();
        int length = candidate.length();

        for (int i = 0; i < length; i++) {
            boolean value = ProbUtils.nextMember(rng, membersProb[i]) != 0;

            candidate.set(i, value);
        }

        return new Learner<>(candidate, original);
    }

    @Override

    public int getMember(Learner<BitSet> learner, int bitIndex) {
        return learner.candidate().get(bitIndex) ? 1 : 0;
    }
}
