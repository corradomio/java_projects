package jext.dataframe.data;

import jext.dataframe.Data;

public class DoubleData implements Data<Double> {

    private double[] data;
    private int n;

    public DoubleData() {
        data = new double[0];
        n = 0;
    }

    public DoubleData(double[] data) {
        this.data = data;
        this.n = data.length;
    }
}
