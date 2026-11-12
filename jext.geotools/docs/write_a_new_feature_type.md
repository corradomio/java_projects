# Question


# Answer

To create a new shapefile with a modified or entirely different SimpleFeatureType using GeoTools, you must read the features from the original shapefile, transform or map them into the new feature structure, and write them to a new shapefile.
Here is a complete, production-ready Java example demonstrating this workflow.

```java
import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.FeatureWriter;
import org.geotools.data.Transaction;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.feature.SimpleFeatureTypeBuilder;
import org.locationtech.jts.geom.Geometry;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ShapefileSchemaTransformer {

    public static void main(String[] args) {
        File inputFile = new File("path/to/input.shp");
        File outputFile = new File("path/to/output.shp");

        try {
            // 1. Read the existing shapefile
            Map<String, Serializable> inputParams = new HashMap<>();
            inputParams.put("url", inputFile.toURI().toURL());
            DataStore inputDataStore = DataStoreFinder.getDataStore(inputParams);
            
            String typeName = inputDataStore.getTypeNames()[0];
            SimpleFeatureSource featureSource = inputDataStore.getFeatureSource(typeName);
            SimpleFeatureType originalType = featureSource.getSchema();

            // 2. Define the NEW SimpleFeatureType definition
            SimpleFeatureTypeBuilder typeBuilder = new SimpleFeatureTypeBuilder();
            typeBuilder.setName("NewFeatureType");
            typeBuilder.setCRS(originalType.getCoordinateReferenceSystem()); // Keep original CRS

            // Add original geometry
            typeBuilder.add(originalType.getGeometryDescriptor().getLocalName(), 
                           originalType.getGeometryDescriptor().getType().getBinding());
            
            // Add a selected attribute from the old schema
            if (originalType.getDescriptor("OLD_ATTR") != null) {
                typeBuilder.add("NEW_NAME", String.class); // Renaming an old attribute
            }
            
            // Add a completely brand new attribute
            typeBuilder.add("NEW_FIELD", Double.class);

            SimpleFeatureType newType = typeBuilder.buildFeatureType();

            // 3. Create the output Shapefile DataStore
            Map<String, Serializable> outputParams = new HashMap<>();
            outputParams.put("url", outputFile.toURI().toURL());
            outputParams.put("create spatial index", Boolean.TRUE);
            
            ShapefileDataStoreFactory dataStoreFactory = new ShapefileDataStoreFactory();
            DataStore outputDataStore = dataStoreFactory.createNewDataStore(outputParams);
            outputDataStore.createSchema(newType);

            // 4. Write data from old to new shapefile using a FeatureWriter
            SimpleFeatureCollection featureCollection = featureSource.getFeatures();
            try (SimpleFeatureIterator iterator = featureCollection.features();
                 FeatureWriter<SimpleFeatureType, SimpleFeature> writer = 
                         outputDataStore.getFeatureWriterAppend(outputDataStore.getTypeNames()[0], Transaction.AUTO_COMMIT)) {

                while (iterator.hasNext()) {
                    SimpleFeature originalFeature = iterator.next();
                    SimpleFeature newFeature = writer.next();

                    // Copy geometry
                    newFeature.setAttribute(0, originalFeature.getAttribute(originalType.getGeometryDescriptor().getLocalName()));
                    
                    // Map old data to renamed field (handle safely)
                    if (originalType.getDescriptor("OLD_ATTR") != null) {
                        newFeature.setAttribute("NEW_NAME", originalFeature.getAttribute("OLD_ATTR"));
                    }
                    
                    // Populate your new custom field
                    newFeature.setAttribute("NEW_FIELD", 123.45);

                    // Write the feature to the new shapefile
                    writer.write();
                }
            }

            // Cleanup
            inputDataStore.dispose();
            outputDataStore.dispose();
            System.out.println("New shapefile successfully created with custom schema!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

Core Architecture Breakdown

• SimpleFeatureTypeBuilder: This utility is mandatory for building the target schema. Use it to specify the coordinate reference system (CRS) and the precise array of attribute types you want in your final output.
• createNewDataStore() & createSchema(): Instantiating the factory handles the underlying shapefile sidecar files (.shx, .dbf, .prj). createSchema(newType) initializes the database column headers on the file layer.
• getFeatureWriterAppend(): This approach streams elements individually into buffer memory. Avoid loading entire feature arrays directly into heavy heap collections (MemoryFeatureCollection) if you are parsing large shapefile geometries to minimize risk of OOM exceptions.
Would you like assistance in applying a specific transformation mapping (such as a coordinate system re-projection or field-type casting) during this migration step? Let me know the exact changes you want to make to the attributes.
