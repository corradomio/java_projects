package jext.jgrapht.alg.closure;

import jext.jgrapht.Graphs;
import jext.jgrapht.util.Utils;
import jext.logging.Logger;
import jext.util.Pair;
import jext.util.concurrent.ConcurrentArrayList;
import jext.util.concurrent.ConcurrentHashSet;
import jext.util.concurrent.ConcurrentTreeSet;
import jext.util.concurrent.Parallel;
import jext.util.concurrent.Serial;
import org.jgrapht.Graph;
import org.jgrapht.alg.TransitiveReduction;
import org.jgrapht.graph.concurrent.AsSynchronizedGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

public class GraphClosures<V, E> {

    private static Logger logger = Logger.getLogger(GraphClosures.class);

    public static class Closure<V> {
        public final int inDegree;
        public final int outDegree;
        public final V vertex;
        public final Set<V> members;

        Closure() {
            this.inDegree = 0;
            this.outDegree = 0;
            this.vertex = null;
            this.members = Collections.emptySet();
        }

        Closure(V vertex, Set<V> members, int inDegree, int outDegree) {
            this.inDegree = inDegree;
            this.outDegree = outDegree;
            this.vertex = vertex;
            this.members = new ConcurrentSkipListSet<>(members);
        }

        /**
         * Check if the closure is empty.
         * Used ONLY when thereare no singletons available
         */
        public boolean isEmpty() {
            return members.isEmpty();
        }

        /**
         * Check if two closures are the same
         * @param that other closure
         * @return if ther are the same
         */
        public boolean isSameSet(Closure<V> that) {
            return Utils.isSameSet(this.members, that.members);
        }

        public boolean isSuperset(Closure<V> that) {
            return Utils.isSuperset(this.members, that.members);
        }

        /**
         * Check if this closure can be removed.
         * Used when two closures are the same and it is necessary to decide
         * which closure to remove.
         *
         * The rules used are:
         *
         *      1) the reference vertex has a smaller input degree
         *      2) the reference vertex has a smaller output degree
         *      3) lexicographically (based on 'vertex.toString()')
         *
         * @param that other closure
         * @return if this closure can be removed
         */
        public boolean isRemovable(Closure<V> that) {
            int thisDegree, thatDegree;

            thisDegree = this.inDegree;
            thatDegree = that.inDegree;
            if (thisDegree < thatDegree)
                return true;
            if (thisDegree > thatDegree)
                return false;

            thisDegree = this.outDegree;
            thatDegree = that.outDegree;
            if (thisDegree < thatDegree)
                return true;
            if (thisDegree > thatDegree)
                return false;

            if (this.vertex.toString().compareTo(that.vertex.toString()) < 0)
                return true;
            else
                return false;
        }

        public int size() {
            return members.size();
        }
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private final Graph<V, E> graph;
    private final Map<V, Closure<V>> closures = new ConcurrentHashMap<>();
    private final Map<Integer, List<V>> bySize = new ConcurrentHashMap<>();
    private final Map<Integer, List<V>> bySmaller = new ConcurrentHashMap<>();

    private Closure<V> singleton;
    private int maxSize;
    private boolean graphReduced;
    private boolean chainMerged;
    private boolean leavesPruned;

    private Graph<V, E> closureGraph;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public GraphClosures(Graph<V, E> graph) {
        this.graph = new AsSynchronizedGraph<>(graph);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public Graph<V, E> getGraph() {
        return graph;
    }

    public Graph<V, E> getClosureGraph() {
        return closureGraph;
    }

    public Map<V, Closure<V>> getClosures() {
        return closures;
    }

    public Closure<V> getClosure(V vertex) {
        return closures.get(vertex);
    }

    public List<Closure<V>> getClosures(int size) {
        return bySize.get(size)
            .stream().map(vertex -> closures.get(vertex))
            .collect(Collectors.toList());
    }

    public List<Closure<V>> getSmallerClosures(int size) {
        List<Closure<V>> smallerClosures = new ArrayList<>();
        Serial.forEach(1, size, ssize-> {
            smallerClosures.addAll(getClosures(ssize));
        });
        return smallerClosures;
    }

    /**
     * Get the singletons as closure.
     * If there are no singletons, return an 'empty' closure without 'reference'
     * vertex (closure.vertex is null)
     *
     * @return
     */
    public Closure<V> getSingletons() {
        computeClosures();
        collectSingletons();

        if (singleton == null)
            return new Closure<>();
        else
            return singleton;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------
    // computeClosures
    // collectSingletons
    // removeDuplicates
    //
    // createClosureGraph
    // computeClosureDependencies
    // transitiveReduction
    // mergeChains

    /**
     * Compute the closures (in parallel)
     */
    public void computeClosures() {
        // check if already computed
        if (!closures.isEmpty())
            return;

        logger.info("computeClosures");

        Parallel.forEach(graph.vertexSet(), vertex -> {
            logger.debugft("... computeClosures[%s]", vertex.toString());

            int inDegree = graph.inDegreeOf(vertex);
            int outDegree = graph.outDegreeOf(vertex);
            Set<V> closure = Graphs.closureOf(graph, vertex);
            add(new Closure<>(vertex, closure, inDegree, outDegree));
        });

        // fill all sizes [0..maxSize]
        // used to skip useless tests
        fill();
    }

    /**
     * Add a closure in 'closures' and 'verticesBySize';
     * @param closure
     */
    private void add(Closure<V> closure) {
        closures.put(closure.vertex, closure);
        int size = closure.size();

        if (!bySize.containsKey(size))
            bySize.put(size, new ConcurrentArrayList<>());

        bySize.get(size).add(closure.vertex);
        if (size > maxSize) maxSize = size;
    }

    /**
     * Fill 'verticesBySize' with missing closure sizes.
     * Used to simplify the code
     */
    private void fill() {
        Serial.forEach(0, maxSize+1, size -> {
            if (!bySize.containsKey(size))
                bySize.put(size, Collections.emptyList());
        });
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    /**
     * Remove the singletons as 'single' closures and collect them in a single
     * 'closure'. Save this 'pseudo closure' in 'singleton'
     *
     * Note: a 'singleton' IS NOT a node with a closure composed by itself
     * BUT a node with in/out degree == 0. Indeed
     *
     *      (a)->(b)
     *
     *  (b) has ONLY ITSELF in the closure
     */
    public void collectSingletons() {
        // check if already computed
        if (singleton != null)
            return;

        logger.info("collectSingletons");

        // check if there are singletons vertices with |closure| == 1
        if (bySize.get(1).isEmpty()) {
            singleton = new Closure<>();
            return;
        }

        // collect all singletons
        TreeSet<V> singletons = new TreeSet<>();
        bySize.get(1).forEach(vertex -> {
            Closure<V> closure = closures.get(vertex);
            if (closure.inDegree == 0 && closure.outDegree == 0)
                singletons.add(vertex);
        });

        // there are vertices with |closure| == 1 but they are NOT
        // singletons because the in degree != 0
        if (singletons.isEmpty()) {
            singleton = new Closure<>();
            return;
        }

        // select as singleton reference the first ordered vertex
        V singletonVertex = singletons.first();
        singleton = new Closure<>(singletonVertex, singletons, 0, 0);

        // remove the singletons from 'closuresBySize' and 'closures'
        singleton.members.forEach(this::remove);

        // insert the singleton in the closures by size
        // NO
        // add(singleton);
        logger.warnf("... singletons [%s]->%s", singleton.vertex, singleton.members);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    /**
     * Remove the duplicate closures. It is possible to create the same closure
     * following two o more different vertices. For example all vertices in
     * a cicle can generate the same closure (in parallel)
     */
    public void removeDuplicates() {
        logger.info("removeDuplicates");
        Set<V> removed = new ConcurrentHashSet<>();

        Parallel.forEach(2, maxSize+1, size -> {
            V[] vertices = Utils.toArray(bySize.get(size));
            int n = vertices.length;

            for(int i=0; i<n; ++i) {
                V vi = vertices[i];
                Closure<V> ci = closures.get(vi);
                for (int j = i + 1; j < n; ++j) {
                    V vj = vertices[j];
                    Closure<V> cj = closures.get(vj);

                    logger.debugft("... comparing %s,%s", vi.toString(), vj.toString());

                    // they are not the same set
                    if (!ci.isSameSet(cj))
                        continue;
                    // already marked to be remove
                    if (removed.contains(ci.vertex))
                        return;

                    if (ci.isRemovable(cj))
                        removed.add(ci.vertex);
                    else
                        removed.add(cj.vertex);
                }
            }
        });

        // remove all vertices from 'closures' and 'verticesBySize'
        removed.forEach(this::remove);
        logger.warnf("... duplicates %s", removed);
    }

    /**
     * Remove vertex from 'closures' and 'verticesBySize'
     * @param vertex
     */
    private void remove(V vertex) {
        // remove from 'closures'
        Closure<V> closure = closures.remove(vertex);
        int size = closure.size();

        // remove from 'verticesBySize'
        bySize.get(size).remove(vertex);
    }

    // ----------------------------------------------------------------------
    // Closure containment graph
    // ----------------------------------------------------------------------

    public void createClosureGraph() {
        logger.info("createClosureGraph");

        Graph<V, E> cgraph = (Graph<V, E>) Graphs.newGraph(
            true,
            false,
            false,
            false,
            graph.getVertexSupplier(),
            graph.getEdgeSupplier());

        closureGraph = new AsSynchronizedGraph<>(cgraph);

        // populate with the edges
        closures.keySet().forEach(vertex -> {
            closureGraph.addVertex(vertex);
        });
    }

    // ----------------------------------------------------------------------
    // computeDependencies
    // ----------------------------------------------------------------------

    /**
     * Compare all closures with smaller closures and creates and edge
     *
     *      closure -> smallerClosure
     *
     * if closure.members is a superset than  smallerClosure.members
     */
    public void computeClosureDependencies() {
        logger.info("computeClosureDependencies");

        List<Pair<V, V>> edges = new ConcurrentArrayList<>();

        Serial.forEach(2, maxSize+1, size -> {
            logger.infoft("... computeClosureDependencies[%d]", size);

            List<Closure<V>> closures = getClosures(size);
            List<Closure<V>> smallerClosures = getSmallerClosures(size);

            Serial.forEach(closures, closure -> {
                Serial.forEach(smallerClosures, smallerClosure -> {
                    V sourceVertex = closure.vertex;
                    V targetVertex = smallerClosure.vertex;

                    logger.debugft("... comparing %s->%s", sourceVertex, targetVertex);

                    if (closure.isSuperset(smallerClosure))
                        //addEdge(sourceVertex, targetVertex);
                        edges.add(new Pair<>(sourceVertex, targetVertex));
                });
            });
        });

        edges.forEach(e -> closureGraph.addEdge(e.getKey(), e.getValue()));
    }

    // ----------------------------------------------------------------------
    // transitiveReduction
    // ----------------------------------------------------------------------

    public void transitiveReduction() {
        if (graphReduced)
            return;
        else
            graphReduced = true;

        logger.info("transitiveReduction");
        TransitiveReduction.INSTANCE.reduce(closureGraph);
    }

    // ----------------------------------------------------------------------
    // mergeChains
    // ----------------------------------------------------------------------

    /**
     * Merge
     *      (a)->(b)->(c)->(d)
     *
     * in
     *
     *      (a)----------->(d)
     *
     * Note:
     *  because the relations are based on containment, the closure of
     *  a is a superset than b,c,d. This means that it is not necessary
     *  to 'merge' the closures!
     */
    public void mergeChains() {
        if (chainMerged)
            return;
        else
            chainMerged = true;

        logger.info("mergeChains");

        boolean merged = true;
        while (merged)
            merged = mergeChainsStep();
    }

    private boolean mergeChainsStep() {
        logger.info("... mergeChainsStep");

        // 1) search all vertices with input/output degree = 1

        Set<V> inChain = new ConcurrentTreeSet<>();

        Parallel.forEach(closureGraph.vertexSet(), vertex -> {
            int inDegree  = closureGraph.inDegreeOf(vertex);
            int outDegree = closureGraph.outDegreeOf(vertex);
            if (inDegree == 1 && outDegree == 1)
                inChain.add(vertex);
        });

        if (inChain.isEmpty())
            return false;

        logger.infof("... inChain %s", inChain);

        // 2) search the chain head (backward)
        //    and the chain tail (forward)
        Set<Pair<V, V>> chains = new ConcurrentTreeSet<>();

        Parallel.forEach(inChain, vertex -> {
            V head = vertex;
            V tail = vertex;

            while(closureGraph.inDegreeOf(head) == 1)
                head = backward(head);
            while(closureGraph.outDegreeOf(tail) == 1)
                tail = forward(tail);

            chains.add(new Pair<>(head, tail));
        });

        logger.infof("... chains %s", chains);

        // remove vertices
        inChain.forEach(closureGraph::removeVertex);

        // add edges
        chains.forEach(edge -> {
            V sourceVertex = edge.getKey();
            V targetVertex = edge.getValue();

            if (inChain.contains(sourceVertex) || inChain.contains(targetVertex))
                return;

            closureGraph.addEdge(sourceVertex, targetVertex);
        });

        return true;
    }

    private V backward(V vertex) {
        Set<E> edges = closureGraph.incomingEdgesOf(vertex);
        E e = edges.iterator().next();
        return closureGraph.getEdgeSource(e);
    }

    private V forward(V vertex) {
        Set<E> edges = closureGraph.outgoingEdgesOf(vertex);
        E e = edges.iterator().next();
        return closureGraph.getEdgeTarget(e);
    }

    // ----------------------------------------------------------------------
    // pruneLeaves
    // ----------------------------------------------------------------------

    public void pruneLeaves() {
        if (leavesPruned)
            return;
        else
            leavesPruned = true;

        logger.info("pruneLeaves");

        boolean terminated = false;

        while (!terminated) {
            List<V> removed = new ConcurrentArrayList<>();

            Parallel.forEach(closureGraph.vertexSet(), vertex -> {
                int inDegree = closureGraph.inDegreeOf(vertex);
                int outDegree = closureGraph.outDegreeOf(vertex);

                if (inDegree == 1 && outDegree == 0)
                    removed.add(vertex);
            });

            terminated = removed.isEmpty();

            removed.forEach(closureGraph::removeVertex);
        }
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
