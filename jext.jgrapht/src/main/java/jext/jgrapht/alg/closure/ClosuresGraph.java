package jext.jgrapht.alg.closure;

import jext.jgrapht.Graphs;
import jext.jgrapht.util.Utils;
import jext.logging.Logger;
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
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ClosuresGraph<V, E> {

    private static Logger logger = Logger.getLogger(ClosuresGraph.class);

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    // current graph
    private final Graph<V, E> graph;
    // vertex -> closure map
    private final Map<V, Closure<V>> closures;
    // size -> {closures with the same size}
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

    public ClosuresGraph() {
        this.graph = new SimpleDirectedGraph(DefaultEdge.class);
        this.closures = new HashMap<>();
        this.bySize = new HashMap<>();
    }

    public ClosuresGraph(Graph<V, E> graph, V singletoneId) {
        this.graph = graph; //new AsSynchronizedGraph<>(graph);
        this.closures = new HashMap<>();
        this.bySize = new HashMap<>();

        // add the 'pseudo-' singleton closure
        this.singletonClosure = new Closure<>(singletoneId, new TreeSet<>(), 0, 0);
        this.graph.addVertex(singletoneId);
        this.closures.put(singletoneId, singletonClosure);
    }

    // ----------------------------------------------------------------------
    // Set properties
    // ----------------------------------------------------------------------

    public ClosuresGraph<V, E> initWith(
        Graph<V, E> cgraph,
        Map<V, Set<V>> closures,
        V singletoneId)
    {
        this.closureGraph = cgraph;
        this.singletonClosure = new Closure<>(singletoneId, new TreeSet<>(), 0, 0);

        for (V vertex : closures.keySet()) {
            Set<V> closure = closures.get(vertex);
            addClosure(vertex, closure);
        }

        return this;
    }

    public void addClosure(V vertex, Set<V> vclosure) {
        if (vertex.equals(singletonClosure.vertex())) {
            singletonClosure.members().addAll(vclosure);
            add(singletonClosure);
        }
        else {
            Closure<V> closure = new Closure<>(vertex, new TreeSet<>(vclosure), 0, 0);
            closures.put(vertex, closure);
            add(closure);
        }
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
     * Retrieve the 'root' vertices.
     * A 'root' vertex is a vertex with inDegree = 0
     */
    public Set<V> getRoots() {
        Set<V> roots = closureGraph.vertexSet().stream()
            .filter(vertex -> closureGraph.inDegreeOf(vertex) == 0)
            .collect(Collectors.toSet());
        return roots;
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
        // collectSingletons();
        // removeDuplicates();
        createClosureGraph();
        computeClosureDependencies();
        transitiveReduction();
        // removeLeaves();
        // removeRoots();
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
        // if (singletonClosure != null)
        //     return singletonClosure.members();

        logger.info("collectSingletons");

        // Used a 'TreeSet' because in this way we are able to retrieve
        // ALWAYS the same vertex id (if it is present)
        Set<V> singletonVertices = new TreeSet<>();

        if (bySize.isEmpty())
            return Collections.emptySet();

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
        singletonClosure.members().addAll(singletonVertices);
        singletonClosure.members().remove(singletonClosure.vertex());

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

    public int createClosureGraph() {
        logger.info("createClosureGraph");

        Graph<V, E> cgraph = (Graph<V, E>) Graphs.newGraph(
            true,
            false,
            false,
            false,
            graph.getVertexSupplier(),
            graph.getEdgeSupplier());

        closureGraph = cgraph; //new AsSynchronizedGraph<>(cgraph);

        // populate with the edges
        // closures contains ONLY the vertices/closures valid
        closures.keySet().forEach(vertex -> {
            closureGraph.addVertex(vertex);
        });

        return closureGraph.vertexSet().size();
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
    public int computeClosureDependencies() {
        logger.info("computeClosureDependencies");

        List<Pair<V, V>> edges = new ConcurrentArrayList<>();

        /*Serial*/Parallel.forEach(2, maxSize+1, size -> {
            logger.infoft("... computeClosureDependencies[%d]", size);

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

        return closureGraph.edgeSet().size();
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
    public int transitiveReduction() {
        logger.info("transitiveReduction");

        TransitiveReduction.INSTANCE.reduce(closureGraph);

        return closureGraph.edgeSet().size();
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

            logger.warnf("... removed roots (%d) %s", removed.size(), trimList(removed));
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
            if (Graphs.isInChain(closureGraph, vertex))
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

            while(Graphs.isInChain(closureGraph, head))
                head = add(backward(head), chain);
            while(Graphs.isInChain(closureGraph, tail))
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

    public static class Edge<V> {
        public V source;
        public V target;
        public boolean directed;

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
        diffEdges(diff, that);
        diffClosures(diff, that);

        return diff;
    }

    private void diffVertices(CGDifferences<V> diff, ClosuresGraph<V, E> that) {
        // vertices added/removed
        Set<V> added   = Utils.difference(this.closureGraph.vertexSet(), that.closureGraph.vertexSet());
        Set<V> removed = Utils.difference(that.closureGraph.vertexSet(), this.closureGraph.vertexSet());

        diff.vertices().added(added);
        diff.vertices().removed(removed);
    }

    private void diffEdges(CGDifferences<V> diff, ClosuresGraph<V, E> that) {
        Set<Edge<V>> thisEdges = this.closureGraph.edgeSet().stream()
            .map(e -> {
                V sourceVertex = this.closureGraph.getEdgeSource(e);
                V targetVertex = this.closureGraph.getEdgeTarget(e);
                return new Edge<V>(sourceVertex, targetVertex, true);
            })
            .collect(Collectors.toSet());

        Set<Edge<V>> thatEdges = that.closureGraph.edgeSet().stream()
            .map(e -> {
                V sourceVertex = that.closureGraph.getEdgeSource(e);
                V targetVertex = that.closureGraph.getEdgeTarget(e);
                return new Edge<V>(sourceVertex, targetVertex, true);
            })
            .collect(Collectors.toSet());

        Set<Edge<V>> added   = Utils.difference(thisEdges, thatEdges);
        Set<Edge<V>> removed = Utils.difference(thatEdges, thisEdges);

        diff.edges().added(added);
        diff.edges().removed(removed);
    }

    private void diffClosures(CGDifferences<V> diff, ClosuresGraph<V, E> that) {
        Set<V> common = Utils.intersection(this.closureGraph.vertexSet(), that.closureGraph.vertexSet());
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
                diff.vertices().changed(vertex).removed(membersRemoved);
        });
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
