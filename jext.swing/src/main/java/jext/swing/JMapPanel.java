package jext.swing;

import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import jext.swing.map.BoundBox;
import jext.swing.map.MapApplet;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

/// Wrapper to 'processing.core.PApplet' (based on 'de.fhpotsdam.unfolding.UnfoldingMap')
/// to visualize OpenStreetMap-lik maps
/// MapApplet extends PApplet adding some useful parameters:
///
///  1) it is possible to decide the 'map provider' between:
///      osm/openstreetmap, bng/microsoft, google, gmapapi (with key)
///     or using an URL following the OSM tile pattern
///      http(s)://<host>:<port>/<path>/{z}/{x}/{y}.png
///
///   2)
public class JMapPanel extends JPanel {

    private final MapApplet mapApplet;
    private final BoundBox boundBox = new BoundBox();

    public JMapPanel() {
        this("osm");
    }

    public JMapPanel(String mapProvider) {
        super(new BorderLayout());

        this.mapApplet = new MapApplet(mapProvider, null);
        this.mapApplet.init();
        // IT IS NOT POSSIBLE to call 'setup' HERE!
        // this.mapApplet.setup();
        this.add(mapApplet, BorderLayout.CENTER);

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                Dimension size = JMapPanel.this.getSize();
                mapApplet.setSize(size);
            }
        });
    }

    public JMapPanel addMarkers(List<Marker>... markersArray) {
        for (List<Marker> markers : markersArray) {
            this.mapApplet.addMarkers(markers);
            this.boundBox.update(markers);
        }
        return this;
    }

    public JMapPanel zoomAndPanTo(double lat, double lon, int zoomLevel) {
        this.zoomAndPanTo(new Location(lat, lon), zoomLevel);
        return this;
    }


    public JMapPanel zoomAndPanTo(Location center, int zoomLevel) {
        this.mapApplet.zoomAndPanTo(center, zoomLevel);
        return this;
    }

    public JMapPanel autoZoomAndPan() {
        Location center = boundBox.center();
        int zoomLevel = boundBox.zoomLevel();

        if (zoomLevel > 0)
            this.mapApplet.zoomAndPanTo(center, zoomLevel);
        return this;
    }

    public void clearMarkers() {
        this.boundBox.clear();
        this.mapApplet.clearMarkers();
    }

    public void dispose() {
        this. mapApplet.dispose();
    }

}
