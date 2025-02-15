package jext.optim;

public interface Solution<T> {

    T candidate();
    float fitness();

}
