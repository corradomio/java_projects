package org.hls.check;

import jext.jgrapht.Graphs;
import jext.jgrapht.util.GraphDump;
import org.jgrapht.Graph;
import org.jgrapht.generate.ScaleFreeGraphGenerator;
import org.jgrapht.graph.DefaultEdge;

public class App {

    public static void main(String[] args) {
        Graph<Integer, DefaultEdge> g = Graphs.newGraph(true, false);
        new ScaleFreeGraphGenerator<Integer, DefaultEdge>(50000)
            .generateGraph(g);

        GraphDump.printGraphInfo(g);
    }
}
