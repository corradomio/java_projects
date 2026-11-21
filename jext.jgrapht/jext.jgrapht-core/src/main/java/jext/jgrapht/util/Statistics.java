package jext.jgrapht.util;

import static jext.jgrapht.util.Utils.sq;

public class Statistics {

    public int count;
    public double min = +Double.MAX_VALUE;
    public double max = -Double.MAX_VALUE;
    public double mean = 0.;
    public double standardDeviation = 0.;
    public double variance = 0.;

    private double sum1 = 0.;
    private double sum2 = 0.;

    public Statistics() { }

    public Statistics add(double value) {
        count += 1;
        if (value < min) min = value;
        if (value > max) max = value;
        sum1 += value;
        sum2 += sq(value);
        return this;
    }

    public Statistics finish() {
        if (count < 1) {
            min = max = 0.;
            return this;
        }
        mean = sum1/count;
        variance = (count > 1) ? (sum2 - count*sq(sum1/count))/(count - 1) : 0.;
        if (variance < 0)
            variance = -variance;
        standardDeviation = Math.sqrt(variance);
        return this;
    }

    public void print() {
        System.out.printf("  Min: %.4f\n", min);
        System.out.printf("  Max: %.4f\n", max);
        System.out.printf("  Mean: %.4f, %.4f\n", mean, standardDeviation);
    }
}
