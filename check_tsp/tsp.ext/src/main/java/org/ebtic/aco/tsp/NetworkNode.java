package org.ebtic.aco.tsp;

public class NetworkNode {

    private String id;
    private Coordinate location = new Coordinate();

    public Coordinate getLocation() {
        return location;
    }

    public String getId() {
        return id;
    }
}
