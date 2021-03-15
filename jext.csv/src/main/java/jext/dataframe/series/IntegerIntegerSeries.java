package jext.dataframe.series;

import jext.dataframe.Data;
import jext.dataframe.Index;
import jext.dataframe.Series;

public class IntegerIntegerSeries extends Series<Integer, Integer> {

    public IntegerIntegerSeries(Index<Integer> index, Data<Integer> data) {
        super(index, data);
    }
}
