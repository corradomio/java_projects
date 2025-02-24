package jext.optim.heuristics.pbil.operator.partition;

import jext.optim.domain.bitset.BitSet;
import jext.optim.domain.partition.Partition;
import jext.optim.heuristics.pbil.Learner;
import jext.optim.heuristics.pbil.ProbUtils;
import jext.optim.heuristics.pbil.UpdatePolicy;

import java.util.random.RandomGenerator;

public class PartitionUpdater implements UpdatePolicy<Partition> {

    @Override
    public boolean compareWith(Learner<Partition> learner, Learner<Partition> reference, int index) {
        return learner.candidate().get(index) == reference.candidate().get(index);
    }

    @Override
    public Learner<Partition> updateMembers(Learner<Partition> original, RandomGenerator rng, double[][] membersProb) {
        Partition candidate = original.candidate().clone();
        int length = candidate.length();

        for (int i = 0; i < length; i++) {
            int part = ProbUtils.nextMember(rng, membersProb[i]);

            candidate.set(i, part);
        }

        return new Learner<>(candidate, original);
    }

    @Override
    public int getMember(Learner<Partition> learner, int index) {
        return learner.candidate().get(index);
    }
}
