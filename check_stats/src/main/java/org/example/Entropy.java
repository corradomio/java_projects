package org.example;

public class Entropy {


    private static double sq(double x) { return x*x; }
    private static double log2(double x){ return 1.44269504089*Math.log(x); }
    private static double nlog2(double x){ return (x == 0) ? 0 : -x*log2(x); }

    private static double nentropy(double[] x) {
        int n = x.length;
        double t = 0;
        double e = 0;
        for (int i=0; i<n; ++i)
            t += x[i];

        if (t == 0) return 0;

        for (int i=0; i<n; ++i)
            e += nlog2(x[i]/t);
        e /= -log2(1./n);
        return e;
    }

    private static double ngini(double[] x) {
        int n = x.length;
        double t = 0;
        double g = 0;
        for (int i=0; i<n; ++i)
            t += x[i];

        if (t == 0) return 0;

        for (int i=0; i<n; ++i)
            g += sq(x[i]/t);
        g = (1.-g)/(1.-1./n);
        return g;
    }

    public static void main(String[] args) {
        double[] x = new double[]{1, 0, 1, 0, 1, 1};
        System.out.println(nentropy(x));
        System.out.println(ngini(x));
    }
}
