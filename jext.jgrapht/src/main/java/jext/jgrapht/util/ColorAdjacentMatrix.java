package jext.jgrapht.util;

import jext.jgrapht.alg.color.ParallelMCMCBColoring;
import jext.jgrapht.util.atomic.AtomicMax;
import jext.jgrapht.util.atomic.AtomicMean;
import jext.jgrapht.util.atomic.AtomicMin;
import jext.jgrapht.util.atomic.AtomicValue;

public class ColorAdjacentMatrix {

    private static class ColorWeight implements Comparable<ColorWeight> {
        public int color;
        public float weight;

        @Override
        public int compareTo(ColorWeight o) {
            float diff = weight - o.weight;
            if (diff < 0)
                return -1;
            if (diff > 0)
                return +1;
            return 0;
        }

        @Override
        public String toString() {
            return String.format("(%d, %.4f)", color, weight);
        }
    }

    private ColorWeight[] cw;
    private SlotFloat[][] weights;
    private int ncolors;
    private WeightMode weightMode;

    public ColorAdjacentMatrix(int ncolors, WeightMode weightMode) {

        this.weightMode = weightMode;
        this.ncolors = ncolors;

        this.weights = new SlotFloat[ncolors][];
        for (int i=0; i<ncolors; ++i) {
            this.weights[i] = new SlotFloat[i + 1];
            for (int j=0; j<=i; ++j) {
                // weights[i][j] = new AtomicFloat();
                switch(weightMode) {
                    case MIN:  this.weights[i][j] = new AtomicMin(); break;
                    case MAX:  this.weights[i][j] = new AtomicMax(); break;
                    case MEAN: this.weights[i][j] = new AtomicMean(); break;
                    default:   this.weights[i][j] = new AtomicValue(); break;
                }
            }
        }
        this.cw = new ColorWeight[ncolors];
        for (int i=0; i<ncolors; ++i)
            this.cw[i] = new ColorWeight();
    }

    public int size() { return ncolors; }

    public void reset() {
        for (int i=0; i<ncolors; ++i)
            for (int j=0; j<=i; ++j)
                weights[i][j].set(0);
    }

    public SlotFloat at(int i, int j) {
        if (i > j)
            return weights[i][j];
        else
            return weights[j][i];
    }

    public void accumulate(int i, int j, float weight) {
        at(i, j).accumulate(weight);
    }

    public float get(int i, int j) {
        return at(i, j).get();
    }

    public ColorAdjacentMatrix swap(ColorAdjacentMatrix cam) {
        ColorWeight[] cw = this.cw;
        SlotFloat[][] weights = this.weights;
        int ncolors = this.ncolors;

        this.cw = cam.cw;
        this.weights = cam.weights;
        this.ncolors = cam.ncolors;

        cam.cw = cw;
        cam.weights = weights;
        cam.ncolors = ncolors;

        return this;
    }

    public int randomColor(float r, int color, ParallelMCMCBColoring.UsableColors usableColors) {
        // compute the discrete distribution between 'usableColors'
        int numberColors = usableColors.ncolors;
        float total = 0;
        float partial = 0;
        // used to invert the propabilities
        float f = 1.f/(numberColors-1);

        boolean isZero = false;
        for (int i=0; i<numberColors; ++i) {
            float value = at(color, usableColors.get(i)).get();
            isZero = isZero || (value == 0);
            total += value;
        }

        if (isZero)
            return usableColors.get(r);

        switch (weightMode) {
            case RANDOM:
                return usableColors.get(r);
            case MAX:
                for (int i=0; i<numberColors; ++i) {
                    partial += (at(color, usableColors.get(i)).get()/total);
                    if (r <= partial)
                        return usableColors.get(i);
                }
                break;
            case MIN:
                // REMEMBER: inverting the probabilities it is necessary
                // to divide by (n-1). This is 'f'
                for (int i=0; i<numberColors; ++i) {
                    partial += f*(1 - at(color, usableColors.get(i)).get()/total);
                    if (r <= partial)
                        return usableColors.get(i);
                }
                break;
            case MEAN:
            default:
                for (int i=0; i<numberColors; ++i) {
                    partial += (at(color, usableColors.get(i)).get()/total);
                    if (r <= partial && partial < 1)
                        return usableColors.get(i);
                }
                break;
        }

        return usableColors.get(r);
    }
}
