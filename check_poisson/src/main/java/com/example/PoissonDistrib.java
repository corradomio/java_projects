package com.example;

public class PoissonDistrib {

    private final int lambda;
    private final int min;

    public PoissonDistrib(int lambda) {
        this.lambda = lambda;
        this.min = 0;
    }

    public PoissonDistrib(int min, int lambda) {
        this.lambda = lambda;
        this.min = min;
    }

    public int nextInt() {
        double L = Math.exp(-(lambda-min));
        double p = 1.0;
        int k = 0;

        do {
            k++;
            p *= Math.random();
        } while (p > L);

        return min + k - 1;
    }
}
