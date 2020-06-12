package com.company;

import jext.jgrapht.GraphMetrics;
import jext.jgrapht.generate.RandomCavemanGraphGenerator;
import jext.jgrapht.nio.adjacent.FileExporter;
import jext.jgrapht.util.distrib.NormalDistrib;
import org.jgrapht.Graph;
import org.jgrapht.generate.GraphGenerator;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.AttributeType;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.nio.dot.DOTExporter;
import org.jgrapht.util.SupplierUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CheckCavemanOut {

    public static void main(String[] args) {
        Graph<Integer, DefaultWeightedEdge> g = new SimpleGraph<>(
                SupplierUtil.createIntegerSupplier(),
                SupplierUtil.createDefaultWeightedEdgeSupplier(),
                true
        );

        int N = 1000;
        int E = 50000;
        int C = 10;
        int CLIQUE = 5;

        // p : betweenCommunities
        // q: in community
        GraphGenerator<Integer, DefaultWeightedEdge, List<Set<Integer>>> gg
                = new RandomCavemanGraphGenerator<Integer, DefaultWeightedEdge>(
                N, E, C, CLIQUE,.001, .99)
                .communityWeights(new NormalDistrib(0.25, .10).minValue(0.01))
                .betweenWeights(new NormalDistrib(1., .10).minValue(0.05));

        gg.generateGraph(g);

        DOTExporter<Integer, DefaultWeightedEdge> dotexp = new DOTExporter<Integer, DefaultWeightedEdge>();
        dotexp.setEdgeAttributeProvider((e) -> {
            Map<String, Attribute> atts = new HashMap<>();
            double weight = g.getEdgeWeight(e);
            atts.put("weight", new DefaultAttribute<>(weight, AttributeType.DOUBLE));
            return atts;
        });

        new FileExporter<>(dotexp)
                .exportGraph(g, new File("relaxcave.dot"));

        new GraphMetrics<>(g).getVertexStatistics().print();
    }
}
