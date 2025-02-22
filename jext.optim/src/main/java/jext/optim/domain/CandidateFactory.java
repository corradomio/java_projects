package jext.optim.domain;

import java.util.random.RandomGenerator;

public interface CandidateFactory<T> {

    int length();

    /** Create a random candidate */
    T candidate(RandomGenerator rng);
}
