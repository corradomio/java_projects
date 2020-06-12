package jext.jgrapht.alg.color;

/*
    A Graph b-coloring Framework for Data Clustering
    https://link.springer.com/article/10.1007/s10852-008-9093-x
 */

import jext.jgrapht.Graphs;
import jext.jgrapht.util.VertexInfo;
import jext.logger.Logger;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;


public class BColoring<V, E> implements VertexColoringAlgorithm<V> {

    private static Logger logger = Logger.getLogger(BColoring.class);

    /** The colors are integers starting from 0 */
    private static final int NO_COLOR = -1;

    /** Graph to analyze */
    private Graph<V, E> graph;

    /** Compare two vertices */
    private Comparator<V> comparator;

    /** Map used to keep local vertices informations */
    private Map<V, VertexInfo<V>> vinfoMap = new HashMap<>();
    private Collection<VertexInfo<V>> vinfos;

    /** Connected component of the graph */
    private List<VertexInfo<V>> component;

    /** Vertex with the maximumDegree. Used only to start the algoritm */
    private V maxDegreeVertex;

    /** Maximum vertex degree */
    private int maxDegree;

    /** Maximun number of colors: maxDegree + 1 */
    private int numberColors;

    /** All available colors. At start, colors from 0 to maxDegree */
    private BitSet availableColors;

    /** Dominant colors */
    private BitSet dominantColors;

    private Set<V> toVisit;

    /** Random generator */
    private Random rnd = new Random();


    public BColoring(Graph<V, E> graph, Comparator<V> comparator) {
        this.graph = graph;
        this.comparator = comparator;
    }

    public BColoring<V, E> seed(long seed) {
        rnd.setSeed(seed);
        return this;
    }

    @Override
    public Coloring<V> getColoring() {

        initAlgorithm();

        while(hasMissingColoredVertices()) {
            initColoring();
            findColoring();
        }

        return new ColoringImpl<>(getColors(), dominantColors.cardinality());
    }

    private void initAlgorithm() {
        logger.infof("Initialization");

        // note: it is not necessary to initialize maxDegree, numberColors, ...
        // because it is necessary to check IF the graph has multiple components
        // This is done in 'hasMissingColoredVertices()'

        vinfoMap = graph.vertexSet().parallelStream()
            .map(v -> {
                VertexInfo<V> vi = new VertexInfo<>();
                vi.vertex = v;
                vi.neighbor = Graphs.neighborSetOf(graph, v);
                vi.degree = vi.neighbor.size();
                vi.color = NO_COLOR;
                return vi;
            })
            .collect(Collectors.toMap(vi -> vi.vertex, vi -> vi));

        vinfos = new ArrayList<>(vinfoMap.values());

        vinfos.forEach(vi -> {
            vi.ncolors = new BitSet(numberColors);
            vi.ninfos = vi.neighbor
                    .stream()
                    .map(t -> vinfoMap.get(t))
                    .collect(Collectors.toList());
        });

        toVisit = new HashSet<>(graph.vertexSet());
    }

    // ------------------------------------------------------------------------------

    private boolean hasMissingColoredVertices() {
        maxDegreeVertex = null;
        maxDegree = -1;

        if (toVisit.size() == 0)
            return false;

        // VertexInfo<V> mi = vinfos.parallelStream()
        //         .filter(vi -> vi.color == NO_COLOR)
        //         .reduce(new VertexInfo<>(), (vi, ti) -> vi.degree >= ti.degree ? vi : ti)
        //         ;

        VertexInfo<V> mi = toVisit.parallelStream()
                .map(v -> vinfoMap.get(v))
                .filter(vi -> vi.color == NO_COLOR)
                .reduce(new VertexInfo<>(), (vi, ti) -> vi.degree >= ti.degree ? vi : ti)
                ;

        if (mi.vertex == null)
            return false;

        maxDegreeVertex = mi.vertex;
        maxDegree = mi.degree;

        numberColors = maxDegree + 1;

        availableColors = new BitSet(numberColors);
        availableColors.set(0, numberColors);
        dominantColors = new BitSet(numberColors);

        return true;
    }

    private void initColoring() {
        logger.infof("Init coloring");

        component = new ArrayList<>();

        SortedSet<V> toVisit = new TreeSet<V>(this::compareVertices);
        // Queue<V> toVisit = new LinkedList<>();
        List<V> toRemove = new ArrayList<>();
        toVisit.add(maxDegreeVertex);
        setColor(maxDegreeVertex, 0);

        while (!toVisit.isEmpty()) {
            V v = remove(toVisit);
            // V v = toVisit.remove();

            VertexInfo<V> vi = vinfoMap.get(v);

            component.add(vi);
            toRemove.add(v);

            for (VertexInfo<V>  ti : vi.ninfos) {
                V t = ti.vertex;
                if (ti.color != NO_COLOR)
                    continue;

                int c = selectRandomColor(t);
                setColor(t, c);

                toVisit.add(t);
            }

            if (vi.ncolors.cardinality() == maxDegree)
                dominantColors.set(vi.color);
        }

        logger.infof("  %d/%d dominant colors",
                dominantColors.cardinality(),
                availableColors.cardinality());

        toVisit.removeAll(toRemove);

        // checkConflicts();
    }

    // ------------------------------------------------------------------------------

    private void findColoring() {
        logger.infof("Find coloring");
        while(dominantColors.cardinality() != availableColors.cardinality()) {

            int ndc = removeNonDominant();

            logger.debugft("  %d colors", availableColors.cardinality());

            List<V> coloredVertices = findColoredVertices(ndc);

            coloredVertices.forEach(v -> {
                int c = selectRandomColor(v);

                setColor(v, c);
            });

            int nDominant = availableColors.cardinality() - 1;

            component.forEach(vi -> {
                if (vi.ncolors.cardinality() == nDominant && !dominantColors.get(vi.color)) {
                    dominantColors.set(vi.color);

                    logger.infoft("  %d/%d dominant colors",
                            dominantColors.cardinality(),
                            availableColors.cardinality());
                }
            });
        }

        // checkConflicts();
    }

    // ------------------------------------------------------------------------------

    private void setColor(V v, int c) {
        VertexInfo<V> vi = vinfoMap.get(v);

        int p = vi.color;
        vi.color = c;

        if (p != NO_COLOR)
        vi.ninfos.forEach(ti -> ti.ncolors.clear(p));

        vi.ninfos.forEach(ti -> ti.ncolors.set(c));
    }

    private int selectRandomColor(V v) {
        BitSet ncolors = vinfoMap.get(v).ncolors;
        int c;
        do {
            c = rnd.nextInt(numberColors);
        } while (ncolors.get(c) || !availableColors.get(c));
        return c;
    }

    private List<V> findColoredVertices(int c) {
        return component.parallelStream()
                .filter(vi -> vi.color == c)
                .map(vi -> vi.vertex)
                .collect(Collectors.toList());
    }

    private int removeNonDominant() {
        int ndc = NO_COLOR;

        // search a dominant color with the highest index
        for (int c = numberColors-1; c >=0; --c)
            if (availableColors.get(c) && !dominantColors.get(c)) {
                ndc = c;
                break;
            }

        // remove from available colors
        availableColors.clear(ndc);

        // reduce the color range if the removed color is the last
        if (ndc == availableColors.cardinality())
            numberColors -= 1;

        return ndc;
    }

    private Map<V, Integer> getColors() {
        return vinfos.parallelStream()
                .collect(Collectors.toMap(vi -> vi.vertex, vi -> vi.color));
    }

    // ------------------------------------------------------------------------------

    private void checkConflicts() {
        long conflicts = graph.edgeSet()
                .stream()
                .filter(e -> {
                    int sourceColor = vinfoMap.get(graph.getEdgeSource(e)).color;
                    int targetColor = vinfoMap.get(graph.getEdgeTarget(e)).color;
                    return sourceColor == targetColor && sourceColor != NO_COLOR;
                }).count();
        if (conflicts > 0) {
            logger.errorf("Found %d conflicts", conflicts);
        }

        long invalidColors = component
                .stream()
                .filter(vi -> vi.color == NO_COLOR || vi.color > maxDegree)
                .count();
        if (invalidColors > 0) {
            logger.errorf("Found %d nodes with invalid colors", invalidColors);
        }

        Set<V>closure = Graphs.closureOf(vinfoMap, maxDegreeVertex);
        if (component.size() != closure.size()) {
            logger.errorf("Found component %d != %d", component.size(), closure.size());
        }

        invalidColors = vinfos
                .stream()
                .filter(vi -> vi.color == NO_COLOR)
                .count();
        if (invalidColors > 0) {
            logger.errorf("Missing %d nodes to color", invalidColors);
        }
    }

    private int compareVertices(V v, V t) {
        int cmp = vinfoMap.get(v).degree - vinfoMap.get(t).degree;
        if (cmp == 0)
            cmp = comparator.compare(v, t);
        return cmp;
    }

    private V remove(SortedSet<V> set) {
        V v = set.first();
        set.remove(v);
        return v;
    }
}

