package org.hls.check;

import jext.jgrapht.DirectedEdge;
import jext.jgrapht.Graphs;
import jext.jgrapht.alg.closure.GraphClosures;
import jext.jgrapht.generate.RandomCavemanGraphGenerator;
import jext.jgrapht.util.Utils;
import jext.logging.Logger;
import jext.util.concurrent.Parallel;
import org.jgrapht.Graph;
import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.nio.dot.DOTExporter;
import org.jgrapht.nio.dot.DOTImporter;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class CheckClosures {

    static DOTExporter<Integer, DirectedEdge<Integer>> newExporter() {
        DOTExporter<Integer, DirectedEdge<Integer>> exporter = new DOTExporter<>();
        exporter.setVertexAttributeProvider((v) -> {
            Map<String, Attribute> map = new LinkedHashMap<>();
            map.put("label", DefaultAttribute.createAttribute(v.toString()));
            return map;
        });
        return exporter;
    }

    static DOTImporter<Integer, DirectedEdge<Integer>> newImporter() {
        DOTImporter<Integer, DirectedEdge<Integer>> importer = new DOTImporter<>();
        // importer.addVertexAttributeConsumer((v) -> {
        //     Map<String, Attribute> map = new LinkedHashMap<>();
        //     map.put("label", DefaultAttribute.createAttribute(v.toString()));
        //     return map;
        // });
        return importer;
    }

    public static void main(String[] args) {

        Logger.configure();

        // --

        Graph<Integer, DirectedEdge<Integer>> g = (Graph)Graphs.newGraph(Integer.class, DirectedEdge.class);

        // --

        //RandomGraphGenerator<String, DirectedEdge> gg = new RandomGraphGenerator();
        RandomCavemanGraphGenerator<Integer, DirectedEdge<Integer>> gg = new RandomCavemanGraphGenerator<>(
            100,
            100,
            3,
            0.1,
            0.9,
            false);
        gg.generateGraph(g);

        // newImporter().importGraph(g, new File("graph.dot"));

        // g = (Graph)Graphs.newGraph(Integer.class, DirectedEdge.class);
        // Graphs.addPath(g, 1,2,3,4,5,6);
        // Graphs.addPath(g, 1,3,6);

        newExporter().exportGraph(g, new File("graph.dot"));

        // -----------------------------------------------------------------------

        GraphClosures<Integer, DirectedEdge<Integer>> gc = new GraphClosures<>(g);

        gc.computeClosures();
        gc.collectSingletons();
        gc.removeDuplicates();

        gc.createClosureGraph();
        gc.computeClosureDependencies();

        newExporter().exportGraph(gc.getClosureGraph(), new File("notransitive.dot"));

        // --

        gc.transitiveReduction();

        newExporter().exportGraph(gc.getClosureGraph(), new File("closures.dot"));

        // --

        gc.pruneLeaves();

        newExporter().exportGraph(gc.getClosureGraph(), new File("leavesPruned.dot"));

        // --

        gc.mergeChains();

        newExporter().exportGraph(gc.getClosureGraph(), new File("chainsMerged.dot"));

        // ---------------------------------------------------------
        //
        // -----------------------------------------------------------------------

        g = gc.getClosureGraph();

        gc = new GraphClosures<>(g);

        gc.computeClosures();
        gc.collectSingletons();
        gc.removeDuplicates();

        gc.createClosureGraph();
        gc.computeClosureDependencies();

        newExporter().exportGraph(gc.getClosureGraph(), new File("notransitive1.dot"));

        // --

        gc.transitiveReduction();

        newExporter().exportGraph(gc.getClosureGraph(), new File("closures1.dot"));

        // --

        gc.pruneLeaves();

        newExporter().exportGraph(gc.getClosureGraph(), new File("leavesPruned1.dot"));

        // --

        gc.mergeChains();

        newExporter().exportGraph(gc.getClosureGraph(), new File("chainsMerged1.dot"));

        // ---------------------------------------------------------



        System.out.println("done");

        Parallel.shutdown();
    }
}
