package org.hls.examples.salesman;

import com.google.inject.Inject;
import org.opt4j.core.genotype.PermutationGenotype;
import org.opt4j.core.problem.Creator;

import java.util.Collections;

public class SalesmanCreator implements Creator<PermutationGenotype<City>> {
    protected final SalesmanProblem problem;
    @Inject
    public SalesmanCreator(SalesmanProblem problem) {
        this.problem = problem;
    }
    @Override
    public PermutationGenotype<City> create() {
        PermutationGenotype<City> genotype = new PermutationGenotype<>();
        for (City city : problem.getCities()) {
            genotype.add(city);
        }
        Collections.shuffle(genotype);
        return genotype;
    }
}
