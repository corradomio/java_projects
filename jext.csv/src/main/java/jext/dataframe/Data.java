package jext.dataframe;

public interface Data<D> {

    int size();

    D at(int iloc);
}
