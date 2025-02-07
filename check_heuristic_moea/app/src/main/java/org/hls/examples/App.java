package org.hls.examples;

import org.moeaframework.algorithm.NSGAII;
import org.moeaframework.analysis.plot.Plot;
import org.moeaframework.problem.DTLZ.DTLZ2;
import org.moeaframework.problem.Problem;

public class App {
    public static void main(String[] args) {
        Problem problem = new DTLZ2(2);

        NSGAII algorithm = new NSGAII(problem);
        algorithm.run(10000);

        algorithm.getResult().display();

        new Plot()
            .add("NSGA-II", algorithm.getResult())
            .show();
    }
}
