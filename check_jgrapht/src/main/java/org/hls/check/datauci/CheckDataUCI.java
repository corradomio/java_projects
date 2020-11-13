package org.hls.check.datauci;

import jext.jgrapht.Graphs;
import jext.jgrapht.nio.adjacent.AdjacentImporter;
import jext.jgrapht.nio.adjacent.FileGraphImporter;
import jext.jgrapht.nio.clustering.CSVClusteringImporter;
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


    static Info<Integer, DefaultWeightedEdge> readInfo(String name) {
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

        return;
    }


    public static void main(String[] args) {

        checkGraph("auto");
        checkGraph("disease");
        checkGraph("wine");
        checkGraph("zoo");

    }
}
