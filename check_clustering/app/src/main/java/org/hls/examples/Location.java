package org.hls.examples;

import jext.geo.LatLonUtils;

public class Location {

    public final String key;
    public final String field;
    public final double longitude;
    public final double latitude;

    public Location(String[] parts) {
        key = parts[0];
        field = parts[1];

        String[] coords = parts[2].split("\\s");
        longitude = Double.parseDouble(coords[0]);
        latitude = Double.parseDouble(coords[1]);
    }

    public double angularDistance(Location that) {
        return LatLonUtils.angularDistance(this.latitude, this.longitude, that.latitude, that.longitude);
    }

    public double haversineDistance(Location that) {
        return LatLonUtils.haversineDistance(this.latitude, this.longitude, that.latitude, that.longitude);
    }

    private static double sq(double x){ return x*x;}
}
