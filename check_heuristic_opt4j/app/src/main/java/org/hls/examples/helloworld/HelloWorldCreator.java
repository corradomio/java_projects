package org.hls.examples.helloworld;

import org.opt4j.core.genotype.SelectGenotype;
import org.opt4j.core.problem.Creator;

import java.util.Random;

public class HelloWorldCreator implements Creator<SelectGenotype<Character>> {
    protected Character[] ALPHABET = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
        'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ' };
    protected Random random = new Random();
    @Override
    public SelectGenotype<Character> create() {
        SelectGenotype<Character> genotype = new SelectGenotype<>(ALPHABET);
        genotype.init(random, 11);
        return genotype;
    }
}
