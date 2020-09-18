package org.hls.check;

import jext.jgrapht.ClusteringMetrics;
import jext.jgrapht.GraphMetrics;
import jext.jgrapht.WeightType;
import jext.jgrapht.util.ContingencyMatrix;
import jext.jgrapht.util.Distrib;
import jext.jgrapht.util.WeightMode;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ClusteringAlgorithm;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClusteringStatistics {

    private List<List> statistics = new ArrayList<>();

    private Graph<Integer, DefaultWeightedEdge> g;
    private ClusteringAlgorithm.Clustering<Integer> groundTrue;
    private WeightType weighType;

    private GraphMetrics<Integer, DefaultWeightedEdge> gMetrics;
    private double emax;

    private int id;
    // private int N, E, C;
    private double betweenProb;
    private double insideProb;
    private Distrib communityWeights;
    private Distrib betweenWeights;
    private WeightMode weighMode;


    public ClusteringStatistics() {

    }

    public ClusteringStatistics setGroundTrue(
            Graph<Integer, DefaultWeightedEdge> g,
            ClusteringAlgorithm.Clustering<Integer> groundTrue,
            WeightType weighType) {
        this.g = g;
        this.groundTrue = groundTrue;
        this.gMetrics = new GraphMetrics<>(g);
        // this.emax = gMetrics.getEdgeStatistics().max;
        this.weighType = weighType;
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
            WeightMode weighMode)
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
    }

    private List header = Arrays.asList(
            "id",
            // "N", "E", "C",
            "insideProb",
            "betweenProb",
            "communityWeightsMean", "communityWeightsSdev",
            "betweenWeightsMean", "betweenWeightsSdev",
            "weighType",
            "weighMode",
            "threshold",
            "order", "minDegree", "maxDegree", "meanDegree", "sdevDegree",
            "size", "components", "density", "graphWeight", "minWeight", "maxWeight", "meanWeight", "sdevWeight",
            "numClusters", "minCsize", "maxCsize", "meanCsize", "sdevCsize",
            "unbalancingIndex",  "modularity", "louvainModularity",
            "dunnIndex", "daviesBouldinIndex", "purity", "giniIndex", "entropy",
            "randIndex", "adjustedRandIndex", "fowlkesMallowsIndex", "jaccardCoefficient",
            "normalizedGamma"
    );

    public void addStats(double threshold,
            Graph<Integer, DefaultWeightedEdge> t,
            ClusteringAlgorithm.Clustering<Integer> clustering)
    {
        GraphMetrics<Integer, DefaultWeightedEdge> gm = new GraphMetrics<>(t);
        GraphMetrics.VertexStatistics vs = gm.getVertexStatistics();
        GraphMetrics.EdgeStatistics   es = gm.getEdgeStatistics();

        ClusteringMetrics<Integer, DefaultWeightedEdge> cm = new ClusteringMetrics<>(g, clustering);
        ClusteringMetrics.ClusterStatistics cs = cm.getStatistics();

        ContingencyMatrix cmt = cm.getContingencyMatrix(groundTrue);

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
            weighType.toString(),
            weighMode.toString(),

            // threshold
            threshold,

            // vertices
            vs.order,
            (int)vs.min,
            (int)vs.max,
            vs.mean,
            vs.standardDeviation,

            // edges
            es.size,
            es.components,
            es.density,
            es.count*es.mean,
            es.min,
            es.max,
            es.mean,
            es.standardDeviation,

            // cluster properties
            cs.numClusters,
            cs.min,
            cs.max,
            cs.mean,
            cs.standardDeviation,

            // Indices
            cmt.getUnbalancingIndex(),
            cm.getModularity(),
            cm.getLouvainModularity(),
            cm.getDunnIndex(),
            cm.getDaviesBouldinIndex(),

            // cluster comparison
            cmt.getPurity(),
            cmt.getGiniIndex(),
            cmt.getEntropy(),
            cmt.getRandIndex(),
            cmt.getAdjustedRandIndex(),
            cmt.getFowlkesMallowsIndex(),
            cmt.getJaccardCoefficient(),
            cmt.getNormalizedGamma()
        );

        statistics.add(stats);
    }


    public void saveCsv(String filepath) {

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
