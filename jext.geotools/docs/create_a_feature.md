# Question

geotools create a feature

# Answer

To create a feature in GeoTools, you generally need three main components: a **`SimpleFeatureType`** (which defines the schema/structure), a **Geometry** (such as a Point or Polygon using JTS), and a **`SimpleFeatureBuilder`** to construct the feature.

Here is a complete step-by-step example in Java:

```java
import org.geotools.api.feature.simple.SimpleFeature;
import org.geotools.api.feature.simple.SimpleFeatureType;
import org.geotools.data.DataUtilities;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

public class CreateFeatureExample {
    public static void main(String[] args) throws Exception {
        // 1. Define the Feature Type (Schema) using DataUtilities
        // Format: "attributeName:Type,..." with standard CRS (e.g., EPSG:4326)
        final SimpleFeatureType TYPE = DataUtilities.createType(
            "Location",
            "the_geom:Point:srid=4326," + 
            "name:String," + 
            "number:Integer"
        );

        // 2. Create a Geometry instance using JTS GeometryFactory
        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
        Coordinate coord = new Coordinate(-124.0, 52.0); // Longitude, Latitude
        Point point = geometryFactory.createPoint(coord);

        // 3. Initialize the SimpleFeatureBuilder with the FeatureType
        SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(TYPE);
        
        // Add attribute values in the exact order they were defined in the schema
        featureBuilder.add(point);       // the_geom
        featureBuilder.add("Canada");    // name
        featureBuilder.add(1);           // number

        // 4. Build the feature with a unique feature ID (or pass null for auto-generated ID)
        SimpleFeature feature = featureBuilder.buildFeature("fid.1");

        // Output to verify
        System.out.println("Created Feature ID: " + feature.getID());
        System.out.println("Feature Attributes: " + feature.getAttributes());
    }
}

```

### Key Concepts:

* **`SimpleFeatureType`**: Acts like a database table schema or a class blueprint, specifying column/attribute names and data types (including geometry type and Coordinate Reference System).
* **JTS (LocationTech JTS)**: GeoTools uses JTS for underlying spatial geometries (`Point`, `LineString`, `Polygon`).
* **`SimpleFeatureBuilder`**: A helper class that safely maps values to your schema definitions and builds the final `SimpleFeature` object.
