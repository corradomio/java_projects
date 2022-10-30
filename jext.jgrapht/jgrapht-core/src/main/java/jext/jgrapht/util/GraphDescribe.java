package jext.jgrapht.util;

import jext.jgrapht.GraphMetrics;
import jext.jgrapht.Graphs;
import org.jgrapht.Graph;
import org.jgrapht.GraphType;
import org.jgrapht.alg.connectivity.ConnectivityInspector;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class GraphDescribe {

    // ----------------------------------------------------------------------
    // Dump graph
    // ----------------------------------------------------------------------

    public static <V, E> void describe(Graph<V, E> g) {
        System.out.printf("Graph: |V|=%d, |E|=%d\n", g.vertexSet().size(), g.edgeSet().size());
        if (!g.vertexSet().isEmpty()) {
            V v = g.vertexSet().iterator().next();
            System.out.println("  v: " + v.getClass());
        }
        if (!g.edgeSet().isEmpty()) {
            E e = g.edgeSet().iterator().next();
            System.out.println("  e: " + e.getClass());
        }

        List<Integer> components = new ConnectivityInspector<>(g)
            .connectedSets()
            .stream()
            .sorted((s1,s2) -> -(s1.size() - s2.size()))
            .map(Set::size)
            .collect(Collectors.toList());

        System.out.printf("components:%n  %s%n", components.size());
        if (components.size() < 10)
            System.out.printf("  %s%n", components);
        else
            System.out.printf("  %s%n", components.subList(0, 10));

        GraphType gt = g.getType();
        System.out.println("properties:");
        if(gt.isSimple())                System.out.println("  simple");
        if(gt.isPseudograph())           System.out.println("  pseudograph");
        if(gt.isMultigraph())            System.out.println("  multigraph");
        if(gt.isDirected())              System.out.println("  directed");
        if(gt.isUndirected())            System.out.println("  undirected");
        if(gt.isMixed())                 System.out.println("  mixed");
        if(gt.isWeighted())              System.out.println("  weighted");
        if(gt.isAllowingSelfLoops())     System.out.println("  loop");
        if(gt.isAllowingMultipleEdges()) System.out.println("  multiple");
        if(gt.isAllowingCycles())        System.out.println("  cycles");
        System.out.println("end");
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
