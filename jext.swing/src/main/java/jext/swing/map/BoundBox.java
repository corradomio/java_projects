package jext.swing.map;

import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;

import java.util.List;

import static java.lang.Math.abs;

public class BoundBox {

    public double minLat = +Double.MAX_VALUE;
    public double minLon = +Double.MAX_VALUE;
    public double maxLat = -Double.MAX_VALUE;
    public double maxLon = -Double.MAX_VALUE;

    // private double sumLat = 0;
    // private double sumLon = 0;
    // private int count = 0;

    public void update(double lat, double lon) {
        if (lat < minLat) minLat = lat;
        if (lat > maxLat) maxLat = lat;
        if (lon < minLon) minLon = lon;
        if (lon > maxLon) maxLon = lon;

        // sumLat += lat;
        // sumLon += lon;
        // count++;
    }

    public void update(List<Marker> markers) {
        markers.forEach(m -> {
            Location l = m.getLocation();
            update(l.getLat(), l.getLon());
        });
    }

    public Location center() {
        // if (count > 0)
        //     return new Location(sumLat/count, sumLon/count);
        // else
        //     return new Location(sumLat, sumLon);
        return new Location((minLat+maxLat)/2, (minLon+maxLon)/2);
    }

    public int zoomLevel() {
        double deltaLon = abs(maxLon - minLon);
        double delta = 360;
        for (int zoomLevel=0; zoomLevel<20; ++zoomLevel) {
            if (deltaLon > delta)
                return zoomLevel;
            delta /= 2;
        }
        return 8;
    }

    public void clear() {
        minLat = +Double.MAX_VALUE;
        minLon = +Double.MAX_VALUE;
        maxLat = -Double.MAX_VALUE;
        maxLon = -Double.MAX_VALUE;
    }
}
