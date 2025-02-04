package il.ac.idc.jdt.model;

import il.ac.idc.jdt.Point;

public class Vertex extends Point {

    private int id;

    public Vertex(int id, double x, double y) {
        super(x, y);
        this.id = id;
    }

    public int getVertexIndex() {
        return id;
    }
}
