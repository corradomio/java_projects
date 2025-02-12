package jext.problems.tsp;

import jext.problems.Distances;
import jext.problems.dist.DistanceMatrix;
import jext.problems.dist.EuclideanSpace;
import jext.problems.dist.Point;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Load a TSPLIB problem.
 * Documentation onf the file format is available in
 *
 * http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/tsp95.pdf
 */
public class TSPLIBProblem {

    public static TSPLIBProblem load(File problemFile) throws IOException {
        String line;
        TSPLIBProblem tsp = new TSPLIBProblem();

        try(BufferedReader br = new BufferedReader(new FileReader(problemFile))) {
            while ((line = br.readLine()) != null) {
                line = line.trim().strip();
                if (line.isEmpty()) continue;

                // System.out.println(line);
                tsp.parse(line);
            }
        }

        return tsp;
    }

    // ----------------------------------------------------------------------
    // Local fields
    // ----------------------------------------------------------------------

    // private static class Point {
    //     int id;
    //     double x, y, z;
    //
    //     Point(int id, double x, double y) {
    //         this.id = id-1;
    //         this.x = x;
    //         this.y = y;
    //     }
    //
    //     Point(int id, double x, double y, double z) {
    //         this.id = id-1;
    //         this.x = x;
    //         this.y = y;
    //         this.z = z;
    //     }
    //
    //     private static double sq(double x) { return x*x; }
    //
    //     double distance(Point that) {
    //         return sqrt(sq(this.x - that.x) + sq(this.y - that.y) + sq(this.z - that.z));
    //     }
    //
    //     private static final double EARTH_RADIUS = 6371.0088; // Km
    //
    //     double distanceGeo(Point that) {
    //         double dlat, dlon, a, c, R;
    //
    //         double lat1 = this.x;
    //         double lon1 = this.y;
    //         double lat2 = that.x;
    //         double lon2 = that.y;
    //
    //         R = EARTH_RADIUS;
    //         lat1 = toRadians(lat1);
    //         lon1 = toRadians(lon1);
    //         lat2 = toRadians(lat2);
    //         lon2 = toRadians(lon2);
    //         dlon = lon2 - lon1;
    //         dlat = lat2 - lat1;
    //
    //         // haversine(dlat) := sq(sin(dlat/2))
    //         a = sq(sin(dlat/2)) + cos(lat1) * cos(lat2) * sq(sin(dlon/2));
    //         c = 2 * atan2(sqrt(a), sqrt(1-a));
    //
    //         return R * c;
    //     }
    // }

    private enum Status {
        INIT,
        NODE_COORDS,
        EXPLICIT_EDGE_WEIGHTS
    }

    private String name = "noname";
    private String comment = "";
    private double[][] distanceMatrix;

    private Status status = Status.INIT;
    private String problemType = "";
    private String edgeWeightType = "";
    private String edgeWeightFormat = "";
    private int dimension = -1;
    private List<Point> points = new ArrayList<Point>();
    private List<Double> edgeWeights = new ArrayList<>();

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private TSPLIBProblem() {}

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public String getName() {
        return this.name;
    }

    public int size() {
        return this.dimension;
    }

    public Distances distanceMatrix() {
        if (distanceMatrix != null) {
            return new DistanceMatrix(distanceMatrix);
        }
        else {
            return new EuclideanSpace(this.points);
        }
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private void parse(String line) {
        if (line.startsWith("NAME"))
            this.name = parsePair(line);
        else if (line.startsWith("COMMENT")) {
            if (this.comment.length() == 0) {
                this.comment = parsePair(line);
            } else {
                this.comment += "\n" + parsePair(line);
            }
        }
        else if (line.startsWith("TYPE")) {
            this.problemType = parsePair(line);
            //
            // resolve the problem with  "TYPE: TSP (M.~Hofmeister)"
            //
            if (this.problemType.length() > 3 && this.problemType.startsWith("TSP"))
                this.problemType = "TSP";
            this.checkProblemType();
        }
        else if (line.startsWith("DIMENSION")) {
            this.dimension = Integer.parseInt(parsePair(line));
            // this.distanceMatrix = new double[dimension][dimension];
        }

        else if (line.startsWith("EDGE_WEIGHT_TYPE")) {
            this.edgeWeightType = parsePair(line);
            this.checkEdgeWeightType();
        }
        else if (line.startsWith("EDGE_WEIGHT_FORMAT")) {
            this.edgeWeightFormat = parsePair(line);
            this.checkEdgeWeightFormat();
        }

        else if (line.startsWith("NODE_COORD_SECTION"))
            this.startNodeCoords();
        else if (line.startsWith("EDGE_WEIGHT_SECTION"))
            this.startEdgeWeights();
        else if (line.startsWith("DISPLAY_DATA_SECTION"))
            startNodeCoords();
        else if (line.startsWith("EOF")) {
            this.finalizeEdgeWeights();
            this.finalizeProblem();
        }

        else if (status == Status.NODE_COORDS)
            this.parseNodeCoords(line);
        else if (status == Status.EXPLICIT_EDGE_WEIGHTS)
            this.parseEdgeWeights(line);
        else
            ; //System.err.println("Unparsed line: " + line);
    }

    // ----------------------------------------------------------------------

    private String parsePair(String line) {
        int pos = line.indexOf(':');
        return line.substring(pos + 1).strip();
    }

    private void startNodeCoords() {
        this.status = Status.NODE_COORDS;
    }

    private void startEdgeWeights() {
        this.status = Status.EXPLICIT_EDGE_WEIGHTS;
    }

    private void finalizeEdgeWeights() {
        int k = 0;

        if (edgeWeights.isEmpty())
            return;

        distanceMatrix = new double[this.dimension][this.dimension];

        // FULL_MATRIX
        // UPPER_ROW
        // LOWER_ROW
        // UPPER_DIAG_ROW
        // LOWER_DIAG_ROW
        // UPPER_COL
        // LOWER_COL
        // UPPER_DIAG_COL
        // LOWER_DIAG_COL

        if (edgeWeightFormat.equals("FULL_MATRIX")) {
            for (int i=0; i<dimension; i++)
                for (int j=0; j<dimension; j++, k++)
                    distanceMatrix[i][j] = edgeWeights.get(k);
        }
        // UPPER_ [DIAG_] [ROW|COL]
        else if (edgeWeightFormat.startsWith("UPPER_")) {
            int of = edgeWeightFormat.contains("_DIAG_") ? 0 : 1;
            for (int i=0; i<dimension; i++)
                for (int j=i+of; j<dimension; j++, k++) {
                    distanceMatrix[i][j] = edgeWeights.get(k);
                    distanceMatrix[j][i] = distanceMatrix[i][j];
                }
        }
        // LOWER_ [DIAG_] [ROW|COL]
        else if (edgeWeightFormat.startsWith("LOWER_")) {
            int of = edgeWeightFormat.contains("_DIAG_") ? 0 : 1;
            for (int i=0; i<dimension; i++)
                for (int j=0; j<i-of; j++, k++) {
                    distanceMatrix[i][j] = edgeWeights.get(k);
                    distanceMatrix[j][i] = distanceMatrix[i][j];
                }
        }
    }

    private static List<String> VERTEX_TO_DISTANCE = Arrays.asList(
        "EUC_2D", "EUC_3D",
        "CEIL_2D", "CEIL_3D", "ATT");

    private void finalizeProblem() {
        if (distanceMatrix != null)
            return;

        dimension = Math.min(dimension,  this.points.size());
        // distanceMatrix = new double[dimension][dimension];
        //
        // if (VERTEX_TO_DISTANCE.contains(this.edgeWeightType)) {
        //     for (int i=0; i<dimension; ++i) {
        //         for (int j=i+1; j<dimension; ++j) {
        //             Point pi = this.points.get(i);
        //             Point pj = this.points.get(j);
        //
        //             distanceMatrix[pi.id][pj.id] = pi.distance(pj);
        //             distanceMatrix[pj.id][pi.id] = pi.distance(pj);
        //         }
        //     }
        // }
        // else if (this.edgeWeightType.equals("GEO")) {
        //     for (int i=0; i<dimension; ++i) {
        //         for (int j=i+1; j<dimension; ++j) {
        //             Point pi = this.points.get(i);
        //             Point pj = this.points.get(j);
        //
        //             distanceMatrix[pi.id][pj.id] = pi.distanceGeo(pj);
        //             distanceMatrix[pj.id][pi.id] = pi.distanceGeo(pj);
        //         }
        //     }
        // }
    }

    // ----------------------------------------------------------------------

    private void parseNodeCoords(String line) {
        String[] parts = line.split("\\s+");
        if (parts.length == 4) {
            int id = Integer.parseInt(parts[0]);
            double x = Double.parseDouble(parts[1]);
            double y = Double.parseDouble(parts[2]);
            double z = Double.parseDouble(parts[3]);

            points.add(new Point(id, x, y, z));
        }
        else if (parts.length == 3) {
            int id = Integer.parseInt(parts[0]);
            double x = Double.parseDouble(parts[1]);
            double y = Double.parseDouble(parts[2]);

            points.add(new Point(id, x, y));
        }
        else {
            throw new UnsupportedOperationException();
        }

    }

    private void parseEdgeWeights(String line) {
        double[] values = parseValues(line.split("\s+"));

        for (double v : values)
            this.edgeWeights.add(v);

    }

    // private void setdm(int i, int j, double value) {
    //     distanceMatrix[i][j] = value;
    //     distanceMatrix[j][i] = value;
    // }

    private double[] parseValues(String[] parts) {
        double[] values = new double[parts.length];
        for(int i=0; i<parts.length; ++i)
            values[i] = Double.parseDouble(parts[i]);
        return values;
    }

    // ----------------------------------------------------------------------

    private static List<String> SUPPORTED_PROBLEM_TYPES = Arrays.asList("TSP");

    private void checkProblemType() {
        if (!SUPPORTED_PROBLEM_TYPES.contains(problemType))
            throw new UnsupportedOperationException("Unsupported problem type: " + problemType);
    }

    private static List<String> SUPPORTED_EDGE_WEIGHT_TYPES = Arrays.asList(
        "EUC_2D", "EUC_3D",
        "CEIL_2D", "CEIL_3D",
        "GEO", "ATT", "EXPLICIT",

        "XRAY1", "XRAY2"        // ???
    );

    private void checkEdgeWeightType() {
        if (!SUPPORTED_EDGE_WEIGHT_TYPES.contains(edgeWeightType))
            throw new UnsupportedOperationException("Unsupported edge type: " + edgeWeightType);
    }

    private static List<String> SUPPORTED_EDGE_WEIGHT_FORMATS = Arrays.asList(
        "FULL_MATRIX",
        "UPPER_ROW", "UPPER_DIAG_ROW",
        "LOWER_ROW", "LOWER_DIAG_ROW",
        "UPPER_COL", "UPPER_DIAG_COL",
        "LOWER_COL", "LOWER_DIAG_COL",

        "FUNCTION"      // ???
    );

    private void checkEdgeWeightFormat() {
        if (!SUPPORTED_EDGE_WEIGHT_FORMATS.contains(edgeWeightFormat))
            throw new UnsupportedOperationException("Unsupported edge format: " + edgeWeightFormat);
    }
}
