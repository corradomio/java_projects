package jext.geotools;

import org.geotools.api.data.FileDataStore;
import org.geotools.api.data.SimpleFeatureSource;
import org.geotools.api.feature.simple.SimpleFeature;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.util.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GeometrySelector {

    public static List<? extends Geometry> selectGeometries(FileDataStore datastore, GeometryType gt, boolean split) throws IOException {
        return selectGeometries(datastore.getFeatureSource(), gt, split);
    }

    /**
     * From the feature source, extract all features and for each feature the specified geometry type
     *
     * @param featureSource geometric object to analyze
     * @param gt geometry types to extrack
     * @param split if to split the GeometryCollections in its components
     * @return the list selected geometries
     * @throws IOException
     */
    public static List<? extends Geometry> selectGeometries(SimpleFeatureSource featureSource, GeometryType gt, boolean split) throws IOException {
        List<Geometry> geometries = new ArrayList<>();
        SimpleFeatureCollection featureCollection = featureSource.getFeatures();
        for(SimpleFeatureIterator fit = featureCollection.features(); fit.hasNext(); ) {
            SimpleFeature f = fit.next();
            Geometry g = (Geometry) f.getDefaultGeometry();

            // skip geometries of the wrong type
            if (gt != GeometryType.of(g)) {
                //
            }
            else if (split && g instanceof GeometryCollection) {
                GeometryCollection gc = (GeometryCollection) g;
                int n = gc.getNumGeometries();

                for(int i=0; i<n ; ++i) {
                    Geometry gi = gc.getGeometryN(i);

                    // check to be confident it is not necessary to split the geometry recursively
                    Assert.isTrue(gi instanceof Polygon || gi instanceof LineString || gi instanceof Point);

                    geometries.add(gi);
                }
            }
            else {
                geometries.add(g);
            }
        }

        return geometries;
    }

}
