package org.hls.check.datauci;

import jext.jgrapht.Graphs;
import jext.jgrapht.nio.adjacent.AdjacentImporter;
import jext.jgrapht.nio.adjacent.FileImporter;
import jext.jgrapht.nio.clustering.CSVClusteringImporter;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ClusteringAlgorithm;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.util.SupplierUtil;

import java.io.File;

public class CheckDataUCI {

    static void checkGraph(String name) {
        File edgesFile = new File(String.format("data.uci/%s.csv", name));
        File targetFile = new File(String.format("data.uci/%s-target.csv", name));

        Graph<Integer, DefaultWeightedEdge> g = Graphs.newGraph(
                false,
                false,
                false,
                true,
                SupplierUtil.createDefaultWeightedEdgeSupplier(),
                SupplierUtil.createIntegerSupplier()
        );

        new FileImporter<>(
                new AdjacentImporter<Integer, DefaultWeightedEdge>()
                        .withSeparator(",")
                        .weighted(true)
        ).importGraph(g, edgesFile);

        ClusteringAlgorithm.Clustering<Integer> gtruth =
                new CSVClusteringImporter<Integer>() {
                    protected Integer toVertex(String v) {
                        return Integer.parseInt(v);
                    }
                }
                .importClustering(targetFile);

        return;
    }


    public static void main(String[] args) {

        checkGraph("auto");
        checkGraph("disease");
        checkGraph("wine");
        checkGraph("zoo");

    }
}
