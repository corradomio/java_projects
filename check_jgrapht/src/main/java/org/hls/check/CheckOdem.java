package org.hls.check;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import jext.jgrapht.DirectedEdge;
import jext.jgrapht.Graphs;
import jext.jgrapht.nio.csv.VECSVExporter;
import jext.jgrapht.nio.odem.OdemImporter;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.io.File;

public class CheckOdem {

    public static void main(String[] args) {
        Graph<String, DirectedEdge> g = Graphs.newGraph(String.class, DirectedEdge.class, true);
        OdemImporter<String, DirectedEdge> imp = new  OdemImporter<>();
        imp.importGraph(g, new File(
            //"orientdb.odem"
            // "ant.odem"
            //"ant-only.odem"
            "D:\\Projects\\jianyi\\clustering\\ant-1.6.0.odem"
        ));

        VECSVExporter<String, DirectedEdge> exp = new VECSVExporter<>();
        exp.exportGraph(g, new File(
            //"orientdb.csv"
            // "ant.csv"
            // "ant-only.csv"
            "ant-1.6.0.csv"
        ));
    }
}
