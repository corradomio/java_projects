package org.hls.check;

import jext.jgrapht.ClusteringMetrics;
import jext.jgrapht.WeightType;
import jext.jgrapht.generate.RandomCavemanGraphGenerator;
import jext.jgrapht.weights.ClusteringWeights;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ClusteringAlgorithm;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

public class CheckCavemanGen {

    public static void main(String[] args) {
        RandomCavemanGraphGenerator<Integer, DefaultWeightedEdge> ggen
                = new RandomCavemanGraphGenerator<>(1_0000, 50_000, 10, .02, .9);

        Graph<Integer, DefaultWeightedEdge> g = new SimpleGraph<>(
                SupplierUtil.createIntegerSupplier(),
                SupplierUtil.createDefaultWeightedEdgeSupplier(),
                true
        );
        ClusteringAlgorithm.Clustering<Integer> c;

        ggen.generateGraph(g);

        g = ggen.getGraph();
        c = ggen.getClustering();

        ClusteringMetrics<Integer, DefaultWeightedEdge> cm =
                new ClusteringMetrics<>(g, c)
                // .setWeightType(WeightType.DISSIMILARITY)
                ;
        ClusteringWeights<Integer, DefaultWeightedEdge> cw = cm.getClusterWeights();

        System.out.println(cw.getModularity());
    }
}