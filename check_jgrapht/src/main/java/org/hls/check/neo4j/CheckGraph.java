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

    static void checkModules() {
        System.out.println("checkModules");
        GraphImporter<Long, DirectedEdge> imp = new Neo4JGraphImporter<Long, DirectedEdge>()
                .vertices("MATCH (s:module {refId:$refId}) RETURN id(s) AS s, s.fullname AS name")
                .vertexProperties("name")
                .edges("MATCH (s:module {refId:$refId}) -[:uses]-> (t:module) RETURN id(s) AS s, id(t) AS t")
                .parameters(
                        "refId", "ac61e44a"
                )
                .labels("s", "t")
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
                .vertices("MATCH (s:source {refId:$refId}) RETURN id(s) AS s")
                .edges("MATCH (s:source {refId:$refId}) -[:uses]-> (t:source) RETURN id(s) AS s, id(t) AS t")
                .parameters(
                        "refId", "ac61e44a"
                )
                .labels("s", "t")
                ;

        Graph<Long, DirectedEdge> g = Graphs.newGraph(Long.class, DirectedEdge.class);
        imp.importGraph(g, new File("config/neo4j.properties"));

        GraphDescribe.describe(g);

        GraphExporter<Long, DirectedEdge> exp = new DOTExporter<>();
        exp.exportGraph(g, new File("lucene-sources.dot"));

        System.out.println();
    }

    static void checkGraph() {
        System.out.println("checkModules");
        GraphImporter<Long, DirectedEdge> imp = new Neo4JGraphImporter<Long, DirectedEdge>()
            .vertices("MATCH (s {refId:$refId}) RETURN id(s) AS s")
            .edges("MATCH (s {refId:$refId}) --> (t) RETURN id(s) AS s, id(t) AS t")
            .parameters(
                "refId", "2b32f1fe"
                // "refId", "c77f6865"
                // "refId", "c5b55d07"
            )
            .labels("s", "t")
            ;

        Graph<Long, DirectedEdge> g = Graphs.newGraph(Long.class, DirectedEdge.class);
        imp.importGraph(g, new File("config/neo4j.properties"));

        GraphDescribe.describe(g);
        System.out.println();
    }

    public static void main(String[] args) {
        // checkModules();
        // checkSources();
        checkGraph();
    }
}
