package org.hls.example;

import jext.geotools.MultiLineStringMerger;
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

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World");

        Set<String> ftypes = new HashSet<String>();

        File file = new File("D:\\Projects.ebtic\\project.ftth\\EbticDATA\\Dubai\\Jumeirah Village\\ROAD.shp");

        System.out.println("FileDataStore");
        FileDataStore store = FileDataStoreFinder.getDataStore(file);
        System.out.println("SimpleFeatureSource");
        SimpleFeatureSource featureSource = store.getFeatureSource();
        System.out.println("SimpleFeatureCollection");
        SimpleFeatureCollection featureCollection = featureSource.getFeatures();
        System.out.println("SimpleFeatureIterator");
        SimpleFeatureIterator  featureIterator = featureCollection.features();

        while(featureIterator.hasNext()) {

            SimpleFeature f =  featureIterator.next();
            Geometry g = (Geometry) f.getDefaultGeometry();

            if (!MultiLineStringMerger.isMultiLineString(f))
                continue;

            // --
            var a = g.getArea();
            var b = g.getBoundary();
            var bd = g.getBoundaryDimension();
            var c = g.getCentroid();
            var xy1 = g.getCoordinate();
            var d = g.getDimension();
            var e = g.getEnvelope();
            var ei = g.getEnvelopeInternal();
            var xy = g.getCoordinates();
            var fa = g.getFactory();
            var gt = g.getGeometryType();
            if (!ftypes.contains(gt)) {
                System.out.printf("... %s\n", gt);
                ftypes.add(gt);
            }
            var ip = g.getInteriorPoint();
            var l = g.getLength();
            var ng = g.getNumGeometries();
            var np = g.getNumPoints();
            var pm = g.getPrecisionModel();
            var srid = g.getSRID();
            var ud = g.getUserData();

            // --
            // var b = f.getBounds();
            // var dgp = f.getDefaultGeometryProperty();
            // var id = f.getIdentifier();
            // SimpleFeatureType t = (SimpleFeatureType) f.getType();
            // System.out.printf("... %s\n", t.getTypeName());
            // var d = f.getDescriptor();
            // var n = f.getName();
            var p = f.getProperties();
            // var ud = f.getUserData();
            // var val = f.getValue();
            // --
            // System.out.printf("%s: %s\n", f.getType(), f.getIdentifier());
            // System.out.println(f);
        }

        return;
    }
}
