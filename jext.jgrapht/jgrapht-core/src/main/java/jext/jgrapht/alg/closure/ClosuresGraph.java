package jext.jgrapht.alg.closure;

import jext.jgrapht.Graphs;
import jext.jgrapht.util.Utils;
import jext.logging.Logger;
import jext.util.Assert;
import jext.util.Pair;
import jext.util.concurrent.ConcurrentArrayList;
import jext.util.concurrent.ConcurrentTreeSet;
import jext.util.concurrent.Parallel;
import jext.util.concurrent.Serial;
import org.jgrapht.Graph;
import org.jgrapht.alg.TransitiveReduction;
import org.jgrapht.alg.cycle.CycleDetector;
import org.jgrapht.graph.concurrent.AsSynchronizedGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ClosuresGraph<V, E> {

    private static Logger logger = Logger.getLogger(ClosuresGraph.class);

    // ----------------------------------------------------------------------
    // Closure
    // ----------------------------------------------------------------------

    public static class Closure<V> {
        public final int inDegree;
        public final int outDegree;
        public V vertex;
        public final Set<V> members;

        Closure(Set<V> members) {
            this.inDegree = 0;
            this.outDegree = 0;
            this.vertex = null;
            this.members = members;
        }

        Closure(V vertex, Set<V> members, int inDegree, int outDegree) {
            this.inDegree = inDegree;
            this.outDegree = outDegree;
            this.vertex = vertex;
            this.members = members; //new ConcurrentSkipListSet<>(members);
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
         * @return if they are the same closure
         */
        public boolean isSameSet(Closure<V> that) {
            return Utils.isSameSet(this.members, that.members);
        }

        /**
         * Check if this closure is a superset than the other one
         * @param that other closure
         * @return if this closure is superset that the other
         */
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

    // current graph
    private final Graph<V, E> graph;
    // vertex -> closure map
    private final Map<V, Closure<V>> closures = new ConcurrentHashMap<>();
    // size -> {closures with the same size}
    private final Map<Integer, List<V>> bySize = new ConcurrentHashMap<>();
    private final Map<Integer, List<V>> bySmaller = new ConcurrentHashMap<>();

    private final Set<V> singletonVertices = new HashSet<>();
    private final Set<V> duplicatedVertices = new HashSet<>();

    // 'virtual closure' collecting all nodes  with in/out degree == 0
    // the 'reference node' is the 'lowest' node
    private Closure<V> singleton;
    // maximum closure size
    private int maxSize;
    // if the graph is already reduces
    private boolean graphReduced;
    // if the chains are already collapsed
    private boolean chainsCollapsed;
    // if the leaves are already pruned
    private boolean leavesPruned;

    // graph created by 'closure subset relation'
    private Graph<V, E> closureGraph;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public ClosuresGraph(Graph<V, E> graph) {
        this.graph = new AsSynchronizedGraph<>(graph);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    /**
     * The graph to analyze
     * @return the graph
     */
    public Graph<V, E> getGraph() {
        return graph;
    }

    /**
     * The graph created by 'closure subset relation'.
     * The graph is created with 'createClosureGraph()' PLUS 'computeClosureDependencies()'
     * It is possible to apply other 'reduction' algorithms
     *
     * @return the graph created
     */
    public Graph<V, E> getClosureGraph() {
        return closureGraph;
    }

    /**
     * The map with the closure for each graph.
     * After 'collectSingletons()' it doesn't contain the singleton nodes
     * After 'removeDuplicates()'  it doesn't contain two nodes with the same closure
     * @return closure's map
     */
    public Map<V, Closure<V>> getClosures() {
        return closures;
    }

    /**
     * Retrieve the closure for the specified vertex
     * @param vertex graph's vertex
     * @return the vertex's closure
     */
    public Closure<V> getClosure(V vertex) {
        return closures.get(vertex);
    }

    /**
     * Closures by size
     * @param size size of the closure
     * @return closures' list
     */
    public List<Closure<V>> getClosures(int size) {
        return bySize.get(size)
            .stream().map(closures::get)
            .collect(Collectors.toList());
    }

    public List<Closure<V>> getSmallerClosures(int size) {
        List<Closure<V>> smallerClosures = new ArrayList<>();
        Serial.forEach(1, size, ssize-> {
            smallerClosures.addAll(getClosures(ssize));
        });
        return smallerClosures;
        // return bySmaller.get(size)
        //     .stream().map(closures::get)
        //     .collect(Collectors.toList());
    }

    /**
     * Get the singletons as closure.
     * If there are no singletons, return an 'empty' closure without 'reference'
     * vertex (closure.vertex is null)
     *
     * @return
     */
    public Closure<V> getSingletons() {
        Assert.check(singleton != null, "singletons not initialized");
        return singleton;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------
    // computeClosures
    // collectSingletons
    // removeDuplicates
    //
    // createClosureGraph
    // computeClosureDependencies
    // transitiveReduction
    // collapseChains

    // ----------------------------------------------------------------------
    // computeClosures
    // ----------------------------------------------------------------------

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

            int inDegree  = graph.inDegreeOf(vertex);
            int outDegree = graph.outDegreeOf(vertex);
            Set<V> closure = Graphs.closureOf(graph, vertex);
            add(new Closure<>(vertex, closure, inDegree, outDegree));
        });

        // fill all sizes [0..maxSize]
        // used to skip useless tests
        fillBySize();
        fillSmaller();
    }

    /**
     * Add a closure in 'closures' and 'bySize';
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
     * Fill 'bySize' with missing closure sizes, and 'bySmaller'
     * with the list of edges with closure sizes smaller than the
     * current size
     * Used to simplify the code
     */
    private void fillBySize() {
        Serial.forEach(0, maxSize+1, size -> {
            if (!bySize.containsKey(size))
                bySize.put(size, Collections.emptyList());
        });
    }

    private void fillSmaller() {
        List<V> smallers = new ArrayList<>();
        Serial.forEach(0, maxSize+1, size -> {
            bySmaller.put(size, smallers);
            smallers.addAll(bySize.get(size));
        });
    }

    // ----------------------------------------------------------------------
    // collectSingletons
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
        else
            singleton = new Closure<>(singletonVertices);

        logger.info("collectSingletons");

        // check if there are singletons vertices with |closure| == 1
        if (bySize.get(1).isEmpty()) {
            return;
        }

        // collect all singletons
        bySize.get(1).forEach(vertex -> {
            Closure<V> closure = closures.get(vertex);
            // a 'singleton' must have in/out degree == 0
            if (closure.inDegree == 0 && closure.outDegree == 0)
                singletonVertices.add(vertex);
        });

        // there are vertices with |closure| == 1 but they are NOT
        // singletons because the in degree != 0
        if (singletonVertices.isEmpty()) {
            return;
        }

        // select as singleton reference the first ordered vertex
        singleton.vertex = singletonVertices.iterator().next();

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

                    // already marked to be remove
                    if (duplicatedVertices.contains(ci.vertex))
                        continue;
                    // they are not the same set
                    if (!ci.isSameSet(cj))
                        continue;

                    if (ci.isRemovable(cj))
                        duplicatedVertices.add(ci.vertex);
                    else
                        duplicatedVertices.add(cj.vertex);
                }
            }
        });

        // remove all vertices from 'closures' and 'bySize'
        duplicatedVertices.forEach(this::remove);
        fillSmaller();

        logger.warnf("... duplicates %s", duplicatedVertices);
    }

    /**
     * Remove vertex from 'closures' and 'bySize'
     * @param vertex
     */
    private void remove(V vertex) {
        // remove from 'closures'
        // Closure<V> closure = closures.remove(vertex);
        Closure<V> closure = closures.get(vertex);
        int size = closure.size();

        // remove from 'bySize'
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
            // skip vertices removed because 'singleton'
            if (singletonVertices.contains(vertex))
                return;
            // skip vertices removed because 'duplicated'
            if (duplicatedVertices.contains(vertex))
                return;
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
                if (isLeaf(vertex))
                    removed.add(vertex);
            });

            terminated = removed.isEmpty();

            removed.forEach(closureGraph::removeVertex);

            logger.infof("... pruned %s", removed);
        }
    }

    private boolean isLeaf(V vertex) {
        return closureGraph.inDegreeOf(vertex) == 1
            && closureGraph.outDegreeOf(vertex) == 0;
    }

    // ----------------------------------------------------------------------
    // collapseChains
    // ----------------------------------------------------------------------

    /**
     * Collapse
     *
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
    public void collapseChains() {
        if (chainsCollapsed)
            return;
        else
            chainsCollapsed = true;

        logger.info("collapseChains");

        boolean collapsed = true;
        while (collapsed)
            collapsed = collapseStep();
    }

    private boolean collapseStep() {
        // logger.info("... collapseStep");

        // 1) search all vertices with input/output degree = 1

        Set<V> inChain = new ConcurrentTreeSet<>();

        Parallel.forEach(closureGraph.vertexSet(), vertex -> {
            if (isInChain(vertex))
                inChain.add(vertex);
        });

        if (inChain.isEmpty())
            return false;

        // logger.infof("... inChain %s", inChain);

        // 2) search the chain head (backward)
        //    and the chain tail (forward)
        Set<Pair<V, V>> chains = new ConcurrentTreeSet<>();

        Parallel.forEach(inChain, vertex -> {
            V head = vertex;
            V tail = vertex;

            Set<V> chain = new HashSet<>();
            add(vertex, chain);

            while(isInChain(head))
                head = add(backward(head), chain);
            while(isInChain(tail))
                tail = add(forward(tail), chain);

            logger.infof("... chain %s", chain);

            chains.add(new Pair<>(head, tail));
        });

        // logger.infof("... chains %s", chains);

        // remove vertices
        inChain.forEach(closureGraph::removeVertex);

        // add edges
        chains.forEach(edge -> {
            V sourceVertex = edge.getKey();
            V targetVertex = edge.getValue();

            if (inChain.contains(sourceVertex) || inChain.contains(targetVertex)) {
                logger.warnf("... %s or %s is a inChain vertex", sourceVertex, targetVertex);
                return;
            }

            closureGraph.addEdge(sourceVertex, targetVertex);
        });

        return true;
    }

    private boolean isInChain(V vertex) {
        return closureGraph.inDegreeOf(vertex) == 1
            && closureGraph.outDegreeOf(vertex) == 1;
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

    private V add(V vertex, Set<V> chain) {
        chain.add(vertex);
        return vertex;
    }

    // ----------------------------------------------------------------------
    // Check for cycles
    // ----------------------------------------------------------------------

    public boolean isDAG() {

        CycleDetector<V, E> cycleDetector = new CycleDetector<>(closureGraph);

        if (cycleDetector.detectCycles()) {
            logger.warn("NOT DAG");
            return false;
        }
        else {
            logger.warn("DAG");
            return true;
        }

    }

    // ----------------------------------------------------------------------
    // Listeners
    // ----------------------------------------------------------------------

    public static class Event<V, E> {
        public Graph<V, E> graph;
        public String name;
        public String message;
        public int count;

        Event(Graph<V, E> g, String n, String m, int c) {
            graph = g;
            name = n;
            message = m;
            count = c;
        }
    }

    public interface Listener<V, E> {
        void stepStarted(Event<V, E> event);
        void step(Event<V, E> event);
        void stepDone(Event<V, E> event);
    }

    private final List<Listener<V, E>> listeners = new ArrayList<>();

    public void addListener(Listener<V, E> l) {
        listeners.add(l);
    }

    private void fireStart(Event<V, E> ev) {
        listeners.forEach(l -> l.stepStarted(ev));
    }

    private void fireStep(Event<V, E> ev) {
        listeners.forEach(l -> l.step(ev));
    }

    private void fireDone(Event<V, E> ev) {
        listeners.forEach(l -> l.stepDone(ev));
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
