package org.hls.check.generate;

import jext.jgrapht.GraphMetrics;
import jext.jgrapht.nio.file.FileGraphImporter;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.nio.dot.DOTImporter;
import org.jgrapht.util.SupplierUtil;

import java.io.File;

public class CheckCavemanIn {

    public static void main(String[] args) {
        Graph<Integer, DefaultWeightedEdge> g = new SimpleGraph<>(
                SupplierUtil.createIntegerSupplier(),
                SupplierUtil.createDefaultWeightedEdgeSupplier(),
                true
        );

        DOTImporter<Integer, DefaultWeightedEdge> dotimp = new DOTImporter<Integer, DefaultWeightedEdge>();
        dotimp.addEdgeAttributeConsumer((p, a) -> {
            String name = p.getSecond();
            if ("weight".equals(name)) {
                DefaultWeightedEdge e = p.getFirst();
                double weight = Double.parseDouble(a.getValue());
                g.setEdgeWeight(e, weight);
            }
        });

        new FileGraphImporter<>(dotimp)
                .importGraph(g, new File("relaxcave.dot.zip"));

        new GraphMetrics<>(g).getVertexStatistics().print();
    }
}
