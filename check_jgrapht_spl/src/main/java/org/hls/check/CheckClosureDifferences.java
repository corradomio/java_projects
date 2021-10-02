package org.hls.check;

import jext.jgrapht.Graphs;
import jext.jgrapht.alg.closure.CGDifferences;
import jext.jgrapht.alg.closure.ClosuresGraph;
import jext.jgrapht.generate.RandomCavemanGraphGenerator;
import jext.jgrapht.util.Utils;
import jext.logging.Logger;
import jext.util.concurrent.Parallel;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class CheckClosureDifferences {

    public static void main(String[] args) {
        Logger.configure();
        Parallel.setup();

        RandomCavemanGraphGenerator<Integer, DefaultEdge> rcgg = new RandomCavemanGraphGenerator<>(
            1000,       // v
            10000,       // e
            10,     // c
            .001,
            .9
        );

        Graph<Integer, DefaultEdge> g = Graphs.newGraph(Integer.class, DefaultEdge.class, true);

        rcgg.generateGraph(g, null);
        Graphs.describe(g);

        ClosuresGraph<Integer, DefaultEdge> cg1 = new ClosuresGraph<>(g);
        cg1.compose();

        g = Graphs.clone(g);
        Graphs.removeVertices(g, 1, 2, 3, 4);
        Graphs.addEdges(g, 1001, 101, 1002, 102, 1003, 103);
        Graphs.describe(g);

        ClosuresGraph<Integer, DefaultEdge> cg2 = new ClosuresGraph<>(g);
        cg2.compose();

        CGDifferences<Integer> diff = cg2.difference(cg1);
        diff.dump();
    }
}
