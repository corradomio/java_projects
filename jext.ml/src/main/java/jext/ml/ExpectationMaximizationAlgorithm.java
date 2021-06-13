package jext.ml;

import java.util.Random;

public class ExpectationMaximizationAlgorithm {

    private Random rnd;
    private float[][] data;
    private int nrows, ncols;

    private int mixtures;
    private float[][] means;
    private float[][] covariances;
    private float[] weights;
    private float[][] responsibilities;
    private boolean loop;

    public ExpectationMaximizationAlgorithm(float[][] data) {
        this.rnd = new Random();
        this.data = data;
        this.nrows = data.length;
        this.ncols = this.nrows > 0 ? data[0].length : 0;
    }

    public void apply(int mixtures) {
        this.mixtures = mixtures;

        this.means = Linalg.alloc(mixtures, ncols);
        this.covariances = Linalg.alloc(mixtures, mixtures);
        this.weights = Linalg.alloc(mixtures);
        this.responsibilities = Linalg.alloc(nrows, mixtures);

        this.loop = true;

        while(loop) {
            estep();
            mstep();
            check();
        }
    }

    private void estep() {

    }

    private void mstep() {

    }

    private void check() {
        loop = false;
    }

}
