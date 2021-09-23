package jext.jgrapht.alg.closure;

import jext.jgrapht.Graphs;
import jext.jgrapht.util.Utils;
import jext.util.concurrent.ConcurrentArrayList;
import jext.util.concurrent.ConcurrentHashSet;
import jext.util.concurrent.Parallel;
import jext.util.concurrent.Serial;
import org.jgrapht.Graph;
import org.jgrapht.alg.TransitiveReduction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class GraphClosures<V, E> {

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
            this.members = members;
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
    private final Map<Integer, List<V>> verticesBySize = new ConcurrentHashMap<>();
    private Closure<V> singleton;
    private int maxSize;

    private Graph<V, E> closureGraph;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public GraphClosures(Graph<V, E> graph) {
        this.graph = graph;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public Map<V, Closure<V>> getClosures() {
        compute();
        return closures;
    }

    public Closure<V> getClosure(V vertex) {
        compute();
        return closures.get(vertex);
    }

    public List<Closure<V>> getClosures(int size) {
        return verticesBySize.get(size)
            .stream().map(vertex -> closures.get(vertex))
            .collect(Collectors.toList());
    }

    public List<Closure<V>> getSmallerClosures(int size) {
        List<Closure<V>> smallerClosures = new ArrayList<>();
        Serial.forEach(1,maxSize-1, smallSize-> {
            smallerClosures.addAll(getClosures(smallSize));
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
        compute();
        collectSingletons();

        if (singleton == null)
            return new Closure<>();
        else
            return singleton;
    }

    public Graph<V, E> getClosureGraph() {
        return closureGraph;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    /**
     * Compute the closures (in parallel)
     */
    public void compute() {
        // check if already computed
        if (!closures.isEmpty())
            return;

        Parallel.forEach(graph.vertexSet(), vertex -> {
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

        if (!verticesBySize.containsKey(size))
            verticesBySize.put(size, new ConcurrentArrayList<>());

        verticesBySize.get(size).add(closure.vertex);
        if (size > maxSize) maxSize = size;
    }

    /**
     * Fill 'verticesBySize' with missing closure sizes.
     * Used to simplify the code
     */
    private void fill() {
        for(int size=0; size<=maxSize; ++size) {
            if (!verticesBySize.containsKey(size))
                verticesBySize.put(size, Collections.emptyList());
        }
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    /**
     * Remove the singletons as 'single' closures and collect them in a single
     * 'closure'. Save this 'pseudo closure' in 'singleton'
     */
    public void collectSingletons() {
        // check if already computed
        if (singleton != null)
            return;

        // check if there are singletons (vertices with closure == 1)
        if (verticesBySize.get(1).isEmpty()) {
            singleton = new Closure<>();
            return;
        }

        // collect all singletons
        TreeSet<V> singletons = new TreeSet<>();
        verticesBySize.get(1).forEach(vertex -> {
            singletons.add(vertex);
        });

        // select as singleton reference the first ordered vertex
        singleton = new Closure<>(singletons.first(), singletons, 0, 0);

        // remove the singletons from 'closuresBySize' and 'closures'
        remove();

        // insert the singleton in the closures by size
        add(singleton);
    }

    /**
     * Remove the singletons from 'closures' and 'verticesBySize'
     */
    private void remove() {
        // clear 'closuresBySize'
        verticesBySize.get(1).clear();

        // remove all singletons from 'closures'
        singleton.members.forEach(closures::remove);
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

        Set<V> removed = new ConcurrentHashSet<>();

        Parallel.forEach(2, maxSize+1, size -> {
            V[] vertices = Utils.toArray(verticesBySize.get(size));
            int n = vertices.length;

            for(int i=0; i<n; ++i) {
                Closure<V> ci = closures.get(vertices[i]);
                for (int j = i + 1; j < n; ++j) {
                    Closure<V> cj = closures.get(vertices[j]);

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
        verticesBySize.get(size).remove(vertex);
    }

    // ----------------------------------------------------------------------
    // Closure containment graph
    // ----------------------------------------------------------------------

    public void createGraphOnClosures() {
        // create a graph
        closureGraph = (Graph<V, E>) Graphs.newGraph(
            true,
            false,
            false,
            false,
            graph.getVertexSupplier(),
            graph.getEdgeSupplier());

        populateVertices();
    }

    private void populateVertices() {
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
    public void computeDependencies() {
        Serial.forEach(2, maxSize, this::computeDependencies);
    }

    private void computeDependencies(int size) {
        List<Closure<V>> closures = getClosures(size);
        List<Closure<V>> smallerClosures = getSmallerClosures(size);

        Parallel.forEach(smallerClosures, smallerClosure -> {
            Serial.forEach(closures, closure -> {
                if (closure.isSuperset(smallerClosure))
                    addEdge(closure.vertex, smallerClosure.vertex);
            });
        });
    }

    private void addEdge(V sourceVertex, V targetVertex) {
        synchronized (graph) {
            closureGraph.addEdge(sourceVertex, targetVertex);
        }
    }

    // ----------------------------------------------------------------------
    // transitiveReduction
    // ----------------------------------------------------------------------

    public void transitiveReduction() {
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

        // 1) search all vertices with input/output degree = 1

        List<V> chainVertices = new ConcurrentArrayList<>();

        Parallel.forEach(closureGraph.vertexSet(), vertex -> {
            int inDegree  = closureGraph.inDegreeOf(vertex);
            int outDegree = closureGraph.outDegreeOf(vertex);
            if (inDegree == 1 && outDegree == 1)
                chainVertices.add(vertex);
        });

        // 2) search the chain heads (backward)
        //    scan backward all nodes with inDegree == 1

        Set<V> heads = new ConcurrentHashSet<>();

        Parallel.forEach(chainVertices, vertex -> {
            V head = vertex;

            while(closureGraph.inDegreeOf(head) == 1) {
                Set<E> edges = closureGraph.incomingEdgesOf(head);
                head = closureGraph.getEdgeSource(edges.iterator().next());
            }

            heads.add(head);
        });

        // 3) collapse all chains

        Parallel.forEach(heads, head -> {
            V last = head;
            List<V> removed = new ArrayList<>();

            // scan forward he chain and collect all internal vertices
            // A 'internal vertex' is a vertex with in/out degree == 1
            boolean rest = false;
            while (closureGraph.outDegreeOf(last) == 1) {
                // skip to remove the chain head
                if (rest)
                    removed.add(last);
                else
                    rest = true;

                // forward a single step
                Set<E> edges = closureGraph.outgoingEdgesOf(last);
                last = closureGraph.getEdgeTarget(edges.iterator().next());
            }

            // nothing to remove
            if (removed.isEmpty()) return;

            // remove all chain's internal vertices
            removeChains(removed);

            // create the edge 'heat->last'
            addEdge(head, last);
        });

    }

    private void removeChains(List<V> removed) {
        synchronized (graph) {
            removed.forEach(closureGraph::removeVertex);
        }
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
