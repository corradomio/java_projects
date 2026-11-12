package jext.geotools;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.MultiLineString;
import org.locationtech.jts.geom.MultiPoint;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;

public enum GeometryType {
    UNKNOWN,
    POINT,
    POLYGON,
    LINE,
    MIXED;

    public static GeometryType of(Geometry g) {
        Class<?> gclass = g.getClass();
        if (Point.class.isAssignableFrom(gclass)) {
            return POINT;
        } else if (MultiPoint.class.isAssignableFrom(gclass)) {
            return POINT;
        } else if (LineString.class.isAssignableFrom(gclass)) {
            return LINE;
        } else if (MultiLineString.class.isAssignableFrom(gclass)) {
            return LINE;
        } else if (Polygon.class.isAssignableFrom(gclass)) {
            return POLYGON;
        } else if (MultiPolygon.class.isAssignableFrom(gclass)) {
            return POLYGON;
        } else {
            return UNKNOWN ;
        }
    }
}
