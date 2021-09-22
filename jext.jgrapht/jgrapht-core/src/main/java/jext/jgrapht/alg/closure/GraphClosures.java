package jext.jgrapht.alg.closure;

import jext.jgrapht.Graphs;
import jext.jgrapht.util.Utils;
import jext.util.concurrent.ConcurrentArrayList;
import jext.util.concurrent.ConcurrentHashSet;
import jext.util.concurrent.Parallel;
import org.jgrapht.Graph;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

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

        Closure(V v, Set<V> members, int inDegree, int outDegree) {
            this.inDegree = inDegree;
            this.outDegree = outDegree;
            this.vertex = v;
            this.members = members;
        }

        public boolean isSameSet(Closure that) {
            return Utils.isSameSet(this.members, that.members);
        }

        public boolean isRemovable(Closure that) {
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

    private final Graph<V, E> graph;
    private final Map<V, Closure<V>> closures = new ConcurrentHashMap<>();
    private final Map<Integer, List<V>> verticesBySize = new ConcurrentHashMap<>();
    private Closure<V> singleton;
    private int maxSize;

    public GraphClosures(Graph<V, E> graph) {
        this.graph = graph;
    }

    public void compute() {
        if (!closures.isEmpty()) return;

        graph.vertexSet().parallelStream().forEach(vertex -> {
            int inDegree = graph.inDegreeOf(vertex);
            int outDegree = graph.outDegreeOf(vertex);
            Set<V> closure = Graphs.closureOf(graph, vertex);
            add(new Closure<>(vertex, closure, inDegree, outDegree));
        });

        // fill all sizes [0..maxSize]
        // used to skip useless tests
        fill();
    }

    private void add(Closure<V> closure) {
        closures.put(closure.vertex, closure);
        int size = closure.size();

        if (!verticesBySize.containsKey(size))
            verticesBySize.put(size, new ConcurrentArrayList<>());

        verticesBySize.get(size).add(closure.vertex);
        if (size > maxSize) maxSize = size;
    }

    private void fill() {
        for(int size=0; size<=maxSize; ++size) {
            if (!verticesBySize.containsKey(size))
                verticesBySize.put(size, Collections.emptyList());
        }
    }

    public void collectSingletons() {
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
        // remove the singletons from 'closuresBySize' and 'closures'
        singleton = new Closure<>(singletons.first(), singletons, 0, 0);

        remove();

        // insert the singleton in the closures by size
        add(singleton);
    }

    private void remove() {
        // clear 'closuresBySize'
        verticesBySize.get(1).clear();

        // remove all singletons from 'closures'
        singleton.members.forEach(closures::remove);
    }

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

        removed.forEach(this::remove);
    }

    private void remove(V vertex) {
        // remove from 'closures'
        Closure<V> closure = closures.remove(vertex);
        int size = closure.size();

        // remove from 'verticesBySize'
        verticesBySize.get(size).remove(vertex);
    }

    public Map<V, Closure<V>> getClosures() {
        compute();
        return closures;
    }

    public Closure<V> getClosure(V vertex) {
        compute();
        return closures.get(vertex);
    }

    public Closure<V> getSingletons() {
        compute();

        if (singleton == null)
            return new Closure<>();
        else
            return closures.get(singleton);
    }

}
