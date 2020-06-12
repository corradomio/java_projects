package jext.jgrapht.util;

import jext.jgrapht.util.atomic.AtomicMax;
import jext.jgrapht.util.atomic.AtomicMean;
import jext.jgrapht.util.atomic.AtomicMin;
import jext.jgrapht.util.atomic.AtomicValue;

public class ColorAdjacentMatrix {

    public static class ColorWeight implements Comparable<ColorWeight> {
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
            return String.format("(%d, %f)", color, weight);
        }
    }

    private ColorWeight[] cw;
    private SlotFloat[][] weights;
    private int ncolors;

    public ColorAdjacentMatrix(int ncolors, WeightType type) {

        //  []
        //  [][]
        //  [][][]
        this.ncolors = ncolors;
        weights = new SlotFloat[ncolors][];
        for (int i=0; i<ncolors; ++i) {
            weights[i] = new SlotFloat[i + 1];
            for (int j=0; j<=i; ++j) {
                // weights[i][j] = new AtomicFloat();
                switch(type) {
                    case MIN: weights[i][j] = new AtomicMin(); break;
                    case MAX: weights[i][j] = new AtomicMax(); break;
                    case MEAN: weights[i][j] = new AtomicMean(); break;
                    default: weights[i][j] = new AtomicValue(); break;
                }
            }
        }
        cw = new ColorWeight[ncolors];
        for (int i=0; i<ncolors; ++i)
            cw[i] = new ColorWeight();
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

    // public ColorWeight[] getColors(int color, int numberColors, int[] usableColors) {
    //     // int index=0;
    //     // for(int j=0; j<ncolors; ++j) {
    //     //     cw[j].color = j;
    //     //     cw[j].weight = at(i, j).get();
    //     // }
    //     //
    //     // Arrays.sort(cw, ColorWeight::compareTo);
    //     // return cw;
    // }

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

    public int randomColor(float r, int color, int numberColors, int[] usableColors) {
        float total = 0;
        for (int i=0; i<numberColors; ++i) {
            total += at(color, usableColors[i]).get();
        }
        float partial = 0;
        float f = 1.f/(numberColors-1);
        for (int i=0; i<numberColors; ++i) {
            partial += f*(1 - at(color, usableColors[i]).get()/total);
            if (r <= partial)
                return usableColors[i];
        }
        return usableColors[(int)(r*numberColors)];
    }
}
