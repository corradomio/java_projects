package jext.dataframe.data;

import jext.dataframe.Data;

public class StringData implements Data<String> {

    private String[] data;
    private int n;

    public StringData() {
        data = new String[0];
        n = 0;
    }

    public StringData(String[] data) {
        this.data = data;
        this.n = data.length;
    }
}
