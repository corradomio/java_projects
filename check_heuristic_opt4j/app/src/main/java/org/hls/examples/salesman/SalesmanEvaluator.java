package org.hls.examples.salesman;

import org.opt4j.core.Objective;
import org.opt4j.core.Objectives;
import org.opt4j.core.problem.Evaluator;

public class SalesmanEvaluator implements Evaluator<SalesmanRoute> {
    public Objectives evaluate(SalesmanRoute salesmanRoute) {
        double dist = 0;
        for (int i = 0; i < salesmanRoute.size(); i++) {
            City one = salesmanRoute.get(i);
            City two = salesmanRoute.get((i + 1) % salesmanRoute.size());
            dist += getEuclideanDistance(one, two);
        }
        Objectives objectives = new Objectives();
        objectives.add("distance", Objective.Sign.MIN, dist);
        return objectives;
    }
    private double getEuclideanDistance(City one, City two) {
        final double x = one.getX() - two.getX();
        final double y = one.getY() - two.getY();
        return Math.sqrt(x * x + y * y);
    }
}
