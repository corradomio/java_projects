package jext.jgrapht.util;

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

    public int randomColor(float r, int color, int numberColors, int[] usableColors) {
        // compute the discrete distribution between 'usableColors'
        float total = 0;
        float partial = 0;
        // float f = 1.f/(numberColors-1);

        boolean isZero = false;
        for (int i=0; i<numberColors; ++i) {
            float value = at(color, usableColors[i]).get();
            isZero = isZero || (value == 0);
            total += value;
        }

        if (isZero)
            return usableColors[(int)(r*numberColors)];

        switch (weightMode) {
            case RANDOM:
                return usableColors[(int)(r*numberColors)];
            case MAX:
                for (int i=0; i<numberColors; ++i) {
                    partial += (at(color, usableColors[i]).get()/total);
                    if (r <= partial)
                        return usableColors[i];
                }
                break;
            case MIN:
                for (int i=0; i<numberColors; ++i) {
                    partial += (1 - at(color, usableColors[i]).get()/total);
                    if (r <= partial)
                        return usableColors[i];
                }
                break;
            // case GREEDY_MIN:
            //     if (r >= f) {
            //         selected = usableColors[0];
            //         cweight  = at(color, usableColors[0]).get();
            //         for (int i=0; i<numberColors; ++i) {
            //             if (at(color, usableColors[i]).get() < cweight) {
            //                 selected = usableColors[i];
            //                 cweight  = at(color, usableColors[i]).get();
            //             }
            //         }
            //         return selected;
            //     }
            //     break;
            // case GREEDY_MAX:
            //     if (r >= f) {
            //         selected = usableColors[0];
            //         cweight  = at(color, usableColors[0]).get();
            //         for (int i=0; i<numberColors; ++i) {
            //             if (at(color, usableColors[i]).get() > cweight) {
            //                 selected = usableColors[i];
            //                 cweight  = at(color, usableColors[i]).get();
            //             }
            //         }
            //         return selected;
            //     }
            //     break;
            // case GREEDY_MEAN:
            //     if (r >= f) {
            //         partial = 0;
            //         for (int i=0; i<numberColors; ++i) {
            //             partial += at(color, usableColors[i]).get();
            //             if (2*partial >= total) {
            //                 return usableColors[i];
            //             }
            //         }
            //     }
            //     break;
            default:
                for (int i=0; i<numberColors; ++i) {
                    partial += (at(color, usableColors[i]).get()/total);
                    if (r <= partial && partial < 1)
                        return usableColors[i];
                }
                break;
        }

        return usableColors[(int)(r*numberColors)];
    }
}