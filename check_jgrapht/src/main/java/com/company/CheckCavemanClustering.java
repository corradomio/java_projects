package com.company;

import jext.jgrapht.ClusteringMetrics;
import jext.jgrapht.GraphMetrics;
import jext.jgrapht.alg.clustering.ColoringClustering;
import jext.jgrapht.alg.color.WeightedBMCColoring;
import jext.jgrapht.generate.RandomCavemanGraphGenerator;
import jext.jgrapht.graph.TransformGraph;
import jext.jgrapht.nio.adjacent.FileExporter;
import jext.jgrapht.util.WeightType;
import jext.jgrapht.util.distrib.NormalDistrib;
import jext.logger.Logger;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ClusteringAlgorithm;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.AttributeType;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.nio.dot.DOTExporter;
import org.jgrapht.util.SupplierUtil;

import javax.swing.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CheckCavemanClustering extends JFrame {

    static RandomCavemanGraphGenerator<Integer, DefaultWeightedEdge> genGraph() {
        Graph<Integer, DefaultWeightedEdge> g = new SimpleGraph<>(
                SupplierUtil.createIntegerSupplier(),
                SupplierUtil.createDefaultWeightedEdgeSupplier(),
                true
        );

        int N = 1000;
        int E = 50000;
        int C = 10;

        // similarita' / dissimilarita'
        // inter -> tra
        // intra -> dentro

        // p : betweenCommunities
        // q: in community
        RandomCavemanGraphGenerator<Integer, DefaultWeightedEdge> gg
                = new RandomCavemanGraphGenerator<Integer, DefaultWeightedEdge>(
                N, E, C, .2, .9)
                .communityWeights(new NormalDistrib(0.30, .10).minValue(0.01))
                .betweenWeights(  new NormalDistrib(0.50, .10).minValue(0.01));

        gg.generateGraph(g);

        {
            DOTExporter<Integer, DefaultWeightedEdge> dotexp = new DOTExporter<Integer, DefaultWeightedEdge>();
            dotexp.setEdgeAttributeProvider((e) -> {
                Map<String, Attribute> atts = new HashMap<>();
                double weight = g.getEdgeWeight(e);
                atts.put("weight", new DefaultAttribute<>(weight, AttributeType.DOUBLE));
                return atts;
            });

            new FileExporter<>(dotexp)
                    .exportGraph(g, new File("relaxcave.dot"));

        }

        new GraphMetrics<>(g).getVertexStatistics().print();

        return gg;
    }

    public static void main(String[] args) {

        Logger.configure("log4j.xml");

        // Graph<Integer, DefaultWeightedEdge> g = new SimpleGraph<>(
        //         SupplierUtil.createIntegerSupplier(),
        //         SupplierUtil.createDefaultWeightedEdgeSupplier(),
        //         true
        // );
        //
        // DOTImporter<Integer, DefaultWeightedEdge> dotimp = new DOTImporter<Integer, DefaultWeightedEdge>();
        // dotimp.addEdgeAttributeConsumer((p, a) -> {
        //     String name = p.getSecond();
        //     if ("weight".equals(name)) {
        //         DefaultWeightedEdge e = p.getFirst();
        //         double weight = Double.parseDouble(a.getValue());
        //         g.setEdgeWeight(e, weight);
        //     }
        // });
        //
        // new FileImporter<>(dotimp)
        //         .importGraph(g, new File("relaxcave.dot"));

        // new GraphMetrics<>(g).getVertexStatistics().print();
        // new GraphMetrics<>(g).getEdgeStatistics().print();

        GraphMetrics.VertexStatistics vs;
        GraphMetrics.EdgeStatistics   es;
        RandomCavemanGraphGenerator<Integer, DefaultWeightedEdge> gg;
        Graph<Integer, DefaultWeightedEdge> g, t;
        ClusteringAlgorithm.Clustering<Integer> groundTrue;
        ClusteringAlgorithm.Clustering<Integer> clustering;

        gg = genGraph();
        g = gg.getGraph();

        ClusteringStatistics stats = new ClusteringStatistics(g, gg.getClustering());

        // vs = new GraphMetrics<>(g).getVertexStatistics();
        // es = new GraphMetrics<>(g).getEdgeStatistics();
        // vs.print();
        // es.print();

        System.out.printf("-- [groundTruth] --------------------\n");
        groundTrue = gg.getClustering();

        // new ClusteringMetrics<>(g, groundTrue).invertWeights(es.max).getStatistics().print();
        // new ClusteringMetrics<>(g, groundTrue).getComparison(groundTrue).print();

        stats.addStats(0., g, groundTrue);

        for (double threshold=0.0; threshold<3.8; threshold+=0.1) {

            t = new TransformGraph<>(g).upperThresholdGraph(threshold);
            if (t.edgeSet().isEmpty())
                break;

            System.out.printf("-- [%.1f] --------------------\n", threshold);
            // vs = new GraphMetrics<>(t).getVertexStatistics();
            // es = new GraphMetrics<>(t).getEdgeStatistics();
            // vs.print();
            // es.print();

            System.out.print("-- cluster\n");
            clustering = new ColoringClustering<Integer, DefaultWeightedEdge>(
                            //new ParallelBMCColoring<>(t)
                            new WeightedBMCColoring<>(t).weightType(WeightType.MEAN)
                    ).getClustering();

            // new ClusteringMetrics<>(g, clustering).invertWeights(es.max).getStatistics().print();
            // new ClusteringMetrics<>(g, clustering).getComparison(groundTrue).print();

            stats.addStats(threshold, t, clustering);
        }

        stats.saveCsv("relaxcave-stats.csv");
    }
}
