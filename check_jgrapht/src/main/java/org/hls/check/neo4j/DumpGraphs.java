package org.hls.check.neo4j;

import jext.jgrapht.Graphs;
import jext.jgrapht.edges.DirectedEdge;
import jext.jgrapht.nio.neo4j.Neo4JGraphImporter;
import jext.jgrapht.util.GraphDescribe;
import jext.jgrapht.vertices.Vertex;
import jext.logging.Logger;
import org.jgrapht.Graph;
import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.AttributeType;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.nio.dot.DOTExporter;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DumpGraphs {

    static String REF_ID =
        "dde55a05"
        ;

    static void dumpNodes(String label) {
        System.out.printf("dump %s%n", label);
        Neo4JGraphImporter<Vertex, DirectedEdge> imp = new Neo4JGraphImporter<Vertex, DirectedEdge>()
            .vertices(label)
            .parameters(
                "refId", REF_ID
            )
            .toVertex((id, map) -> {
                Vertex v = new Vertex(id);
                v.putAll(map);
                return v;
            })
            ;

        Graph<Vertex, DirectedEdge> g = Graphs.newGraph(Vertex.class, DirectedEdge.class);
        imp.importGraph(g, new File("config/neo4j.properties"));

        GraphDescribe.describe(g);

        if (label == null || label.isEmpty())
            label = "all";

        DOTExporter<Vertex, DirectedEdge> exp = new DOTExporter<>();
        exp.setVertexAttributeProvider((v) -> {
            Map<String, Attribute> map = new HashMap<>();
            Map<String, Object> data = v.data();
            for(String key : data.keySet()) {
                Object value = data.get(key);
                if (value == null)
                    value = "null";
                map.put(key, new DefaultAttribute<>(value.toString(), AttributeType.STRING));
            }
            return map;
        });
        exp.exportGraph(g, new File(String.format("graph-%s-%s.dot", REF_ID, label)));

        System.out.println();
    }


    public static void main(String[] args) {
        Logger.configure();

        dumpNodes("module");
        dumpNodes("source");
        dumpNodes("type");
        dumpNodes("method");
        dumpNodes("");
    }
}
