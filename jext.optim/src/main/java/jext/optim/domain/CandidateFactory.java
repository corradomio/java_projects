package jext.optim.domain;

import java.util.random.RandomGenerator;

public interface CandidateFactory<T> {

    /// Number of possible values for each element of the solution
    int size();

    /// Solution length, also problem size/dimension
    int length();

    /// Generate a random candidate
    T candidate(RandomGenerator rng);
}
