package com.company;

import jext.jgrapht.ClusteringMetrics;
import jext.jgrapht.GraphMetrics;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ClusteringAlgorithm;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ClusteringStatistics {

    static class Stats {
        public double threshold;

        // vertices
        public int order;
        public int minDegree;
        public int maxDegree;
        public double meanDegree;
        public double sdevDegree;
        // edges
        public int size;
        public int components;
        public double density;
        public double weight;
        public double minWeight;
        public double maxWeight;
        public double meanWeight;
        public double sdevWeight;
        // cluster
        public int numClusters;
        public double modularity;
        public double louvainModularity;
        public double dunnIndex;
        public double daviesBouldinIndex;
        // cluser comparison
        public int numClusters2;
        public double purity;
        public double giniIndex;
        public double entropy;
        public double randIndex;
        public double adjustedRandIndex;
        public double fowlkesMallowsIndex;
        public double jaccardCoefficient;
        public double normalizedGamma;

        @Override
        public String toString() {
            return String.format(
                    "%.4f,%d,%d,%d,%.4f,%.4f,%d,%d,%.4f,%.4f,%.4f,%.4f,%.4f,%.4f," +
                    "%d,%.4f,%.4f,%.4f,%.4f,%d,%.4f,%.4f,%.4f,%.4f,%.4f,%.4f,%.4f,%.4f,",
                    threshold,
                    // vertices
                    order,
                    minDegree,
                    maxDegree,
                    meanDegree,
                    sdevDegree,
                    // edges
                    size,
                    components,
                    density,
                    weight,
                    minWeight,
                    maxWeight,
                    meanWeight,
                    sdevWeight,
                    // cluster
                    numClusters,
                    modularity,
                    louvainModularity,
                    dunnIndex,
                    daviesBouldinIndex,
                    // cluser comparison
                    numClusters2,
                    purity,
                    giniIndex,
                    entropy,
                    randIndex,
                    adjustedRandIndex,
                    fowlkesMallowsIndex,
                    jaccardCoefficient,
                    normalizedGamma
            );
        }

    }

    private List<Stats> statistics = new ArrayList<>();

    private Graph<Integer, DefaultWeightedEdge> g;
    private ClusteringAlgorithm.Clustering<Integer> groundTrue;
    private GraphMetrics<Integer, DefaultWeightedEdge> gMetrics;
    private double emax;

    public ClusteringStatistics(Graph<Integer, DefaultWeightedEdge> g, ClusteringAlgorithm.Clustering<Integer> groundTrue) {
        this.g = g;
        this.groundTrue = groundTrue;
        this.gMetrics = new GraphMetrics<>(g);
        this.emax = gMetrics.getEdgeStatistics().max;
    }

    public void addStats(double threshold,
                         Graph<Integer, DefaultWeightedEdge> t,
                         ClusteringAlgorithm.Clustering<Integer> clustering)
    {
        Stats stats = new Stats();
        GraphMetrics<Integer, DefaultWeightedEdge> tm = new GraphMetrics<>(t);
        GraphMetrics.VertexStatistics vs = tm.getVertexStatistics();
        GraphMetrics.EdgeStatistics   es = tm.getEdgeStatistics();
        ClusteringMetrics<Integer, DefaultWeightedEdge> cm =
                new ClusteringMetrics<>(t, clustering).invertWeights(emax);
        ClusteringMetrics.Statistics cs = cm.getStatistics();
        ClusteringMetrics.Comparison cmp = cm.getComparison(groundTrue);

        // threshold
        stats.threshold = threshold;

        // vertices
        stats.order = vs.order;
        stats.minDegree = (int)vs.min;
        stats.maxDegree = (int)vs.max;
        stats.meanDegree = vs.mean;
        stats.sdevDegree = vs.standardDeviation;

        // edges
        stats.size = es.size;
        stats.components = es.components;
        stats.density = es.density;
        stats.weight = es.sum1;
        stats.minWeight = es.min;
        stats.maxWeight = es.max;
        stats.meanWeight = es.mean;
        stats.sdevWeight = es.standardDeviation;

        // cluster properties
        stats.numClusters = cs.numClusters;
        stats.modularity = cs.modularity;
        stats.louvainModularity = cs.louvainModularity;
        stats.dunnIndex = cs.dunnIndex;
        stats.daviesBouldinIndex = cs.daviesBouldinIndex;

        // cluster comparison
        stats.numClusters2 = cmp.numClusters1;
        stats.purity = cmp.purity;
        stats.giniIndex = cmp.giniIndex;
        stats.entropy = cmp.entropy;
        stats.randIndex = cmp.randIndex;
        stats.adjustedRandIndex = cmp.adjustedRandIndex;
        stats.fowlkesMallowsIndex = cmp.fowlkesMallowsIndex;
        stats.jaccardCoefficient = cmp.jaccardCoefficient;
        stats.normalizedGamma = cmp.normalizedGamma;

        statistics.add(stats);
    }


    public void saveCsv(String filepath) {
        try(FileWriter w = new FileWriter(filepath)) {
            w.write("threshold,order,minDegree,maxDegree,meanDegree,sdevDegree,size,components,density," +
                    "weight,minWeight,maxWeight,meanWeight,sdevWeight,numClusters,modularity,louvainModularity," +
                    "dunnIndex,daviesBouldinIndex,numClusters2,purity,giniIndex,entropy,randIndex,adjustedRandIndex," +
                    "fowlkesMallowsIndex,jaccardCoefficient,normalizedGamma\n");

            for(Stats stats : statistics) {
                w.write(stats.toString());
                w.write("\n");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }


}
