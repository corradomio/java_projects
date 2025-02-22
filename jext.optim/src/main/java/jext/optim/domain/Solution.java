package jext.optim.domain;

public interface Solution<T> {

    T candidate();

    double fitness();

}
