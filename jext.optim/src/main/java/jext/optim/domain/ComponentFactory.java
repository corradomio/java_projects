package jext.optim.domain;

public interface ComponentFactory<T> extends CandidateFactory<T> {

    /// Number of possible values for each element of the solution
    int size();

}
