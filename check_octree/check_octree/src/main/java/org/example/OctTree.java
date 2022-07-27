package org.example;

import java.util.ArrayList;
import java.util.List;

class Box {

    private final Box parent;
    private final Coords lower, upper, center;
    private final int n;
    private Box[] octants;
    private final List<Coords> points = new ArrayList<>();

    Box(Box parent, Coords lower, Coords upper, int n) {
        this.parent = parent;
        this.lower = lower;
        this.upper = upper;
        this.center = Coords.of((lower.x + upper.x)/2, (lower.y + upper.y)/2, (lower.z + upper.z)/2);
        this.n = n;
    }

    public void add(Coords pt) {
        if (this.octants != null)
            insertIntoOctant(pt);
        else
            insertHere(pt);
    }

    private void insertHere(Coords pt) {
        this.points.add(pt);

        if (this.points.size() > this.n)
            createOctants();
    }

    private void insertIntoOctant(Coords pt) {
        int octant = 0;
        octant |= pt.x < center.x ? 0 : 1;
        octant |= pt.y < center.y ? 0 : 2;
        octant |= pt.z < center.z ? 0 : 4;

        this.octants[octant].add(pt);
    }

    private void createOctants() {
        float lx = lower.x;
        float ly = lower.y;
        float lz = lower.z;

        float cx = center.x;
        float cy = center.y;
        float cz = center.z;

        float ux = upper.x;
        float uy = upper.y;
        float uz = upper.z;


        this.octants = new Box[8];
    }
}

public class OctTree extends Box {

    public static OctTree create(Coords lower, Coords upper, int n) {
        return new OctTree(lower, upper, n);
    }

    private Box root;

    private OctTree(Coords lower, Coords upper, int n) {
        super(null, lower, upper, n);
        this.root = root;
    }

}
