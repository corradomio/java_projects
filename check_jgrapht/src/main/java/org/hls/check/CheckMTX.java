package org.hls.check;

import jext.jgrapht.Graphs;
import jext.jgrapht.nio.mtx.MTXImporter;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.nio.dot.DOTExporter;

import java.io.File;

public class CheckMTX {

    public static void main(String[] args) {
        System.out.println((int)'\n');
        System.out.println((int)'\r');
        Graph<Integer, DefaultEdge> g = Graphs.newGraph(Integer.class, DefaultEdge.class);

        MTXImporter<Integer, DefaultEdge> imp = new MTXImporter<>();
        imp.importGraph(g, new File("lesmis.mtx"));

        DOTExporter<Integer, DefaultEdge> exp = new DOTExporter<>();
        exp.exportGraph(g, new File("lesmis.dot"));
    }
}
