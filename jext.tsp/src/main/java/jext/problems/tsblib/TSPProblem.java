package jext.problems.tsblib;

import jext.optim.problems.Coords;
import jext.optim.problems.Distances;
import jext.optim.problems.distance.DistanceMatrix;
import jext.optim.problems.distance.EuclideanSpace;
import jext.optim.problems.distance.Point;
import jext.util.ArrayUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TSPProblem {

    /**
     * Load a TSPLIB problem.
     * Documentation onf the file format is available in
     *
     * http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/tsp95.pdf
     */
    public static TSPProblem load(File problemFile) throws IOException {
        String line;
        TSPProblem tsp = new TSPProblem();

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



    private static final List<String> SUPPORTED_PROBLEM_TYPES = Arrays.asList(
        "TSP",
        "CVRP", "OVRP", "DCVRP", "ACVRP"
    );
    private static final List<String> SUPPORTED_EDGE_WEIGHT_TYPES = Arrays.asList(
        "EUC_2D", "EUC_3D",
        "CEIL_2D", "CEIL_3D",
        "EXACT_2D", "EXACT_3D",     // VRP
        "GEO", "EXPLICIT",
        "ATT",                      // ???
        "XRAY1", "XRAY2"            // ???
    );

    // ----------------------------------------------------------------------
    // Local fields
    // ----------------------------------------------------------------------

    private Status status = Status.INIT;
    
    // TSP problems

    private String name = "noname";
    private String comment = "";
    private float[][] distanceMatrix;

    private String type = "";
    private String problemType = "";
    private String edgeWeightType = "";
    private String edgeWeightFormat = "";

    private String displayDataType = "";
    private String nodeCoordType = "";

    private int dimension = -1;
    private final List<Coords> points = new ArrayList<>();
    private final List<Float> edgeWeights = new ArrayList<>();
    
    // VRP problems

    private int vehicles;
    private int capacity;
    private double distance;
    private double serviceTime = -1;
    private Map<Integer, Integer> locationsDemand = new LinkedHashMap<>();
    private List<Integer> deposits = new ArrayList<>();

    private Writer writer;

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public TSPProblem() {

    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public String getName() {
        return this.name;
    }

    public int getDimension() {
        return this.dimension;
    }

    public Distances getDistances() {
        if (distanceMatrix != null) {
            return new DistanceMatrix(distanceMatrix);
        }
        else {
            return new EuclideanSpace(this.points);
        }
    }

    public double getCapacity() {
        return capacity;
    }

    public double getDistance() {
        return distance;
    }

    public double getServiceTime() {
        return serviceTime;
    }

    public int getVehicles() {
        return vehicles;
    }

    // ----------------------------------------------------------------------

    public void setName(String name) {
        setName(name, "");
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
        this.type = problemType;
    }

    public void setName(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setVehicles(int vehicles) {
        setVehicles(vehicles, 1);
    }

    public void setVehicles(int vehicles, int capacity) {
        this.vehicles = vehicles;
        this.capacity = capacity;
    }

    public void setPoints(Coords[] points) {
        setPoints(points, "EUC_2D");
    }

    public void setPoints(Coords[] points, String edgeWeightType) {
        setPoints(Arrays.asList(points), edgeWeightType);
    }

    public void setPoints(List<Coords> points) {
        setPoints(points, "EUC_2D");
    }

    public void setPoints(List<Coords> points, String edgeWeightType) {
        this.points.clear();
        this.points.addAll(points);
        this.edgeWeightType = edgeWeightType;
        this.dimension = points.size();
    }

    public void setEdges(Distances distances) {
        // check if it is possible to use the point coordinates
        int n = distances.size();

        this.dimension = distances.size();
        this.edgeWeightType = "EXPLICIT";
        this.edgeWeightFormat = "UPPER_ROW";

        this.edgeWeights.clear();

        // upper triangular excluding the diagonal

        for (int i=0; i<n; ++i)
            for (int j=i+1; j<n; ++j)
                this.edgeWeights.add(distances.distance(i, j));
    }

    public void setDeposits(int n) {
        deposits.clear();
        for (int i=1; i<=n; ++i)
            deposits.add(i);
    }

    public void setDeposits(int... deposits) {
        setDeposits(ArrayUtils.asList(deposits));
    }

    public void setDeposits(List<Integer> dlist) {
        deposits.clear();
        deposits.addAll(dlist);

    }

    public void setLocationsDemand(int nDeposits, int[] locationDemands) {
        int m = locationDemands.length;
        this.locationsDemand.clear();
        for (int i=0; i<m; ++i)
            this.locationsDemand.put(1 + nDeposits + i, locationDemands[i]);
    }

    // ----------------------------------------------------------------------
    // Parsing
    // ----------------------------------------------------------------------

    public void parse(String line) {
        if (line.startsWith("EOF")) {
            this.finalizeEdgeWeights();
            this.finalizeProblem();
        }

        // UNSUPPORTED
        else if (line.startsWith("DISPLAY_DATA_TYPE"))
            this.displayDataType = parseString(line);
        else if (line.startsWith("NODE_COORD_TYPE"))
            this.nodeCoordType = parseString(line);


        else if (line.startsWith("NAME"))
            this.name = parseString(line);
        else if (line.startsWith("COMMENT")) {
            if (this.comment.length() == 0) {
                this.comment = parseString(line);
            } else {
                this.comment += "\n" + parseString(line);
            }
        }
        else if (line.startsWith("TYPE")) {
            this.type = parseString(line);
            this.problemType = stripProblemType(this.type);
            this.checkProblemType();
        }

        else if (line.startsWith("DIMENSION"))
            this.dimension = Integer.parseInt(parseString(line));
        else if (line.startsWith("CAPACITY"))
            this.capacity = (int)parseDouble(line);
        else if (line.startsWith("VEHICLES"))
            this.vehicles = parseInt(line);
        else if (line.startsWith("DISTANCE"))
            this.distance = parseDouble(line);
        else if (line.startsWith("SERVICE_TIME"))
            this.serviceTime = parseDouble(line);

        else if (line.startsWith("EDGE_WEIGHT_TYPE")) {
            this.edgeWeightType = parseString(line);
            this.checkEdgeWeightType();
        }
        else if (line.startsWith("EDGE_WEIGHT_FORMAT")) {
            this.edgeWeightFormat = parseString(line);
            this.checkEdgeWeightFormat();
        }

        // TSP
        else if (line.startsWith("NODE_COORD_SECTION"))
            this.status = Status.NODE_COORD_SECTION;
        else if (line.startsWith("EDGE_WEIGHT_SECTION"))
            this.status = Status.EDGE_WEIGHT_SECTION;
        else if (line.startsWith("DISPLAY_DATA_SECTION"))
            this.status = Status.NODE_COORD_SECTION;

        // VRP
        else if (line.startsWith("DEMAND_SECTION"))
            this.status = Status.DEMAND_SECTION;
        else if (line.startsWith("DEPOT_SECTION"))
            this.status = Status.DEPOT_SECTION;
        else if (line.startsWith("BACKHAUL_SECTION"))
            this.status = Status.BACKHAUL_SECTION;

        else if (this.status == Status.DEMAND_SECTION)
            parseDemand(line);
        else if (this.status == Status.DEPOT_SECTION)
            parseDepot(line);
        else if (this.status == Status.BACKHAUL_SECTION)
            System.err.println("Unsupported BACKHAUL_SECTION line: " + line);

        else if (status == Status.NODE_COORD_SECTION)
            this.parseNodeCoords(line);
        else if (status == Status.EDGE_WEIGHT_SECTION)
            this.parseEdgeWeights(line);
        else
            System.err.println("Unparsed line: " + line);
    }

    // ----------------------------------------------------------------------
    //  label : value

    private String parseString(String line) {
        int pos = line.indexOf(':');
        return line.substring(pos + 1).strip();
    }

    private int parseInt(String line) {
        return Integer.parseInt(parseString(line));
    }

    private double parseDouble(String line) {
        return Double.parseDouble(parseString(line));
    }

    private float[] parseValues(String[] parts) {
        float[] values = new float[parts.length];
        for(int i=0; i<parts.length; ++i)
            values[i] = Float.parseFloat(parts[i]);
        return values;
    }

    private String stripProblemType(String type) {
        int pos = type.indexOf(' ');
        return (pos == -1) ? type : type.substring(0, pos);
    }

    // ----------------------------------------------------------------------

    private void finalizeEdgeWeights() {
        int k = 0;

        if (edgeWeights.isEmpty())
            return;

        distanceMatrix = new float[this.dimension][this.dimension];

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

    private void finalizeProblem() {
        if (distanceMatrix != null)
            return;

        dimension = Math.min(dimension,  this.points.size());
    }

    // ----------------------------------------------------------------------

    private void parseNodeCoords(String line) {
        String[] parts = line.split("\\s+");
        if (parts.length == 4) {
            int id = Integer.parseInt(parts[0]);
            float x = Float.parseFloat(parts[1]);
            float y = Float.parseFloat(parts[2]);
            float z = Float.parseFloat(parts[3]);

            points.add(new Point(id, x, y, z));
        }
        else if (parts.length == 3) {
            int id = Integer.parseInt(parts[0]);
            float x = Float.parseFloat(parts[1]);
            float y = Float.parseFloat(parts[2]);

            points.add(new Point(id, x, y));
        }
        else {
            throw new UnsupportedOperationException(line);
        }

    }

    private void parseEdgeWeights(String line) {
        float[] values = parseValues(line.split("\s+"));

        for (float v : values)
            this.edgeWeights.add(v);
    }

    private void parseDemand(String line) {
        String[] parts = line.split("\\s+");
        int node = Integer.parseInt(parts[0]);
        int demand = Integer.parseInt(parts[1]);

        locationsDemand.put(node, demand);
    }

    private void parseDepot(String line) {
        int depot = Integer.parseInt(line);
        if (depot < 0) return;
        deposits.add(depot);
    }

    // ----------------------------------------------------------------------

    // private static List<String> SUPPORTED_PROBLEM_TYPES = Arrays.asList("TSP");

    private void checkProblemType() {
        if (!SUPPORTED_PROBLEM_TYPES.contains(problemType))
            throw new UnsupportedOperationException("Unsupported problem type: " + problemType);
    }

    private void checkEdgeWeightType() {
        if (!SUPPORTED_EDGE_WEIGHT_TYPES.contains(edgeWeightType))
            throw new UnsupportedOperationException("Unsupported edge weight type: " + edgeWeightType);
    }

    private static final List<String> SUPPORTED_EDGE_WEIGHT_FORMATS = Arrays.asList(
        "FULL_MATRIX",
        "UPPER_ROW", "UPPER_DIAG_ROW",
        "LOWER_ROW", "LOWER_DIAG_ROW",
        "UPPER_COL", "UPPER_DIAG_COL",
        "LOWER_COL", "LOWER_DIAG_COL",

        "FUNCTION"      // ???
    );

    private void checkEdgeWeightFormat() {
        if (!SUPPORTED_EDGE_WEIGHT_FORMATS.contains(edgeWeightFormat))
            throw new UnsupportedOperationException("Unsupported edge weight format: " + edgeWeightFormat);
    }

    // ----------------------------------------------------------------------
    // Saving
    // ----------------------------------------------------------------------

    // alias
    public void save(File file) {
        file = file.getAbsoluteFile();
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();

        try(Writer w = new FileWriter(file)) {
            this.writer = w;
            write();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void write() {
        write("NAME", name);
        writeComment();
        write("TYPE", problemType);
        write("DIMENSION", dimension);
        writeVehicles();
        write("EDGE_WEIGHT_TYPE", edgeWeightType);
        writeEdgeWeightFormat();
        writeDisplayDataType();
        writeCapacity();
        writeDistance();
        writeServiceTime();
        writeNodeCoordType();

        writeEdgeWeightSection();
        writeNodeSectionCoords();
        writeDemandSection();
        writeDepotSection();
        eof();
    }

    private void write(String label, String value){
        write(String.format("%s : %s", label, value));
    }

    private void write(String label, int value){
        write(String.format("%s : %d", label, value));
    }

    private void write(String line) {
        try {
            writer.write(line);
            writer.write("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void eof(){
        write("EOF");
    }

    // ----------------------------------------------------------------------

    private void writeComment() {
        if (comment.isEmpty())
            return;
        String[] parts = comment.split("\n");
        for(String part : parts)
            write("COMMENT", part);
    }

    private void writeEdgeWeightFormat() {
        if (!edgeWeightFormat.isEmpty())
            write("EDGE_WEIGHT_FORMAT", edgeWeightFormat);
    }

    private void writeDisplayDataType() {
        if (!displayDataType.isEmpty())
            write("DISPLAY_DATA_TYPE", displayDataType);
    }

    private void writeNodeCoordType() {
        if (!nodeCoordType.isEmpty())
            write("NODE_COORD_TYPE", nodeCoordType);
    }

    private void writeVehicles() {
        if (vehicles != 0)
            write("VEHICLES", vehicles);
    }

    private void writeCapacity(){
        if (capacity != 0)
            write("CAPACITY", (int)capacity);
    }

    private void writeDistance(){
        if (distance != 0)
            write("DISTANCE", (int)distance);
    }

    private void writeServiceTime(){
        if (serviceTime != -1)
            write("SERVICE_TIME", (int)serviceTime);
    }

    private void writeEdgeWeightSection() {
        if (edgeWeights.isEmpty())
            return;

        write("EDGE_WEIGHT_SECTION");
        for(int i=0; i<edgeWeights.size(); ++i) {
            writeReal(edgeWeights.get(i));
            if (i%10 == 9) write("");
        }
        // add a new line ONLY if the las row is not composed by 10 columns
        if (edgeWeights.size()%10 != 0)
            write("");
    }

    private void writeInt(int value) {
        try {
            writer.write(String.format("%5d ", (int)value));
        }
        catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }

    private void writeReal(double value) {
        try {
            writer.write(String.format("%5f ", value));
        }
        catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }

    private void writeNodeSectionCoords(){
        if (points.isEmpty())
            return;

        int i = 1;
        write("NODE_COORD_SECTION");
        for(Coords p : points) {
            write(String.format("%3d %5f %5f", i++, p.getX(), p.getY()));
        }
    }

    private void writeDemandSection(){
        if (locationsDemand.isEmpty())
            return;

        int n = deposits.size();

        write("DEMAND_SECTION");
        for (int i=0; i<n; ++i)
            write(String.format("%d %d", i+1, 0));

        for (Map.Entry<Integer, Integer> entry : locationsDemand.entrySet()) {
            write(String.format("%d %d", entry.getKey(), entry.getValue()));
        }
    }

    private void writeDepotSection(){
        if (deposits.isEmpty())
            return;

        write("DEPOT_SECTION");
        for (Integer depot : deposits) {
            write(depot.toString());
        }
        write("-1");
    }

}
