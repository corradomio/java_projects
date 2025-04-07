package jext.swing.map;

import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.utils.MapPosition;
import processing.core.PGraphics;

import java.util.List;

public class ArrowMarker extends LinesMarker {
    // protected int color;
    // protected int strokeColor;
    // protected int strokeWeight;
    // protected int highlightColor;
    // protected int highlightStrokeColor;
    // protected Location location;
    // protected HashMap<String, Object> properties;
    // protected boolean selected;
    // protected String id;

    private double arrowSize = 1.0;

    public ArrowMarker() {

    }

    public ArrowMarker(double lat1, double lon1, double lat2, double lon2) {
        this(new Location(lat1, lon1), new Location(lat2, lon2));
    }

    public ArrowMarker(Location from, Location to) {
        super(from, to);
    }

    @Override
    public void draw(PGraphics pGraphics, List<MapPosition> list) {
        super.draw(pGraphics, list);
        //     pGraphics.pushStyle();
        //     pGraphics.noFill();
        //     if (this.isSelected()) {
        //         pGraphics.stroke(this.highlightColor);
        //     } else {
        //         pGraphics.stroke(this.color);
        //     }
        //
        //     pGraphics.strokeWeight((float)this.strokeWeight);
        //     pGraphics.smooth();
        //     pGraphics.beginShape(4);
        //     MapPosition var3 = (MapPosition)list.get(0);
        //
        //     for(int var4 = 1; var4 < list.size(); ++var4) {
        //         MapPosition var5 = (MapPosition)list.get(var4);
        //         pGraphics.vertex(var3.x, var3.y);
        //         pGraphics.vertex(var5.x, var5.y);
        //         var3 = var5;
        //     }
        //
        //     pGraphics.endShape();
        //     pGraphics.popStyle();
        // }
    }
}
