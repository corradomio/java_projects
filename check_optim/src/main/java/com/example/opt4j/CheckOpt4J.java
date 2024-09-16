package com.example.opt4j;


import org.opt4j.core.Objectives;
import org.opt4j.core.genotype.SelectGenotype;

public class CheckOpt4J {

    public static void main(String[] args) {

        HelloWorldCreator c = new HelloWorldCreator();
        SelectGenotype<Character> genotype = c.create();

        HelloWorldDecoder d = new HelloWorldDecoder();
        String phenotype = d.decode(genotype);

        HelloWorldEvaluator eval = new HelloWorldEvaluator();
        Objectives objectives = eval.evaluate(phenotype);
    }
}
