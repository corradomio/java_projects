package org.hls.examples;

import jext.igraph.IntegerGraph;
import jext.jgrapht.edges.Edge;

public class App {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println();

        // byte[] name = new byte[20];
        // int[] major = new int[1];
        // int[] minor = new int[1];
        // int[] patch = new int[1];
        // byte[] buffer = new byte[32];

        // IGraphLibrary.version(name, major, minor, patch);
        // System.out.printf("Version: %d-%d.%d.%d\n", name[0], major[0], minor[0], patch[0]);

        IntegerGraph g = IntegerGraph.create(10, false);

        Edge<Integer> e = g.addEdge(0, 1);

        System.out.printf("order: %d, size: %d, type; %s\n", g.order(), g.size(), g.getType().toString());
        System.out.printf("edge: %s\n", e);

        System.out.println("Done");


    }
}
