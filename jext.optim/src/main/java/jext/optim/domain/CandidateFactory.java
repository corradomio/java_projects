package jext.optim.domain;

import java.util.random.RandomGenerator;

public interface CandidateFactory<T> {

    /// Solution length, also problem size/dimension
    int length();

    /// Generate a random candidate
    T candidate(RandomGenerator rng);
}
