package com.example;

import de.fhpotsdam.unfolding.providers.Google;
import processing.core.PApplet;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.utils.MapUtils;

import java.awt.*;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Hello world!
 *
 */
public class App extends PApplet
{

    UnfoldingMap map;

    public void setup() {
        size(1440, 900, OPENGL);

        this.frame.setResizable(true);

        map = new UnfoldingMap(this, new Google.GoogleMapProvider());
        // map = new UnfoldingMap(this, new Microsoft.RoadProvider());
        // map = new UnfoldingMap(this, new OpenStreetMap.OpenStreetMapProvider());
        // map = new UnfoldingMap(this, new MBTilesMapProvider("D:/Dropbox/Software/maptiler-osm-2020-02-10-v3.11-planet.mbtiles"));
        // map = new UnfoldingMap(this, new Local.LocalProvider());
        map.zoomAndPanTo(10, new Location(52.5f, 13.4f));

        MapUtils.createDefaultEventDispatcher(this, map);
    }

    // @Override
    // public void resize(int w, int h) {
    //     super.resize(w,h);
    //     Dimension d = size();
    //     if (w == d.width && h == d.height)
    //         return;
    //
    //     this.frame.setSize(w,h);
    //     if (this.map != null)
    //         this.map.mapDisplay.resize(w, h);
    // }

    @Override
    public void draw() {
        Dimension d = this.frame.getSize();
        this.map.mapDisplay.resize(d.width, d.height);
        background(0);
        this.map.draw();
    }

    public InputStream createInputRaw(String filename) {
        if (filename == null || filename.indexOf(':') == -1)
            return super.createInputRaw(filename);

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

    public static void main(String[] args) {
        PApplet.main(new String[] { App.class.getName() });
    }

}
