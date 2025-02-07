package org.ebtic.aco.tsp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
//import java.util.HashMap;
//import java.util.HashSet;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;


public class Driver {
	static final int numberOfAnts = 5000;
	static final double processingCycleProbability = 0.8;
	static ArrayList<NetworkNode> initialRoute;
//	static ArrayList<City> initialRoute = new ArrayList<City>(Arrays.asList(
//			
////			new City("Boston", 42.3601, -71.0589),
////			new City("Houston", 29.7604,-95.3698),
////			new City("Austin", 30.2672, -97.7431),
////			new City("San Francisco", 37.7749, -122.4194),
////			new City("Denver", 39.7392, -104.9903),
////			new City("Los Angeles", 34.0522, -118.2437),
////			new City("Chicago", 41.8781, -87.6298),
////			new City("New York", 40.7128, -74.0059)
//////			,
//////			new City("Sydny", -33.8675, 151.2070),
//////			new City("Tokyo", 35.6895, 139.6917),
//////			new City("Cape Town", -33.9249, 18.4241),
//////			new City("Khor Fakkan", 25.3416700, 56.3486234),
//////			new City("Fujairah", 25.1288099, 56.3264849),
//////			new City("Hatta", 24.8061691, 56.1253848),
//////			new City("Al Ain", 24.1301619, 55.8023118),
//////			new City("Khalifa University", 24.4472344, 54.3946966),
//////			new City("RAK", 25.8006926, 55.9761994),
//////			new City("The palm", 25.0072733, 54.9873447),
//////			new City("AUH", 24.4440339, 54.6494457)
//	));
	static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	static ExecutorCompletionService<Ant> executorCompletionService = new ExecutorCompletionService<Ant>(executorService);
	private Route shortestRoute = null;
	public Route getShortestRoute() {
		return shortestRoute;
	}
	private int activeAnts = 0;
	
	public static void main(String[] args) throws IOException {
		System.out.println("> "+numberOfAnts+"Artificial Ants ...");
		Driver driver = new Driver();
		driver.printHeading();
		AntColonyOptimization aco = new AntColonyOptimization();
		IntStream.range(1,numberOfAnts).forEach(x -> {
			//System.out.println("\nDriver.main: executorCompletionService.submit(new Ant())");
			executorCompletionService.submit(new Ant(aco, x));
			driver.activeAnts++;
			if (Math.random() > processingCycleProbability) driver.processAnts();
		});
		driver.processAnts();
		executorService.shutdown();
		System.out.println("\nOptimal Route : " + Arrays.toString(driver.shortestRoute.getCities().toArray()));
		System.out.println("w/ Distance   : " + driver.shortestRoute.getDistance());
	}
	
	public void run(SingleLayerNetwork net, ArrayList<NetworkNode> nodeList) throws IOException {
//		convertToCities(nodeList);
		initialRoute = nodeList;
		System.out.println("> "+numberOfAnts+"Artificial Ants ...");
		printHeading();
		AntColonyOptimization aco = new AntColonyOptimization();
		IntStream.range(1,numberOfAnts).forEach(x -> {
			//System.out.println("\nDriver.main: executorCompletionService.submit(new Ant())");
			executorCompletionService.submit(new Ant(aco, x,net));
			activeAnts++;
			if (Math.random() > processingCycleProbability) processAnts();
		});
		processAnts();
		executorService.shutdown();
		System.out.println("\nOptimal Route : " + Arrays.toString(shortestRoute.getCities().toArray()));
		System.out.println("w/ Distance   : " + shortestRoute.getDistance());
	}
//	private void convertToCities(ArrayList<NetworkNode> nodeList) {
//		// TODO Auto-generated method stub
//		initialRoute.clear();
//		for(NetworkNode customer: nodeList) {
//			String nodeID = customer.getId();
//			double nodeX = customer.getLocationX();
//			double nodeY = customer.getLocationY();
//			City city =new City(nodeID, nodeX, nodeY);
//			initialRoute.add(city);
//		}
//		System.out.println(initialRoute);
//	}

	private void processAnts() {
		while (activeAnts > 0) {
			//System.out.println("Driver.main: executorCompletionService.take()");
			try {
				Ant ant = executorCompletionService.take().get();
				Route currentRoute = ant.getRoute();
				if (shortestRoute == null || currentRoute.getDistance() < shortestRoute.getDistance()) {
					shortestRoute = currentRoute;
					StringBuffer distance = new StringBuffer("        "+String.format("%.2f", currentRoute.getDistance()));
					IntStream.range(0, 21 - distance.length()).forEach(k -> distance.append(""));
					System.out.println(Arrays.toString(shortestRoute.getCities().toArray()) + " |" + distance + "| "+ ant.getAntNumb());
				}
			} catch (Exception e) {e.printStackTrace(); }
			activeAnts--;
		}
	}
	private void printHeading() {
		String headingColumn1 = "Route";
		String remainingHeadingColumns = "Distance (in miles) | ant #";
		int cityNamesLength = 0;
		for (int x = 0; x < initialRoute.size(); x++) cityNamesLength += initialRoute.get(x).getId().length();
		int arrayLength = cityNamesLength +initialRoute.size()*2;
		int partialLength = (arrayLength - headingColumn1.length())/2;
		for (int x = 0; x < partialLength; x++) System.out.print(" ");
		System.out.print(headingColumn1);
		for (int x = 0; x < partialLength; x++) System.out.print(" ");
		if ((arrayLength % 2) == 0) System.out.print("");
		System.out.println(" | " + remainingHeadingColumns);
		cityNamesLength += remainingHeadingColumns.length() +3;
		for (int x=0; x < cityNamesLength + initialRoute.size()*2; x++) System.out.print("-");
		System.out.println("");
	}
}
