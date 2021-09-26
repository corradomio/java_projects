package org.hls.check;

import jext.jgrapht.DirectedEdge;
import jext.jgrapht.Graphs;
import jext.jgrapht.alg.closure.ClosuresGraph;
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

public class App {

    static GraphExporter<Integer, DirectedEdge<Integer>> newExporter() {
        return newExporter("dot");
    }

    static GraphExporter<Integer, DirectedEdge<Integer>> newExporter(String type) {
        GraphExporter<Integer, DirectedEdge<Integer>> exporter = null;

        if ("dot".equals(type)) {
            exporter = new DOTExporter<>();
            ((DOTExporter<Integer, DirectedEdge<Integer>>)exporter).setVertexAttributeProvider((v) -> {
                Map<String, Attribute> map = new LinkedHashMap<>();
                map.put("label", DefaultAttribute.createAttribute(v.toString()));
                return map;
            });
        }
        // if ("json".equals(type)) {
        //     exporter = new JSONGraphExporter<>();
        //     ((JSONGraphExporter<Integer, DirectedEdge<Integer>>)exporter).setVertexAttributeProvider((v) -> {
        //         Map<String, Attribute> map = new LinkedHashMap<>();
        //         map.put("label", DefaultAttribute.createAttribute(v.toString()));
        //         return map;
        //     });
        // }
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

        Graph<Integer, DirectedEdge<Integer>> g = (Graph) Graphs.newGraph(Integer.class, DirectedEdge.class);

        // --

        // newImporter().importGraph(g, new File("graph.dot"));

        g = (Graph)Graphs.newGraph(Integer.class, DirectedEdge.class);
        Graphs.addVertices(g, 1,2,3,4,5,6,7,8,9, 10,11, 12);
        Graphs.addEdges(g, 1,2, 2,10, 10,11, 12,10, 1,3, 4,5, 4,6);

        newExporter().exportGraph(g, new File("graph.dot"));
        // newExporter("json").exportGraph(g, new File("graph.json"));

        // -----------------------------------------------------------------------

        ClosuresGraph<Integer, DirectedEdge<Integer>> cg = new ClosuresGraph<>(g);

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

        cg.pruneLeaves();

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

        cg.pruneLeaves();

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
