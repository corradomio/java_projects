package org.hls.check;

import jext.jgrapht.edges.Edge;
import jext.jgrapht.Graphs;
import jext.jgrapht.util.GraphDescribe;
import jext.logging.Logger;
import org.jgrapht.Graph;
import org.jgrapht.generate.ScaleFreeGraphGenerator;

public class App2 {

    public static void main(String[] args) {
        Logger.configure();

        Graph<Long, Edge> g = Graphs.newGraph(true, false, Long.class, Edge.class);
        new ScaleFreeGraphGenerator<Long, Edge>(50000)
            .generateGraph(g);

        GraphDescribe.printGraphInfo(g);
        GraphDescribe.describe(g);
    }
}
