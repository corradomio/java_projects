package jext.dataframe;

import java.util.Map;

public class DataFrame<I> {
    private Index<I> index;
    private Map<String, Series<I, ?>> columns;
}
