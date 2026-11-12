# GeoTools

url: https://geotools.org/
github: https://github.com/geotools/geotools
Maven: https://mvnrepository.com/artifact/org.geotools

Minimum JDK: https://docs.geotools.org/latest/userguide/build/install/jdk.html


# JTS Java Topology Suite

url: https://locationtech.github.io/jts/
github: https://github.com/locationtech/jts
Maven: https://mvnrepository.com/artifact/org.locationtech.jts
Doc: https://locationtech.github.io/jts/javadoc/index.html

https://www.javacodegeeks.com/exploring-geometry-operations-with-jts.html


# Geometry types

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

# FeatureType

```
PropertyType (org.geotools.api.feature.type)
    OperationType (org.geotools.api.feature.type)
    AssociationType (org.geotools.api.feature.type)
        AssociationTypeImpl (org.geotools.feature.type)
    PropertyTypeImpl (org.geotools.feature.type)
        AttributeTypeImpl (org.geotools.feature.type)
            ComplexTypeImpl (org.geotools.feature.type)
                FeatureTypeImpl (org.geotools.feature.type)
                    SimpleFeatureTypeImpl (org.geotools.feature.simple)
                GeometryTypeImpl (org.geotools.feature.type)
        AssociationTypeImpl (org.geotools.feature.type)
    AttributeType (org.geotools.api.feature.type)
        ComplexType (org.geotools.api.feature.type)
            AbstractLazyComplexTypeImpl (org.geotools.feature.type)
            ComplexTypeImpl (org.geotools.feature.type)
                FeatureTypeImpl (org.geotools.feature.type)
                    SimpleFeatureTypeImpl (org.geotools.feature.simple)
            FeatureType (org.geotools.api.feature.type)
                SimpleFeatureType (org.geotools.api.feature.simple)
                    FeatureTypeImpl (org.geotools.feature.type)
        AttributeTypeImpl (org.geotools.feature.type)
            ComplexTypeImpl (org.geotools.feature.type)
                FeatureTypeImpl (org.geotools.feature.type)
                    SimpleFeatureTypeImpl (org.geotools.feature.simple)
                GeometryTypeImpl (org.geotools.feature.type)
            GeometryType (org.geotools.api.feature.type)
                GeometryTypeImpl (org.geotools.feature.type)
            AbstractLazyAttributeTypeImpl (org.geotools.feature.type)
                AbstractLazyComplexTypeImpl (org.geotools.feature.type)
```
