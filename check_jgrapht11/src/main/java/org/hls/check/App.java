package org.hls.check;

import jext.jgrapht.Graphs;
import jext.jgrapht.nio.neo4j.Neo4JGraphImporter;
import jext.jgrapht.util.GraphDump;
import org.jgrapht.Graph;
import org.jgrapht.generate.ScaleFreeGraphGenerator;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.nio.GraphImporter;

public class App {

    public static void main(String[] args) {
        Graph<String, DefaultEdge> g = Graphs.newGraph(true, false);
        // new ScaleFreeGraphGenerator<Integer, DefaultEdge>(50000)
        //     .generateGraph(g);
        //
        // GraphDump.printGraphInfo(g);

        GraphImporter<String, DefaultEdge> n4jimp = new Neo4JGraphImporter<>();

    }
}
