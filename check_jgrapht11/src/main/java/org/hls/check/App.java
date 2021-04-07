package org.hls.check;

import jext.jgrapht.Graphs;
import jext.jgrapht.nio.neo4j.Neo4JGraphImporter;
import jext.logging.Logger;
import jext.util.MapUtils;
import jext.util.Parameters;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.nio.GraphImporter;

import java.io.File;

public class App {

    public static void main(String[] args) {
        Logger.configure();
        Graph<String, DefaultEdge> g = Graphs.newGraph(true, false);
        // new ScaleFreeGraphGenerator<Integer, DefaultEdge>(50000)
        //     .generateGraph(g);
        //
        // GraphDump.printGraphInfo(g);

        GraphImporter<String, DefaultEdge> n4jimp = new Neo4JGraphImporter<String, DefaultEdge>()
            .query("MATCH (s:type {refId:$refId,type:'type'}) -[:uses]-> (t:type {type:'type'}) RETURN s AS source, t AS target",
                Parameters.params(
                    "refId", "abe112c1"
                ))
            // .directed()
            // .from(null, MapUtils.asMap(
            //     "type", "type",
            //     "refId", "abe112c1"
            // ))
            // .to("type", MapUtils.asMap(
            //     "type", "type",
            //     "refId", "abe112c1"
            // ))
            // .edge("uses", Collections.emptyMap())
        ;
        n4jimp.importGraph(g, new File("config/neo4j.properties"));

        Graphs.describe(g);

    }
}
