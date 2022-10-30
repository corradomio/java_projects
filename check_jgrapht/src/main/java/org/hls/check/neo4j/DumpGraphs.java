package org.hls.check.neo4j;

import jext.jgrapht.Graphs;
import jext.jgrapht.edges.DirectedEdge;
import jext.jgrapht.nio.neo4j.Neo4JGraphImporter;
import jext.jgrapht.util.GraphDescribe;
import org.jgrapht.Graph;
import org.jgrapht.nio.GraphExporter;
import org.jgrapht.nio.GraphImporter;
import org.jgrapht.nio.dot.DOTExporter;

import java.io.File;

public class DumpGraphs {

    static String REF_ID = "dde55a05";

    static void dumpNodes(String label) {
        System.out.printf("dump %s%n", label);
        Neo4JGraphImporter<Long, DirectedEdge> imp = new Neo4JGraphImporter<Long, DirectedEdge>()
            .nodes(label)
            .parameters(
                "refId", REF_ID
            )
            ;

        Graph<Long, DirectedEdge> g = Graphs.newGraph(Long.class, DirectedEdge.class);
        imp.importGraph(g, new File("config/neo4j.properties"));

        GraphDescribe.describe(g);

        if (label == null || label.isEmpty())
            label = "all";

        GraphExporter<Long, DirectedEdge> exp = new DOTExporter<>();
        exp.exportGraph(g, new File(String.format("graph-%s.dot", label)));

        System.out.println();
    }


    public static void main(String[] args) {
        dumpNodes("module");
        dumpNodes("source");
        dumpNodes("type");
        dumpNodes("method");
        dumpNodes("");
    }
}
