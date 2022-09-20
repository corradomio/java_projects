package jext.jgrapht.alg.closure;

import jext.jgrapht.edges.EdgeType;
import jext.jgrapht.Graphs;
import org.jgrapht.Graph;
import org.jgrapht.graph.AsSubgraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class GraphComponents<V, E> {

    public class Components {
        private List<Set<V>> components = new ArrayList<>();
        private Set<V> largestComponent = Collections.emptySet();

        void add(Set<V> component) {
            components.add(component);
            if (component.size() > largestComponent.size())
                largestComponent = component;
        }

        public int size() { return components.size(); }

        public Set<V> get(int index) { return components.get(index); }
        public Set<V> getLargestComponent() { return largestComponent; }
        public List<Set<V>> getComponents() { return components; }
        public int getNumberOfComponents() { return components.size(); }

        public Graph<V, E> getSubgraph(int index) {
            return new AsSubgraph<>(graph, components.get(index));
        }
        public Graph<V, E> getLargestSubgraph() {
            return new AsSubgraph<>(graph, largestComponent);
        }
    }

    private Graph<V, E> graph;
    private Map<V, Set<V>> neighbors = new ConcurrentHashMap<>();

    public GraphComponents(Graph<V, E> graph) {
        this.graph = graph;
    }

    public Components getComponents(EdgeType edgeType) {

        // compute the neighbors
        graph.vertexSet().parallelStream().forEach(v -> {
            neighbors.put(v, Graphs.neighborSetOf(graph, v, edgeType));
        });

        Set<V> vertices = new HashSet<>(graph.vertexSet());
        Queue<V> toVisit = new LinkedList<>();
        Set<V> visited = new HashSet<>();
        Components components = new Components();

        while(!vertices.isEmpty()) {
            V startVertex = vertices.iterator().next();

            toVisit.add(startVertex);
            while(!toVisit.isEmpty()) {
                V v = toVisit.remove();

                if (visited.contains(v))
                    continue;

                visited.add(v);
                toVisit.addAll(neighbors.get(v));
            }

            components.add(visited);

            vertices.removeAll(visited);
        }

        return components;
    }
}
