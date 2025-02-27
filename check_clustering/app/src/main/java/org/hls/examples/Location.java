package org.hls.examples;

import jext.geo.LatLonUtils;

public class Location {

    public int index;
    public final String key;
    public final String field;
    public final double longitude;
    public final double latitude;

    public Location(String[] parts) {
        this.key = parts[0];
        this.field = parts[1];

        String[] coords = parts[2].split("\\s");
        this.longitude = Double.parseDouble(coords[0]);
        this.latitude = Double.parseDouble(coords[1]);
    }

    public double angularDistance(Location that) {
        return LatLonUtils.angularDistance(this.latitude, this.longitude, that.latitude, that.longitude);
    }

    public double haversineDistance(Location that) {
        return LatLonUtils.haversineDistance(this.latitude, this.longitude, that.latitude, that.longitude);
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Location that = (Location) obj;
        return this.key.equals(that.key);
    }
}
