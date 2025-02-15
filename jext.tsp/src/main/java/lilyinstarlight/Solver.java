/*
	https://github.com/lilyinstarlight/TSP
 */
package lilyinstarlight;

import java.util.HashSet;

/**
 * Solves the traveling salesman problem using Branch and Bound by utilizing Node's
 */
public class Solver {
	private float[][] distances;
	private float best_cost;
	private int[] best_path;

	/**
	 * Constructs a new Solver and initializes distances array
	 */
	public Solver(float[][] distances) {
		this.distances = distances;
	}

	/**
	 * Calculates the shortest (non-repeating) path between a series of nodes
	 *
	 * @return An array with the locations of the best path
	 */
	public int[] calculate() {
		HashSet<Integer> location_set = new HashSet<Integer>(distances.length);
		for(int i = 0; i < distances.length; i++)
			location_set.add(i);

		best_cost = findGreedyCost(0, location_set, distances);

		int[] active_set = new int[distances.length];
		for(int i = 0; i < active_set.length; i++)
			active_set[i] = i;

		Node root = new Node(null, 0, distances, active_set, 0);
		traverse(root);

		return best_path;
	}

	/**
	 * Get current path cost
	 *
	 * @return The cost
	 */
	public float getCost() {
		return best_cost;
	}

	/**
	 * Find the greedy cost for a set of locations
	 *
	 * @param i The current location
	 * @param location_set Set of all remaining locations
	 * @param distances The 2D array containing point distances
	 * @return The greedy cost
	 */
	private float findGreedyCost(int i, HashSet<Integer> location_set, float[][] distances) {
		if(location_set.isEmpty())
			return distances[0][i];

		location_set.remove(i);

		float lowest = Float.MAX_VALUE;
		int closest = 0;
		for(int location : location_set) {
			float cost = distances[i][location];
			if(cost < lowest) {
				lowest = cost;
				closest = location;
			}
		}

		float greedyCost = findGreedyCost(closest, location_set, distances);

		return lowest + greedyCost;
	}

	/**
	 * Recursive method to go through the tree finding and pruning paths
	 *
	 * @param parent The root/parent node
	 */
	private void traverse(Node parent) {
		Node[] children = parent.generateChildren();

		for(Node child : children) {
			if(child.isTerminal()) {
				float cost = child.getPathCost();
				if(cost < best_cost) {
					best_cost = cost;
					best_path = child.getPath();
				}
			}
			else if(child.getLowerBound() <= best_cost) {
				traverse(child);
			}
		}
	}
}
