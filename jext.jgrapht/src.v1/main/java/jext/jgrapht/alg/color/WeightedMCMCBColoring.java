package jext.jgrapht.alg.color;

import jext.jgrapht.util.ColorAdjacentMatrix;
import jext.jgrapht.util.VertexInfo;
import jext.jgrapht.util.WeightMode;
import org.jgrapht.Graph;

import java.util.concurrent.ThreadLocalRandom;

public class WeightedMCMCBColoring<V,E> extends ParallelMCMCBoloring<V,E> {

    private ColorAdjacentMatrix cam;
    private ColorAdjacentMatrix futureCam;
    private WeightMode mode = WeightMode.MEAN;

    public WeightedMCMCBColoring(Graph<V,E> graph) {
        super(graph);
    }

    public WeightedMCMCBColoring<V, E> weightMode(WeightMode mode) {
        this.mode = mode;
        return this;
    }

    protected void initAlgorithm() {
        super.initAlgorithm();

        cam = new ColorAdjacentMatrix(super.colorRange, mode);
        futureCam = new ColorAdjacentMatrix(super.colorRange, mode);
    }

    protected long findConflicts() {
        futureCam.reset();
        return super.findConflicts();
    }

    protected void analyzeVertex(VertexInfo<V> vi, int isdominant) {
        vi.einfos.forEach(ei -> {
            futureCam.accumulate(vi.color, ei.ui.color, ei.weight);
        });
        super.analyzeVertex(vi, isdominant);
    }

    protected void updateAvailableColors() {
        cam.swap(futureCam);
        super.updateAvailableColors();
    }

    protected int randomColor(int color) {
        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        int numberColors = super.numberColors;
        int[] usableColors = super.usableColors;
        float r = tlr.nextFloat();
        return futureCam.randomColor(r, color, numberColors, usableColors);
    }

}
