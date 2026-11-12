package jext.geotools;

import org.geotools.api.data.DataStore;
import org.geotools.api.data.DataStoreFinder;
import org.geotools.api.data.SimpleFeatureSource;
import org.geotools.api.feature.simple.SimpleFeatureType;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.MultiLineString;
import org.locationtech.jts.geom.MultiPoint;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
    Geometry hierarchy

    Geometry (org.locationtech.jts.geom)
        GeometryCollection (org.locationtech.jts.geom)
            MultiLineString (org.locationtech.jts.geom)
                MultiCurve (org.geotools.geometry.jts)
            MultiPoint (org.locationtech.jts.geom)
            MultiPolygon (org.locationtech.jts.geom)
                MultiSurface (org.geotools.geometry.jts)
        LineString (org.locationtech.jts.geom)
            CompoundCurve (org.geotools.geometry.jts)
            CircularString (org.geotools.geometry.jts)
            LinearRing (org.locationtech.jts.geom)
                CircularRing (org.geotools.geometry.jts)
                CompoundRing (org.geotools.geometry.jts)
        Polygon (org.locationtech.jts.geom)
            CurvePolygon (org.geotools.geometry.jts)
        Point (org.locationtech.jts.geom)

 */

/**
 * Analyze a '.shp' file to understand if it contains primitives of a single type or
 * multiple types
 */
public class ShapeFileType {

    /**
     * Analyze the file to understand the primitive types used
     * It is classified in the following way:
     * .
     *      NONE: the file is empty of composed by unknown primitives
     *      POINT: Point, MultiPoint
     *      POLYGON: Polygon/*, MultiPolygon/*
     *      LINE: LineString/*, MultiLineString/*
     *      MIXED: if it contains 2 or 3 different primitive types
     *
     * @param shapeFile file to analyze
     * @return a Type based on the file content
     */
    public static GeometryType analyze(File shapeFile) {
        Map<String, Object> map = new HashMap<>();
        map.put("url", shapeFile.toURI().toString());

        boolean points = false;
        boolean lines = false;
        boolean polygons = false;

        DataStore dataStore = null;
        try {
            // Find and open the Shapefile DataStore
            dataStore = DataStoreFinder.getDataStore(map);
            if (dataStore == null) {
                return GeometryType.UNKNOWN;
            }

            // Get the type name (usually matches the filename)
            String typeName = dataStore.getTypeNames()[0];
            SimpleFeatureSource featureSource = dataStore.getFeatureSource(typeName);

            // Get the schema metadata
            SimpleFeatureType schema = featureSource.getSchema();

            // Fetch the Class type that the default geometry binds to
            Class<?> geometryBinding = schema.getGeometryDescriptor().getType().getBinding();

            // Check if the shapefile contains only Point or MultiPoint geometries
            if (Point.class.isAssignableFrom(geometryBinding)) {
                points = true;
            } else if (MultiPoint.class.isAssignableFrom(geometryBinding)) {
                points = true;
            } else if (LineString.class.isAssignableFrom(geometryBinding)) {
                lines = true;
            } else if (MultiLineString.class.isAssignableFrom(geometryBinding)) {
                lines = true;
            } else if (Polygon.class.isAssignableFrom(geometryBinding)) {
                polygons = true;
            } else if (MultiPolygon.class.isAssignableFrom(geometryBinding)) {
                polygons = true;
            } else {
                System.err.println("Geometry Type not supported: " + geometryBinding.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dataStore != null) {
                dataStore.dispose();
            }
        }

        if (points && lines || lines && polygons || points && polygons)
            return GeometryType.MIXED;
        else if (points)
            return GeometryType.POINT;
        else if (lines)
            return GeometryType.LINE;
        else if (polygons)
            return GeometryType.POLYGON;
        else
            return GeometryType.UNKNOWN;
    }
}
