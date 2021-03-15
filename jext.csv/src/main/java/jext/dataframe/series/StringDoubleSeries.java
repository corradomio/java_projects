package jext.dataframe.series;

import jext.dataframe.Data;
import jext.dataframe.Index;
import jext.dataframe.Series;

public class StringDoubleSeries extends Series<String, Double> {

    public StringDoubleSeries(Index<String> index, Data<Double> data) {
        super(index, data);
    }
}
