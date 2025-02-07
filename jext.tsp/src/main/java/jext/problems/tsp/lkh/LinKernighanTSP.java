package jext.problems.tsp.lkh;

import jext.problems.tsp.AbstractTSP;
import jext.problems.tsp.Solution;
import jext.problems.tsp.TourUtils;

import java.util.ArrayList;

/**
 * Implementation of the Lin-Kernighan heuristic to solve the Symmetric TSP problem
 *
 *     https://en.wikipedia.org/wiki/Lin%E2%80%93Kernighan_heuristic
 *     http://akira.ruc.dk/~keld/research/LKH/
 *     http://akira.ruc.dk/~keld/research/LKH-3/
 *     https://arxiv.org/abs/1003.5330
 *     https://github.com/RodolfoPichardo/LinKernighanTSP
 *     https://github.com/JakeCoxon/kernighan-lin
 */
public class LinKernighanTSP extends AbstractTSP {

    /*
        Original code:

        https://github.com/RodolfoPichardo/LinKernighanTSP
     */

    /**
     * This class is meant for representing the edges, it allows to store
     * the endpoints ids and compare the edges
     */
    private static class Edge implements Comparable<Edge> {
        /*
         * Instance variables
         */

        // The first node
        private int endPoint1;

        // The second node
        private int endPoint2;

        /**
         * Constructor that takes the two endpoints id
         * @param a the id of the first endpoint
         * @param b the id of the second endpoint
         */
        public Edge(int a, int b) {
            this.endPoint1 = a > b? a:b;
            this.endPoint2 = a > b? b:a;
        }

        /**
         * Getter that returns the first endpoint id
         *
         * @return the first endpoint id
         */
        public int get1() {
            return this.endPoint1;
        }

        /**
         * Getter that returns the second endpoint id
         *
         * @return the second endpoint id
         */
        public int get2() {
            return this.endPoint2;
        }

        /**
         * Method that compares two edges, here to make this class Comparable
         * @see java.lang.Comparable
         * @param e2 the edge that is going to be compared against this one
         * @return int will return -1 if less, 0 if equal, and 1 if greater
         */
        public int compareTo(Edge e2) {
            if(this.get1() < e2.get1() || this.get1() == e2.get1() && this.get2() < e2.get2()) {
                return -1;
            } else if (this.equals(e2)) {
                return 0;
            } else {
                return 1;
            }

        }

        /**
         * Method that compares two edges, here to make this class Comparable
         * @see java.lang.Comparable
         * @param e2 the edge that is going to be compared against this one
         * @return boolean true if both share the same endpoints, false otherwise
         */
        public boolean equals(Edge e2) {
            if(e2 == null) return false;
            return (this.get1() == e2.get1()) && (this.get2() == e2.get2());
        }

        public String toString() {
            return "("+ endPoint1 + ", " + endPoint2 + ")";
        }

    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private int[] tour;
    private int size;
    
    // ----------------------------------------------------------------------
    // 
    // ----------------------------------------------------------------------

    public LinKernighanTSP() {
        
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    protected Solution solve() {
        size = distances.size();
        tour = TourUtils.randomTour(size);

        runAlgorithm();

        return new Solution(distances.distances(), distances.resolve(tour));
    }

    /**
     * This function is the crown jewel of this class, it tries to optimize
     * the current tour
     * 
     * @return void
     */
    private void runAlgorithm() {
        double oldDistance = 0;
        double newDistance = getDistance();

        do {
            oldDistance = newDistance;
            improve();
            newDistance = getDistance();
        } while(newDistance < oldDistance);
    }

    /**
     * This function returns the current tour distance
     *
     * @return double the distance of the tour
     */
    private double getDistance() {
        double sum = 0;

        for(int i = 0; i < this.size; i++) {
            int a = tour[i];                  // <->
            int b = tour[(i+1)%this.size];    // <->
            sum += this.distances.distance(a, b);
        }

        return sum;
    }

    /**
     * This function tries to improve the tour
     * 
     * @return void
     */
    private void improve() {
        for(int i = 0; i < size; ++i) {
            improve(i);
        }
    }

    /**
     * This functions tries to improve by stating from a particular node
     * @param x the reference to the city to start with.
     * @return void
     */
    private void improve(int x){
        improve(x, false);
    }

    /**
     * This functions attempts to improve the tour by stating from a particular node
     * @param t1 the reference to the city to start with.
     * @return void
     */
    private void improve(int t1, boolean previous) {
        int t2 = previous? getPreviousIdx(t1): getNextIdx(t1);
        int t3 = getNearestNeighbor(t2);

        if(t3 != -1 && getDistance(t2, t3) < getDistance(t1, t2)) { // Implementing the gain criteria
            startAlgorithm(t1,t2,t3);
        } else if(!previous) {
            improve(t1, true);
        }
    }

    /**
     * This function returns the previous index for the tour, this typically should be x-1
     *  but if x is zero, well, it is the last index.
     *  @param index the index of the node
     *  @return the previous index
     */
    private int getPreviousIdx(int index) {
        return index == 0? size-1: index-1;
    }

    /**
     * This function returns the next index for the tour, this typically should be x+1
     *  but if x is the last index it should wrap to zero
     *  @param index the index of the node
     *  @return the next index
     */
    private int getNextIdx(int index) {
        return (index+1)%size;
    }

    /**
     * This function returns the nearest neighbor for an specific node
     * @param index the index of the node
     * @return the index of the nearest node
     */
    private int getNearestNeighbor(int index) {
        double minDistance = Double.MAX_VALUE;
        int nearestNode = -1;
        int actualNode = tour[index];
        for(int i = 0; i < size; ++i) {
            if(i != actualNode) {
                double distance = this.distances.distance(i, actualNode);
                if(distance < minDistance) {
                    nearestNode = getIndex(i);
                    minDistance = distance;
                }
            }
        }
        return nearestNode;
    }

    /**
     * This functions retrieves the distance between two nodes given its indexes
     * @param n1 index of the first node
     * @param n2 index of the second node
     * @return double the distance from node 1 to node 2
     */
    private double getDistance(int n1, int n2) {
        return distances.distance(tour[n1], tour[n2]);
    }

    /**
     * This function is actually the step four from the lin-kernighan's original paper
     * @param t1 the index that references the chosen t1 in the tour
     * @param t2 the index that references the chosen t2 in the tour
     * @param t3 the index that references the chosen t3 in the tour
     * @return void
     */
    private void startAlgorithm(int t1, int t2, int t3) {
        ArrayList<Integer> tIndex = new ArrayList<Integer>();
        tIndex.add(0, -1); // Start with the index 1 to be consistent with Lin-Kernighan Paper
        tIndex.add(1, t1);
        tIndex.add(2, t2);
        tIndex.add(3, t3);
        double initialGain = getDistance(t2, t1) - getDistance(t3, t2); // |x1| - |y1|
        double GStar = 0;
        double Gi = initialGain;
        int k = 3;
        for(int i = 4;; i+=2) {
            int newT = selectNewT(tIndex);
            if(newT == -1) {
                break; // This should not happen according to the paper
            }
            tIndex.add(i, newT);
            int tiplus1 = getNextPossibleY(tIndex);
            if(tiplus1 == -1) {
                break;
            }


            // Step 4.f from the paper
            Gi += getDistance(tIndex.get(tIndex.size()-2), newT);
            if(Gi - getDistance(newT, t1) > GStar) {
                GStar = Gi - getDistance(newT, t1);
                k = i;
            }

            tIndex.add(tiplus1);
            Gi -= getDistance(newT, tiplus1);


        }
        if(GStar > 0) {
            tIndex.set(k+1, tIndex.get(1));
            tour = getTPrime(tIndex, k); // Update the tour
        }

    }

    /**
     * This function gets all the ys that fit the criterion for step 4
     * @param tIndex the list of t's
     * @return an array with all the possible y's
     */
    private int getNextPossibleY(ArrayList<Integer> tIndex) {
        int ti = tIndex.get(tIndex.size() - 1);
        ArrayList<Integer> ys = new ArrayList<Integer>();
        for(int i = 0; i < size; ++i) {
            if(!isDisjunctive(tIndex, i, ti)) {
                continue; // Disjunctive criteria
            }

            if(!isPositiveGain(tIndex, i)) {
                continue; // Gain criteria
            };
            if(!nextXPossible(tIndex, i)) {
                continue; // Step 4.f.
            }
            ys.add(i);
        }

        // Get closest y
        double minDistance = Double.MAX_VALUE;
        int minNode = -1;
        for(int i: ys) {
            if(getDistance(ti, i) < minDistance) {
                minNode = i;
                minDistance = getDistance(ti, i);
            };
        }

        return minNode;

    }

    /**
     * This function implements the part e from the point 4 of the paper
     * @param tIndex
     * @param i
     * @return
     */
    private boolean nextXPossible(ArrayList<Integer> tIndex, int i) {
        return isConnected(tIndex, i, getNextIdx(i)) || isConnected(tIndex, i, getPreviousIdx(i));
    }

    private boolean isConnected(ArrayList<Integer> tIndex, int x, int y) {
        if(x == y) return false;
        for(int i = 1; i < tIndex.size() -1 ; i+=2) {
            if(tIndex.get(i) == x && tIndex.get(i + 1) == y) return false;
            if(tIndex.get(i) == y && tIndex.get(i + 1) == x) return false;
        }
        return true;
    }

    /**
     *
     * @param tIndex
     * @param ti
     * @return true if the gain would be positive 
     */
    private boolean isPositiveGain(ArrayList<Integer> tIndex, int ti) {
        int gain = 0;
        for(int i = 1; i < tIndex.size() - 2; ++i) {
            int t1 = tIndex.get(i);
            int t2 = tIndex.get(i+1);
            int t3 = i == tIndex.size()-3? ti :tIndex.get(i+2);

            gain += getDistance(t2, t3) - getDistance(t1,t2); // |yi| - |xi|


        }
        return gain > 0;
    }

    /**
     * This function gets a new t with the characteristics described in the paper in step 4.a.
     * @param tIndex
     * @return
     */
    private int selectNewT(ArrayList<Integer> tIndex) {
        int option1 = getPreviousIdx(tIndex.get(tIndex.size()-1));
        int option2 = getNextIdx(tIndex.get(tIndex.size()-1));

        int[] tour1 = constructNewTour(tour, tIndex, option1);

        if(isTour(tour1)) {
            return option1;
        } else {
            int[] tour2 = constructNewTour(tour, tIndex, option2);
            if(isTour(tour2)) {
                return option2;
            }
        }
        return -1;
    }

    private int[] constructNewTour(int[] tour2, ArrayList<Integer> tIndex, int newItem) {
        ArrayList<Integer> changes = new ArrayList<Integer>(tIndex);

        changes.add(newItem);
        changes.add(changes.get(1));
        return constructNewTour(tour2, changes);
    }

    /**
     * This function validates whether a sequence of numbers constitutes a tour
     * @param tour an array with the node numbers
     * @return boolean true or false
     */
    private boolean isTour(int[] tour) {
        if(tour.length != size) {
            return false;
        }

        for(int i =0; i < size-1; ++i) {
            for(int j = i+1; j < size; ++j) {
                if(tour[i] == tour[j]) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Construct T prime
     */
    private int[] getTPrime(ArrayList<Integer> tIndex, int k) {
        ArrayList<Integer> al2 = new ArrayList<Integer>(tIndex.subList(0, k + 2 ));
        return constructNewTour(tour, al2);
    }

    /**
     * This function constructs a new Tour deleting the X sets and adding the Y sets
     * @param tour The current tour
     * @param changes the list of t's to derive the X and Y sets
     * @return an array with the node numbers
     */
    private int[] constructNewTour(int[] tour, ArrayList<Integer> changes) {
        ArrayList<Edge> currentEdges = deriveEdgesFromTour(tour);

        ArrayList<Edge> X = deriveX(changes);
        ArrayList<Edge> Y = deriveY(changes);
        int s = currentEdges.size();

        // Remove Xs
        for(Edge e: X) {
            for(int j = 0; j < currentEdges.size(); ++j) {
                Edge m = currentEdges.get(j);
                if(e.equals(m)) {
                    s--;
                    currentEdges.set(j, null);
                    break;
                }
            }
        }

        // Add Ys
        for(Edge e: Y) {
            s++;
            currentEdges.add(e);
        }


        return createTourFromEdges(currentEdges, s);

    }

    /**
     * This function takes a list of edges and converts it into a tour
     * @param currentEdges The list of edges to convert
     * @return the array representing the tour
     */
    private int[] createTourFromEdges(ArrayList<Edge> currentEdges, int s) {
        int[] tour = new int[s];

        int i = 0;
        int last = -1;

        for(; i < currentEdges.size(); ++i) {
            if(currentEdges.get(i) != null) {
                tour[0] = currentEdges.get(i).get1();
                tour[1] = currentEdges.get(i).get2();
                last = tour[1];
                break;
            }
        }

        currentEdges.set(i, null); // remove the edges

        int k=2;
        while(true) {
            // E = find()
            int j = 0;
            for(; j < currentEdges.size(); ++j) {
                Edge e = currentEdges.get(j);
                if(e != null && e.get1() == last) {
                    last = e.get2();
                    break;
                } else if(e != null && e.get2() == last) {
                    last = e.get1();
                    break;
                }
            }
            // If the list is empty
            if(j == currentEdges.size()) break;

            // Remove new edge
            currentEdges.set(j, null);
            if(k >= s) break;
            tour[k] = last;
            k++;
        }

        return tour;
    }

    /**
     * Get the list of edges from the t index
     * @param changes the list of changes proposed to the tour
     * @return The list of edges that will be deleted
     */
    private ArrayList<Edge> deriveX(ArrayList<Integer> changes) {
        ArrayList<Edge> es = new ArrayList<Edge>();
        for(int i = 1; i < changes.size() - 2; i+=2) {
            Edge e = new Edge(tour[changes.get(i)], tour[changes.get(i+1)]);
            es.add(e);
        }
        return es;
    }

    /**
     * Get the list of edges from the t index
     * @param changes the list of changes proposed to the tour
     * @return The list of edges that will be added
     */
    private ArrayList<Edge> deriveY(ArrayList<Integer> changes) {
        ArrayList<Edge> es = new ArrayList<Edge>();
        for(int i = 2; i < changes.size() - 1; i+=2) {
            Edge e = new Edge(tour[changes.get(i)], tour[changes.get(i+1)]);
            es.add(e);
        }
        return es;
    }


    /**
     * Get the list of edges from the tour, it is basically a conversion from 
     * a tour to an edge list
     * @param tour the array representing the tour
     * @return The list of edges on the tour
     */
    private ArrayList<Edge> deriveEdgesFromTour(int[] tour) {
        ArrayList<Edge> es = new ArrayList<Edge>();
        for(int i = 0; i < tour.length ; ++i) {
            Edge e = new Edge(tour[i], tour[(i+1)%tour.length]);
            es.add(e);
        }

        return es;
    }

    /**
     * This function allows to check if an edge is already on either X or Y (disjunctivity criteria)
     * @param tIndex the index of the nodes in the tour
     * @param x the index of one of the endpoints
     * @param y the index of one of the endpoints
     * @return true when it satisfy the criteria, false otherwise
     */
    private boolean isDisjunctive(ArrayList<Integer> tIndex, int x, int y) {
        if(x == y) return false;
        for(int i = 0; i < tIndex.size() -1 ; i++) {
            if(tIndex.get(i) == x && tIndex.get(i + 1) == y) return false;
            if(tIndex.get(i) == y && tIndex.get(i + 1) == x) return false;
        }
        return true;
    }


    /**
     * This function gets the index of the node given the actual number of the node in the tour
     * @param node node id
     * @return the index on the tour
     */
    private int getIndex(int node) {
        int i = 0;
        for(int t: tour) {
            if(node == t) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /**
     * This function returns a string with the current tour and its distance
     *
     * @return String with the representation of the tour
     */
    @Override
    public String toString() {
        String str = "[" + this.getDistance() + "] : ";
        boolean add = false;
        for(int city: this.tour) {
            if(add) {
                str += " => " + city;
            } else {
                str += city;
                add = true;
            }
        }
        return str;
    }
}
