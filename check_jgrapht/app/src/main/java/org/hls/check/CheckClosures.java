package org.hls.check;

import jext.jgrapht.edges.DirectedEdge;
import jext.jgrapht.Graphs;
import jext.jgrapht.alg.closure.ClosuresGraph;
import jext.jgrapht.generate.RandomCavemanGraphGenerator;
import jext.jgrapht.nio.jsn.JSONGraphExporter;
import jext.logging.Logger;
import jext.util.concurrent.Parallel;
import org.jgrapht.Graph;
import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.nio.GraphExporter;
import org.jgrapht.nio.dot.DOTExporter;
import org.jgrapht.nio.dot.DOTImporter;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class CheckClosures {

    static GraphExporter<Integer, DirectedEdge> newExporter() {
        return newExporter("dot");
    }

    static GraphExporter<Integer, DirectedEdge> newExporter(String type) {
        GraphExporter<Integer, DirectedEdge> exporter = null;

        if ("dot".equals(type)) {
            exporter = new DOTExporter<>();
            ((DOTExporter<Integer, DirectedEdge>)exporter).setVertexAttributeProvider((v) -> {
                Map<String, Attribute> map = new LinkedHashMap<>();
                map.put("label", DefaultAttribute.createAttribute(v.toString()));
                return map;
            });
        }
        if ("json".equals(type)) {
            exporter = new JSONGraphExporter<>();
            ((JSONGraphExporter<Integer, DirectedEdge>)exporter).setVertexAttributeProvider((v) -> {
                Map<String, Attribute> map = new LinkedHashMap<>();
                map.put("label", DefaultAttribute.createAttribute(v.toString()));
                return map;
            });
        }
        return exporter;
    }


    static DOTImporter<Integer, DirectedEdge> newImporter() {
        DOTImporter<Integer, DirectedEdge> importer = new DOTImporter<>();
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

        Graph<Integer, DirectedEdge> g = Graphs.newGraph(Integer.class, DirectedEdge.class);

        // --

        //RandomGraphGenerator<String, DirectedEdge> gg = new RandomGraphGenerator();
        RandomCavemanGraphGenerator<Integer, DirectedEdge> gg = new RandomCavemanGraphGenerator<>(
            300,
            300,
            10,
            0.1,
            0.9);
        gg.generateGraph(g);

        // newImporter().importGraph(g, new File("graph.dot"));

        g = Graphs.newGraph(Integer.class, DirectedEdge.class);
        Graphs.addVertices(g, 1,2,3,4,5,6,7);
        Graphs.addEdges(g, 1,2, 1,3, 4,5, 4,6);

        newExporter().exportGraph(g, new File("graph.dot"));
        newExporter("json").exportGraph(g, new File("graph.json"));

        // -----------------------------------------------------------------------

        ClosuresGraph<Integer, DirectedEdge> cg = new ClosuresGraph<>(g);

        cg.computeClosures();
        cg.collectSingletons();
        cg.removeDuplicates();

        cg.createClosureGraph();
        cg.computeClosureDependencies();

        newExporter().exportGraph(cg.getClosureGraph(), new File("notransitive.dot"));

        // --

        cg.transitiveReduction();

        newExporter().exportGraph(cg.getClosureGraph(), new File("closures.dot"));

        // --

        cg.removeLeaves();

        newExporter().exportGraph(cg.getClosureGraph(), new File("leavesPruned.dot"));

        // --

        cg.collapseChains();
        cg.isDAG();

        newExporter().exportGraph(cg.getClosureGraph(), new File("chainsCollapsed.dot"));

        // ---------------------------------------------------------
        //
        // -----------------------------------------------------------------------

        g = cg.getClosureGraph();

        cg = new ClosuresGraph<>(g);

        cg.computeClosures();
        cg.collectSingletons();
        cg.removeDuplicates();

        cg.createClosureGraph();
        cg.computeClosureDependencies();

        newExporter().exportGraph(cg.getClosureGraph(), new File("notransitive1.dot"));

        // --

        cg.transitiveReduction();

        newExporter().exportGraph(cg.getClosureGraph(), new File("closures1.dot"));

        // --

        cg.removeLeaves();

        newExporter().exportGraph(cg.getClosureGraph(), new File("leavesPruned1.dot"));

        // --

        cg.collapseChains();
        cg.isDAG();

        newExporter().exportGraph(cg.getClosureGraph(), new File("chainsCollapsed1.dot"));

        // ---------------------------------------------------------



        System.out.println("done");

        Parallel.shutdown();
    }
}
