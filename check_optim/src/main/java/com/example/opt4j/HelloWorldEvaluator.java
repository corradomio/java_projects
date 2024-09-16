package com.example.opt4j;

import org.opt4j.core.Objective;
import org.opt4j.core.Objectives;
import org.opt4j.core.problem.Evaluator;

public class HelloWorldEvaluator implements Evaluator<String> {
    @Override
    public Objectives evaluate(String phenotype) {
        int value = 0;
        for (int i = 0; i < phenotype.length(); i++) {
            value += (phenotype.charAt(i) == "HELLO WORLD".charAt(i)) ? 1 : 0;
        }
        Objectives objectives = new Objectives();
        objectives.add("objective", Objective.Sign.MAX, value);
        return objectives;
    }
}