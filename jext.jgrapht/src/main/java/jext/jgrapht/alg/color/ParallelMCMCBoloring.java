package jext.jgrapht.alg.color;

import jext.jgrapht.util.EdgeInfo;
import jext.jgrapht.util.VertexInfo;
import jext.logging.Logger;
import jext.util.concurrent.SharedBitSet;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * MCMCBColoring
 *      Monte Carlo Markov Chain B Coloring
 *
 * @param <V>
 * @param <E>
 */
public class ParallelMCMCBoloring<V, E> implements VertexColoringAlgorithm<V>
{
    private final Logger logger = Logger.getLogger(getClass());

    /** The colors are integers starting from 0 */
    private static final int NO_COLOR = -1;

    /** Graph to analyze */
    private Graph<V, E> graph;

    /** Probability to change an invalid color */
    private float epsilon = 0;

    /** Reduction factor */
    private double reductionFactor = 0.5;

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
    private double factorToRemove;
    /** Max graph degree */
    protected int maxDegree;

    /** Caches to speedup usable colors in recoloring */
    protected int[] usableColors;
    protected int numberColors;

    /** List of vertices that requires a recoloring for conflicts */
    private List<VertexInfo<V>> conflicts;

    /** List of conflicting colors */
    private SharedBitSet conflictColors;

    /** Potential dominant colors */
    private SharedBitSet futureDominants;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ParallelMCMCBoloring(Graph<V, E> graph) {
        this.graph = graph;
    }

    // ----------------------------------------------------------------------
    // Configuration
    // ----------------------------------------------------------------------

    public ParallelMCMCBoloring<V, E> epsilon(float e) {
        epsilon = e;
        return this;
    }

    public ParallelMCMCBoloring<V, E> reductionFactor(float reductionFactor) {
        this.reductionFactor = reductionFactor;
        return this;
    }

    public ParallelMCMCBoloring<V, E> numRetries(int numRetries) {
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

        initAlgorithm();

        // initial random coloring
        initColoring();
        updateNeighbors();

        // loop until all available colors are also dominant colors
        int iretry = 0;
        int numRemovingColors = maxDegree;
        while (availableColors.cardinality() != dominantColors.cardinality()) {
            long lconflicts = vinfos.size();    // last conflicts
            long cconflicts = findConflicts();  // current conflicts

            // loop until:
            //   1) conflicts > 0 &&
            //   2) the number of conflicts decrease
            while (cconflicts > 0 && (cconflicts < lconflicts)) {
                // update ONLY vertices with conflict
                findColoring();
                updateNeighbors();

                lconflicts = cconflicts;
                cconflicts = findConflicts();
            }

            if (cconflicts == 0) {
                // no conflicts: it was possible to remove ALL "removedColors"
                // - save the current colors (used for rollback)
                updateAvailableColors();
                // checkDominantColors();
                iretry = 0;
            }
            else {
                // conflicts: it is NOT possible to remove ALL "removedColors"
                // - rollback
                // - half the number of colors to remove (minimum 1)
                rollbackColors();
                iretry += 1;
            }

            if(cconflicts > 0 && numRemovingColors == 1 && iretry > numRetries) {
                logger.errorf("Unable to find other dominant colors after %d retries", numRetries);
                break;
            }

            // select the colors to remove
            numRemovingColors = selectRemovingColors();
            // recolor the "removingColors" with 'random' other colors
            recolorRemovingColors();
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
        Map<V, Integer> vmap = new ConcurrentHashMap<>();

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
                    viarr[idx] = vi;         // save vi in the array
                    vmap.put(v, idx);       // register the index of this vertex
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
        // REAL epsilon value to use
        eps = epsilon/ maxDegree;
        // list of dominant colors. Update concurrently
        dominantColors = new SharedBitSet(colorRange);
        futureDominants = new SharedBitSet(colorRange);
        // list of available colors
        availableColors = new BitSet(colorRange);
        availableColors.set(0, colorRange);
        // list of colors to remove (none at the begin)
        removingColors = new BitSet();

        // cache used for usable colors (recoloring & packing)
        usableColors = new int[colorRange];
        // cache used to analyze the number of color conflicts
        conflictColors = new SharedBitSet(colorRange);
        // list of conflicts
        conflicts = Collections.synchronizedList(new ArrayList<>());

        // 2) create the data structure used to speedup the operations
        vinfos.parallelStream().forEach(vi -> {
            // current vertex
            V v = vi.vertex;
            // bitset with the colors of the neighbour vertices
            vi.ncolors = new BitSet(colorRange);
            // neighbour vertex infos
            vi.ninfos = vi.neighbor
                    .stream()
                    .map(n -> viarr[vmap.get(n)])
                    .collect(Collectors.toList());
            // if the graph is weighted
            if (weighted) {
                // incident edge infos
                vi.einfos = vi.neighbor
                        .stream()
                        .map(u -> {
                            // edge (v, u)
                            E e = graph.getEdge(vi.vertex, u);
                            //V t = Graphs.getOppositeVertex(graph, e, v);

                            // edge info
                            EdgeInfo<V> ei = new EdgeInfo<>();
                            // u - vertex info
                            ei.ui = viarr[vmap.get(u)];
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

        int colorRange = this.colorRange;

        // assign a random color to all vertices
        // note: it is not necessary to set the flag 'update', because
        // ALL vertices are scanned
        vinfos.parallelStream().forEach(vi -> {
            vi.color = ThreadLocalRandom.current().nextInt(colorRange);
            // vi.ninfos.forEach(ni -> ni.changed = true);
            // vi.changed = true;
        });
    }

    private void updateNeighbors() {
        // update the LOCAL data structure with the colors of the neighborhoods
        // but ONLY on vertices with changes in the neighborhoods
        vinfos.parallelStream()
                // .filter(vi -> vi.changed)
                .forEach(vi -> {
                    vi.ncolors.clear();
                    vi.ninfos.forEach(ni -> {
                        vi.ncolors.set(ni.color);
                    });
                });

        // reset the 'changed' flags
        // vinfos.parallelStream()
        //         .filter(vi -> vi.changed)
        //         .forEach(vi -> {
        //             vi.changed = false;
        //         });
    }

    private void findColoring() {
        logger.debugft("Find coloring");

        // list of usable colors. The vector speedup the selection of random colors
        selectUsableColors();

        conflicts.forEach(vi -> {
            // assign the new color to a conflict node
            vi.color = this.selectColor(vi);
            // broadcast to the neighborhoods that this node is changed
        });
    }

    private int selectColor(VertexInfo<V> vi) {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        int ccolor = vi.color;              // current color
        int ncolor = vi.color;              // next color
        BitSet ncolors = vi.ncolors;        // neighbour colors
        int nc = ncolors.cardinality();     // n of neighbour colors
        float r = rnd.nextFloat();          // random number

        // there are not enough colors to recolor
        if (nc >= numberColors)
            return ccolor;

        if (ncolors.get(ccolor)) {
            // conflict: there is a neighbor with the same current color
            if (r < nc * eps) {
                // select an invalid color
                do {
                    ncolor = randomColor(ccolor);
                }
                while (!ncolors.get(ncolor));   // retry if not used
            } else {
                // select a valid color
                do {
                    ncolor = randomColor(ccolor);
                }
                while (ncolors.get(ncolor));    // retry if already used
            }
        } else {
            // no conflicts
            if (r < eps)
                // change color
                ncolor = randomColor(ccolor);
        }
        return ncolor;
    }

    protected int randomColor(int ccolor) {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        return usableColors[rnd.nextInt(numberColors)];
    }

    protected long findConflicts() {
        int isdominant = availableColors.cardinality() - removingColors.cardinality() - 1;
        conflicts.clear();
        conflictColors.clear();
        futureDominants.clear();

        // create the list of vertices with conflicts
        vinfos.parallelStream()
                .forEach(vi -> this.analyzeVertex(vi, isdominant));

        return conflicts.size();
    }

    protected void analyzeVertex(VertexInfo<V> vi, int isdominant) {
        if (vi.ncolors.cardinality() == isdominant) {
            this.futureDominants.set(vi.color);
        }
        if(vi.ncolors.get(vi.color)) {
            this.conflictColors.set(vi.color);
            this.conflicts.add(vi);
        }
    }

    private void rollbackColors() {
        long conflicts = this.conflicts.size();
        int ccolors = conflictColors.cardinality();
        logger.debugf("  !!! Found %d conflicts on %d colors: rollback", conflicts, ccolors);

        // restore the previous coloring
        vinfos.parallelStream().forEach(vi -> vi.color = vi.saved);

        // reduce the number of colors to remove
        factorToRemove *= reductionFactor;
    }

    // ----------------------------------------------------------------------

    private boolean selectUsableColors() {
        // select the list of usable colors as a little integer vector
        // this speedup the generation of a new "available" random color
        numberColors = availableColors.cardinality() - removingColors.cardinality();
        int index = 0;

        for (int c = 0; c < colorRange; ++ c)
            if (availableColors.get(c) && !removingColors.get(c))
                usableColors[index++] = c;
        return numberColors > 0;
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

        return colorsToRemove;
    }

    private void recolorRemovingColors() {
        // in the vertex, replace "removing" color with a random "available" color.
        // At this stage, it is not useful to analyze the neighborhoods, because
        // this step is similar to the random initialization

        // there are usable colors?
        if(!selectUsableColors())
            return;

        BitSet removingColors = this.removingColors;
        int[] usableColors = this.usableColors;
        int numberColors = this.numberColors;

        // recolor
        vinfos.parallelStream()
                .filter(vi -> removingColors.get(vi.color))
                .forEach(vi -> {
                    vi.color = usableColors[ThreadLocalRandom.current().nextInt(numberColors)];
                });
    }

    protected void updateAvailableColors() {
        // update the available colors removing the "removed" colors
        availableColors.andNot(removingColors);
        dominantColors.set(futureDominants);;

        // logging
        logger.debugf("  Removed %d colors: %d available",
                removingColors.cardinality(),
                availableColors.cardinality());

        removingColors.clear();

        // reduce the color index to the last set bit in availableColors
        colorRange = availableColors.previousSetBit(colorRange) + 1;
    }

    private void packColors() {
        // pack the colors in the range [0...#dominantColors-1]
        int maxColor = dominantColors.maxSetBit();

        int index = 0;
        for (int i=0; i <= maxColor; ++i) {
            if (!dominantColors.get(i))
                continue;
            usableColors[i] = index++;
        }

        vinfos.parallelStream()
                .forEach(vi ->  vi.color = usableColors[vi.color]);
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
