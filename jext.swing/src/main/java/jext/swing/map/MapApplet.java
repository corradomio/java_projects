package jext.swing.map;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.events.EventDispatcher;
import de.fhpotsdam.unfolding.events.MapEvent;
import de.fhpotsdam.unfolding.events.PanMapEvent;
import de.fhpotsdam.unfolding.events.ZoomMapEvent;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.interactions.MouseHandler;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.AbstractMapProvider;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MapApplet extends PApplet {

    // To keep eclipse from reporting a warning
    private static final long serialVersionUID = 1L;

    // OSM provider and. for GoogleMap, the API Key
    private final String mapProvider;
    private final String mapApiKey;

    // This map is used to display
    private UnfoldingMap umap;

    private ZoomSlider slider;
    private EventDispatcher eventDispatcher;

    private final List<List<Marker>> markers = new ArrayList<>();
    private int zoomLevel = 8;
    private Location panLocation = new Location(23.599909533722876, 54.25133537816678);

    // public myPApplet(String mapProvider) {
    //     this(mapProvider, null);
    // }

    public MapApplet() {
        this("osm", null);
    }

    public MapApplet(String mapProvider, String mapApiKey) {
        if (mapProvider == null)
            mapProvider = "";
        this.mapProvider = mapProvider.strip();
        this.mapApiKey = mapApiKey;
    }

    // Function which implements the unfolds
    // library
    public void setup() {
        if (umap != null)
            return;

        // Set the Applet window to be
        // 900x600 width and height.
        // The OPENGL argument indicates
        // to use the Processing
        // library's 2D drawing
        this.size(1500, 1000, P2D);

        // This sets the background colour
        // for the Applet. Here, colour
        // blue is chosen
        // this.background(0, 0, 128);

        AbstractMapProvider provider;

        switch (this.mapProvider) {
            case "":
            case "osm":
            case "openstreetmap":
                provider = new OpenStreetMap.OpenStreetMapProvider();
                break;
            case "bing":
            case "microsoft":
                provider = new Microsoft.RoadProvider();
                break;
            case "google":
                provider = new GoogleLang.GoogleMapProvider();
                break;
            case "gmapapi":
                provider = new GoogleLang.GoogleApiMapProvider(this.mapApiKey);
                break;
            default:
                provider = new CustomMap.CustomMapProvider(this.mapProvider);
                break;
        }

        // Select a map provider.
        // Here we are using google provider
        // provider = new Google.GoogleTerrainProvider();
        // provider = new Microsoft.RoadProvider();
        // provider = new OpenStreetMap.OpenStreetMapProvider();
        // provider = new OpenStreetMapExt.OpenStreetMapProviderExt();

        // Set a zoom level to focus on
        // our specified location
        // int zoomLevel = 8;
        Dimension rect = super.getSize();

        // Creating the first map
        // umap = new UnfoldingMap(this, 0, 0, 1500,1000, provider);
        // umap = new UnfoldingMap(this, 0, 0, rect.width,rect.height, provider);
        umap = new UnfoldingMap(this, 0, 0, 1920, 1080, provider);

        umap.mapDisplay.setBackgroundColor(color(200)); // Set background color to grey

        // This line zooms in and centers
        // the map at 28.7041 (latitude)
        // and  77.1025° (longitude) for Mumbai.
        // map1.zoomAndPanTo(new Location(28.7041,  77.1025), zoomLevel);

        // center of England
        // map1.zoomAndPanTo(new Location(50, 0), zoomLevel);

        // center of UAE
        // umap.zoomAndPanTo(new Location(23.599909533722876, 54.25133537816678), zoomLevel);
        umap.zoomAndPanTo(panLocation, zoomLevel);

        // This line makes the map interactive
        // as we can zoom in and out. And, here
        // we have zoomed our focus to the
        // Mumbai location by setting the
        // zoom level to 10.

        MapUtils.createDefaultEventDispatcher(this, umap);

        addMarkers();

        eventDispatcher = new EventDispatcher();
        MouseHandler mouseHandler = new MouseHandler(this, umap);
        eventDispatcher.addBroadcaster(mouseHandler);
        listen();
        slider = new ZoomSlider(this, umap, 20, 20);
    }

    private void addMarkers() {
        for (List<Marker> markers : this.markers)
            if (!markers.isEmpty())
                this.umap.addMarkers(markers);
    }

    // Note: Already available in the superClass
    // @Override
    // public void setSize(int width, int height) {
    //     this.setSize(new Dimension(width, height));
    // }
    //
    // @Override
    // public void setSize(Dimension dim) {
    //     super.setSize(dim);
    // }

    // ----------------------------------------------------------------------
    // Note: it is not possible to use umap BEFORE to visualize the map
    // because umap is initialized during the thread initialization used
    // to handle the map.

    public void zoomAndPanTo(Location location, int zoomLevel) {
        if (this.umap == null) {
        this.panLocation = location;
        this.zoomLevel = zoomLevel;
        }
        else {
            this.umap.zoomAndPanTo(location, zoomLevel);
            this.slider.setZoomLevel(zoomLevel);
        }
    }

    public void addMarkers(List<Marker> markers) {
        if (this.umap == null) {
            this.markers.add(markers);
        }
        else {
                this.umap.getDefaultMarkerManager().addMarkers(markers);
        }
    }

    public void clearMarkers() {
        if (this.umap == null) {
            this.markers.clear();
        }
        else {
                this.umap.getDefaultMarkerManager().clearMarkers();
                this.markers.clear();
        }
    }

    // ----------------------------------------------------------------------

    public void init() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.screenWidth = screen.width;
        this.screenHeight = screen.height;
        this.setFocusTraversalKeysEnabled(false);
        this.finished = false;
        this.looping = true;
        this.redraw = true;
        this.firstMouse = true;
        // this.sizeMethods = new PApplet.RegisteredMethods();
        // this.preMethods = new PApplet.RegisteredMethods();
        // this.drawMethods = new PApplet.RegisteredMethods();
        // this.postMethods = new PApplet.RegisteredMethods();
        // this.mouseEventMethods = new PApplet.RegisteredMethods();
        // this.keyEventMethods = new PApplet.RegisteredMethods();
        // this.disposeMethods = new PApplet.RegisteredMethods();

        try {
            this.getAppletContext();
            this.online = true;
        } catch (NullPointerException var6) {
            this.online = false;
        }

        try {
            if (this.sketchPath == null) {
                this.sketchPath = System.getProperty("user.dir");
            }
        } catch (Exception var5) {
        }

        Dimension size = this.getSize();
        if (size.width != 0 && size.height != 0) {
            this.g = this.makeGraphics(size.width, size.height, this.sketchRenderer(), (String) null, true);
        } else {
            this.defaultSize = true;
            int w = this.sketchWidth();
            int h = this.sketchHeight();
            this.g = this.makeGraphics(w, h, this.sketchRenderer(), (String) null, true);
            this.setSize(w, h);
            this.setPreferredSize(new Dimension(w, h));
        }

        this.width = this.g.width;
        this.height = this.g.height;
        // this.addListeners();
        this.start();
    }

    // Function to draw the applet window
    public void draw() {
        background(200); // Set background color
        try {
            umap.draw();
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
        slider.draw();
    }

    public void mapChanged(MapEvent mapEvent) {
        // Updates slider based on current map zoom
        slider.setZoomLevel(umap.getZoomLevel());
    }

    public void mousePressed() {
        if (slider.contains(mouseX, mouseY)) {
            slider.startDrag(mouseX, mouseY);
            mute(); // mute mouse event handling
        }
    }

    public void mouseDragged() {
        if (slider.isDragging()) {
            slider.drag(mouseX, mouseY);
        }
    }

    public void mouseReleased() {
        if (slider.isDragging()) {
            slider.endDrag();
            listen(); // unmute mouse event handling
        }
    }

    public void mute() {
        eventDispatcher.unregister(umap, PanMapEvent.TYPE_PAN, umap.getId());
        eventDispatcher.unregister(umap, ZoomMapEvent.TYPE_ZOOM, umap.getId());
    }

    public void listen() {
        eventDispatcher.register(umap, PanMapEvent.TYPE_PAN, umap.getId());
        eventDispatcher.register(umap, ZoomMapEvent.TYPE_ZOOM, umap.getId());
    }

    public void mouseMoved() {
        Marker hitMarker = umap.getFirstHitMarker(mouseX, mouseY);

        if (hitMarker != null) {
            // Select current marker
            hitMarker.setSelected(true);
        } else {
            // Deselect all other markers
            for (Marker marker : umap.getMarkers()) {
                marker.setSelected(false);
            }
        }
    }

    // class CustomMarker extends SimplePointMarker {
    //
    //     String id; // Text to display when hovered
    //
    //     public CustomMarker(Location location, String id) {
    //         super(location);
    //         this.id = id;
    //     }
    //
    //     // Override draw method to display rectangle and text when hovered
    //     @Override
    //     public void draw(PGraphics pg, float x, float y) {
    //         if (isSelected()) {
    //             // Draw rectangle
    //             pg.fill(255);
    //             pg.stroke(0);
    //             //pg.rect(x, y, 100, 50);
    //             pg.rect(x, y-20, 30, 20);
    //
    //             // Draw text
    //             pg.fill(0);
    //             // pg.textFont(new PFont(PFont.findFont("Consolas"), true));
    //             pg.textSize(12);
    //             // pg.text(id, x + 10, y + 25);
    //             pg.text(id, x+3, y-5);
    //         }
    //         super.draw(pg, x, y);
    //     }
    //
    //     // Override showTitle method to prevent default title from showing
    //     //        @Override
    //     //        public void showTitle(PGraphics pg, float x, float y) {
    //     //             Do nothing
    //     //        }
    //
    //     // Override isInside method to change behavior on hover
    //     @Override
    //     public boolean isInside(float checkX, float checkY, float x, float y) {
    //         // Check if mouse is inside rectangle bounds
    //         //            DecimalFormat df = new DecimalFormat("#.0");
    //         //
    //         //            checkX = Float.valueOf(df.format(checkX));
    //         //            checkY = Float.valueOf(df.format(checkY));
    //         //            x = Float.valueOf(df.format(x));
    //         //            y = Float.valueOf(df.format(y));
    //
    //         return checkX >= x && checkX <= x + 10 && checkY >= y && checkY <= y + 10;
    //     }
    // }

    /**
     * Override the original 'createInputRaw' to create an HTPP connection adding the 'User-Agent' header.
     * This header is mandatory for 'OpenStreetMap Tile Server'.
     *
     * Note: it is used the User-Agent of a Firefox browser.
     *
     * @param filename URL to use for the connection
     * @return
     */
    public InputStream createInputRaw(String filename) {

        if (filename == null || filename.indexOf(":") == -1)
            return super.createInputRaw(filename);

        try {
            URL url = new URL(filename);
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:130.0) Gecko/20100101 Firefox/130.0");
            InputStream stream = conn.getInputStream();
            return stream;
        } catch (Exception var19) {
            var19.printStackTrace();
        }

        return null;
    }
}
