package il.ac.idc.jdt.algorithm;

import il.ac.idc.jdt.DelaunayTriangulation;
import il.ac.idc.jdt.model.Triangle;
import il.ac.idc.jdt.model.Vertex;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Triangulation extends DelaunayTriangulation {

    public void addVertex(int id, double x, double y) {
        this.insertPoint(new Vertex(id, x, y));
    }

    public void triangulate() {

    }

    public List<Triangle> getTriangles() {
        List<Triangle> triangles = new ArrayList<>();

        Iterator<il.ac.idc.jdt.Triangle> it = this.trianglesIterator();
        while (it.hasNext()) {
            triangles.add(new Triangle(it.next()));
        }

        return triangles;
    }
}
