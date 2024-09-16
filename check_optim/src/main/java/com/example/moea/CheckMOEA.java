package com.example.moea;

import org.moeaframework.algorithm.NSGAII;
import org.moeaframework.core.NondominatedPopulation;
import org.moeaframework.core.Problem;
import org.moeaframework.problem.CEC2009.UF1;

public class CheckMOEA {

    public static void main(String[] args) {
        Problem problem = new UF1();

        NSGAII algorithm = new NSGAII(problem);
        algorithm.setInitialPopulationSize(250);
        algorithm.run(10000);

        NondominatedPopulation result = algorithm.getResult();

        System.out.println(result);
    }
}
