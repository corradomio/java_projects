package jext.jgrapht;

import org.jgrapht.Graph;

import java.util.Map;
import java.util.TreeMap;

public class GraphDump {


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
