package jext.dataframe.data;

import jext.dataframe.Data;

public class ObjectData implements Data<Object> {

    private Object[] data;
    private int n;

    public ObjectData() {
        data = new Object[0];
        n = 0;
    }

    public ObjectData(Object[] data) {
        this.data = data;
        this.n = data.length;
    }
}
