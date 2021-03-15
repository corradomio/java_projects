package jext.dataframe;

public interface Index<I> extends Iterable<I> {

    int size();

    int at(I loc);

}
