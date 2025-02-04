package org.hls.check.impexp;

import jext.jgrapht.edges.DirectedEdge;
import jext.jgrapht.Graphs;
import jext.jgrapht.nio.csv.VECSVGraphExporter;
import jext.jgrapht.nio.odem.OdemImporter;
import org.jgrapht.Graph;

import java.io.File;

public class CheckOdem {

    public static void main(String[] args) {
        Graph<String, DirectedEdge> g = Graphs.newGraph(String.class, DirectedEdge.class);
        OdemImporter<String, DirectedEdge> imp = new  OdemImporter<>();
        imp.importGraph(g, new File(
            //"orientdb.odem"
            // "ant.odem"
            //"ant-only.odem"
            "D:\\Projects\\jianyi\\label_propagation\\code4article\\data\\ant-1.6.5_dependencies.odem"
        ));

        VECSVGraphExporter<String, DirectedEdge> exp = new VECSVGraphExporter<>();
        exp.exportGraph(g, new File(
            //"orientdb.csv"
            // "ant.csv"
            // "ant-only.csv"
            "D:\\Projects\\jianyi\\label_propagation\\code4article\\data\\ant-1.6.5.csv"
        ));
    }
}
