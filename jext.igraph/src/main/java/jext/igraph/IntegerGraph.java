package jext.igraph;

import jext.igraph.impl.IGraphType;
import jext.jgrapht.graph.BaseGraph;
import org.jgrapht.graph.DefaultGraphType;

import static jext.igraph.IGraphLibrary.check;

public class IntegerGraph extends BaseGraph<Integer, IntegerEdge> {

    public static IntegerGraph create(int n, boolean directed) {
        IGraphType igraph = new IGraphType();

        check(IGraphLibrary.empty(igraph, n, directed));

        return new IntegerGraph(igraph);
    }

    public static IntegerEdge createEdge(Integer v1, Integer v2) {
        return new IntegerEdge(null, -1, v1, v2);
    }

    // ----------------------------------------------------------------------
    // IGraph handler
    // ----------------------------------------------------------------------

    private IGraphType g;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    IntegerGraph(IGraphType g) {
        this.g = g;

        DefaultGraphType.Builder gtb = new DefaultGraphType.Builder();

        if (IGraphLibrary.is_directed(g))
            gtb.directed();
        else
            gtb.undirected();


        this.type = gtb.build();
    }

    protected void finalize() {
        if (g != null) {
            IGraphLibrary.destroy(g);
            g = null;
        }
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public int order() {
        // num of vertices
        return IGraphLibrary.vcount(g);
    }

    public int size() {
        // num of edges
        return IGraphLibrary.ecount(g);
    }

    // ----------------------------------------------------------------------
    // Vertices
    // ----------------------------------------------------------------------

    // ----------------------------------------------------------------------
    // Edges
    // ----------------------------------------------------------------------

    @Override
    public IntegerEdge addEdge(Integer u, Integer v) {
        int eid = IGraphLibrary.add_edge(g, u, v);
        return new IntegerEdge(g, eid, u, v);
    }

}
