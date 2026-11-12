package jext.geotools;

import org.locationtech.jts.geom.Polygon;

import java.util.ArrayList;
import java.util.List;

public class PolygonSelector {

    /**
     * Select the polygons having an area
     *
     * @param polygons list of polygons to analyze
     * @param minArea minimum area
     * @return list of selected polygons
     */
    public static List<Polygon> selectPolygons(List<Polygon> polygons, double minArea) {
        List<Polygon> selected = new ArrayList<>();
        for (Polygon polygon : polygons) {
            if (polygon.getArea() >= minArea)
                selected.add(polygon);
        }
        return selected;
    }
}
