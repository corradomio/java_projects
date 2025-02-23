package jext.optim.domain;

import java.util.List;
import java.util.random.RandomGenerator;

/// A neighbour is a candidate with minimum distance from the current one
public interface CandidateNeighbours<T> {

    /// A single neighbour at minimum distance from candidate
    default T neighbour(T candidate, RandomGenerator rng) {
        return neighbours(candidate, 1, 1, rng).get(0);
    }

    /// A list of neighbours at minimum distance from candidate
    /// Note: also if it is specified n, it is possible the list contain less (at minimum 1) or more neighbours
    default List<T> neighbours(T candidate, int n, RandomGenerator rng) {
        return neighbours(candidate, n, 1, rng);
    }

    /// All possible neighbours
    List<T> neighbours(T candidate);

    /// A list of n random neighbours with distance greater than 1
    /// If distance == 1, it must be equivalent than  neighbours(candidate, n, rng)
    List<T> neighbours(T candidate, int n, int distance, RandomGenerator rng);
}
