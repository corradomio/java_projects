package jext.optim.domain;

public interface FitnessFunction<T> {

    /// Value of the candiate solution
    double fitness(T candidate);
}
