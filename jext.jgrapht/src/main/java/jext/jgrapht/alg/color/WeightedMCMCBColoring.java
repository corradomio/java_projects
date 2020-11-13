package jext.jgrapht.alg.color;

import jext.jgrapht.util.ColorAdjacentMatrix;
import jext.jgrapht.util.VertexInfo;
import jext.jgrapht.util.WeightMode;
import jext.util.concurrent.ThreadLocalRandom;
import org.jgrapht.Graph;

public class WeightedMCMCBColoring<V,E> extends ParallelMCMCBColoring<V,E> {

    private ColorAdjacentMatrix cam;
    private ColorAdjacentMatrix futureCam;
    private WeightMode mode = WeightMode.RANDOM;

    public WeightedMCMCBColoring(Graph<V,E> graph) {
        super(graph);
    }

    public WeightedMCMCBColoring<V, E> withWeightMode(WeightMode mode) {
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

    protected int nextColor(int color) {
        float r = ThreadLocalRandom.current().nextFloat();
        return futureCam.randomColor(r, color, usableColors);
    }

}
