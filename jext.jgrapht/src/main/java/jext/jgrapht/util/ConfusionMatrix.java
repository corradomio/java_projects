package jext.jgrapht.util;

public class ConfusionMatrix {

    private final int kd;
    private final int kt;
    private final int[][] m;

    /**
     *
     * @param kt ground truth (rows)
     * @param kd determined clusters (columns)
     *
     * (Data Mining - The text book, pag. 199)
     */
    public ConfusionMatrix(int kt, int kd) {
        this.kd = kd;
        this.kt = kt;
        this.m = new int[kt][];
        for(int i=0; i<kt; ++i)
            this.m[i] = new int[kd];
    }

    public ConfusionMatrix add(int i, int j) {
        m[i][j] += 1;
        return this;
    }

    public double getPurity() {
        double num = 0;
        double den = 0;

        for(int j=0; j<kd; j++) {
            num += P(j);
            den += M(j);
        }

        return den != 0 ? num/den : 0.;
    }

    public double getGiniIndex() {
        double num = 0;
        double den = 0;

        for(int j=0; j<kd; j++){
            num += G(j)* M(j);
            den += M(j);
        }

        return div(num, den);
    }

    public double getEntropy() {
        double num = 0;
        double den = 0;

        for(int j=0; j<kd; j++){
            num += E(j)* M(j);
            den += M(j);
        }

        return div(num, den);
    }

    private int N(int i) {
        int s =0;
        for(int j = 0; j<kd; ++j)
            s += m[i][j];
        return s;
    }

    private int M(int j) {
        int s = 0;
        for(int i = 0; i< kt; ++i)
            s += m[i][j];
        return s;
    }

    private int P(int j) {
        int maxi = m[0][j];
        for (int i=1; i<kt; ++i) {
            if (m[i][j] > maxi) {
                maxi = m[i][j];
            }
        }
        return maxi;
    }

    private double G(int j) {
        double gini = 1.;
        double mj = M(j);
        for (int i=0; i<kt; ++i) {
            gini -= sq(div(m[i][j], mj));
        }
        return gini;
    }

    private double E(int j) {
        double entropy = 0;
        for(int i=0; i<kt; ++i) {
            entropy -= div(m[i][j], M(j))*log2(div(m[i][j], M(j)));
        }
        return entropy;
    }


    private static double div(double x, double y) { return y != 0 ? x/y : 0; }
    private static double sq(double x) { return x*x;}
    private static double log2(double x) { return x == 0? 0. : Math.log(x)/Math.log(2); }
}
