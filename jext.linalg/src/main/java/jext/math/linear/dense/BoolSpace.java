package jext.math.linear.dense;

import jext.math.linear.BaseSpace;

public class BoolSpace extends BaseSpace {

    public boolean[] data;

    // @Override
    public int length() {
        return data.length;
    }

}
