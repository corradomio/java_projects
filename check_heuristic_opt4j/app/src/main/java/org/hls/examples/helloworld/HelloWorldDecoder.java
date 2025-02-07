package org.hls.examples.helloworld;

import org.opt4j.core.genotype.SelectGenotype;
import org.opt4j.core.problem.Decoder;

public class HelloWorldDecoder implements Decoder<SelectGenotype<Character>, String> {
    public String decode(SelectGenotype<Character> genotype) {
        String phenotype = "";
        for (int i = 0; i < genotype.size(); i++) {
            phenotype += genotype.getValue(i);
        }
        return phenotype;
    }
}
