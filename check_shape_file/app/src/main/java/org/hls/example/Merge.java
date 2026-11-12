package org.hls.example;

import jext.geotools.MultiLineStringMerger;
import jext.util.UniqueKeyMap;
import org.geotools.api.data.FileDataStore;
import org.geotools.api.data.FileDataStoreFinder;
import org.geotools.api.data.SimpleFeatureSource;
import org.geotools.api.feature.simple.SimpleFeature;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.locationtech.jts.geom.Geometry;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Merge {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World");

        Set<String> ftypes = new HashSet<String>();

        File file = new File("D:\\Projects.ebtic\\project.ftth\\EbticDATA\\Dubai\\Jumeirah Village\\ROAD.shp");

        FileDataStore store = FileDataStoreFinder.getDataStore(file);
        SimpleFeatureSource featureSource = store.getFeatureSource();
        SimpleFeatureCollection featureCollection = featureSource.getFeatures();
        SimpleFeatureIterator featureIterator = featureCollection.features();

        MultiLineStringMerger mlsm = new MultiLineStringMerger(2.0);
        UniqueKeyMap<String, SimpleFeature> mlsMap = new UniqueKeyMap<>();



        while(featureIterator.hasNext()) {

            SimpleFeature f =  featureIterator.next();
            Geometry g = (Geometry) f.getDefaultGeometry();

            // if (!MultiLineStringMerger.isMultiLineString(f))
            //     continue;

            String id = f.getID();
            mlsMap.put(id, f);

            mlsm.add(id, f);
        }

        mlsm.mergeLines();

        return;
    }
}
