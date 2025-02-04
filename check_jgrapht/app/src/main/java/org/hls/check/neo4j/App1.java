package org.hls.check.neo4j;

import jext.jgrapht.edges.Edge;
import jext.jgrapht.Graphs;
import jext.jgrapht.nio.neo4j.Neo4JGraphImporter;
import jext.jgrapht.util.GraphDescribe;
import jext.logging.Logger;
import jext.util.Parameters;
import org.jgrapht.Graph;
import org.jgrapht.nio.GraphExporter;
import org.jgrapht.nio.GraphImporter;
import org.jgrapht.nio.dot.DOTExporter;

import java.io.File;

public class App1 {

    public static void main(String[] args) {
        Logger.configure();
        Graph<Long, Edge> g = Graphs.newGraph(true, false, Long.class, Edge.class);
        // new ScaleFreeGraphGenerator<Long, Edge>(50000)
        //     .generateGraph(g);

        GraphDescribe.printGraphInfo(g);

        GraphImporter<Long, Edge> imp = new Neo4JGraphImporter<Long, Edge>()
            .vertices("MATCH (s:type {refId:$refId}) RETURN id(s) AS s")
            .edges("MATCH (s:type {refId:$refId}) -[:uses]-> (t:type) RETURN id(s) AS s, id(t) AS t")
            .parameters(
                Parameters.params(
                    "refId", "7246bd36"
                ))
            .labels("s", "t")
        ;
        imp.importGraph(g, new File("config/neo4j.properties"));

        GraphExporter<Long, Edge> exp =
            // new EdgesExporter<String, DefaultEdge>().withSeparator(",")
            new DOTExporter<>()
        ;
        exp.exportGraph(g, new File("graph.dot"));

        GraphDescribe.describe(g);
    }
}
