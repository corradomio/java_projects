package jext.igraph;

import jext.igraph.impl.IGraphType;
import jext.jgrapht.edges.Edge;
import org.jgrapht.graph.DefaultEdge;

import java.lang.reflect.Field;

import static jext.igraph.IGraphLibrary.check;

public class IntegerEdge extends DefaultEdge implements Edge<Integer> {
    private static Field SOURCE_FIELD;
    private static Field TARGET_FIELD;

    static {
        try {
            Class INTRUSIVE_EDGE = Class.forName("org.jgrapht.graph.IntrusiveEdge");
            SOURCE_FIELD = INTRUSIVE_EDGE.getDeclaredField("source");
            TARGET_FIELD = INTRUSIVE_EDGE.getDeclaredField("target");
            SOURCE_FIELD.setAccessible(true);
            TARGET_FIELD.setAccessible(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private IGraphType g;
    private int eid;

    // ----------------------------------------------------------------------

    public IntegerEdge(IGraphType g, int eid, Integer u, Integer v) {
        this.g = g;
        this.eid = eid;
        setVertices(u, v);

    }

    public IntegerEdge(IGraphType g, int eid) {
        this.g = g;
        this.eid = eid;
    }

    // ----------------------------------------------------------------------

    @Override
    public Integer getSource() {
        getVertices();
        return (Integer) super.getSource();
    }

    @Override
    public Integer getTarget() {
        getVertices();
        return (Integer) super.getTarget();
    }

    // ----------------------------------------------------------------------

    private void getVertices() {
        if (super.getSource() != null) return;

        int[] u = new int[1];
        int[] v = new int[1];

        check(IGraphLibrary.edge(g, eid, u, v));

        setVertices(u[0], v[0]);
    }

    private void setVertices(Integer u, Integer v) {
        try {
            SOURCE_FIELD.set(this, u);
            TARGET_FIELD.set(this, v);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // ----------------------------------------------------------------------

    @Override
    public String toString() {
        return String.format("(%d,%d)", getSource(), getTarget());
    }
}
