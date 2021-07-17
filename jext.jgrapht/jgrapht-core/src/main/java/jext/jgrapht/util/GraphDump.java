package jext.jgrapht.util;

import jext.jgrapht.GraphMetrics;
import jext.jgrapht.Graphs;
import org.jgrapht.Graph;

import java.util.Map;
import java.util.TreeMap;

public class GraphDump {

    // ----------------------------------------------------------------------
    // Dump graph
    // ----------------------------------------------------------------------

    public static <V, E> void describe(Graph<V, E> g) {
        System.out.printf("Graph: |V|=%d, |E|=%d\n", g.vertexSet().size(), g.edgeSet().size());
        if (!g.vertexSet().isEmpty()) {
            V v = g.vertexSet().iterator().next();
            System.out.println(" v: " + v.getClass());
        }
        if (!g.edgeSet().isEmpty()) {
            E e = g.edgeSet().iterator().next();
            System.out.println(" e: " + e.getClass());
        }
    }

    public static <V, E> void printGraphInfo(Graph<V, E> graph) {
        System.out.printf("%s\n", graph.getClass().getName());
        System.out.printf("  nVertices: %d\n", graph.vertexSet().size());
        System.out.printf("  nEdges   : %d\n", graph.edgeSet().size());
        System.out.printf("  type     : %s\n", graph.getType());
    }

    public static <V, E> void printDegreeStatistics(Graph<V, E> graph) {
        new GraphMetrics<>(graph)
                .getVertexStatistics()
                .print();
    }

    public static <V, E> void printComponentStatistics(Graph<V, E> graph) {
        Map<Integer, Integer> cc = new TreeMap<>();
        Graphs.components(graph).forEach(c -> {
            int size = c.size();
            if (cc.containsKey(size)) {
                cc.put(size, 1 + cc.get(size));
            }
            else {
                cc.put(size, 1);
            }
        });

        cc.forEach((sz, nc) -> {
            System.out.printf("  c[%d]:%d\n", sz, nc);
        });
    }
}
