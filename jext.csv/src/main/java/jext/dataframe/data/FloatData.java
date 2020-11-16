package jext.dataframe.data;

import jext.dataframe.Data;

public class FloatData implements Data<Float> {

    private float[] data;
    private int n;

    public FloatData() {
        data = new float[0];
        n = 0;
    }

    public FloatData(float[] data) {
        this.data = data;
        this.n = data.length;
    }
}
