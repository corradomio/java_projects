package com.company;

import jext.jgrapht.GraphMetrics;
import jext.jgrapht.alg.clustering.ColoringClustering;
import jext.jgrapht.alg.color.WeightedBMCColoring;
import jext.jgrapht.generate.RandomCavemanGraphGenerator;
import jext.jgrapht.graph.TransformGraph;
import jext.jgrapht.nio.adjacent.FileExporter;
import jext.jgrapht.nio.adjacent.FileImporter;
import jext.jgrapht.util.WeightType;
import jext.jgrapht.util.distrib.NormalDistrib;
import jext.jgrapht.util.distrib.UnifomDistrib;
import jext.logging.Logger;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ClusteringAlgorithm;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.AttributeType;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.nio.dot.DOTExporter;
import org.jgrapht.nio.dot.DOTImporter;
import org.jgrapht.util.SupplierUtil;

import javax.swing.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CheckCavemanClustering extends JFrame {

    static RandomCavemanGraphGenerator<Integer, DefaultWeightedEdge> generateGraph() {
        Graph<Integer, DefaultWeightedEdge> g = new SimpleGraph<>(
                SupplierUtil.createIntegerSupplier(),
                SupplierUtil.createDefaultWeightedEdgeSupplier(),
                true
        );

        // similarita' / dissimilarita'
        // inter -> tra
        // intra -> dentro

        int N = 10000;               // order, n of vertices
        int E = 500000;              // size,  n of edges
        int C = 10;                 // n of communities
        double betweenProb = .2;    // between communities
        double insideProb  = .9;    // inside  communities

        int meanSize = N/C;
        int deltaSize = meanSize/4;

        // p : betweenCommunities
        // q : in community
        RandomCavemanGraphGenerator<Integer, DefaultWeightedEdge> gg
                = new RandomCavemanGraphGenerator<Integer, DefaultWeightedEdge>(
                N, E, C, betweenProb, insideProb)
                .communitySizes(  new UnifomDistrib().centered(meanSize, deltaSize))
                .communityWeights(new NormalDistrib(0.30, .10).minValue(0.01))
                .betweenWeights(  new NormalDistrib(0.50, .10).minValue(0.01));

        gg.generateGraph(g);

        // export
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

        // Import
        // {
        //     DOTImporter<Integer, DefaultWeightedEdge> dotimp = new DOTImporter<>();
        //     dotimp.addEdgeAttributeConsumer((e, a) -> {
        //         System.out.printf("%s: %s", e, a);
        //     });
        //
        //     new FileImporter<>(dotimp)
        //             .importGraph(g, new File("relaxcave.dot"));
        // }

        new GraphMetrics<>(g).getVertexStatistics().print();
        new GraphMetrics<>(g).getEdgeStatistics().print();

        return gg;
    }

    public static void main(String[] args) {

        Logger.configure();

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
        RandomCavemanGraphGenerator<Integer, DefaultWeightedEdge> ggen;
        Graph<Integer, DefaultWeightedEdge> g, t;
        ClusteringAlgorithm.Clustering<Integer> groundTrue;
        ClusteringAlgorithm.Clustering<Integer> clustering;

        ggen = generateGraph();
        g = ggen.getGraph();
        groundTrue = ggen.getClustering();

        // Clustering statistics
        ClusteringStatistics stats = new ClusteringStatistics(g, groundTrue);

        // vs = new GraphMetrics<>(g).getVertexStatistics();
        // es = new GraphMetrics<>(g).getEdgeStatistics();
        // vs.print();
        // es.print();

        System.out.printf("-- [groundTruth] --------------------\n");

        // new ClusteringMetrics<>(g, groundTrue).invertWeights(es.max).getStatistics().print();
        // new ClusteringMetrics<>(g, groundTrue).getComparison(groundTrue).print();

        stats.addStats(0., g, groundTrue);

        for (double threshold=0.0; threshold<3.8; threshold+=0.03) {

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
