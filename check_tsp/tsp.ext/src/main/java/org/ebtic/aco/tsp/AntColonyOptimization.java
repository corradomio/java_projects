package org.ebtic.aco.tsp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

import org.ebtic.aco.tsp.NetworkNode;
import org.ebtic.aco.tsp.SingleLayerNetwork;



public class AntColonyOptimization {
	private AtomicDouble[][] phermoneLevelsMatrix = null;
	private double[][] distancesMatrix = null;
	private ArrayList<NetworkNode> cities = Driver.initialRoute;
	private int citiesSize = Driver.initialRoute.size();
	public AntColonyOptimization() throws IOException{
		initializeDistances();
		initializePhermoneLevels();
	}
	public AtomicDouble[][] getPhermoneLevelsMatrix() {return phermoneLevelsMatrix;}
	public double[][] getDistancesMatrix() {return distancesMatrix;}
	private void initializeDistances() throws IOException {
		distancesMatrix = new double[citiesSize][citiesSize];
		IntStream.range(0, citiesSize).forEach(x -> {
			NetworkNode cityY = cities.get(x);
			IntStream.range(0, citiesSize).forEach(y -> distancesMatrix[x][y] = GeometricFunctions.cartesianDistance(cityY.getLocation(), cities.get(y).getLocation()));
		});
	}
	private void initializePhermoneLevels() {
		phermoneLevelsMatrix = new AtomicDouble[citiesSize][citiesSize];
		Random random = new Random();
		IntStream.range(0, citiesSize).forEach(x -> {
			IntStream.range(0, citiesSize).forEach(y -> phermoneLevelsMatrix[x][y] = new AtomicDouble(random.nextDouble()));
		});
	}

}
