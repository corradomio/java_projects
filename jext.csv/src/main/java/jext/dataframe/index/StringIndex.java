package jext.dataframe.index;

import jext.dataframe.Index;

public class StringIndex implements Index<String> {

    private String[] index;
    private int n;

    public StringIndex() {
        index = new String[0];
        n = 0;
    }

    public StringIndex(String[] index) {
        this.index = index;
        this.n = index.length;
    }
}
