package jext.dataframe;

import java.util.Map;

public class DataFrame<I> {

    private Index<I> index;
    private Data<?> columns;
    private Map<String, Integer> columnNames;
}
