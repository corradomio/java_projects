package il.ac.idc.jdt.model;

import java.util.List;

public class Triangle {

    private il.ac.idc.jdt.Triangle tri;

    public Triangle(il.ac.idc.jdt.Triangle tri) {
        this.tri = tri;
    }

    public List<Vertex> getVertices() {
        return (List<Vertex>)tri.getPoints();
    }
}
