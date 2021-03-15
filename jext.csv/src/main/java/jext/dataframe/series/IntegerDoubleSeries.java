package jext.dataframe.series;

import jext.dataframe.Data;
import jext.dataframe.Index;
import jext.dataframe.Series;

public class IntegerDoubleSeries  extends Series<Integer, Double> {

    public IntegerDoubleSeries(Index<Integer> index, Data<Double> data) {
        super(index, data);
    }
}
