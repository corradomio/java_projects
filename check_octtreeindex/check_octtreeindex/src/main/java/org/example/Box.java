package org.example;

import java.util.ArrayList;
import java.util.List;

public class Box {

    private final Coords lower, upper, center;
    private final List<Coords> points = new ArrayList<>();
    private final Box parent;

    Box(Box p, Coords l, Coords u) {
        parent = p;
        lower = l;
        upper = u;
        center = Coords.of((l.x+u.x)/2, (l.y+u.y)/2, (l.z+u.z)/2);
    }
}
