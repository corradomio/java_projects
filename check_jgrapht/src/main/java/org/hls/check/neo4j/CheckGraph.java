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

public class CheckGraph {

    static String REF_ID = "dde55a05";

    static void checkModules() {
        System.out.println("checkModules");
        GraphImporter<Long, DirectedEdge> imp = new Neo4JGraphImporter<Long, DirectedEdge>()
            .nodes("module")
            .parameters(
                    "refId", REF_ID
            )
            ;

        Graph<Long, DirectedEdge> g = Graphs.newGraph(Long.class, DirectedEdge.class);
        imp.importGraph(g, new File("config/neo4j.properties"));

        GraphDescribe.describe(g);

        GraphExporter<Long, DirectedEdge> exp = new DOTExporter<>();
        exp.exportGraph(g, new File("lucene-modules.dot"));

        System.out.println();
    }

    static void checkSources() {
        System.out.println("checkSources");
        GraphImporter<Long, DirectedEdge> imp = new Neo4JGraphImporter<Long, DirectedEdge>()
            .nodes("source")
            .parameters(
                    "refId", REF_ID
            )
            ;

        Graph<Long, DirectedEdge> g = Graphs.newGraph(Long.class, DirectedEdge.class);
        imp.importGraph(g, new File("config/neo4j.properties"));

        GraphDescribe.describe(g);

        GraphExporter<Long, DirectedEdge> exp = new DOTExporter<>();
        exp.exportGraph(g, new File("lucene-sources.dot"));

        System.out.println();
    }

    static void checkGraph() {
        System.out.println("checkGraph");
        GraphImporter<Long, DirectedEdge> imp = new Neo4JGraphImporter<Long, DirectedEdge>()
            // .vertices("MATCH (s {refId:$refId}) RETURN id(s) AS s")
            // .edges("MATCH (s {refId:$refId}) --> (t) RETURN id(s) AS s, id(t) AS t")
            .nodes(null)
            .parameters(
                "refId", REF_ID
            )
            // .labels("s", "t")
            ;

        Graph<Long, DirectedEdge> g = Graphs.newGraph(Long.class, DirectedEdge.class);
        imp.importGraph(g, new File("config/neo4j.properties"));

        GraphDescribe.describe(g);
        System.out.println();
    }

    public static void main(String[] args) {
        checkModules();
        checkSources();
        checkGraph();
    }
}
