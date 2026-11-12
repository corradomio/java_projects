package org.hls.example;

import jext.geotools.BoundarySaver;
import jext.geotools.ClusterBoundaryGenerator;
import jext.geotools.GeometrySelector;
import jext.geotools.GeometryType;
import jext.geotools.PolygonClusterer;
import org.geotools.api.data.FileDataStore;
import org.geotools.api.data.FileDataStoreFinder;
import org.geotools.feature.SchemaException;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Polygon;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PolygonClusters {

    private static final File EBTIC_ROOT = new File("D:\\Projects.ebtic\\project.ftth");

    public static void main(String[] args) throws IOException, SchemaException {
        File polygonsFile = new File(EBTIC_ROOT, "EbticDATA/Dubai/Jumeirah Village/PARCEL.shp");
        File boundariesFile = new File(EBTIC_ROOT, "EbticDATA/Dubai/Jumeirah Village/PARCEL-boundaries.shp");

        // File polygonsFile = new File(EBTIC_ROOT, "EbticDATA/Etisalat/Greenfield/NorthernEmirates/From Hashim/yasmeenShape/yasmeen.shp");
        // File boundariesFile = new File(EBTIC_ROOT, "EbticDATA/Etisalat/Greenfield/NorthernEmirates/From Hashim/yasmeenShape/yasmeen-boundaries.shp");

        // File polygonsFile = new File(EBTIC_ROOT, "EbticDATA/Etisalat/Greenfield/NorthernEmirates/From Hashim/ALSEYOUH2468/revised/Al_Seyouh_2468.shp");
        // File boundariesFile = new File(EBTIC_ROOT, "EbticDATA/Etisalat/Greenfield/NorthernEmirates/From Hashim/ALSEYOUH2468/revised/Al_Seyouh_2468-boundaries.shp");

        // File polygonsFile = new File(EBTIC_ROOT, "EbticDATA/Etisalat/Greenfield/NorthernEmirates/From Hashim/ZUBAIR/ZUBAIR.shp");
        // File boundariesFile = new File(EBTIC_ROOT, "EbticDATA/Etisalat/Greenfield/NorthernEmirates/From Hashim/ZUBAIR/ZUBAIR-boundaries.shp");

        FileDataStore store = FileDataStoreFinder.getDataStore(polygonsFile);

        // for (String typeName : store.getTypeNames()) {
        //     // System.out.println(typeName);
        //     SimpleFeatureType schema = store.getSchema(typeName);
        //     System.out.println("Feature Type Name: " + schema.getTypeName());
        //     System.out.println("Geometry Attribute: " + schema.getGeometryDescriptor());
        //     System.out.println("Attributes Count: " + schema.getAttributeCount());
        //     for (AttributeDescriptor ad : schema.getAttributeDescriptors()) {
        //         System.out.println("... "+ ad.getLocalName());
        //         System.out.println("... "+ ad.getType());
        //     }
        // }

        // if (true) return;

        List<Polygon> polygons = (List<Polygon>) GeometrySelector.selectGeometries(store, GeometryType.POLYGON, true);
        System.out.printf("Polygon count: %d\n", polygons.size());

        List<List<Polygon>> clusters = PolygonClusterer.clusterPolygons(polygons, 4.);
        System.out.printf("Polygon clusters: %d\n", clusters.size());

        List<LineString> boundaries = ClusterBoundaryGenerator.createBoundaries(clusters, 4.0);
        System.out.printf("Cluster boundaries: %d\n", clusters.size());


        BoundarySaver.saveBoundaries(boundariesFile, boundaries);
    }
}
