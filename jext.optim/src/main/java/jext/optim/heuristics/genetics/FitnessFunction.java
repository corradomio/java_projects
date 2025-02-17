package jext.optim.heuristics.genetics;

public interface FitnessFunction<T> {

    double fitness(T t);
}
