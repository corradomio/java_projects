package jext.optim.heuristics.pbil;

import java.util.random.RandomGenerator;

public interface UpdatePolicy<T> {

    boolean compareWith(Learner<T> particle, Learner<T> reference, int index);

    Learner<T> updateMembers(Learner<T> learner, RandomGenerator rng, double[][] membersProb);

    int getMember(Learner<T> learner, int index);
}
