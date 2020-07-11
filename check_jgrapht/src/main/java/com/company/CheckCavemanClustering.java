package com.company;

import jext.jgrapht.GraphMetrics;
import jext.jgrapht.alg.clustering.ColoringClustering;
import jext.jgrapht.alg.color.WeightedMCMCBColoring;
import jext.jgrapht.generate.RandomCavemanGraphGenerator;
import jext.jgrapht.graph.TransformGraph;
import jext.jgrapht.nio.adjacent.FileExporter;
import jext.jgrapht.nio.clustering.ClusteringExporter;
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
import org.jgrapht.util.SupplierUtil;

import javax.swing.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CheckCavemanClustering extends JFrame {

    static RandomCavemanGraphGenerator<Integer, DefaultWeightedEdge> generateGraph(
            int id,
            int E,
            double betweenProb,
            double insideProb,
            double communityWeightSdev,
            double betweenWeightSdev
    ) {
        Graph<Integer, DefaultWeightedEdge> g = new SimpleGraph<>(
                SupplierUtil.createIntegerSupplier(),
                SupplierUtil.createDefaultWeightedEdgeSupplier(),
                true
        );

        // similarita' / dissimilarita'
        // inter -> tra
        // intra -> dentro

        int N = 1000;               // order, n of vertices
        // int E = 50000;              // size,  n of edges
        int C = 10;                 // n of communities
        // double betweenProb = .2;    // between communities
        // double insideProb  = .9;    // inside  communities
        double communityWeightMean = 0.3;
        // double communityWeightSdev = 0.1;
        double betweenWeightMean = 0.5;
        // double betweenWeightSdev = 0.1;

        int meanSize = N/C;
        int deltaSize = meanSize/10;

        // p : betweenCommunities
        // q : in community
        RandomCavemanGraphGenerator<Integer, DefaultWeightedEdge> gg
                = new RandomCavemanGraphGenerator<Integer, DefaultWeightedEdge>(
                N, E, C, betweenProb, insideProb)
                .communitySizes(  new UnifomDistrib().centered(meanSize, deltaSize))
                .communityWeights(new NormalDistrib(communityWeightMean, communityWeightSdev).minValue(0.01))
                .betweenWeights(  new NormalDistrib(betweenWeightMean, betweenWeightSdev).minValue(0.01));

        gg.generateGraph(g);

        String clustName = String.format("generated/relaxcave-groundTruth-%d.json", id);
        String graphName = String.format("generated/relaxcave-%d.json", id);

        // export clustering
        {
            ClusteringExporter<Integer> cexp = new ClusteringExporter<>();
            cexp.exportClustering(gg.getClustering(), new File(clustName));
        }

        // export graph
        {
            DOTExporter<Integer, DefaultWeightedEdge> dotexp = new DOTExporter<Integer, DefaultWeightedEdge>();
            dotexp.setEdgeAttributeProvider((e) -> {
                Map<String, Attribute> atts = new HashMap<>();
                double weight = g.getEdgeWeight(e);
                atts.put("weight", new DefaultAttribute<>(weight, AttributeType.DOUBLE));
                return atts;
            });

            new FileExporter<>(dotexp)
                    .exportGraph(g, new File(graphName));
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

    static void analyzeGraph(
            int id,
            int E,
            double betweenProb,
            double insideProb,
            double communityWeightSdev,
            double betweenWeightSdev,
            WeightType[] weighTypes,
            ClusteringStatistics disStats,
            ClusteringStatistics simStats
    )
    {
        RandomCavemanGraphGenerator<Integer, DefaultWeightedEdge> ggen;
        Graph<Integer, DefaultWeightedEdge> g, t;
        Graph<Integer, DefaultWeightedEdge> h, u;

        TransformGraph<Integer, DefaultWeightedEdge> transform;
        double maxWeight;

        ClusteringAlgorithm.Clustering<Integer> groundTrue;
        ClusteringAlgorithm.Clustering<Integer> clustering;

        ggen = generateGraph(id, E, betweenProb, insideProb, communityWeightSdev, betweenWeightSdev);
        g = ggen.getGraph();

        transform = new TransformGraph<>(g);
        maxWeight = transform.getMaxWeight()*1.1;
        h = transform.invertWeights(maxWeight);

        groundTrue = ggen.getClustering();

        for(WeightType weighType : weighTypes) {

            disStats.setParameters(id, betweenProb, insideProb, communityWeightSdev, betweenWeightSdev, weighType);
            disStats.setGroundTrue(g, groundTrue);

            simStats.setParameters(id, betweenProb, insideProb, communityWeightSdev, betweenWeightSdev, weighType);
            simStats.setGroundTrue(h, groundTrue);

            System.out.print("-- [groundTruth] --------------------\n");

            disStats.addStats(0., g, groundTrue);
            simStats.addStats(0., h, groundTrue);

            for (double threshold = 0.0; ; threshold += 0.02) {

                t = transform.upperThresholdGraph(threshold);
                u = new TransformGraph<>(t).invertWeights(maxWeight);
                if (t.edgeSet().isEmpty())
                    break;

                System.out.printf("-- [%.1f] --------------------\n", threshold);

                System.out.print("-- cluster\n" );
                clustering = new ColoringClustering<Integer, DefaultWeightedEdge>(
                        //new ParallelBMCColoring<>(t)
                        new WeightedMCMCBColoring<>(t).weightType(weighType)
                ).getClustering();

                disStats.addStats(threshold, t, clustering);
                disStats.saveCsv("generated/relaxcave-dis-stats.csv");

                simStats.addStats(threshold, t, clustering);
                simStats.saveCsv("generated/relaxcave-sim-stats.csv");
            }
        }
    }

    public static void main(String[] args) {
        Logger.configure();

        // Clustering statistics (dissimilarity, similarity)
        ClusteringStatistics disStats = new ClusteringStatistics();
        ClusteringStatistics simStats = new ClusteringStatistics();

        int id = 100;
        int[] EList = {10000, 50000, 100000};
        double[] betweenProbList = new double[]{ .9 };
        double[] insideProbList = new double[]{ .2, .02 };
        // double[] communityWeightSdevList = new double[]{ .1 };
        // double[] betweenWeightSdevList = new double []{ .1 };
        double[][] weightsSdevList= new double[][]{ {.1, .1}, {.04, .04}, {.1, .04}, {.04, .1} };
        WeightType[] weightTypeList = { WeightType.MEAN, WeightType.MIN, WeightType.MAX };

        for (int E : EList)
        for (double betweenProb : betweenProbList)
        for (double insideProb  : insideProbList)
        // for (double communityWeightSdev:communityWeightSdevList)
        // for (double betweenWeightSdev:betweenWeightSdevList)
        for (double[] weightsSdev : weightsSdevList)
            analyzeGraph(++id, E, betweenProb, insideProb, weightsSdev[0], weightsSdev[1], weightTypeList,
                    disStats, simStats);

        //stats.saveCsv("relaxcave-stats.csv");
    }
}
