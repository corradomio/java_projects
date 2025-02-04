package org.example;

import java.util.concurrent.ThreadLocalRandom;

public class Multinomial {
    private double[] probs;

    public Multinomial(int[] iprobs) {
        double[] fprobs = new double[iprobs.length];
        for(int i=0; i<iprobs.length; ++i)
            fprobs[i] = iprobs[i];
        this.probs = fprobs;
        normalize();
    }

    public Multinomial(double[] fprobs) {
        this.probs = fprobs;
        normalize();
    }

    private void normalize() {
        double total = 0;
        int n = probs.length;
        for (int i=0; i<n; ++i)
            total += probs[i];
        for (int i=0; i<n; ++i)
            probs[i] /= total;
    }

    public int nextInt() {
        double r = ThreadLocalRandom.current().nextDouble();
        double s = 0;
        int n = probs.length;
        for (int i=0; i<n; ++i) {
            s += probs[i];
            if (r <= s)
                return i;
        }
        return n;
    }
}
