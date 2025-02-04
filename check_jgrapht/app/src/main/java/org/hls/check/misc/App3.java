package org.hls.check.misc;

import jext.jgrapht.edges.Edge;
import jext.jgrapht.Graphs;
import jext.jgrapht.generate.RandomGraphGenerator;
import jext.jgrapht.util.GraphDescribe;
import jext.logging.Logger;
import org.jgrapht.Graph;

public class App3 {

    public static void main(String[] args) {
        Logger.configure();

        Graph<Long, Edge> g = Graphs.newGraph(true, false, Long.class, Edge.class);
        new RandomGraphGenerator<Long, Edge>(50000, 50000)
            .generateGraph(g);

        GraphDescribe.printGraphInfo(g);
        GraphDescribe.describe(g);
    }
}
