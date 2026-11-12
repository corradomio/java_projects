package jext.geotools;

import jext.util.Assert;
import jext.util.concurrent.Flatten;
import org.geotools.api.data.FileDataStore;
import org.geotools.api.data.FileDataStoreFinder;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Polygon;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CorridorsGenerator {

    public static class Result {
        public File polygonsFile;
        public FileDataStore store;
        public List<Polygon> allPolygons;
        public List<Polygon> polygons;
        public List<List<Polygon>> clusters;
        public List<LineString> boundaries;
        public List<OutwardDirections> outwards;
        public List<BoundaryIntersections> intersections;
        public List<BoundaryExpander> expandedBoundaries;
        public List<LineString> expanded;
        public List<LineString> ticks;
    }

    private double minPolygonArea = 0;              // m^2
    private double clusterTolerance = 4;            // m
    private double bridgeTolerance = 20;            // m
    private double maxCollinearAngle = 10;          // degree
    private double maxIntersectionDistance = 40;    // m
    private double maxIntersectionAngle = 10;       // degree
    private ExpanderOffsets offsets = new  ExpanderOffsets().initialize();

    public CorridorsGenerator() {

    }

    // ----------------------------------------------------------------------

    public void setMinPolygonArea(double minPolygonArea) {
        this.minPolygonArea = minPolygonArea;
    }

    public void setClusterTolerance(double clusterTolerance) {
        this.clusterTolerance = clusterTolerance;
    }

    public void setBridgeTolerance(double bridgeTolerance) {
        this.bridgeTolerance = bridgeTolerance;
    }

    public void setMaxIntersectionDistance(double maxIntersectionDistance) {
        this.maxIntersectionDistance = maxIntersectionDistance;
    }

    public void setMaxIntersectionAngle(double maxIntersectionAngle) {
        this.maxIntersectionAngle = maxIntersectionAngle;
    }

    public void setExpanderOffsets(ExpanderOffsets offsets) {
        this.offsets = offsets;
    }

    // ----------------------------------------------------------------------

    public Result generate(File polygonsFile) throws IOException {
        FileDataStore store = FileDataStoreFinder.getDataStore(polygonsFile);

        Result result = generate(store);
        result.polygonsFile = polygonsFile;

        return result;
    }

    public Result generate(FileDataStore store) throws IOException {
        List<Polygon> allPolygons = (List<Polygon>) GeometrySelector.selectGeometries(store, GeometryType.POLYGON, true);

        Result result = generate(allPolygons);
        result.store = store;

        return result;
    }

    public Result generate(List<Polygon> allPolygons) {
        // select the polygons
        List<Polygon> polygons = PolygonSelector.selectPolygons(allPolygons, minPolygonArea);

        // create clusters
        List<List<Polygon>> clusters = PolygonClusterer.clusterPolygons(polygons, clusterTolerance);
        int n = clusters.size();

        // create boundaries
        List<LineString> boundaries = ClusterBoundaryGenerator.createBoundaries(clusters, bridgeTolerance);
        Assert.verify(boundaries.size() == n, "Wrong number of boundaries");

        // compute outward directions
        List<OutwardDirections> outwardDirections = OutwardDirections.outwardDirections(boundaries);
        Assert.verify(outwardDirections.size() == n, "Wrong number of outwardDirections");

        // compute segments intersections
        List<BoundaryIntersections> boundaryIntersections = BoundaryIntersections.boundaryIntersections(
            outwardDirections, maxIntersectionDistance, maxIntersectionAngle);
        Assert.verify(boundaryIntersections.size() == n, "Wrong number of boundaryIntersections");

        // compute the boundary offsets
        List<BoundaryOffsets> boundaryOffsets = BoundaryOffsets.assignBoundaryOffsets(boundaryIntersections, offsets);
        Assert.verify(boundaryOffsets.size() == n, "Wrong number of boundaryOffsets");

        // expand the boundaries
        List<BoundaryExpander> expandedBoundaries = BoundaryExpander.boundaryExpand(boundaryOffsets, maxCollinearAngle);
        Assert.verify(expandedBoundaries.size() == n, "Wrong number of expandedBoundaries");

        // compose the result
        List<LineString> expanded = expandedBoundaries.stream().map(be -> be.expanded).toList();
        List<LineString> ticks = Flatten.flatten(expandedBoundaries.stream().map(be -> be.offsets.ticks()).toList());

        Result result = new Result();
        result.allPolygons = allPolygons;
        result.polygons = polygons;
        result.clusters = clusters;
        result.boundaries = boundaries;
        result.outwards = outwardDirections;
        result.intersections = boundaryIntersections;
        result.expandedBoundaries = expandedBoundaries;
        result.expanded = expanded;
        result.ticks = ticks;

        return result;
    }

}
