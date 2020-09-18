package org.hls.check;

import jext.jgrapht.GraphMetrics;
import jext.jgrapht.WeightType;
import jext.jgrapht.alg.clustering.ColoringClustering;
import jext.jgrapht.alg.color.WeightedMCMCBColoring;
import jext.jgrapht.generate.RandomCavemanGraphGenerator;
import jext.jgrapht.graph.TransformGraph;
import jext.jgrapht.nio.adjacent.FileExporter;
import jext.jgrapht.nio.clustering.ClusteringExporter;
import jext.jgrapht.util.Distrib;
import jext.jgrapht.util.WeightMode;
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

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CheckCavemanClustering2 {

    static void save(int id, RandomCavemanGraphGenerator<Integer, DefaultWeightedEdge> gg) {

        Graph<Integer, DefaultWeightedEdge> g = gg.getGraph();

        String clustName = String.format("generated/relaxcave2-groundTruth-%d.json", id);
        String graphName = String.format("generated/relaxcave2-%d.json", id);

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
    }

    static RandomCavemanGraphGenerator<Integer, DefaultWeightedEdge> generateGraph(
            int id,
            int N,
            int E,
            int C,
            double insideProb,
            double betweenProb,
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
        // q : insideCommunities
        RandomCavemanGraphGenerator<Integer, DefaultWeightedEdge> gg
                = new RandomCavemanGraphGenerator<Integer, DefaultWeightedEdge>(
                N, E, C, betweenProb, insideProb)
                .communitySizes(  new UnifomDistrib().centered(meanSize, deltaSize))
                //.communityWeights(new NormalDistrib(communityWeightMean, communityWeightSdev).minValue(0.01))
                //.betweenWeights(  new NormalDistrib(betweenWeightMean,   betweenWeightSdev  ).minValue(0.01))
                .communityWeights(communityWeights)
                .betweenWeights(betweenWeights)
                ;

        gg.generateGraph(g);

        // save(id, gg);

        new GraphMetrics<>(g).getVertexStatistics().print();
        new GraphMetrics<>(g).getEdgeStatistics().print();

        return gg;
    }

    static void analyzeGraph(
            int id,
            int N,
            int E,
            int C,
            double insideProb,
            double betweenProb,
            Distrib communityWeights,
            Distrib betweenWeights,
            WeightType weighType,
            WeightMode[] weightModes,
            ClusteringStatistics stats
    )
    {
        RandomCavemanGraphGenerator<Integer, DefaultWeightedEdge> ggen;
        Graph<Integer, DefaultWeightedEdge> g, t;
        Graph<Integer, DefaultWeightedEdge> h, u;

        TransformGraph<Integer, DefaultWeightedEdge> transform;

        ClusteringAlgorithm.Clustering<Integer> groundTrue;
        ClusteringAlgorithm.Clustering<Integer> clustering;

        // generate the caveman graph
        ggen = generateGraph(id, N, E, C, insideProb, betweenProb, communityWeights, betweenWeights);

        // select the graph
        g = ggen.getGraph();

        transform = new TransformGraph<>(g);

        groundTrue = ggen.getClustering();

        for(WeightMode weightMode : weightModes) {

            stats.setGroundTrue(g, groundTrue, weighType);
            stats.setParameters(id,
                    // N, E, C,
                    insideProb, betweenProb, communityWeights, betweenWeights, weightMode);

            System.out.print("-- [groundTruth] --------------------\n");

            stats.addStats(0., g, groundTrue);

            for (double threshold = 0.0; ; threshold += 0.02) {

                if (weighType == WeightType.SIMILARITY)
                    t = transform.lowerThresholdGraph(threshold);
                else
                    t = transform.upperThresholdGraph(threshold);

                if (t.edgeSet().isEmpty())
                    break;

                System.out.printf("-- [%.1f] --------------------\n", threshold);

                System.out.print("-- cluster\n" );
                clustering = new ColoringClustering<Integer, DefaultWeightedEdge>(
                        //new ParallelBMCColoring<>(t)
                        new WeightedMCMCBColoring<>(t).weightMode(weightMode)
                ).getClustering();

                stats.addStats(threshold, t, clustering);
                stats.saveCsv("generated/relaxcave2-stats.csv");
            }
        }
    }

    public static void main(String[] args) {
        Logger.configure();

        new File("generated/relaxcave2-stats.csv").delete();

        int id = 100;                                           // is used to save the graph in a file

        int[] Nlist = {1000};
        int[] Elist = {10000, 50000, 100000};
        int[] Clist = {10};

        double[] insideProbList    = new double[]{ .9 };        // internal edges
        double[] betweenProbList   = new double[]{ .02, .2 };   // external edges
        //
        // [ communityWeight, betweenWeight ]
        //
        double[][] weightsMeanList = new double[][]{ {.5, .3}, {.3, .5} };
        double[][] weightsSdevList = new double[][]{ {.1, .1}, {.04, .04}, {.1, .04}, {.04, .1} };
        // WeightType weighType = WeightType.SIMILARITY;
        WeightMode[] weightModeList = {
                WeightMode.RANDOM,
                WeightMode.MIN, WeightMode.MAX,
                // WeightMode.GREEDY_MIN, WeightMode.GREEDY_MAX
        };

        ClusteringStatistics stats = new ClusteringStatistics();

        for (int N : Nlist)
        for (int E : Elist)
        for (int C : Clist)
        for (double betweenProb : betweenProbList)
        for (double insideProb  : insideProbList)
        for (double[] weightsMean : weightsMeanList)
        for (double[] weightsSdev : weightsSdevList)
            analyzeGraph(++id,
                    N, E, C,
                    insideProb,
                    betweenProb,
                    new NormalDistrib(weightsMean[0], weightsSdev[0]).minValue(0.001),
                    new NormalDistrib(weightsMean[1], weightsSdev[1]).minValue(0.001),
                    weightsMean[0] > weightsMean[1]
                            ? WeightType.SIMILARITY
                            : WeightType.DISSIMILARITY,
                    weightModeList,
                    stats
            );
    }
}
