package jext.optim.problems;

public interface Solution<T> {

    T candidate();
    double fitness();

}
