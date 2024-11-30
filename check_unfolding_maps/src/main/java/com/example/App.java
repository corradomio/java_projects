package com.example;

import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import processing.core.PApplet;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
// import de.fhpotsdam.unfolding.utils.MapUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import jext.util.JSONUtils;
import jext.util.MapUtils;


/**
 * Hello world!
 *
 */
public class App extends PApplet
{
    private UnfoldingMap map;

    public void setup() {
        size(1440, 900, OPENGL);

        this.frame.setResizable(true);

        // map = new UnfoldingMap(this, new Google.GoogleMapProvider());
        // map = new UnfoldingMap(this, new Microsoft.RoadProvider());
        map = new UnfoldingMap(this, new OpenStreetMap.OpenStreetMapProvider());
        // map = new UnfoldingMap(this, new MBTilesMapProvider("D:/Dropbox/Software/maptiler-osm-2020-02-10-v3.11-planet.mbtiles"));
        // map = new UnfoldingMap(this, new Local.LocalProvider());
        // map.zoomAndPanTo(10, new Location(52.5f, 13.4f));            // Berlin
        map.zoomAndPanTo(10, new Location(51.5f, 0.08f));   // London

        de.fhpotsdam.unfolding.utils.MapUtils.createDefaultEventDispatcher(this, map);

        fillWarehouses(100);
    }

    private void fillWarehouses(int count) {
        Map<String, Map<String, Object>> wlist = MapUtils.get(warehouses, "warehouses");
        List<Marker> markers = new ArrayList<>();

        for(Map<String, Object> w : wlist.values()) {
            if (count-- <= 0) break;

            System.out.println((String)MapUtils.get(w, "name"));

            String name = MapUtils.getString(w, "name");
            float lat = Float.parseFloat(MapUtils.getString(w, "lat"));
            float lon = Float.parseFloat(MapUtils.getString(w, "lon"));

            Location loc = new Location(lat, lon);
        }
    }

    @Override
    public void draw() {
        Dimension d = this.frame.getSize();
        this.map.mapDisplay.resize(d.width, d.height);
        background(0);
        this.map.draw();
    }

    @Override
    public InputStream createInputRaw(String filename) {
        if (filename == null || filename.indexOf(':') == -1)
            return super.createInputRaw(filename);

        // HTTP connection
        try {
            URL url = new URL(filename);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla");
            InputStream stream = connection.getInputStream();
            return stream;
        } catch (Exception e) {

        }

        return null;
    }

    // ----------------------------------------------------------------------

    private static Map<String, Object> warehouses;

    public static void main(String[] args) {
        try {
            warehouses = JSONUtils.load(new File("warehouses_500.json"), HashMap.class);
            PApplet.main(new String[] { App.class.getName() });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
