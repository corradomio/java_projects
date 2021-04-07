package org.hls.check;

import jext.jgrapht.Graphs;
import jext.jgrapht.nio.neo4j.Neo4JGraphImporter;
import jext.logging.Logger;
import jext.util.Parameters;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.nio.GraphExporter;
import org.jgrapht.nio.GraphImporter;
import org.jgrapht.nio.dot.DOTExporter;

import java.io.File;

public class App {

    public static void main(String[] args) {
        Logger.configure();
        Graph<String, DefaultEdge> g = Graphs.newGraph(true, false);
        // new ScaleFreeGraphGenerator<Integer, DefaultEdge>(50000)
        //     .generateGraph(g);
        //
        // GraphDump.printGraphInfo(g);

        GraphImporter<String, DefaultEdge> imp = new Neo4JGraphImporter<String, DefaultEdge>()
            .query(
                "MATCH (s:type {refId:$refId,type:'type'}) -[:uses]-> (t:type {type:'type'}) RETURN s.fullname AS source, t.fullname AS target",
                Parameters.params(
                    "refId", "a885add5"
                ))
        ;
        imp.importGraph(g, new File("config/neo4j.properties"));

        GraphExporter<String, DefaultEdge>  exp = new DOTExporter<>();
        exp.exportGraph(g, new File("graph.dot"));

        Graphs.describe(g);

    }
}
