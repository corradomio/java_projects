package org.hls.examples.salesman;

import org.opt4j.core.genotype.PermutationGenotype;
import org.opt4j.core.problem.Decoder;

public class SalesmanDecoder implements Decoder<PermutationGenotype<City>, SalesmanRoute> {
    public SalesmanRoute decode(PermutationGenotype<City> genotype) {
        SalesmanRoute salesmanRoute = new SalesmanRoute();
        for (City city : genotype) {
            salesmanRoute.add(city);
        }
        return salesmanRoute;
    }
}
