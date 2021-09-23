package org.hls.check;

import jext.jgrapht.DirectedEdge;
import jext.jgrapht.Graphs;
import jext.jgrapht.alg.closure.GraphClosures;
import jext.jgrapht.generate.RandomCavemanGraphGenerator;
import jext.jgrapht.util.Utils;
import jext.logging.Logger;
import jext.util.concurrent.Parallel;
import org.jgrapht.Graph;
import org.jgrapht.nio.GraphExporter;
import org.jgrapht.nio.dot.DOTExporter;

import java.io.File;
import java.util.Set;

public class CheckClosures {

    public static void main(String[] args) {

        Logger.configure();

        Set<Integer> s1 = Utils.asSet(1,2,3);
        Set<Integer> s2 = Utils.asSet(1,2);

        System.out.println(Utils.isSuperset(s1,s2));
        System.out.println(Utils.isSubset(s1,s2));
        System.out.println(Utils.isSameSet(s1,s2));

        Graph<String, DirectedEdge> g = Graphs.newGraph(String.class, DirectedEdge.class);

        //RandomGraphGenerator<String, DirectedEdge> gg = new RandomGraphGenerator();
        RandomCavemanGraphGenerator gg = new RandomCavemanGraphGenerator(
            100,
            100,
            5,
            0.1,
            0.9,
            false);
        gg.generateGraph(g);

        GraphExporter<String, DirectedEdge> ge = new DOTExporter<>();
        ge.exportGraph(g, new File("graph.dot"));

        GraphClosures<String, DirectedEdge> gc = new GraphClosures<>(g);

        gc.computeClosures();
        gc.collectSingletons();
        gc.removeDuplicates();

        gc.createClosureGraph();
        gc.computeClosureDependencies();
        gc.transitiveReduction();
        gc.mergeChains();

        ge = new DOTExporter<>();
        ge.exportGraph(gc.getClosureGraph(), new File("closure.dot"));

        System.out.println(gc.getSingletons().size());

        System.out.println(g.getClass());
        System.out.println("done");

        Parallel.shutdown();
    }
}
