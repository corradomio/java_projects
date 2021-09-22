package org.hls.check;

import jext.jgrapht.DirectedEdge;
import jext.jgrapht.Graphs;
import jext.jgrapht.alg.closure.GraphClosures;
import jext.jgrapht.generate.RandomCavemanGraphGenerator;
import org.jgrapht.Graph;
import org.jgrapht.nio.GraphExporter;
import org.jgrapht.nio.dot.DOTExporter;

import java.io.File;

public class CheckClosures {

    public static void main(String[] args) {

        Graph<String, DirectedEdge> g = Graphs.newGraph(String.class, DirectedEdge.class);

        //RandomGraphGenerator<String, DirectedEdge> gg = new RandomGraphGenerator();
        RandomCavemanGraphGenerator gg = new RandomCavemanGraphGenerator(
            100,
            1000,
            5,
            0.1,
            0.9,
            false);
        gg.generateGraph(g);

        GraphExporter<String, DirectedEdge> ge = new DOTExporter<>();
        ge.exportGraph(g, new File("test.dot"));

        GraphClosures<String, DirectedEdge> gc = new GraphClosures<>(g);

        gc.compute();
        gc.collectSingletons();
        gc.removeDuplicates();

        System.out.println(gc.getSingletons().size());

        System.out.println(g.getClass());

    }
}
