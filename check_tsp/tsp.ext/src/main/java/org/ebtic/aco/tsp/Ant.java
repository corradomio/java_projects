package org.ebtic.aco.tsp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.ebtic.aco.tsp.NetworkNode;
import org.ebtic.aco.tsp.SingleLayerNetwork;

public class Ant implements Callable<Ant> {
	public static final double Q = 0.0005;
	public static final double RHO = 0.2;
	public static final double ALPHA = 0.01;
	public static final double BETA = 9.5;
	private AntColonyOptimization aco;
	private int antNumb;
	private Route route = null;
	static int invalidCityIndex = -1;
	static int numbOfCities = Driver.initialRoute.size();
	public Route getRoute() { return route; }
	public Ant(AntColonyOptimization aco, int antNumb) {
		this.aco = aco;
		this.antNumb = antNumb;
	}
	int originatingCityIndex;
	public Ant(AntColonyOptimization aco, int antNumb, SingleLayerNetwork net) {
		// TODO Auto-generated constructor stub
		int n = Driver.initialRoute.indexOf(net.getNodesByType("MMR").get(0));
		originatingCityIndex = n;
		this.aco = aco;
		this.antNumb = antNumb;
		
		
	}
	public Ant call() throws Exception {
//		NetworkNode n= ;
//		mate=n.getEdges().get(0).getMate(n);
		ArrayList<NetworkNode> routeCities = new ArrayList<NetworkNode>(numbOfCities);
		HashMap<String,Boolean> visitedCities = new HashMap<String, Boolean>(numbOfCities);
		IntStream.range(0, numbOfCities).forEach(x -> visitedCities.put(Driver.initialRoute.get(x).getId(), false));
		int numbOfVisitedCities = 0;
		visitedCities.put(Driver.initialRoute.get(originatingCityIndex).getId(), true);
		double routeDistance = 0.0;
		int x = originatingCityIndex;
		int y = invalidCityIndex;
		if (numbOfVisitedCities != numbOfCities) 
			y = getY(x, visitedCities); //next city
		while(y != invalidCityIndex) {
			routeCities.add(numbOfVisitedCities++, Driver.initialRoute.get(x));
			routeDistance += aco.getDistancesMatrix()[x][y];
			adjustPhermoneLevel(x,y,routeDistance);
			visitedCities.put(Driver.initialRoute.get(y).getId(), true);
			x = y;
			if (numbOfVisitedCities != numbOfCities) y = getY(x, visitedCities);
			else y = invalidCityIndex;
		}
		routeDistance += aco.getDistancesMatrix()[x][originatingCityIndex]; // last city 
		routeCities.add(numbOfVisitedCities, Driver.initialRoute.get(x));
		route = new Route(routeCities, routeDistance);
		return this;
	}
	private void adjustPhermoneLevel(int x, int y, double distance) {
		boolean flag = false;
		while(!flag) {
			double currentPhermoneLevel = aco.getPhermoneLevelsMatrix()[x][y].doubleValue();
			double updatedPhermoneLevel = (1-RHO)*currentPhermoneLevel + Q/distance;
			if (updatedPhermoneLevel < 0.00) flag = aco.getPhermoneLevelsMatrix()[x][y].compareAndSet(0);
			else flag = aco.getPhermoneLevelsMatrix()[x][y].compareAndSet(updatedPhermoneLevel);
		}
	}
	private int getY(int x, HashMap<String, Boolean> visitedCities) {
		int returnY = invalidCityIndex;
		double random = ThreadLocalRandom.current().nextDouble();
		ArrayList<Double> transitionProbabilities = getTransitionProbabilities(x, visitedCities);
		for (int y = 0; y < numbOfCities; y++) {
			if (transitionProbabilities.get(y) > random) {
				returnY = y;
				break;
			}else random -= transitionProbabilities.get(y);
		}
		return returnY;
	}
	private ArrayList<Double> getTransitionProbabilities(int x, HashMap<String, Boolean> visitedCities){
		ArrayList<Double> transitionProbabilities = new ArrayList<Double> (numbOfCities);
		IntStream.range(0, numbOfCities).forEach(i -> transitionProbabilities.add(0.0));
		double denominator = getTPDenominator(transitionProbabilities, x, visitedCities);
		IntStream.range(0, numbOfCities).forEach(y -> transitionProbabilities.set(y, transitionProbabilities.get(y)/denominator));
		return transitionProbabilities;
	}
	private double getTPDenominator(ArrayList<Double> transitionProbabilities, int x, HashMap<String, Boolean> visitedCities) {
		double denominator = 0.0;
		for (int y= 0; y < numbOfCities; y++) {
			if(!visitedCities.get(Driver.initialRoute.get(y).getId())) {
				if (x == y) transitionProbabilities.set(y, 0.0);
				else transitionProbabilities.set(y, getTPNumerator(x, y));
				denominator += transitionProbabilities.get(y);
			}
		}
		return denominator;
	}
	private double getTPNumerator(int x, int y) {
		double numerator = 0.0;
		double phermonLevel = aco.getPhermoneLevelsMatrix()[x][y].doubleValue();
		if(phermonLevel != 0.0) numerator = Math.pow(phermonLevel, ALPHA) * Math.pow(1/aco.getDistancesMatrix()[x][y], BETA);
		return numerator;
	}
	public int getAntNumb() {return antNumb;}

}
