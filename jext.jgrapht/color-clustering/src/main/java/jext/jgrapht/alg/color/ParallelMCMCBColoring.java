package jext.jgrapht.alg.color;

import jext.jgrapht.Graphs;
import jext.jgrapht.util.EdgeInfo;
import jext.jgrapht.util.VertexInfo;
import jext.logging.Logger;
import jext.util.concurrent.SharedBitSet;
import jext.util.concurrent.ThreadLocalRandom;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * MCMCBColoring
 *      Monte Carlo Markov Chain B Coloring
 *
 * @param <V>
 * @param <E>
 */
public class ParallelMCMCBColoring<V, E> implements VertexColoringAlgorithm<V>
{
    public static class UsableColors {
        public int[] colors;
        public int ncolors;

        UsableColors(int maxColors) {
            colors = new int[maxColors+1];
        }

        int nextColor() {
            Random rnd = ThreadLocalRandom.current();
            return colors[rnd.nextInt(ncolors)];
        }

        void clear() {
            ncolors = 0;
        }

        void add(int c) {
            colors[ncolors++] = c;
            colors[ncolors] = -1;
        }

        void set(int i, int c) {
            colors[i] = c;
        }

        public int get(int i) {
            return colors[i];
        }

        public int get(double r) {
            return colors[(int)(r*(ncolors))];
        }

        public BitSet toBitSet() {
            BitSet bs = new BitSet(colors.length);
            for (int i=0; i<ncolors; ++i)
                bs.set(colors[i]);
            return bs;
        }
    }

    private final Logger logger = Logger.getLogger(getClass());

    /** The colors are integers starting from 0 */
    private static final int NO_COLOR = -1;

    /** Graph to analyze */
    private Graph<V, E> graph;

    /** Probability to change an invalid color */
    private float epsilon = 0.f;

    /** Reduction factor */
    private float reductionFactor = 0.5f;

    /** N of retries with conflicts */
    private int numRetries = 3;

    // --

    /** Data structure for fast graph access */
    private List<VertexInfo<V>> vinfos;

    /** Real epsilon: eps = epsilon/maxDegree */
    private float eps;

    /** Number of colors = maxDegree+1 */
    protected int colorRange;

    /** Available colors. It contains the dominant colors */
    private BitSet availableColors;

    /** Dominant colors */
    private SharedBitSet dominantColors;

    /** Colors to remove in a single step */
    private BitSet removingColors;

    /** % of non-dominant colors to remove in a single step (minimum 1 color)  */
    private float factorToRemove;

    /** minimum value of factorToRemove (=1/num_vertices) */
    private float minFactor;

    /** Max graph degree */
    protected int maxDegree;

    /** Caches to speedup usable colors in recoloring */
    protected UsableColors usableColors;

    /** List of vertices that requires a recoloring for conflicts */
    private List<VertexInfo<V>> conflictVertices;

    /** List of conflicting colors */
    private SharedBitSet conflictColors;

    /** Potential dominant colors */
    // private SharedBitSet futureDominants;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ParallelMCMCBColoring(Graph<V, E> graph) {
        this.graph = graph;
    }

    // ----------------------------------------------------------------------
    // Configuration
    // ----------------------------------------------------------------------

    public ParallelMCMCBColoring<V, E> withEpsilon(double e) {
        epsilon = (float) e;
        return this;
    }

    public ParallelMCMCBColoring<V, E> withReductionFactor(double reductionFactor) {
        this.reductionFactor = (float) reductionFactor;
        return this;
    }

    public ParallelMCMCBColoring<V, E> withNumRetries(int numRetries) {
        this.numRetries = numRetries;
        return this;
    }

    public Collection<VertexInfo<V>> getVInfos() {
        return vinfos;
    }

    // ----------------------------------------------------------------------
    // Coloring
    // ----------------------------------------------------------------------

    @Override
    public Coloring<V> getColoring() {
        int iretry = 0;
        // loop until all available colors are also dominant colors
        int numRemovingColors = maxDegree;

        initAlgorithm();
        selectUsableColors();
        initColoring();
        updateNColors();

        while (availableColors.cardinality() != dominantColors.cardinality())
        {
            long lconflicts = vinfos.size();    // last conflicts
            long cconflicts = findConflicts();  // current conflicts

            // loop until:
            //   1) conflicts > 0 &&
            //   2) the number of conflicts decrease &&
            //   3) retry < n retries
            int cretry = 0;
            while (cconflicts > 0 /*&& (cconflicts < lconflicts)*/ && cretry < numRetries) {
                // update ONLY vertices with conflict
                findColoring();
                updateNColors();
                findConflicts();

                lconflicts = cconflicts;
                cconflicts = conflictVertices.size();

                if (cconflicts < lconflicts)
                    cretry = 0;
                else
                    cretry += 1;
            }

            if (cconflicts == 0) {
                // no conflicts: it is possible to remove ALL "removedColors"
                // - save the current colors (used for rollback)
                saveColors();
                // update the available colors
                updateAvailableColors();
                updateDominantColors();
                iretry = 0;
            }
            else {
                // conflicts: it is NOT possible to remove ALL "removedColors"
                // - rollback
                // - half the number of colors to remove (minimum 1)
                rollbackColors();
                iretry += 1;
            }

            if(cconflicts > 0 && numRemovingColors <= 1 && iretry > numRetries) {
                logger.errorf("Unable to find other dominant colors after %d retries: %d conflicts", numRetries, cconflicts);
                break;
            }

            // select the colors to remove
            selectRemovingColors();
            // select the colors to use
            selectUsableColors();

            if (!removingColors.isEmpty())
                // recolor the "removingColors" with 'random' other colors
                recolorRemovingColors();
            else
                // no colors to remove
                break;
        }

        // pack the colors in range [0, #dominantColors-1]
        packColors();

        if (availableColors.cardinality() != dominantColors.cardinality())
            logger.infof("Found %d colors (%d dominants)", availableColors.cardinality(), dominantColors.cardinality());
        else
            logger.infof("Found %d dominant colors", dominantColors.cardinality());

        return new VertexColoringAlgorithm.ColoringImpl<>(getColorMap(), countColors());
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    protected void initAlgorithm() {
        logger.debugf("Initialization");

        boolean weighted = graph.getType().isWeighted();
        AtomicInteger index = new AtomicInteger(-1);

        VertexInfo<V>[] viarr = new VertexInfo[graph.vertexSet().size()];
        Map<V, Integer> vidx = new ConcurrentHashMap<>();

        // 1) create the data structure used to speedup the operations
        graph.vertexSet()
                .parallelStream()
                .forEach(v -> {
                    VertexInfo<V> vi = new VertexInfo<>();
                    int idx = index.incrementAndGet();
                    vi.vertex = v;
                    vi.index = idx;
                    vi.neighbor = Graphs.neighborSetOf(graph, v);
                    vi.degree = vi.neighbor.size();
                    vi.color = NO_COLOR;
                    viarr[idx] = vi;            // save vi in the array
                    vidx.put(v, idx);           // register the index of this vertex
                });

        // fast access to the list of VertexInfo's
        vinfos = Arrays.asList(viarr);

        // maximum degree
        maxDegree = vinfos.parallelStream()
                .reduce(new VertexInfo<>(), (vi, ti) -> vi.degree >= ti.degree ? vi : ti)
                .degree;

        // colors to use: maxDegree + 1
        colorRange = maxDegree + 1;
        // TRICK if the degree it too small
        if (maxDegree < 8) colorRange = 8;
        // start removing the HALF of the available colors
        factorToRemove = reductionFactor;
        // min factor to use, equals to 1/nVertices
        minFactor = 1.f/graph.vertexSet().size();
        // REAL epsilon value to use
        eps = epsilon / maxDegree;
        // list of dominant colors. Update concurrently
        dominantColors  = new SharedBitSet(colorRange);
        // futureDominants = new SharedBitSet(colorRange);
        // list of available colors
        availableColors = new BitSet(colorRange);
        availableColors.set(0, colorRange);
        // list of colors to remove (none at the begin)
        removingColors = new BitSet();

        // cache used for usable colors (recoloring & packing)
        usableColors = new UsableColors(colorRange);
        // cache used to analyze the number of color conflicts
        conflictColors = new SharedBitSet(colorRange);
        // list of vertices with conflicts
        conflictVertices = Collections.synchronizedList(new ArrayList<>());

        // 2) create the data structure used to speedup the operations
        vinfos.parallelStream().forEach(vi -> {
            // current vertex
            V v = vi.vertex;
            // bitset with the colors of the neighbour vertices
            vi.ncolors = new BitSet(colorRange);
            // neighbour vertex infos
            vi.ninfos = vi.neighbor.stream()
                    .map(vj -> viarr[vidx.get(vj)])
                    .collect(Collectors.toList());
            // if the graph is weighted
            if (weighted) {
                // incident edge infos
                vi.einfos = vi.neighbor.stream()
                        .map(u -> {
                            // edge (v, u)
                            E e = graph.getEdge(v, u);

                            // edge info
                            EdgeInfo<V> ei = new EdgeInfo<>();
                            // u - vertex info
                            ei.ui = viarr[vidx.get(u)];
                            // edge weight
                            ei.weight = (float)graph.getEdgeWeight(e);
                            return ei;
                        })
                        .collect(Collectors.toList());
            }
        });

        // discharge the graph
        // to keep a link to the graph is not necessary
        // graph = null;
    }

    private void initColoring() {
        logger.debugf("Init coloring");

        // assign a random color to all vertices
        vinfos.parallelStream().forEach(vi -> {
            vi.color = usableColors.nextColor();
            vi.saved = vi.color;
        });
    }

    private void updateNColors() {
        // update the LOCAL data structure with the colors of the neighbors
        vinfos.parallelStream()
                .forEach(VertexInfo::updateNColors);
    }

    private void findColoring() {
        logger.debugft("Find coloring");

        // list of usable colors. The vector speedup the selection of random colors
        // selectUsableColors();

        if (conflictVertices.size() < 16) {
            findHackColoring();
            findConflicts();
            return;
        }

        conflictVertices.parallelStream().forEach(vi -> {
            // assign the new color to a conflict node
            vi.color = this.selectColor(vi);
        });
    }

    private void findHackColoring() {
        for (VertexInfo<V> vi : conflictVertices) {
            vi.color = this.selectColor(vi);
            vi.ninfos.forEach(VertexInfo::updateNColors);
        }
    }

    private int selectColor(VertexInfo<V> vi) {
        int currc = vi.color;                           // current color
        int nextc = vi.color;                           // next color
        BitSet ncolors = vi.ncolors;                    // neighbour colors
        int nc = ncolors.cardinality();                 // n of neighbour colors

        // there are not enough colors to recolor
        if (nc >= usableColors.ncolors)
            return currc;

        do {
            nextc = nextColor(currc);
        }
        while (ncolors.get(nextc));    // retry if already used

        // Random rnd = ThreadLocalRandom.current();
        // float r = rnd.nextFloat();                   // random number
        // if (ncolors.get(currc)) {
        //     // conflict: there is a neighbor with the same current color
        //     if (r < /*nc * eps*/ epsilon) {
        //         // select an invalid color
        //         do {
        //             nextc = nextColor(currc);
        //         }
        //         while (!ncolors.get(nextc));   // retry if not used
        //     } else {
        //         // select a valid color
        //         do {
        //             nextc = nextColor(currc);
        //         }
        //         while (ncolors.get(nextc));    // retry if already used
        //     }
        // } else {
        //     // no conflicts
        //     if (r < eps)
        //         nextc = nextColor(currc);
        // }

        return nextc;
    }

    protected int nextColor(int color) {
        return usableColors.nextColor();
    }

    protected long findConflicts() {
        // isdominant: n of neighbor colors necessary to have a dominant color
        //        n available colors
        //  MINUS n of colors to remove
        //  MINUS the color of the current vertex
        int isdominant = availableColors.cardinality() - removingColors.cardinality() - 1;

        // list of vertices with conflicts
        conflictColors.clear();
        // list of colors generating conflics
        conflictVertices.clear();
        // possible dominant colors
        // futureDominants.clear();

        // create the list of vertices with conflicts
        vinfos
            .parallelStream()
            .forEach(vi -> this.analyzeVertex(vi, isdominant));

        return conflictVertices.size();
    }

    protected void analyzeVertex(VertexInfo<V> vi, int isdominant) {
        // check if the current color can be dominant
        // if (vi.ncolors.cardinality() == isdominant) {
        //     this.futureDominants.set(vi.color);
        // }
        // check if some neighbor has the same color
        if(vi.ncolors.get(vi.color)) {
            this.conflictColors.set(vi.color);
            this.conflictVertices.add(vi);
        }
    }

    private void saveColors() {
        vinfos.parallelStream().forEach(vi -> vi.saved = vi.color);
    }

    private void rollbackColors() {
        long conflicts = this.conflictVertices.size();
        int ccolors = conflictColors.cardinality();
        logger.debugf("  !!! Found %d conflicts on %d colors: rollback", conflicts, ccolors);

        // restore the previous coloring
        vinfos.parallelStream().forEach(vi -> vi.color = vi.saved);

        // reduce the number of colors to remove
        if (factorToRemove > minFactor)
            factorToRemove *= reductionFactor;
    }

    // ----------------------------------------------------------------------

    private int selectUsableColors() {
        // select the list of usable colors as a little integer vector
        // this speedup the generation of a new "available" random color

        // list of usable colors for recoloring are:
        //
        //       list of available colors
        // MINUS list of removing colors
        // MINUS list of dominant colors   NO!!!
        //      there is no reason to do not reuse a dominant color to recolor
        //      another node

        usableColors.clear();

        for (int c = 0; c < colorRange; ++ c)
            if (availableColors.get(c) && !removingColors.get(c))
                usableColors.add(c);

        return usableColors.ncolors;
    }

    private int selectRemovingColors() {
        // select the colors to remove between the "available" colors with highest indices
        removingColors.clear();

        // number of colors to remove. At minimum 1
        int colorsToRemove = Math.max(
                (int)(factorToRemove*(availableColors.cardinality() - dominantColors.cardinality())),
                1);

        for (int c = colorRange-1; c >=0 && removingColors.cardinality() != colorsToRemove; --c) {
            if (availableColors.get(c) && !dominantColors.get(c))
                removingColors.set(c);
        }

        if (!removingColors.isEmpty())
            logger.debugf("Try to remove %d colors", removingColors.cardinality());

        return removingColors.cardinality();
    }

    private void recolorRemovingColors() {
        // in the vertex, replace "removing" color with a random "available" color.
        // At this stage, it is not useful to analyze the neighborhoods, because
        // this step is similar to the random initialization

        // recolor
        vinfos.parallelStream()
                .filter(vi -> removingColors.get(vi.color))
                .forEach(vi -> {
                    vi.color = usableColors.nextColor();
                });
    }

    protected void updateAvailableColors() {
        // update the available colors removing the "removed" colors
        availableColors.andNot(removingColors);
        // dominantColors.set(futureDominants);

        // logging
        logger.debugf("  Removed %d colors: %d available",
                removingColors.cardinality(),
                availableColors.cardinality());

        removingColors.clear();

        // reduce the color index to the last set bit in availableColors
        colorRange = availableColors.previousSetBit(colorRange) + 1;
    }

    protected void updateDominantColors() {
        // a color is dominant if the n of neighborhoods colors
        // is n available colors - 1 (the color of the node)
        int ncolors = availableColors.cardinality()-1;
        dominantColors.clear();

        vinfos.parallelStream()
                .forEach(vi -> {
                    if (vi.ncolors.cardinality() == ncolors)
                        dominantColors.set(vi.color);
                });
    }

    private void packColors() {
        // pack the colors in the range [0...#dominantColors-1]
        int maxColor = dominantColors.maxSetBit();

        int index = 0;
        for (int i=0; i <= maxColor; ++i) {
            if (!dominantColors.get(i))
                continue;
            usableColors.set(i, index++);
        }

        vinfos.parallelStream()
                .forEach(vi ->  vi.color = usableColors.get(vi.color));
    }

    // ----------------------------------------------------------------------

    private Map<V, Integer> getColorMap() {
        Map<V, Integer> coloring = new HashMap<>();

        // release the memory during the creation of the colormap
        vinfos.forEach(vi -> {
            vi.ncolors = null;
            vi.neighbor = null;
            vi.ninfos = null;
            coloring.put(vi.vertex, vi.color);
        });

        return coloring;
    }

    private int countColors() {
        return availableColors.cardinality();
    }

}
