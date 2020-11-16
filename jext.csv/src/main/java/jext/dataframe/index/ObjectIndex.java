package jext.dataframe.index;

import jext.dataframe.Index;

public class ObjectIndex implements Index<Object> {

    private Object[] index;
    private int n;

    public ObjectIndex() {
        index = new Object[0];
        n = 0;
    }

    public ObjectIndex(Object[] index) {
        this.index = index;
        this.n = index.length;
    }
}
