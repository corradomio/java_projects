package jext.problems.vrp.lkh;

import jext.problems.vrp.VRPParameters;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/// Parameters to write in ".par" file
/// LKH-2_USER_GUIDE.pdf
/// LKH-3_PARAMETERS.pdf
///
///
/// v2
/// --
/// The parameter file contains control parameters for the solution process. The solution process is typically carried
/// out using default values for the parameters. The default values have
/// proven to be adequate in many applications. Actually, almost all computational tests reported in this paper have
/// been made using these default settings. The only information that cannot be left out is the name of the problem file.
///
/// During the solution process information about the progress being made is written to standard output. The user may
/// control the level of detail of this information (by the value of the
/// TRACE_LEVEL parameter).
/// Before the program terminates, a summary of key statistics is written to standard output,
/// and, if specified by the TOUR_FILE parameter, the best tour found is written to a file (in
/// TSPLIB format).
///
/// v3
/// --
/// Parameters to LKH-3 are specified in a parameter file. All entries are of the form
/// <keyword> = <value> (or <keyword><whitespace><value>), where <keyword> denotes
/// an alphanumeric keyword and <value> denotes alphanumeric or numeric data. Keywords
/// are not case sensitive.
///
/// The order of specifications in the file is arbitrary. The following specification is mandatory
///
/// http://webhotel4.ruc.dk/~keld/research/LKH-3/
///
public class LKHParameters extends VRPParameters {

    //
    // LKH v2
    //

    /// Specifies the name of the problem file.
    public File problemFile;

    /// Specifies the name of a file to which the candidate sets are to be written. If, however, the file already
    /// exists, the candidate edges are read from the file. The first line of the file contains the dimension of
    /// the instance. Each of the following lines contains a node number, the number of the dad of the node
    /// in the minimum spanning tree (0, if the node has no dad), the number of candidate edges emanating
    /// from the node, followed by the candidate edges. For each candidate edge its end node number and
    /// alpha-value are given. It is possible to give more than one CANDIDATE_FILE specification. In this
    /// case the given files are read and the union of their candidate edges is used as candidate sets.
    public File candidateFile;

    /// A comment.
    public String comment = "";

    /// The number of candidate edges to be associated with each node during the ascent. The candidate set
    /// is complemented such that every candidate edge is associated with both its two end nodes.
    public int ascentCandidates = 50;

    /// The number of backbone trials in each run.
    public int backboneTrials = 0;

    /// Specifies whether a backtracking K-opt move is to be used as the first move in a sequence of moves
    /// (where K = MOVE_TYPE).
    public boolean backtracking = false;

    /// Specifies the candidate set type. ALPHA is LKH's default type. It is applicable in general. The other
    /// three types can only be used for instances given by coordinates. The optional suffix PURE for the
    /// DELAUNAY type specifies that only edges of the Delaunay graph are used as candidates.
    /// { ALPHA | DELAUNAY \[PURE\] | NEAREST-NEIGHBOR | QUADRANT }
    public String candidateSetType = "ALPHA";

    /// The maximum alpha-value allowed for any candidate edge is set to EXCESS times the absolute
    /// value of the lower bound of a solution tour (determined by the ascent).
    /// Default: value of 1.0/DIMENSION
    public double excess;     // 1/DIMENSION

    /// Number of extra candidate edges to be added to the candidate set of each node. Their candidate set
    /// type may be specified using the keyword EXTRA_CANDIDATE_SET_ TYPE. The integer may be
    /// followed by the keyword SYMMETRIC, signifying that these extra candidate edges is to be complemented such that
    /// each of them is associated with both its two end nodes.
    public int extraCandidates = 0;

    /// The candidate set type of extra candidate edges.
    ///  { NEAREST-NEIGHBOR | QUADRANT }
    public String extraCandidateSetType = "QUADRANT";

    /// Specifies whether the Gain23 function is used,
    public boolean gain23 = false; // default true;

    /// Specifies whether Lin and Kernighan’s gain criterion is used.
    public boolean gainCriterion = true;

    /// The length of the first period in the ascent.
    /// Default: value of DIMENSION/2 (but at least 100).
    public int initialPeriod = 100;   // DIMENSION/2

    /// The initial step size used in the ascent
    public int initialStepSize = 1;

    /// Specifies the algorithm for obtaining an initial tour.
    /// { BORUVKA | GREEDY | MOORE | NEAREST-NEIGHBOR | QUICK-BORUVKA | SIERPINSKI | WALK }
    public String initialTourAlgorithm = "WALK";

    /// Specifies the name of a file containing a tour to be used as the initial tour in the search. The tour is
    /// given by a list of integers giving the sequence in which the nodes are visited in the tour. The tour is
    /// terminated by a -1.
    public File initialTourFile;

    ///  Specifies the fraction of the initial tour to be constructed by means of INITIAL_ TOUR_FILE edges.
    public double initialTourFraction = 1;

    /// Specifies the name of a file containing a tour. The tour is used to limit the search (the last edge to be
    /// excluded in a non-gainful move must not belong to the tour). In addition, the Alpha field of its edges
    /// is set to zero. The tour is given by a list of integers giving the sequence in which the nodes are visited in
    /// the tour. The tour is terminated by a -1.
    public File inputTourFile;

    /// Specifies the value of K for a random K-swap kick (an extension of the double-bridge move). If
    /// KICK_TYPE is zero, then the LKH's special kicking strategy, WALK, is used.
    public int kickType = 4; // default 0;

    /// Specifies the number of times to "kick" a tour found by Lin-Kernighan. Each kick is a random Kswap kick.
    /// However, if KICKS is zero, then LKH's special kicking strategy, WALK, is used instead.
    public int kicks = 1;

    /// Specifies the maximum number of candidate edges considered at each level of the search for a move.
    public int maxBreath = Integer.MAX_VALUE;

    /// The maximum number of candidate edges to be associated with each node. The integer may be followed by the keyword SYMMETRIC, signifying that the candidate set is to be complemented such
    /// that every candidate edge is associated with both its two end nodes. If MAX_CANDIDATES is zero
    /// the candidate sets are made up of the edges represented in the CANDIDATE_FILEs, the
    /// INITIAL_TOUR_FILE, the INPUT_TOUR_FILE, the SUBPROBLEM_TOUR_FILE, and the
    /// MERGE_TOUR_FILEs.
    public int maxCandidates = 5;

    /// Specifies the maximum number of swaps (flips) allowed in any search for a tour improvement.
    /// Default: value of DIMENSION.
    public int maxSwaps;

    /// The maximum number of trials in each run.
    /// Default: number of nodes (DIMENSION, given in the problem file).
    public int maxTrials = -1;

    /// Specifies the name of a tour to be merged. The edges of the tour are added to the candidate sets. It is
    /// possible to give more than two MERGE_TOUR_FILE specifications.
    public File mergeTourFile;

    /// Specifies the sequential move type to be used in local search. A value K >= 2 signifies that a sequential K-opt move is to be used.
    public int moveType = 5;

    /// Specifies the nonsequential move type to be used. A value K >= 4 signifies that attempts are made to
    /// improve a tour by nonsequential k-opt moves where 4 <= k <= K. Note, however, that the effect depends on the specifications of PATCHING_A and PATCHING_B.
    /// Default: value of (MOVE_TYPE + PATCHING_A + PATCHING_B - 1)
    public int nonSequentialMoveType;

    /// Specifies the name of a file where the best tour is to be written. Each time a trial has produced a new
    /// best tour, the tour is written to this file. The character '$' in the name has a special meaning. All occurrences are replaced by the cost of the tour.
    public File outputTourFile;

    /// Known optimal tour length. If STOP_AT_OPTIMUM is YES, a run will be terminated if the tour
    /// length becomes equal to this value.
    /// Default: value of -LLONG_MIN
    public double optimum;

    /// The maximum number of disjoint alternating cycles to be used for patching. An attempt to patch cycles is made if the corresponding non-sequential move is gainful. The integer may be followed by
    /// the keyword RESTRICTED or EXTENDED. The keyword RESTRICTED signifies that gainful
    /// moves are only considered if all its inclusion edges are candidate edges. The keyword EXTENDED
    /// signifies that the non-sequential move need not be gainful if only all its inclusion edges are candidate
    /// edges.
    public int patchingA = 1;

    /// The maximum number of disjoint cycles to be patched in an attempt to find a feasible and gainful
    /// move. An attempt to patch cycles is made if the corresponding non-sequential move is gainful. The
    /// integer may be followed by the keyword RESTRICTED or EXTENDED. The keyword
    /// RESTRICTED signifies that gainful moves are only considered if all its inclusion edges are candidate edges. The keyword EXTENDED signifies that the non-sequential move need not be gainful if
    /// only all its inclusion edges are candidate edges.
    public int patchingC = 0;

    /// Specifies the name of a file to which penalties (Pi-values determined by the ascent) are to be written.
    /// If the file already exists, the penalties are read from the file, and the ascent is skipped. The first line
    /// of the file contains the number of nodes. Each of the following lines is of the form
    /// <integer> <integer>
    /// where the first integer is a node number, and the second integer is the Pi-value associated with the
    /// node. The file name "0" represents a file with all Pi-values equal to zero.
    public File piFile;

    /// Specifies the maximum size of the population in LKH’s genetic algorithm. Tours found by the first
    /// POPULATION_SIZE runs constitute an initial population of tours. In each of the remaining runstwo
    /// tours (parents) from the current population is recombined into a new tour (child) using a variant of
    /// the Edge Recombination Crossover (ERX). The parents are chosen with random linear bias towards
    /// the best members of the population. The child is used as initial tour for the next run. If this run
    /// produces a tour better than the worst tour of the population, then the resulting tour replaces the worst
    /// tour. Premature convergence is avoided by requiring that all tours in the population have different
    /// costs.
    public int populationSize = 0;

    /// The internal precision in the representation of transformed distances:
    /// d\[i\]\[j\] = PRECISION*c\[i\]\[j\] + pi\[i\] + pi\[j\],
    /// where d\[i\]\[j\], c\[i\]\[j\], pi\[i\] and pi\[j\] are all integral.
    public int precision = 100;

    /// Specifies whether the following search pruning technique is used: The first edge to be broken in a
    /// move must not belong to the currently best solution tour. When no solution tour is known, it must
    /// not belong to the minimum spanning 1-tree.
    public boolean restrictedSearch = true;

    /// The total number of runs.
    public int runs = 10;

    /// Specifies the initial seed for random number generation
    public int seed = 1;

    /// Specifies whether a run is stopped, if the tour length becomes equal
    /// to OPTIMUM.
    public boolean stopAtOptimum = true;

    /// Specifies whether the pi-values should be determined by subgradient optimization.
    public boolean subgradient = true;

    /// The number of nodes in a division of the original problem into subproblems. The division is made
    /// according to the tour given by SUBPROBLEM_TOUR_FILE. The value 0 signifies that no division
    /// is made. By default the subproblems are determined by subdividing the tour into segments of equal
    /// size. However, the integer may be followed by DELAUNAY, KARP, K-MEANS, ROHE, or
    /// SIERPINSKI. DELAUNAY signifies that a Delaunay partitioning scheme is used, KARP that Karp's
    /// partitioning scheme is used, K-MEANS that a partitioning scheme based on K-means clustering is
    /// used, ROHE that André Rohes’s random rectangle partitioning scheme is used, and MOORE or
    /// SIERPINSKI that a partitioning scheme based on either a Moore or a Sierpinski space-filling curve
    /// is used. The BORDERS specification signifies that the subproblems along the borders between
    /// subproblems are to be solved too. The COMPRESSED specification signifies that each subproblem
    /// is compressed by removing from the problem all nodes with two incident subproblem tour edges that
    /// belong to all tours to be merged (at least two MERGE_TOUR_FILEs should be given).
    ///
    ///  \[DELAUNAY|KARP|K-MEANS|MOORE|SIERPINSKI\]
    ///  \[BORDERS\] \[COMPRESSED\]
    public int subproblemSize = 0;

    /// Specifies the name of a file containing a tour to be used for dividing the original problem into subproblems. The approximate number of nodes in each is given by SUBPROBLEM_SIZE. The tour is
    /// given by a list of integers giving the sequence in which the nodes are visited in the tour. The tour is
    /// terminated by a -1
    public File subproblemTourFile;

    /// Specifies the move type to be used for all moves following the first move in a sequence of moves.
    /// The value K >= 2 signifies that a K-opt move is to be used. The value 0 signifies that all moves are
    /// of the same type (K = MOVE_TYPE).
    public int subsequentMoveType = 0;

    /// Specifies whether patching is used for moves following the first move in a sequence of moves.
    public boolean subsequentPatching = true;

    /// Specifies a time limit in seconds for each run.
    public double timeLimit = Double.MAX_VALUE;

    /// Specifies the name of a file to which the best tour is to be written.
    public File tourFile;

    /// Specifies the level of detail of the output given during the solution process. The value 0 signifies a
    /// minimum amount of output. The higher the value is the more information is given.
    public int traceLevel = 1;

    //
    // LKH v3
    //

    /// Specifies the three parameters (B, Q, L) to a BWTSP instance.
    /// B: Number of black nodes.
    /// Q: Maximum number of white nodes on "black-to-black" paths.
    /// L: Maximum length of any "black-to-black" path.
    public int[] BWTSP = new int[3];

    /// Specifies the depot node.
    public int depot = 1;

    /// Specifies if makespan optimization is to be used for a TSPTW instance.
    public boolean makeSpan = false;

    /// Specifies the minimum number of cities each salesman must visit in an MTSP or CVRP
    /// instance. If negative, its value is set to
    /// DIMENSION/(ceil(1.0*DIMENSION/MTSP_MAX_SIZE) + 1)
    public int MTSPMinSize = 0;

    /// Specifies the maximum number of cities each salesman may visit in an MTSP or CVRP
    /// instance.
    public int MTSPMaxSize  = 1;

    /// Specifies the objective function type for an MTSP instance.
    /// MINMAX - Minimize the length of the longest route.
    /// MINMAX_SIZE - Minimize the size of the largest route.
    /// MINSUM - Minimize the length of the overall tour.
    /// All its routes must satisfy the MTSP_MIN_SIZE
    /// and MTSP_MAX_SIZE constraints.
    public String MTSPObjective;

    /// Specifies the name of a file where the solution of an MTSP (or VRP) instance is to be
    /// written. The character $ in the name has a special meaning. All occurrences are replaced
    /// by the cost of the tour.
    // public File MTSPSolutionFile;
    public File solutionFile;

    /// Specifies the number of salesmen in an MTSP or VRP instance.
    public int salesmen = 1;

    /// Scale factor for Euclidean and ATT instances.
    public int scale = 1;

    /// Specifies the name of a file where the solution of an MTSP or VRP instance is to be written.
    /// The solution is written in SINTEF format. The character $ in the name has a special meaning. All occurrences
    /// are replaced by the cost of the tour.
    public File sintefSolutionFile;

    /// Specifies the number of vehicles/salesmen in an MTSP or VRP instance.
    public int vehicles = 1;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private Writer writer;

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
        write("SPECIAL");
        write("PROBLEM_FILE", problemFile.getName());
        write("MAX_TRIALS", maxTrials);
        write("RUNS", runs);
        write("INITIAL_PERIOD", maxTrials/10);
        write("INITIAL_TOUR_ALGORITHM", "CVRP");

        write("TOUR_FILE", tourFile.getName());
        write("MTSP_SOLUTION_FILE", solutionFile.getName());

        write("TRACE_LEVEL", 1);
        eof();
    }

    private void write(String label, String value){
        write(String.format("%s = %s", label, value));
    }

    private void write(String label, int value){
        if (value >= 0)
            write(String.format("%s = %d", label, value));
    }

    private void write(String label, double value){
        if (value >= 0)
            write(String.format("%s = %f", label, value));
    }

    private void write(String label, boolean value){
        write(String.format("%s = %s", label, value ? "YES" : "NO"));
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

}
