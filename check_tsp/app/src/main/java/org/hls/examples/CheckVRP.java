package org.hls.examples;

import ae.ac.ebtic.tools.vr.data.Location;
import ae.ac.ebtic.tools.vr.data.LocationList;
import ae.ac.ebtic.tools.vr.data.TaskList;
import ae.ac.ebtic.tools.vr.data.VehicleList;
import jext.optim.problems.Distances;
import jext.optim.problems.distance.EuclideanSpace;
import jext.problems.tsblib.TSPProblem;
import jext.problems.vrp.Solution;
import jext.problems.vrp.VRPConstraints;
import jext.problems.vrp.VRPSolver;
import jext.problems.vrp.lkh.LKHParameters;
import jext.problems.vrp.lkh.LinKernighanHeuristicVRP;
import jext.util.FileUtils;
import jext.util.logging.Logger;

import java.io.File;

public class CheckVRP {

    public static void main(String[] args) throws Exception {

        // Distances distances, int[] locations, int[] vehicles, VRPConstraints constraints

        File problemHome = new File("D:\\Projects.github\\java_projects\\check_tsp\\problems\\5000_100_50_v1");

        LocationList locationList = LocationList.load(new File(problemHome, "locations.csv"));
        TaskList taskList = TaskList.load(new File(problemHome, "tasks.csv"));
        VehicleList vehicleList = VehicleList.load(new File(problemHome, "vehicles.csv"));

        // Distance d = Distance.distanceMatrix(locationList.getLocations());

        int[] locations = taskList.getLocations(locationList.getLocations()).stream().mapToInt(Location::getId).toArray();
        int[] vehicles = vehicleList.getLocations(locationList.getLocations()).stream().mapToInt(Location::getId).toArray();

        VRPConstraints constraints = new VRPConstraints();
        LKHParameters par = new LKHParameters();
        par.maxTrials=1000;

        constraints.maxVehicleCapacity = taskList.getTasks().size() / vehicleList.getVehicles().size();

        Distances d = new EuclideanSpace(locationList.getLocations());

        // final Distances distances,
        // final int[] locations,
        // final int[] vehicles,
        // final VRPConstraints constraints
        VRPSolver solver =
            // new LinearProgrammingVRP()
            new LinKernighanHeuristicVRP()
        ;

        Solution sol = solver.solve(
            d,
            locations,
            new int[]{0},               // deposit
            new int[]{vehicles.length},  // num of vehicles in each deposit
            constraints, par);
    }

    public static void main1(String[] args) {

        Logger.configure(new File("logging.properties"));

        File vrpProblems = new File("D:\\Projects.github\\java_projects\\jext.tsp\\problems\\vrplib");
        File tstProblems = new File("D:\\Projects.github\\java_projects\\jext.tsp\\testfile\\tstlib");

        for(File vrpProblem : FileUtils.listFiles(vrpProblems, pathname -> pathname.getName().endsWith(".vrp"))) {
            try {
                System.out.println(vrpProblem);

                TSPProblem vrp = TSPProblem.load(vrpProblem);

                String relativePath = FileUtils.relativePath(vrpProblems, vrpProblem);
                File tstProblem = new File(tstProblems, relativePath);

                vrp.save(tstProblem);

                continue;
            }
            catch(Exception e) {
                e.printStackTrace();
                System.out.println();
            }
        }

        System.out.println("done");
    }

}
