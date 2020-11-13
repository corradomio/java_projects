package org.hls.check.generate;

import jext.jgrapht.GraphMetrics;
import jext.jgrapht.WeightType;
import jext.jgrapht.alg.clustering.ColoringClustering;
import jext.jgrapht.alg.color.WeightedMCMCBColoring;
import jext.jgrapht.generate.RandomCavemanGraphGenerator;
import jext.jgrapht.graph.TransformGraph;
import jext.jgrapht.nio.adjacent.FileGraphExporter;
import jext.jgrapht.nio.clustering.JSONClusteringExporter;
import jext.jgrapht.util.Distrib;
import jext.jgrapht.util.WeightMode;
import jext.jgrapht.util.distrib.NormalDistrib;
import jext.jgrapht.util.distrib.UnifomDistrib;
import jext.logging.Logger;

import org.hls.check.ClusteringStatistics;
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

    static void save(int id, RandomCavemanGraphGenerator<Integer, DefaultWeightedEdge> gg) {

        Graph<Integer, DefaultWeightedEdge> g = gg.getGraph();

        String clustName = String.format("generated/relaxcave2-groundTruth-%d.json", id);
        String graphName = String.format("generated/relaxcave2-%d.json", id);

        // export clustering
        {
            JSONClusteringExporter<Integer> cexp = new JSONClusteringExporter<>();
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

            new FileGraphExporter<>(dotexp)
                    .exportGraph(g, new File(graphName));
        }

    }

    static RandomCavemanGraphGenerator<Integer, DefaultWeightedEdge> generateGraph(
            int id,
            int N,
            int E,
            int C,
            double betweenProb,
            double insideProb,
            Distrib communityWeights,
            Distrib betweenWeights
    ) {
        Graph<Integer, DefaultWeightedEdge> g = new SimpleGraph<>(
                SupplierUtil.createIntegerSupplier(),
                SupplierUtil.createDefaultWeightedEdgeSupplier(),
                true
        );

        // similarita' / dissimilarita'
        // inter -> tra
        // intra -> dentro

        int meanSize = N/C;
        int deltaSize = meanSize/10;

        // p : betweenCommunities
        // q : in community
        RandomCavemanGraphGenerator<Integer, DefaultWeightedEdge> gg
                = new RandomCavemanGraphGenerator<Integer, DefaultWeightedEdge>(
                N, E, C, betweenProb, insideProb)
                .communitySizes(  new UnifomDistrib().centered(meanSize, deltaSize))
                .communityWeights(communityWeights)
                .betweenWeights(  betweenWeights);

        gg.generateGraph(g);

        save(id, gg);

        new GraphMetrics<>(g).getVertexStatistics().print();
        new GraphMetrics<>(g).getEdgeStatistics().print();

        return gg;
    }

    static void analyzeGraph(
            int id,
            int r,
            int N,
            int E,
            int C,
            double betweenProb,
            double insideProb,
            Distrib communityWeights,
            Distrib betweenWeights,
            WeightMode[] weightModes,
            WeightType weightType,
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

        // generate the caveman graph
        ggen = generateGraph(id, N, E, C, betweenProb, insideProb, communityWeights, betweenWeights);

        // select the graph
        g = ggen.getGraph();

        transform = new TransformGraph<>(g);

        // extract the max weight
        maxWeight = transform.getMaxWeight();

        // invert the edge weights
        h = transform.invertWeights(maxWeight*1.1);

        groundTrue = ggen.getClustering();

        for(WeightMode weightMode : weightModes) {

            disStats.setParameters(id, r,
                    // N, E, C,
                    betweenProb, insideProb, communityWeights, betweenWeights, weightMode, weightType);
            disStats.setGroundTrue(g, groundTrue);

            simStats.setParameters(id, r,
                    // N, E, C,
                    betweenProb, insideProb, communityWeights, betweenWeights, weightMode, weightType);
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
                        new WeightedMCMCBColoring<>(t).weightMode(weightMode)
                ).getClustering();

                disStats.addStats(threshold, t, clustering);
                disStats.saveCsv();

                simStats.addStats(threshold, t, clustering);
                simStats.saveCsv();
            }
        }
    }

    public static void main(String[] args) {
        Logger.configure();

        File disFile = new File("generated/relaxcave-dis-stats.csv");
        disFile.delete();
        File simFile = new File("generated/relaxcave-sim-stats.csv");
        simFile.delete();

        // Clustering statistics (dissimilarity, similarity)
        ClusteringStatistics disStats = new ClusteringStatistics(disFile);
        ClusteringStatistics simStats = new ClusteringStatistics(simFile);

        int id = 100;
        int[] Nlist = {1000};
        int[] Elist = {10000, 50000, 100000};
        int[] Clist = {10};

        double[] insideProbList = new double[]{ .2, .02 };      // internal edges
        double[] betweenProbList = new double[]{ .9 };          // external edges

        double[][] weightsMeanList = new double[][]{ {.5, .3}, {.3, .5} };
        double[][] weightsSdevList= new double[][]{ {.1, .1}, {.04, .04}, {.1, .04}, {.04, .1} };
        WeightMode[] weightModeList = { WeightMode.RANDOM, WeightMode.MIN, WeightMode.MAX };

        for (int N : Nlist)
        for (int E : Elist)
        for (int C : Clist)
        for (double betweenProb : betweenProbList)
        for (double insideProb  : insideProbList)
        for (double[] weightsMean : weightsMeanList)
        for (double[] weightsSdev : weightsSdevList)
            analyzeGraph(++id, 0, N, E, C, betweenProb, insideProb,
                    new NormalDistrib(weightsMean[0], weightsSdev[0]).minValue(0.001),
                    new NormalDistrib(weightsMean[1], weightsSdev[1]).minValue(0.001),
                    weightModeList,
                    weightsMean[0] > weightsMean[1] ? WeightType.SIMILARITY : WeightType.DISSIMILARITY,
                    disStats, simStats);
    }
}
