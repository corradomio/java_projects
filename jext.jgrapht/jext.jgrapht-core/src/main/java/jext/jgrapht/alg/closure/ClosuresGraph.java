package jext.jgrapht.alg.closure;

import jext.jgrapht.Graphs;
import jext.jgrapht.util.Utils;
import jext.util.logging.Logger;
import jext.util.Assert;
import jext.util.Pair;
import jext.util.concurrent.ConcurrentArrayList;
import jext.util.concurrent.ConcurrentHashSet;
import jext.util.concurrent.ConcurrentTreeSet;
import jext.util.concurrent.Parallel;
import jext.util.concurrent.Serial;
import org.jgrapht.Graph;
import org.jgrapht.alg.TransitiveReduction;
import org.jgrapht.alg.cycle.CycleDetector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ClosuresGraph<V, E> {

    private static final Logger logger = Logger.getLogger(ClosuresGraph.class);

    // ----------------------------------------------------------------------
    // Closure
    // ----------------------------------------------------------------------

    // current graph
    private final Graph<V, E> graph;
    // vertex -> closure map
    private final Map<V, Closure<V>> closures;
    // size : closures with the same size
    private final Map<Integer, List<V>> bySize;

    // 'virtual closure' collecting all nodes  with in/out degree == 0
    // the 'reference node' is the 'lowest' node
    private Closure<V> singletonClosure;
    // maximum closure size
    private transient int maxSize = -1;
    // graph created by 'closure subset relation'
    private Graph<V, E> closureGraph;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public ClosuresGraph(Graph<V, E> graph) {
        this.graph = graph;

        closures = new HashMap<>();
        bySize = new HashMap<>();
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
        // Note: closures can be modifies!!!!
        // It is better to return a copy!
        return new HashMap<>(closures);
    }

    /**
     * Retrieve the closure for the specified vertex
     *
     * @param vertex graph's vertex
     * @return the vertex's closure
     */
    public Closure<V> getClosure(V vertex) {
        return closures.get(vertex);
    }

    /**
     * Retrieve the closure for the specified vertex
     *
     * @param vertex graph's vertex
     * @param remap map to convert the current vertex in another vertex
     * @return the vertex's closure
     */
    public Closure<V> getClosure(V vertex, Map<V, V> remap) {
        Closure<V> closure = closures.get(vertex);
        Set<V> mappedMembers = closure.members()
            .stream()
            .map(remap::get)
            .collect(Collectors.toSet());

        return new Closure<>(closure.vertex(), mappedMembers, closure.inDegree(), closure.outDegree());
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
    }

    /**
     * Get the singletons as closure.
     * If there are no singletons, return an 'empty' closure without 'reference'
     * vertex (closure.vertex is null)
     */
    public Closure<V> getSingletonClosure() {
        Assert.check(singletonClosure != null, "singletons not initialized");
        return singletonClosure;
    }

    public Set<V> getSingletons() {
        Assert.check(singletonClosure != null, "singletons not initialized");
        return singletonClosure.members();
    }

    /**
     * Retrieve the 'root' vertices.
     * A 'root' vertex is a vertex with inDegree = 0
     */
    public Set<V> getRoots() {
        return closureGraph.vertexSet().stream()
            .filter(vertex -> closureGraph.inDegreeOf(vertex) == 0)
            .collect(Collectors.toSet());
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

    public ClosuresGraph<V,E> compose() {
        computeClosures();
        createClosureGraph();
        computeClosureDependencies();
        transitiveReduction();
        return this;
    }

    /**
     * Compute the closures (in parallel)
     */
    public ClosuresGraph<V,E> computeClosures() {
        logger.info("computeClosures");

        Parallel.forEach(graph.vertexSet(), vertex -> {
            logger.debugft("... computeClosures[%s]", vertex.toString());

            int inDegree  = graph.inDegreeOf(vertex);
            int outDegree = graph.outDegreeOf(vertex);

            Set<V> closure = computeClosure(vertex);

            add(new Closure<>(vertex, closure, inDegree, outDegree));
        });

        return this;
    }

    private Set<V> computeClosure(V startVertex) {
        Set<V> visited = new HashSet<>();
        Queue<V> toVisit = new LinkedList<>();

        toVisit.add(startVertex);
        while (!toVisit.isEmpty()) {
            V vertex = toVisit.remove();
            visited.add(vertex);

            Set<V> closure = get(vertex);
            if (closure != null) {
                visited.addAll(closure);
                continue;
            }

            Graphs.successorListOf(graph, vertex).forEach(t -> {
                if (!visited.contains(t))
                    toVisit.add(t);
            });
        }

        return visited;
    }

    /**
     * Add a closure in 'closures' and 'bySize';
     * @param closure
     */
    private synchronized void add(Closure<V> closure) {
        closures.put(closure.vertex(), closure);
        int size = closure.size();

        if (size > maxSize) {
            for (int sz = 0; sz <= size; ++sz)
                if (!bySize.containsKey(sz))
                    bySize.put(sz, new ArrayList<>());
            maxSize = size;
        }

        bySize.get(size).add(closure.vertex());
    }

    private synchronized Set<V> get(V vertex) {
        Closure<V> closure = closures.get(vertex);
        return closure != null ? closure.members() : null;
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
    public Set<V>  collectSingletons() {
        // check if already computed
        if (singletonClosure != null)
            return singletonClosure.members();

        logger.info("collectSingletons");

        Set<V> singletonVertices = new HashSet<>();

        // check if there are singletons vertices with |closure| == 1
        if (bySize.get(1).isEmpty()) {
            return singletonVertices;
        }

        // collect all singletons
        bySize.get(1).forEach(vertex -> {
            Closure<V> closure = closures.get(vertex);
            // a 'singleton' must have in/out degree == 0
            if (closure.inDegree() == 0 && closure.outDegree() == 0)
                singletonVertices.add(vertex);
        });

        // there are vertices with |closure| == 1 but they are NOT
        // singletons because the in degree != 0
        if (singletonVertices.isEmpty()) {
            return singletonVertices;
        }

        // create the closure for the 'singleton' object
        // select as singleton reference the first ordered vertex
        singletonClosure = new Closure<>(singletonVertices);

        // remove the singletons from 'closuresBySize' and 'closures'
        singletonVertices.forEach(this::remove);

        // insert the singleton in the closures by size
        // NO??? WHY NO???
        //
        add(singletonClosure);

        logger.warnf("... singletons (%d) [%s]-> %s",
            singletonClosure.members().size(),
            singletonClosure.vertex(),
            trimList(singletonClosure.members()));

        return singletonVertices;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    /**
     * Remove the duplicate closures. It is possible to create the same closure
     * following two o more different vertices. For example all vertices in
     * a cicle can generate the same closure (in parallel)
     */
    public Set<V>  removeDuplicates() {
        logger.info("removeDuplicates");

        Set<V> duplicatedVertices = new ConcurrentHashSet<>();

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
                    if (duplicatedVertices.contains(ci.vertex()))
                        continue;
                    // they are not the same set
                    if (!ci.isSameSet(cj))
                        continue;

                    if (ci.isRemovable(cj))
                        duplicatedVertices.add(ci.vertex());
                    else
                        duplicatedVertices.add(cj.vertex());
                }
            }
        });

        // remove all vertices from 'closures' and 'bySize'
        duplicatedVertices.forEach(this::remove);

        logger.warnf("... duplicates (%d) %s", duplicatedVertices.size(), trimList(duplicatedVertices));

        return duplicatedVertices;
    }

    /**
     * Remove vertex from 'closures' and 'bySize'
     * @param vertex
     */
    private void remove(V vertex) {
        // remove from 'closures'
        Closure<V> closure = closures.remove(vertex);
        // Closure<V> closure = closures.get(vertex);
        int size = closure.size();

        // remove from 'bySize'
        bySize.get(size).remove(vertex);
    }

    // ----------------------------------------------------------------------
    // Closure containment graph
    // ----------------------------------------------------------------------

    public ClosuresGraph<V,E>  createClosureGraph() {
        logger.info("createClosureGraph");

        Graph<V, E> cgraph = Graphs.newGraph(
            true,
            false,
            false,
            false,
            true,
            graph.getVertexSupplier(),
            graph.getEdgeSupplier());

        closureGraph = cgraph; //new AsSynchronizedGraph<>(cgraph);

        // populate with the edges
        // closures contains ONLY the vertices/closures valid
        closures.keySet().forEach(vertex -> {
            closureGraph.addVertex(vertex);
        });

        return this;
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
    public ClosuresGraph<V,E>  computeClosureDependencies() {
        logger.info("computeClosureDependencies");

        List<Pair<V, V>> edges = new ConcurrentArrayList<>();

        Serial.forEach(2, maxSize+1, size -> {
            logger.debugft("... computeClosureDependencies[%d]", size);

            List<Closure<V>> closures = getClosures(size);
            List<Closure<V>> smallerClosures = getSmallerClosures(size);

            Serial.forEach(closures, closure -> {
                Serial.forEach(smallerClosures, smallerClosure -> {
                    V sourceVertex = closure.vertex();
                    V targetVertex = smallerClosure.vertex();

                    logger.debugft("... comparing %s->%s", sourceVertex, targetVertex);

                    if (closure.isSuperset(smallerClosure))
                        //addEdge(sourceVertex, targetVertex);
                        edges.add(new Pair<>(sourceVertex, targetVertex));
                });
            });
        });

        edges.forEach(e -> closureGraph.addEdge(e.getKey(), e.getValue()));

        return this;
    }

    // ----------------------------------------------------------------------
    // transitiveReduction
    // ----------------------------------------------------------------------

    /**
     * Apply the 'transitive reduction':
     *
     *      (a)->(b)->(c)
     *      (a)------>(c)       <== remove this edge
     *
     */
    public ClosuresGraph<V,E>  transitiveReduction() {
        logger.info("transitiveReduction");

        TransitiveReduction.INSTANCE.reduce(closureGraph);

        return this;
    }

    // ----------------------------------------------------------------------
    // pruneLeaves
    // ----------------------------------------------------------------------

    public List<V> removeLeaves() {
        return removeLeaves((v)-> true);
    }

    /**
     * Prune all leaves:
     *
     *      (a)->(b)->(c)       <== remove '->(c)'
     *      (d)->(b)
     *
     * @param predicate predicate on the vertex
     */
    public List<V> removeLeaves(Predicate<V> predicate) {
        logger.info("removeLeaves");

        boolean terminated = false;
        List<V> leavesRemoved = new ArrayList<>();

        while (!terminated) {
            List<V> removed = new ConcurrentArrayList<>();

            Parallel.forEach(closureGraph.vertexSet(), vertex -> {
                if (isLeaf(vertex) && predicate.test(vertex))
                    removed.add(vertex);
            });

            terminated = removed.isEmpty();
            removed.forEach(closureGraph::removeVertex);
            leavesRemoved.addAll(removed);

            logger.warnf("... removed leaves (%d) %s", removed.size(), trimList(removed));
        }

        return leavesRemoved;
    }

    private boolean isLeaf(V vertex) {
        return closureGraph.inDegreeOf(vertex) == 1
            && closureGraph.outDegreeOf(vertex) == 0;
    }

    // ----------------------------------------------------------------------
    // pruneRoots
    // ----------------------------------------------------------------------

    public Set<V> removeRoots() {
        return removeRoots((v) -> true);
    }

    /**
     * Remove all nodes that satisfy the predicate IF they are roots:
     *
     *      (a)->(b)->(c)
     *
     * If 'a' and 'c' satisfy the predicate, it is removed ONLY 'a'
     *
     * @param predicate predicate on the vertex
     */
    public Set<V> removeRoots(Predicate<V> predicate) {
        logger.info("pruneRoots");

        boolean terminated = false;
        Set<V> rootsRemoved = new HashSet<>();

        while (!terminated) {
            Set<V> removed = new ConcurrentHashSet<>();

            Parallel.forEach(closureGraph.vertexSet(), vertex -> {
                if (isRoot(vertex) && predicate.test(vertex))
                    removed.add(vertex);
            });

            terminated = removed.isEmpty();
            removed.forEach(closureGraph::removeVertex);
            rootsRemoved.addAll(removed);

            logger.warnf("... removed roots (%d) %s", rootsRemoved.size(), trimList(rootsRemoved));
        }

        return rootsRemoved;
    }

    private boolean isRoot(V vertex) {
        return closureGraph.inDegreeOf(vertex) == 0
            && closureGraph.outDegreeOf(vertex) == 1;
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
    public Set<V>  collapseChains() {
        logger.info("collapseChains");

        Set<V> inChainRemoved = new HashSet<>();

        boolean collapsed = true;
        while (collapsed)
            collapsed = collapseStep(inChainRemoved);

        return inChainRemoved;
    }

    private boolean collapseStep(Set<V> inChainRemoved) {
        // 1) search all vertices with input/output degree = 1

        Set<V> inChain = new ConcurrentTreeSet<>();

        Parallel.forEach(closureGraph.vertexSet(), vertex -> {
            if (isInChain(vertex))
                inChain.add(vertex);
        });

        if (inChain.isEmpty()) {
            // just to print "... removed inChain []"
            logger.warnf("... removed inChain (0) []");
            return false;
        }

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

            logger.debugft("... chain %s", chain);

            chains.add(new Pair<>(head, tail));
        });

        // logger.infof("... chains %s", chains);

        // remove vertices
        inChain.forEach(closureGraph::removeVertex);
        inChainRemoved.addAll(inChain);

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

        logger.warnf("... removed inChain (%d) %s", inChain.size(),  trimList(inChain));
        logger.warnf("... added     edges (%d) %s", chains.size(),   trimList(chains));

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

    private static <E> Collection<E> trimList(Collection<E> coll) {
        int maxSize = 16;

        if (coll.size() <= maxSize)
            return coll;

        List<E> list = new ArrayList<>();
        for (E e : coll) {
            list.add(e);
            if (list.size() == maxSize)
                break;
        }
        return list;
    }

    // ----------------------------------------------------------------------
    // Check for cycles
    // ----------------------------------------------------------------------

    /**
     * Check if the graph is a DAG
     */
    public boolean isDAG() {

        CycleDetector<V, E> cycleDetector = new CycleDetector<>(closureGraph);

        if (cycleDetector.detectCycles()) {
            logger.warn("NOT DAG");
            return false;
        }
        else {
            logger.info("DAG");
            return true;
        }

    }

    // ----------------------------------------------------------------------
    // Difference
    // ----------------------------------------------------------------------

    static class Edge<V> {
        public final V source;
        public final V target;
        public final boolean directed;

        public Edge(V s, V t, boolean d) {
            source = s;
            target = t;
            directed = d;
        }

        @Override
        public boolean equals(Object obj) {
            Edge<V> that = (Edge<V>) obj;
            return this.source.equals(that.source)
                && this.target.equals(that.target);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.source, this.target);
        }

        @Override
        public String toString() {
            if (directed)
                return String.format("%s->%s", source, target);
            else
                return String.format("{%s, %s}", source, target);
        }
    }

    /**
     * Differences between THIS and THAT
     */
    public CGDifferences<V> difference(ClosuresGraph<V, E> that) {
        CGDifferences<V> diff = new CGDifferences<>();

        diffVertices(diff, that);
        diffClosures(diff, that);
        diffEdges(diff, that);

        return diff;
    }

    private void diffVertices(CGDifferences<V> diff, ClosuresGraph<V, E> that) {
        // vertices added/removed
        Set<V> added   = Utils.difference(this.graph.vertexSet(), that.graph.vertexSet());
        Set<V> removed = Utils.difference(that.graph.vertexSet(), this.graph.vertexSet());

        diff.vertices().added(added);
        diff.vertices().removed(removed);
    }

    private void diffClosures(CGDifferences<V> diff, ClosuresGraph<V, E> that) {
        Set<V> common = Utils.intersection(this.graph.vertexSet(), that.graph.vertexSet());
        common.forEach(vertex -> {
            Closure<V> thisClosure = this.getClosure(vertex);
            Closure<V> thatClosure = that.getClosure(vertex);

            if (thisClosure.isSameSet(thatClosure))
                return;

            Set<V> membersAdded   = thisClosure.difference(thatClosure);
            Set<V> membersRemoved = thatClosure.difference(thisClosure);

            if (!membersAdded.isEmpty())
                diff.vertices().changed(vertex).added(membersAdded);
            if (!membersRemoved.isEmpty())
                diff.vertices().changed(vertex).added(membersRemoved);
        });
    }

    private void diffEdges(CGDifferences<V> diff, ClosuresGraph<V, E> that) {
        Set<Edge<V>> thisEdges = this.graph.edgeSet().stream()
            .map(e -> {
                V sourceVertex = this.graph.getEdgeSource(e);
                V targetVertex = this.graph.getEdgeTarget(e);
                return new Edge<V>(sourceVertex, targetVertex, true);
            })
            .collect(Collectors.toSet());

        Set<Edge<V>> thatEdges = that.graph.edgeSet().stream()
            .map(e -> {
                V sourceVertex = that.graph.getEdgeSource(e);
                V targetVertex = that.graph.getEdgeTarget(e);
                return new Edge<V>(sourceVertex, targetVertex, true);
            })
            .collect(Collectors.toSet());

        Set<Edge<V>> added   = Utils.difference(thisEdges, thatEdges);
        Set<Edge<V>> removed = Utils.difference(thatEdges, thisEdges);

        diff.edges().added(added);
        diff.edges().removed(removed);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
