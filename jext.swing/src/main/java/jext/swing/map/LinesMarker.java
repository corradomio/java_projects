package jext.swing.map;

import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;

import java.awt.Color;
import java.util.List;

public class LinesMarker extends SimpleLinesMarker {

    public LinesMarker() {
        super();
    }

    public LinesMarker(List<Location> locations) {
        super(locations);
    }

    public LinesMarker(Location l1, Location l2) {
        super();
        addLocations(l1, l2);
    }

    public LinesMarker points(double lat1, double lon1, double lat2, double lon2) {
        return points(new Location(lat1, lon1), new Location(lat2, lon2));
    }

    public LinesMarker points(Location... locations) {
        addLocations(locations);
        return this;
    }


    public LinesMarker color(Color color) {
        setColor(color.getRGB());
        setStrokeColor(color.getRGB());
        return this;
    }

    public LinesMarker tickness(int thick) {
        setStrokeWeight(thick);
        return this;
    }

    public LinesMarker scale(double scale) {
        setStrokeColor((int)(strokeWeight*scale));
        return this;
    }

}
