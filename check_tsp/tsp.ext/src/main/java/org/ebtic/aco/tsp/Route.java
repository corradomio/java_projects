package org.ebtic.aco.tsp;
import java.util.ArrayList;
import java.util.Arrays;

import org.ebtic.aco.tsp.NetworkNode;
import org.ebtic.aco.tsp.SingleLayerNetwork;

public class Route {
	private ArrayList<NetworkNode> cities;
	private double distance;
	public Route(ArrayList<NetworkNode> cities, double distance) {
		this.cities = cities;
		this.distance = distance;
	}
	public ArrayList<NetworkNode> getCities() { return cities;}
	public double getDistance() {return distance; }
	public String toString() { return Arrays.toString(cities.toArray()) +" | "+distance; }
}
