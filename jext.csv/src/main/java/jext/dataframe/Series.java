package jext.dataframe;

import jext.dataframe.data.IntegerData;
import jext.dataframe.index.IntRange;
import jext.dataframe.index.IntegerIndex;
import jext.dataframe.index.StringIndex;

import java.util.Map;

public class Series<I, D> {

    public static Series<Integer, Integer> create(int[] data) {
        Index<Integer> index = new IntRange(data.length);
        Data<Integer>  sdata = new IntegerData(data);
        return new Series<>(index, sdata);
    }

    public static Series<Integer, Integer> create(int[] data, int[] idx) {
        Index<Integer> index = new IntegerIndex(idx);
        Data<Integer>  sdata = new IntegerData(data);
        return new Series<>(index, sdata);
    }

    public static Series<String, Integer> create(int[] data, String[] idx) {
        Index<String> index = new StringIndex(idx);
        Data<Integer> sdata = new IntegerData(data);
        return new Series<>(index, sdata);
    }

    public static Series<String, Integer> create(Map<String, Integer> data) {
        StringIndex index = new StringIndex();
        IntegerData sdata = new IntegerData();

        for(Map.Entry<String, Integer> e : data.entrySet()) {
            index.add(e.getKey());
            sdata.add(e.getValue());
        }
    }



    private final Index<I> index;
    private final Data<D>   data;

    public Series(Index<I> index, Data<D> data) {
        this.index = index;
        this.data = data;
    }

    public Index<I> getIndex() { return index; }
    public Data<D>  getData()  { return data; }
}
