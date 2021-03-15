package jext.dataframe.series;

import jext.dataframe.Data;
import jext.dataframe.Index;
import jext.dataframe.Series;

public class StringIntegerSeries extends Series<String, Integer> {

    public StringIntegerSeries(Index<String> index, Data<Integer> data) {
        super(index, data);
    }
}
