package org.hls.check;

import jext.jgrapht.ClusteringMetrics;
import jext.jgrapht.GraphMetrics;
import jext.jgrapht.WeightType;
import jext.jgrapht.metrics.ContingencyMatrix;
import jext.jgrapht.util.Distrib;
import jext.jgrapht.util.WeightMode;
import jext.jgrapht.metrics.ClusteringWeights;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ClusteringAlgorithm;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClusteringStatistics {

    private List<List<?>> statistics = new ArrayList<>();

    private Graph<Integer, DefaultWeightedEdge> graph;
    private Graph<Integer, DefaultWeightedEdge> invtg;
    private ClusteringAlgorithm.Clustering<Integer> groundTrue;

    private int id;
    // private int N, E, C;
    private double betweenProb;
    private double insideProb;
    private Distrib communityWeights;
    private Distrib betweenWeights;
    private WeightMode weighMode;
    private WeightType weighType;


    public ClusteringStatistics() {

    }

    public ClusteringStatistics setGroundTrue(
            Graph<Integer, DefaultWeightedEdge> g,
            ClusteringAlgorithm.Clustering<Integer> groundTrue) {
        this.graph = g;
        this.invtg = g;
        this.groundTrue = groundTrue;
        return this;
    }

    public ClusteringStatistics setGroundTrue(
            Graph<Integer, DefaultWeightedEdge> g,
            Graph<Integer, DefaultWeightedEdge> i,
            ClusteringAlgorithm.Clustering<Integer> groundTrue) {
        this.graph = g;
        this.invtg = i;
        this.groundTrue = groundTrue;
        return this;
    }

    public void setParameters(
            int id,
            // int N,
            // int E,
            // int C,
            double insideProb,
            double betweenProb,
            Distrib communityWeights,
            Distrib betweenWeights,
            WeightMode weighMode,
            WeightType weighType)
    {
        this.id = id;
        // this.N = N;
        // this.E = E;
        // this.C = C;
        this.insideProb = insideProb;
        this.betweenProb = betweenProb;
        this.communityWeights = communityWeights;
        this.betweenWeights = betweenWeights;
        this.weighMode = weighMode;
        this.weighType = weighType;
    }

    private List<String> header = Arrays.asList(
            "id",
            // "N", "E", "C",
            "insideProb",
            "betweenProb",
            "communityWeightsMean", "communityWeightsSdev",
            "betweenWeightsMean", "betweenWeightsSdev",
            "weighMode",
            "weighType",
            "threshold",

            "order", "minVertexDegree", "maxVertexDegree", "meanVertexDegree", "sdevVertexDegree",
            "size",  "graphWeight", "minEdgeWeight", "maxEdgeWeight", "meanEdgeWeight", "sdevEdgeWeight",
            "numComponents", "density",

            "numClusters", "minClusterSize", "maxClusterSize", "meanClusterSize", "sdevClusterSize",

            "insideWeight", "betweenWeight",

            "unbalancingIndex",
            "purity", "giniIndex", "entropy",
            "randIndex", "adjustedRandIndex", "fowlkesMallowsIndex", "jaccardIndex",
            "normalizedGamma",

            "modularity", "louvainModularity",
            "dunnIndex", "daviesBouldinIndex"
    );

    public void addStats(double threshold,
            Graph<Integer, DefaultWeightedEdge> t,
            ClusteringAlgorithm.Clustering<Integer> clustering)
    {
        // similarity graph
        GraphMetrics<Integer, DefaultWeightedEdge> tm = new GraphMetrics<>(t);

        GraphMetrics.VertexStatistics vs = tm.getVertexStatistics();
        GraphMetrics.EdgeStatistics   es = tm.getEdgeStatistics();

        // clustering metrics\ on "similarity"
        ClusteringMetrics<Integer, DefaultWeightedEdge>
                cmsim = new ClusteringMetrics<>(graph, clustering);
        ClusteringMetrics<Integer, DefaultWeightedEdge>
                cmdis = new ClusteringMetrics<>(invtg, clustering);

        // clustering statistics
        ClusteringMetrics.ClusterStatistics cs = cmsim.getStatistics();
        // contingency matrix with the ground truth
        ContingencyMatrix cmt = cmsim.getContingencyMatrix(groundTrue);
        // clustering weights
        ClusteringWeights cw  = cmsim.getClusterWeights();

        List<?> stats = Arrays.asList(
            // id
            id,

            // configuration
            // N, E, C,
            insideProb,
            betweenProb,
            communityWeights.mean(),
            communityWeights.sdev(),
            betweenWeights.mean(),
            betweenWeights.sdev(),
            weighMode.toString(),
            weighType.toString(),

            // threshold
            threshold,

            // vertices on threshold graph
            vs.order,
            vs.min,
            vs.max,
            vs.mean,
            vs.standardDeviation,

            // edges on threshold graph
            es.size,
            es.count*es.mean,
            es.min,
            es.max,
            es.mean,
            es.standardDeviation,

            // connectivity & density on threshold graph
            es.components,
            es.density,

            // cluster properties
            cs.numClusters,
            cs.min,
            cs.max,
            cs.mean,
            cs.standardDeviation,

            // clusters weights
            cw.getInternalWeight(),
            cw.getExternalWeight(),

            // contingency matrix
            cmt.getUnbalancingIndex(),
            cmt.getPurity(),
            cmt.getGiniIndex(),
            cmt.getEntropy(),
            cmt.getRandIndex(),
            cmt.getAdjustedRandIndex(),
            cmt.getFowlkesMallowsIndex(),
            cmt.getJaccardIndex(),
            cmt.getNormalizedGamma(),

            // similarity
            cw.getModularity(),
            cw.getLouvainModularity(),

            // dissimilarity == distance
            cw.getDunnIndex(),
            cw.getDaviesBouldinIndex()
        );

        statistics.add(stats);
    }


    public void saveCsv(String filepath) {

        int nStats = statistics.size();

        try(FileWriter w = new FileWriter(filepath)) {
            w.write(toString(header));
            w.write("\n");

            for(List<?> stats : statistics) {
                w.write(toString(stats));
                w.write("\n");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static String toString(List<?> stats) {
        StringBuilder sb = new StringBuilder();
        for (Object e : stats) {
            if (sb.length() > 0)
                sb.append(",");

            if (e instanceof String) {
                sb.append(e.toString());
            }
            else if (e instanceof Integer) {
                sb.append(e.toString());
            }
            else if (e instanceof Double) {
                double d = (Double)e;
                sb.append(String.format("%.4f", d));
            }
            else if (e instanceof Float) {
                double d = (Float)e;
                sb.append(String.format("%.4f", d));
            }
        }
        return sb.toString();
    }

}
