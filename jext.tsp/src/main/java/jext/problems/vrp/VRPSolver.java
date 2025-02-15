package jext.problems.vrp;

import jext.problems.Distances;

public interface VRPSolver {

    /**
     * Solve the TSP problem: found the best permutation of locations
     * based on the distance matrix
     *
     * @param distances distances matrix
     * @param locations locations to visit
     * @param vehicles where the vehicles are
     * @param constraints problem constraints
     * @param parameters algorithm parameters
     * @return the solution
     */
    Solution solve(final Distances distances,
                   final int[] locations,
                   final int[] vehicles,
                   final VRPConstraints constraints,
                   final VRPParameters parameters);

    /**
     * Solve the TSP problem: found the best permutation of locations
     * based on the distance matrix
     *
     * @param distances distances matrix
     * @param locations locations to visit
     * @param deposits where the deposits are located
     * @param numDepositVehicles n of vehicles for each deposit
     * @param constraints problem constraints
     * @param parameters algorithm parameters
     * @return the solution
     */
    Solution solve(final Distances distances,
                   final int[] locations,
                   final int[] deposits,
                   final int[] numDepositVehicles,
                   final VRPConstraints constraints,
                   final VRPParameters parameters);


    /**
     *
     * @param distances distances matrix
     * @param locations locations to visit
     * @param locationsDemand locations demands
     * @param deposits where the deposits are located
     * @param numDepositVehicles n of vehicles for each deposit
     * @param constraints problem constraints
     * @param parameters algorithm parameters
     * @return the solution
     */
    Solution solve(final Distances distances,
                   final int[] locations, final int[] locationsDemand,
                   final int[] deposits,final  int[] numDepositVehicles,
                   final VRPConstraints constraints,
                   final VRPParameters parameters);
}
