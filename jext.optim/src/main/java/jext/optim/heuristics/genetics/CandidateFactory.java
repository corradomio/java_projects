package jext.optim.heuristics.genetics;

import java.util.random.RandomGenerator;

public interface CandidateFactory<T> {

    /** Create a random candidate */
    T candidate(RandomGenerator rng);
}
