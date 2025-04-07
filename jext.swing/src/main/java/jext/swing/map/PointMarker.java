package jext.swing.map;

import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import processing.core.PGraphics;

import java.awt.Color;

public class PointMarker extends SimplePointMarker {


    public PointMarker(double latitude, double longitude) {
        this(new Location(latitude, longitude));
    }

    public PointMarker(Location location) {
        super(location);
    }

    public PointMarker(String id, double latitude, double longitude) {
        this(id, new Location(latitude, longitude));
    }

    public PointMarker(String id, Location location) {
        super(location);
        this.id = id;
    }

    // public PointMarker color(int color) {
    //     super.setColor(color);
    //     super.setStrokeColor(color);
    //     return this;
    // }

    public PointMarker color(Color color) {
        super.setColor(color.getRGB());
        super.setStrokeColor(color.getRGB());
        return this;
    }

    public PointMarker color(Color color, Color strokeColor) {
        super.setColor(color.getRGB());
        super.setStrokeColor(strokeColor.getRGB());
        return this;
    }

    public PointMarker radius(float radius) {
        super.setRadius(radius);
        return this;
    }

    public PointMarker scale(float scale) {
        super.setRadius(radius*scale);
        return this;
    }


    @Override
    public void draw(PGraphics pg, float x, float y) {
        // {
        //     pg.pushStyle();
        //     pg.strokeWeight((float)this.strokeWeight);
        //     if (this.isSelected()) {
        //         pg.fill(this.highlightColor);
        //         pg.stroke(this.highlightStrokeColor);
        //     } else {
        //         pg.fill(this.color);
        //         pg.stroke(this.strokeColor);
        //     }
        //
        //     pg.ellipse((float)((int)x), (float)((int)y), this.radius, this.radius);
        //     pg.popStyle();
        // }
        if (isSelected() && id != null && !id.isEmpty()) {
            // Draw rectangle
            pg.fill(255);
            pg.stroke(0);
            //pg.rect(x, y, 100, 50);
            pg.rect(x, y-20, 30, 20);

            // Draw text
            pg.fill(0);
            // pg.textFont(new PFont(PFont.findFont("Consolas"), true));
            pg.textSize(12);
            // pg.text(id, x + 10, y + 25);
            pg.text(id, x+3, y-5);
        }
        super.draw(pg, x, y);
    }

    @Override
    public boolean isInside(float checkX, float checkY, float x, float y) {
        // Check if mouse is inside rectangle bounds
        return checkX >= x && checkX <= x + 10 && checkY >= y && checkY <= y + 10;
    }
}
