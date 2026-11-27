package jext.geotools;

import jext.geotools.junction.BoundaryChamfers;
import jext.geotools.junction.BoundaryJunctions;
import jext.geotools.junction.ChamfersLocator;
import jext.geotools.junction.JunctionBridge;
import jext.geotools.junction.JunctionBridgeFinder;
import jext.geotools.junction.JunctionsLocator;
import jext.util.Assert;
import jext.util.concurrent.Flatten;
import jext.apache.logging.log4j.Logger;
import org.geotools.api.data.FileDataStore;
import org.geotools.api.data.FileDataStoreFinder;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Polygon;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CorridorsGenerator {

    private static final Logger logger = Logger.getLogger(CorridorsGenerator.class);

    public static class Result {
        public File polygonsFile;
        public FileDataStore store;
        public List<Polygon> allPolygons;
        public List<Polygon> polygons;
        public List<List<Polygon>> clusters;

        public List<OutwardDirections> outwards;
        public List<BoundaryIntersections> intersections;
        public List<BoundaryExpander> expandedBoundaries;
        public List<BoundaryChamfers> chamferLocations;
        public List<BoundaryJunctions> junctionLocations;
        public List<JunctionBridge> junctionBridges;

        public List<LineString> boundaries;
        public List<LineString> expanded;
        public List<LineString> junctioned;
        public List<LineString> bridges;
        public List<LineString> ticks;
    }

    // remove polygons too small
    private double minPolygonArea = 0;                  // m^2
    // max polygons distance to be considered members of the same cluster
    private double clusterTolerance = 4;                // m
    // max length of the segment when it is created the boundary and it
    // is necessary to connect not adjacent polygons
    private double boundarySides = 20;                // m

    // min segment length to consider valid. If it is shorter
    // it is removed. Default: 20cm
    // This step is MANDATORY because if a segment has length 0
    // this generates a lot of problems in the remaining steps
    private double minSegmentLength = 0.2;              // m
    // max segment length in boundary.
    // If a segment is longer it is split in smaller component
    private double maxSegmentLength = 0;                // m
    // max angle to consider two segments collinear
    private double maxCollinearAngle = 0;               // radians

    // max angle to consider two segments collinear during the offset propagation
    private double maxOffsetCollinearAngle = 0;

    // max accepted angle to consider the intersection valid
    // It is the difference between 90 degres: 10 degrees means [80,100]
    private double maxIntersectionAngle = Math.toRadians(10);           // radians
    // max valid intersection distance: if the intersection is
    // farther it is not considered
    // boundary offsets to use, based on the intersection distance
    private double maxIntersectionDistance = 40;        // m

    private ExpanderOffsets offsets = new  ExpanderOffsets().initialize();

    //
    // Chamfers
    //
    // chamfer offset where to create the vertex to connect boundaries
    private double chamferJunctionDistance = 5;
    // maximum angle between two boundary segments to consider a chamfer
    private double maxChamferAngle = Math.toRadians(60);
    // distance between 2 junctions on boundaries
    private double junctionDistance = 300;

    //
    // Bridges
    // Note: as default values it is reasonable to use
    //  maxIntersectionDistance, maxIntersectionAngle
    //
    // maximum angle, on BOTH sides, of the bridge against the outward
    // directions of the two segments containing the junctions
    private double maxBridgeAngle = Math.toRadians(10);
    // maximum bridge distance to consider it a valid bridge
    private double maxBridgeDistance = 50;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public CorridorsGenerator() {

    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    /// Minimum acceptable segment lengths in polygons and boundaries
    /// It is necessary to remove too small segments (or 0-length segments)
    /// because all steps requires consecutive points MUST BE DIFFERENT
    /// Default: 0.2 m (20 cm)
    public void setMinSegmentLength(double minSegmentLength) {
        this.minSegmentLength = minSegmentLength;
    }

    /// Exclude polygons too small
    /// Default: 0 m^2 (no polygon is removed)
    public void setMinPolygonArea(double minPolygonArea) {
        this.minPolygonArea = minPolygonArea;
    }

    /// Maximum distance between two polygons belonging to the same cluster
    /// Default: 4 m
    public void setClusterTolerance(double clusterTolerance) {
        this.clusterTolerance = clusterTolerance;
    }

    /// length of the segments when it is necessary to connect not adjacent polygons
    /// Default: 20 m
    public void setBoundarySides(double boundarySides) {
        this.boundarySides = boundarySides;
    }

    /// Which is the maximum segment length of the boundaries. It is used to increase the places where
    /// to compute the intersections
    /// Default: 0 m
    public void setMaxSegmentLength(double maxSegmentLength) {
        this.maxSegmentLength = maxSegmentLength;
    }

    /// During the boundary simplification, the maximum angle to consider 2 adjacent segments 'collinear'
    /// If the segments are 'collinear' they are merged.
    /// 10 degrees  means the acceptable angles are [170,190] degrees
    /// Default: 0 degrees (no collinear segment is considered)
    public void setMaxCollinearAngle(double maxCollinearAngle) {
        this.maxCollinearAngle = maxCollinearAngle;
    }
    public void setMaxCollinearAngleDegree(double maxCollinearAngle) {
        this.maxCollinearAngle = Math.toRadians(maxCollinearAngle);
    }

    /// Maximum distance of an intersection from one boundary's segment to the segment of another
    /// boundary
    /// Default 40 m
    public void setMaxIntersectionDistance(double maxIntersectionDistance) {
        this.maxIntersectionDistance = maxIntersectionDistance;
    }

    /// Maximum angle, respect 90 degrees, of an intersection from one boundary's segment to the segment
    /// of another boundary. 10 degrees means the acceptable angles are [80,110] degrees
    /// Default: 10 degrees
    public void setMaxIntersectionAngle(double maxIntersectionAngle) {
        this.maxIntersectionAngle = maxIntersectionAngle;
    }
    public void setMaxIntersectionAngleDegree(double maxIntersectionAngle) {
        this.maxIntersectionAngle = Math.toRadians(maxIntersectionAngle);
    }

    /// Maximum angle, respect 180 degrees, between two adjacent segments on the
    /// expanded boundary, to consider them 'collinear'
    public void setMaxOffsetCollinearAngle(double maxOffsetCollinearAngle) {
        this.maxOffsetCollinearAngle = maxOffsetCollinearAngle;
    }
    public void setMaxOffsetCollinearAngleDegree(double maxOffsetCollinearAngle) {
        this.maxOffsetCollinearAngle = Math.toRadians(maxOffsetCollinearAngle);
    }

    /// Offsets to use to expand the boundary, based on the intersection distance
    /// Default: 0 m (no offset is applied)
    public void setExpanderOffsets(ExpanderOffsets offsets) {
        this.offsets = offsets;
    }

    // ----------------------------------------------------------------------
    // Chamfers & Junctions

    /// Set the minimum angle
    public void setMaxChamferAngle(double maxChamferAngle) {
        this.maxChamferAngle = maxChamferAngle;
    }
    public void setMaxChamferAngleDegree(double maxChamferAngle) {
        this.maxChamferAngle = Math.toRadians(maxChamferAngle);
    }

    public void setChamferJunctionDistance(double chamferJunctionDistance) {
        this.chamferJunctionDistance = chamferJunctionDistance;
    }

    public void setJunctionDistance(double junctionDistance) {
        this.junctionDistance = junctionDistance;
    }

    // ----------------------------------------------------------------------
    // Bridges

    public void setMaxBridgeAngle(double maxBridgeAngle) {
        this.maxBridgeAngle = maxBridgeAngle;
    }
    public void setMaxBridgeAngleDegree(double maxBridgeAngle) {
        this.maxBridgeAngle = Math.toRadians(maxBridgeAngle);
    }

    public void setMaxBridgeDistance(double maxBridgeDistance) {
        this.maxBridgeDistance = maxBridgeDistance;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public Result generate(File polygonsFile) throws IOException {
        FileDataStore store = FileDataStoreFinder.getDataStore(polygonsFile);

        Result result = generate(store);
        result.polygonsFile = polygonsFile;
        store.dispose();
        return result;
    }

    public Result generate(FileDataStore store) throws IOException {
        List<Polygon> allPolygons = (List<Polygon>) GeometrySelector.selectGeometries(store, GeometryType.POLYGON, true);

        Result result = generate(allPolygons);
        result.store = store;

        return result;
    }

    public Result generate(List<Polygon> allPolygons) {
        Assert.verify(minSegmentLength > 0, "minSegmentLength must be > 0");

        // select polygons
        logger.info("select polygons");
        List<Polygon> polygons = PolygonSelector.selectPolygons(allPolygons, minPolygonArea);

        // create clusters
        logger.info("create clusters");
        List<List<Polygon>> clusters = PolygonClusterer.clusterPolygons(polygons, clusterTolerance);
        int n = clusters.size();

        // create boundaries
        logger.info("create boundaries");
        List<LineString> boundaries = ClusterBoundaryGenerator.createBoundaries(clusters, boundarySides);
        Assert.verify(boundaries.size() == n, "Wrong number of boundaries");

        // remove segments too short
        logger.info("remove segments too short and merge collinear segments");
        boundaries = BoundarySimplifier.simplify(boundaries, minSegmentLength, maxCollinearAngle);
        Assert.verify(boundaries.size() == n, "Wrong number of boundaries");

        // split segments too long
        logger.info("split segments too long");
        boundaries = BoundarySegmenter.segmentate(boundaries, maxSegmentLength);
        Assert.verify(boundaries.size() == n, "Wrong number of boundaries");

        // compute outward directions
        logger.info("compute outward directions");
        List<OutwardDirections> outwardDirections = OutwardDirections.outwardDirections(boundaries);
        Assert.verify(outwardDirections.size() == n, "Wrong number of outwardDirections");

        // compute segments intersections
        logger.info("compute segments intersections");
        List<BoundaryIntersections> boundaryIntersections = BoundaryIntersections.findBoundaryIntersections(
            outwardDirections, maxIntersectionDistance, maxIntersectionAngle);
        Assert.verify(boundaryIntersections.size() == n, "Wrong number of boundaryIntersections");

        // compute the boundary offsets
        logger.info("compute the boundary offsets");
        List<BoundaryOffsets> boundaryOffsets = BoundaryOffsets.assignBoundaryOffsets(boundaryIntersections, offsets, maxOffsetCollinearAngle, maxChamferAngle);
        Assert.verify(boundaryOffsets.size() == n, "Wrong number of boundaryOffsets");

        // expand boundaries
        logger.info("expand boundaries");
        List<BoundaryExpander> expandedBoundaries = BoundaryExpander.boundaryExpand(boundaryOffsets);
        Assert.verify(expandedBoundaries.size() == n, "Wrong number of expandedBoundaries");

        // Note: now the expanded boundary contains ALWAYS 2 points for each point in boundary

        // NO: this step IS EXCLUDED  otherwise it became complex to find the junction locations.
        //     The main problem is to hande the chamfers in extended boundary: in the original boundary, there are
        //     NO chamfers, BUT 2 segments with an outer angle > 180 degrees,
        //     The shifted (by offset)  of the segments generate the chamfer
        //     The parameter 'minJunctionChamferAngle' specify which is the minimum angle, EXCEEDING 180 degrees
        //     between 2 segments to confirm the two segmentes generate a 'chamfer'
        //     .
        //     NOTE: the expanded boundary contains MORE segments than the original boundary
        //
        //
        // simplify the boundaries: remove segments too short & collinear segments
        // update 'expandedBoundaries'
        // List<LineString> expanded = BoundarySimplifier.simplifyExpanded(expandedBoundaries, 0, 0);
        // Assert.verify(expanded.size() == n, "Wrong number of expanded");

        // just extract the expanded boundaries from BoundaryExpander data structure
        List<LineString> expanded = BoundaryExpander.expandedBoundaries(expandedBoundaries);
        Assert.verify(expanded.size() == n, "Wrong number of expandedBoundaries");

        // find the chamfer locations
        List<BoundaryChamfers> chamferLocations = ChamfersLocator.findChamferLocations(expandedBoundaries, maxChamferAngle);
        Assert.verify(chamferLocations.size() == n, "Wrong number of expandedBoundaries");

        // find the junction locations. There are 2 places to consider
        //      1) locations near chamfers, (parameter 'chamferJunctionDistance')
        //      2) some intelligent places distributed along the boundary borders (parameter 'junctionDistance')
        logger.info("find junctions");
        List<BoundaryJunctions> boundariesJunctions = JunctionsLocator.findJunctionLocations(
            chamferLocations, chamferJunctionDistance, junctionDistance
        );
        Assert.verify(chamferLocations.size() == n, "Wrong number of boundariesJunctions");

        // compare all boundary junction locations to create the junction bridges
        // A bridge is possible only if th other junction is not tto far and the angles
        // are not too extreme (less 10 degrees)
        logger.info("find junction bridges");
        List<JunctionBridge> junctionBridges = JunctionBridgeFinder.findJunctionBridges(
            boundariesJunctions, maxBridgeDistance, maxBridgeAngle
        );

        logger.info("split expanded with junctions");
        List<LineString> junctioned = JunctionsLocator.expandedWithJunctions(boundariesJunctions);

        // compose the result
        logger.info("compose the result");
        List<LineString> ticks = Flatten.flatten(expandedBoundaries.stream().map(be -> be.offsets.ticks()).toList());
        List<LineString> bridges = junctionBridges.stream().map(JunctionBridge::bridge).toList();

        Result result = new Result();
        result.allPolygons = allPolygons;
        result.polygons = polygons;
        result.clusters = clusters;

        result.boundaries = boundaries;
        result.junctioned = junctioned;
        result.bridges = bridges;
        result.ticks = ticks;

        result.outwards = outwardDirections;
        result.intersections = boundaryIntersections;
        result.expandedBoundaries = expandedBoundaries;
        result.expanded = expanded;
        result.chamferLocations = chamferLocations;
        result.junctionLocations = boundariesJunctions;
        result.junctionBridges = junctionBridges;

        return result;
    }

}
