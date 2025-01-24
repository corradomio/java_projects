package org.hls.check;

import jext.jgrapht.edges.DirectedEdge;
import jext.jgrapht.Graphs;
import jext.jgrapht.generate.RandomCavemanGraphGenerator;
import jext.jgrapht.nio.csv.VECSVExporter;
import org.jgrapht.Graph;
import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.nio.GraphExporter;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CheckExporters {

    static <V, E> void exportGraph(Graph<V, E> g, GraphExporter<V, E> exp, File file) {

        exp.exportGraph(g, file);
    }

    public static void main(String[] args) {

        Supplier<String>       vs = Graphs.vertexSupplier(String.class);
        Supplier<DirectedEdge> es = Graphs.vertexSupplier(DirectedEdge.class);

        vs = new Supplier<String>() {
            private int id=0;

            @Override
            public String get() {
                return String.format("v%d", id++);
            }
        };

        Graph<String, DirectedEdge> g =
            // Graphs.newGraph(String.class, DirectedEdge.class)
            Graphs.newGraph(true, vs, es)
            ;

        RandomCavemanGraphGenerator<String, DirectedEdge> gen =
            new RandomCavemanGraphGenerator<>(100, 1000, 10, 1, .001);

        gen.generateGraph(g);

        // exportGraph(g, new DOTExporter<String, DirectedEdge>(), new File("test.dot"));
        // exportGraph(g, new GmlExporter<String, DirectedEdge>(), new File("test.gml"));
        // exportGraph(g, new MatrixExporter<String, DirectedEdge>(), new File("test.mtx"));
        // exportGraph(g, new GraphMLExporter<String, DirectedEdge>(), new File("test.graphml"));
        // exportGraph(g, new JSONExporter<String, DirectedEdge>(), new File("test.json"));
        // exportGraph(g, new CSVExporter<String, DirectedEdge>(), new File("test.csn"));

        VECSVExporter exp = new VECSVExporter<String, DirectedEdge>();
        exp.setVertexAttributeProvider((v) -> {
            Map<String, Attribute> map = new LinkedHashMap<>();
            map.put("namespace", DefaultAttribute.createAttribute("a.b.c"));
            map.put("name", DefaultAttribute.createAttribute(v.toString()));
            map.put("inDegree", DefaultAttribute.createAttribute(0));
            map.put("outDegree", DefaultAttribute.createAttribute(0));
            map.put("nOfFields", DefaultAttribute.createAttribute(0));
            map.put("nOfMethods", DefaultAttribute.createAttribute(0));

            return map;
        });
        exportGraph(g, exp, new File("test.csv"));

    }
}
