# Question

geotools java add feature to shape file

# Answer

To add a new feature to an existing or newly created shapefile in GeoTools, you must obtain a SimpleFeatureStore from the DataStore and wrap your insertion in a Transaction. [1] (https://gis.stackexchange.com/questions/215660/modifying-feature-attributes-of-a-shapefile-in-geotools)
Here is a complete, production-ready code example demonstrating how to build a feature and commit it safely to a shapefile.
```java
import org.geotools.data.DefaultTransaction;
import org.geotools.data.Transaction;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.simple.SimpleFeatureStore;
import org.geotools.feature.DefaultFeatureCollection;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import java.io.File;

public class AddFeatureToShapefile {
public static void main(String[] args) {
File file = new File("path/to/your/file.shp");

        // 1. Initialize a Transaction
        Transaction transaction = new DefaultTransaction("add-feature");

        try {
            // 2. Open the existing Shapefile DataStore
            ShapefileDataStore dataStore = new ShapefileDataStore(file.toURI().toURL());
            String typeName = dataStore.getTypeNames()[0];
            SimpleFeatureType schema = dataStore.getSchema(typeName);

            // 3. Cast the FeatureSource to a FeatureStore (required for writing)
            SimpleFeatureStore featureStore = (SimpleFeatureStore) dataStore.getFeatureSource(typeName);
            featureStore.setTransaction(transaction);

            // 4. Create the new geometry using JTS (LocationTech)
            GeometryFactory geometryFactory = new GeometryFactory();
            Point point = geometryFactory.createPoint(new Coordinate(-122.4194, 37.7749));

            // 5. Build the new SimpleFeature based on the shapefile's existing schema
            SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(schema);
            
            // Note: Attribute names must exactly match your schema definitions (case-sensitive)
            featureBuilder.set("the_geom", point); 
            featureBuilder.set("name", "San Francisco");
            featureBuilder.set("population", 873965);
            
            SimpleFeature newFeature = featureBuilder.buildFeature(null);

            // 6. Collect features and write them out
            DefaultFeatureCollection collection = new DefaultFeatureCollection();
            collection.add(newFeature);

            featureStore.addFeatures(collection);
            
            // 7. Commit changes to make them persistent on disk
            transaction.commit();
            System.out.println("Feature successfully added!");

        } catch (Exception e) {
            e.printStackTrace();
            try {
                // Rollback if something goes wrong to avoid corrupting the files
                transaction.rollback();
            } catch (Exception rollbackException) {
                rollbackException.printStackTrace();
            }
        } finally {
            try {
                transaction.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
```
Use code with caution.

⚠️ Critical Implementation Gotchas

- Attribute Order & Names: Attribute names must precisely match the schema of your existing shapefile. If your geometry field is named "the_geom", you must use "the_geom" in your SimpleFeatureBuilder. [1] (https://docs.geotools.org/stable/userguide/tutorial/feature/csv2shp.html), [2] (https://docs.geotools.org/latest/userguide/library/api/feature.html)
- Coordinate Reference System (CRS): Ensure your new geometry points/polygons use the same CRS (e.g., EPSG:4326) as the target shapefile. GeoTools will not automatically re-project geometries on insert. [1] (https://docs.geotools.org/latest/userguide/library/api/feature.html), [2] (https://docs.geotools.org/stable/userguide/tutorial/feature/csv2shp.html)
- The FeatureStore Cast: getFeatureSource() natively returns a read-only SimpleFeatureSource. You must explicitly check or cast it to a SimpleFeatureStore to expose write/append permissions. [1] (https://github.com/geotools/geotools/blob/main/docs/src/main/java/org/geotools/data/ShapefileExample.java), [2] (https://gis.stackexchange.com/questions/215660/modifying-feature-attributes-of-a-shapefile-in-geotools)
I can help tailor this further if you let me know:
- Are you appending data to an existing shapefile, or generating a brand new file from scratch?
- What type of geometry are you working with? (Points, LineStrings, or Polygons?)
