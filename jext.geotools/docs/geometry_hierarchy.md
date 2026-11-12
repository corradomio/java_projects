# GeoTools Geometry Hierarchy

```
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
```
