package org.hls.check.datauci;

import jext.jgrapht.GraphMetrics;
import jext.jgrapht.Graphs;
import jext.jgrapht.alg.clustering.ColoringClustering;
import jext.jgrapht.alg.color.WeightedMCMCBColoring;
import jext.jgrapht.graph.TransformGraph;
import jext.jgrapht.nio.adjacent.AdjacentImporter;
import jext.jgrapht.nio.adjacent.FileGraphImporter;
import jext.jgrapht.nio.clustering.CSVClusteringImporter;
import jext.jgrapht.util.WeightMode;
import jext.logging.Logger;
import org.hls.check.ClusteringStatistics;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ClusteringAlgorithm;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.util.SupplierUtil;

import java.io.File;

public class CheckDataUCI {

    static class Info<V, E> {
        public Graph<V, E> graph;
        public ClusteringAlgorithm.Clustering<V> clustering;

        public Info(Graph<V, E> graph, ClusteringAlgorithm.Clustering<V> clustering) {
            this.graph = graph;
            this.clustering = clustering;
        }
    }


    static Info<Integer, DefaultWeightedEdge> loadInfo(String name) {
        File edgesFile = new File(String.format("data.uci/%s.csv", name));
        File clusteringFile = new File(String.format("data.uci/%s-target.csv", name));

        Graph<Integer, DefaultWeightedEdge> g = Graphs.newGraph(
                false,
                false,
                false,
                true,
                SupplierUtil.createDefaultWeightedEdgeSupplier(),
                SupplierUtil.createIntegerSupplier()
        );

        new FileGraphImporter<>(
                new AdjacentImporter<Integer, DefaultWeightedEdge>()
                        .withToVertex(Integer::valueOf)
                        .withSeparator(",")
        ).importGraph(g, edgesFile);

        ClusteringAlgorithm.Clustering<Integer> gtruth =
                new CSVClusteringImporter<Integer>()
                        .withToVertex(Integer::valueOf)
                        .importClustering(clusteringFile);

        return new Info<>(g, gtruth);
    }

    static void checkGraph(String name) {
        Info<Integer, DefaultWeightedEdge> info = loadInfo(name);
        TransformGraph<Integer, DefaultWeightedEdge> transform;
        Graph<Integer, DefaultWeightedEdge> g, i, t;
        ClusteringStatistics stats = new ClusteringStatistics(
                new File(String.format("data.uci/%s-stats.csv", name))
        );

        g = info.graph;
        transform = new TransformGraph<>(g);
        i = transform.invertWeights();

        GraphMetrics<Integer, DefaultWeightedEdge> gm = new GraphMetrics<>(g);

        GraphMetrics.EdgeStatistics es = gm.getEdgeStatistics();

        ClusteringAlgorithm.Clustering<Integer> gtruth;
        ClusteringAlgorithm.Clustering<Integer> clustering;

        gtruth = info.clustering;

        stats.setGroundTruth(g, i, gtruth);
        stats.initParameters();

        System.out.print("-- [groundTruth] --------------------\n");

        stats.addStats(0., g, gtruth);

        // WeightType == DISSIMILARITY
        double init, delta;
        delta = (es.max - es.min)/20;
        init = es.min;

        for (double threshold = init; true ; threshold += delta) {

            // from bottom to top, keep above
            t = transform.upperThresholdGraph(threshold);

            // exit on null graph
            if (Graphs.isNull(t))
                break;

            System.out.printf("-- [%.2f] --------------------\n", threshold);

            System.out.print("-- cluster\n" );
            clustering = new ColoringClustering<Integer, DefaultWeightedEdge>(
                    //new ParallelBMCColoring<>(t)
                    new WeightedMCMCBColoring<>(t)
                            .withWeightMode(WeightMode.MEAN)
                            .withNumRetries(6))
                    .getClustering();

            stats.addStats(threshold, t, clustering);
            stats.saveCsv();
        }
        stats.addStatsEnd();
        stats.saveCsv();

        return;
    }


    public static void main(String[] args) {
        Logger.configure();

        checkGraph("auto");
        checkGraph("disease");
        checkGraph("wine");
        checkGraph("zoo");

    }
}
