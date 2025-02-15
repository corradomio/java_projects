package jext.problems.vrp.lkh;

import jext.problems.Coords;
import jext.problems.dist.TourDistances;
import jext.problems.tsblib.TSPProblem;
import jext.problems.vrp.AbstractVRP;
import jext.problems.vrp.Solution;
import jext.util.ArrayOps;
import jext.util.FileUtils;

import java.io.File;
import java.util.Optional;

/*
    http://webhotel4.ruc.dk/~keld/research/LKH-3/


    CVRP: Capacitated vehicle routing problem
    DCVRP: Distance constrained capacitated vehicle routing problem
 */

public class LinKernighanHeuristicVRP extends AbstractVRP {

    @Override
    public Solution solve() {

        // 1) create the VRP problem
        TSPProblem vrp = composeProblem();

        // 2) compose the input file for LKH
        File problemFile = composeProblemFile(vrp);
        File parametersFile = composeParameterFile(vrp, problemFile);

        // 3) call LKH.exe
        File resultFile = callExecutable(parametersFile);

        // 3) parse the result
        int[][] vehiclesTour = parseResultFile(resultFile);

        return new Solution(distances, vehiclesTour);
    }

    private TSPProblem composeProblem() {

        int nVehicles = ArrayOps.sum(numDepositVehicles);
        TourDistances tourDistances = new TourDistances(distances).withDepositsAndLocations(deposits, locations);
        Optional<Coords[]> coords = tourDistances.coordinates();

        TSPProblem vrp = new TSPProblem();
        vrp.setName("test");
        vrp.setProblemType("CVRP");
        vrp.setVehicles(nVehicles);
        if (coords.isEmpty())
            vrp.setEdges(tourDistances);
        else
            vrp.setPoints(coords.get(), "EUC_2D");

        vrp.setCapacity(constraints.maxVehicleCapacity);

        vrp.setDeposits(deposits.length);
        vrp.setLocationsDemand(deposits.length, locationsDemand);

        return vrp;
    }

    private File composeProblemFile(TSPProblem vrp) {

        File problemFile = new File("lkh-test.vrp");
        vrp.save(problemFile);

        return problemFile;
    }

    private File composeParameterFile(TSPProblem vrp, File problemFile) {
        File parametersFile = FileUtils.replaceExtension(problemFile, ".par");
        File tourFile = FileUtils.replaceExtension(problemFile, ".tour");
        File solutionFile = FileUtils.replaceExtension(problemFile, ".sol");
        int dimension = vrp.getDimension();

        LKHParameters par = (LKHParameters) this.parameters;
        par.problemFile = problemFile;
        par.tourFile = tourFile;
        par.solutionFile = solutionFile;
        par.vehicles = ArrayOps.sum(this.numDepositVehicles);

        par.maxTrials = dimension;
        par.salesmen = dimension - this.deposits.length;
        par.excess = 1./dimension;

        par.save(parametersFile);
        return parametersFile;
    }

    private File callExecutable(File problemFile) {

        return problemFile;
    }

    private int[][] parseResultFile(File resultFile) {
        return new int[0][0];
    }
}
