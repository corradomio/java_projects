package jext.geometry.delaunay.model;

import com.google.common.collect.Sets;

import java.util.Set;

public class Vertex extends Vector {
	private final Set<Vertex> neighborVertices = Sets.newLinkedHashSet();
	private final Set<Triangle> neighborTriangles = Sets.newLinkedHashSet();
	private Integer hilbertIndex;
	private final int vertexIndex;

	public Vertex(double x, double y) {
		super(x, y);
		vertexIndex = 0;
	}

	public Vertex(int id, double x, double y) {
		super(x, y);
		vertexIndex = id;
	}

	public Set<Vertex> getNeighborVertices() {
		return neighborVertices;
	}
	
	public Set<Triangle> getNeighborTriangles() {
		return neighborTriangles;
	}

	public void addTriangle(Triangle tri) {
		neighborTriangles.add(tri);
		neighborVertices.addAll(tri.getVertices());
		neighborVertices.remove(this);
		for (Triangle t : neighborTriangles) {
			t.invalidateNeighbors();
		}
	}

	public void removeTriangle(Triangle tri) {
		neighborTriangles.remove(tri);
		neighborVertices.removeAll(tri.getVertices());
		for (Triangle t : neighborTriangles) {
			t.invalidateNeighbors();
		}
	}

	public void setHilbertIndex(Integer hilbertIndex) {
		this.hilbertIndex = hilbertIndex;
	}

	public Integer getHilbertIndex() {
		return hilbertIndex;
	}

	public int getVertexIndex() {
		return vertexIndex;
	}
}
