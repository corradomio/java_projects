package com.company;

import jext.jgrapht.util.GraphDump;
import jext.jgrapht.alg.color.ColoringTests;
import jext.jgrapht.alg.color.ParallelMCMCBoloring;
import jext.jgrapht.nio.adjacent.AdjacentImporter;
import jext.jgrapht.nio.adjacent.FileImporter;
import jext.logging.Logger;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.nio.dot.DOTImporter;
import org.jgrapht.util.SupplierUtil;

import java.io.File;

public class CheckMCColoring {

    // private static void createGraph(Graph<String, DefaultEdge> g) {
    //     System.out.println("Generating graph ...");
    //     int n = 10000;
    //     int m = (int)(n*n*0.10);
    //     new RandomGraphGenerator<String, DefaultEdge>(n, m).generateGraph(g);
    //     System.out.println("Done");
    // }

    public static void main(String[] args) {

        Logger.configure();

        Graph<String, DefaultWeightedEdge> g;

        // ------------------------------------------------------------------
        System.out.println("Loading graph");

        new AdjacentImporter<String, DefaultWeightedEdge>()
                // .importGraph(g, new File("email-Eu-core.txt"))
                // .importGraph(g,new File("roadNet-CA.txt"))
                // .importGraph(g, new File("Slashdot0902.zip"))
                // .importGraph(g, new File("sx-stackoverflow-a2q.txt"))
                // .importGraph(g, new File("sx-stackoverflow-a2q.txt.gz"))
                // .skipLines(2).importGraph(g, new File("sc-shipsec5.zip"))
                // .skipLines(2).importGraph(g, new File("sc-pwtk.zip"))
                // .skipLines(2).importGraph(g, new File("ca-coauthors-dblp.zip"))
                // .skipLines(2).importGraph(g, new File("ca-hollywood-2009.zip"))
                // .comment("%").importGraph(g, new File("actor-collaboration/out.actor-collaboration"))
                .weighted(true)
                .skipLines(2)
                //.importGraph(g, new File("D:\\Projects\\java\\check_jgrapht\\weighted\\out.opsahl-usairport"));
        ;

        g = new SimpleGraph<>(
                SupplierUtil.createStringSupplier(),
                SupplierUtil.createDefaultWeightedEdgeSupplier(), true);

        new FileImporter<>(
                // new AdjacentImporter<String, DefaultWeightedEdge>()
                // .weighted(true)
                // .skipLines(2)
                new DOTImporter<String, DefaultWeightedEdge>()
        ).importGraph(g,
                //new File("D:\\Projects\\java\\check_jgrapht\\weighted\\out.opsahl-usairport")
                new File("relaxcave.dot")
        );


        // createGraph(g);

        // ------------------------------------------------------------------

        // System.out.println("Selecting the largest component");
        // g = new GraphComponents<>(g).getComponents(EdgeType.UNDIRECTED).getLargestSubgraph();

        // ------------------------------------------------------------------
        System.out.println("Start coloring");

        GraphDump.printGraphInfo(g);
        GraphDump.printDegreeStatistics(g);

        VertexColoringAlgorithm<String> a  =
                new ParallelMCMCBoloring<>(g)
                // new WeightedBMCColoring<>(g)
                ;

        VertexColoringAlgorithm.Coloring<String> coloring = a.getColoring();

        System.out.printf("  n of colors: %d == %d\n", coloring.getNumberColors(), ColoringTests.countColors(coloring));
        System.out.printf("  n of conflicts: %d\n", ColoringTests.countConflicts(g, coloring));
        // System.out.printf("  n of dcolors: %d\n", ColoringTests.countDominantColors(
        //         ((VertexInfos<String>)a).getVertexInfos()
        // ));

        // System.out.println("  " + new HashSet<>(coloring.getColors().values()).toString());

        System.out.println("Coloring done");
    }
}
