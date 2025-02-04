import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String args[]) {
        // File I/O operations
        String fileName;
        // fileName = "[INPUT FILE NAME WITH FULL PATH]"; //input file name
        fileName = "example-input-2.txt";
        InputHandler inputHandler = new InputHandler(fileName);
        String outputName = fileName.replace("input", "output");

        // Creating a list of cities
        for (int i = 0; i < inputHandler.getRowList().size(); i++) {
            new City(inputHandler.getRowList().get(i));
        }

        int totalTravelled;
        ArrayList<Integer> route;
        // Creating a adjacency matrix to use in TSP Solver
        MatrixCreator matrixCreator = new MatrixCreator();

        System.out.println("create solver");
        TSPSolver tspSolver = new TSPSolver(MatrixCreator.getM());
        System.out.println("create route");
        route = tspSolver.getRoute();
        System.out.println("get total distance");
        totalTravelled = tspSolver.getTotalTraveledDistance();

        System.out.println("save on file");
        // File out operation
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter(outputName);
            myWriter.write(String.valueOf(totalTravelled));
            for (Integer i : route) {
                myWriter.write('\n');
                myWriter.write(String.valueOf(i));
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
