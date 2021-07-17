package jext.jgrapht.igraph;

import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultGraphType;
import org.jgrapht.util.SupplierUtil;

public class GraphIGraph extends AbstractBaseGraph<Double, DefaultEdge> {

    private igraph_lib l = igraph_lib.INSTANCE;
    private igraph_t[] g = new igraph_t[1];

    public GraphIGraph() {
        super(
            SupplierUtil.createSupplier(Double.class),
            SupplierUtil.createDefaultEdgeSupplier(),
            new DefaultGraphType.Builder()
                .undirected().allowMultipleEdges(false).allowSelfLoops(false).weighted(false)
                .build());
    }

    public String version() {
        String[] version = new String[1];
        int[] major = new int[1];
        int[] minor = new int[1];
        int[] subminor = new int[1];

        l.igraph_version(version, major, minor, subminor);
        return version[0];
    }

}
