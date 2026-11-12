package jext.geotools;

import jext.util.MapUtils;
import org.geotools.api.data.DataStore;
import org.geotools.api.data.FeatureWriter;
import org.geotools.api.data.Transaction;
import org.geotools.api.feature.simple.SimpleFeature;
import org.geotools.api.feature.simple.SimpleFeatureType;
import org.geotools.data.DataUtilities;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.feature.SchemaException;
import org.locationtech.jts.geom.LineString;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Save the list of boundaries, represented by a LineString, in a shape file
 */
public class BoundarySaver {

    public static void saveBoundaries(File outputFile, List<LineString> boundaries) throws SchemaException, IOException {
        Map<String, Serializable> outputParams = MapUtils.asMap(
            "url", outputFile.toURI().toURL(),
            "create spatial index", Boolean.TRUE
        );
        ShapefileDataStoreFactory dataStoreFactory = new ShapefileDataStoreFactory();
        DataStore outputDataStore = dataStoreFactory.createNewDataStore(outputParams);
        addBoundaries(outputDataStore, boundaries, outputParams);
        outputDataStore.dispose();
    }

    public static void addBoundaries(DataStore outputDataStore, List<LineString> boundaries, Map<String, Serializable> outputParams) throws SchemaException, IOException {

        // create a feature schema
        final SimpleFeatureType BOUNDARY_TYPE = DataUtilities.createType(
            "Boundary",
            "the_geom:LineString"
        );

        outputDataStore.createSchema(BOUNDARY_TYPE);

        try (FeatureWriter<SimpleFeatureType, SimpleFeature> writer =
            outputDataStore.getFeatureWriterAppend(outputDataStore.getTypeNames()[0], Transaction.AUTO_COMMIT)) {

            int id = 1;
            for (LineString lineString : boundaries) {
                // featureBuilder.set("the_geom", lineString);

                // SimpleFeature newFeature = featureBuilder.buildFeature(String.format("%s:%d", prefix, id++));
                SimpleFeature newFeature = writer.next();
                newFeature.setAttribute("the_geom", lineString);
                // collection.add(newFeature);
                // featureStore.addFeatures(collection);
                writer.write();
            }
        }
    }
}
