package jext.dataframe;

import jext.dataframe.data.DoubleData;
import jext.dataframe.data.IntegerData;
import jext.dataframe.index.RangeIndex;
import jext.dataframe.index.SparseIndex;
import jext.dataframe.index.StringIndex;
import jext.dataframe.series.IntegerDoubleSeries;
import jext.dataframe.series.IntegerIntegerSeries;
import jext.dataframe.series.StringDoubleSeries;
import jext.dataframe.series.StringIntegerSeries;

public class Series<I, D> {

    // ----------------------------------------------------------------------

    public static IntegerIntegerSeries create(int[] data) {
        Index<Integer> index = new RangeIndex(data.length);
        Data<Integer>  sdata = new IntegerData(data);
        return new IntegerIntegerSeries(index, sdata);
    }

    public static IntegerIntegerSeries create(int[] data, int[] index) {
        if (data == null || index == null || data.length != index.length)
            throw new IllegalArgumentException();
        Index<Integer> vindex = new SparseIndex(index);
        Data<Integer>  values = new IntegerData(data);
        return new IntegerIntegerSeries(vindex, values);
    }

    public static StringIntegerSeries create(int[] data, String[] idx) {
        Index<String> index = new StringIndex(idx);
        Data<Integer> sdata = new IntegerData(data);
        return new StringIntegerSeries(index, sdata);
    }

    // ----------------------------------------------------------------------

    public static IntegerDoubleSeries create(double[] data) {
        Index<Integer> index = new RangeIndex(data.length);
        Data<Double>  sdata = new DoubleData(data);
        return new IntegerDoubleSeries(index, sdata);
    }

    public static IntegerDoubleSeries create(double[] data, int[] index) {
        if (data == null || index == null || data.length != index.length)
            throw new IllegalArgumentException();
        Index<Integer> vindex = new SparseIndex(index);
        Data<Double>  values = new DoubleData(data);
        return new IntegerDoubleSeries(vindex, values);
    }

    public static StringDoubleSeries create(double[] data, String[] idx) {
        Index<String> index = new StringIndex(idx);
        Data<Double> sdata = new DoubleData(data);
        return new StringDoubleSeries(index, sdata);
    }

    // ----------------------------------------------------------------------

    private final Index<I> index;
    private final Data<D> data;

    // ----------------------------------------------------------------------

    public Series(Index<I> index, Data<D> data) {
        this.index = index;
        this.data = data;
    }

    // ----------------------------------------------------------------------

    public Index<I> index() { return index; }
    public Data<D> values() { return data; }
    public Data<D>   data() { return data; }

    // ----------------------------------------------------------------------

    public D at(I loc) {
        int iloc = index.at(loc);
        return data.at(iloc);
    }

    // ----------------------------------------------------------------------

    @Override
    public String toString() {
        return String.format("Series(%s, index=%s)", data, index);
    }

    // ----------------------------------------------------------------------

    public void print() {
        for(I loc : index)
            System.out.printf("%s %s\n", loc, at(loc));
    }
}
