package jext.optim.domain;

public interface Solution<T> {

    /// Candidate used as solution
    T candidate();

    /// Fitness value of the candiate
    double fitness();

}
