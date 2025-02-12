package jext.optim;

public interface Solution<T> {

    T candidate();
    double fitness();

}
