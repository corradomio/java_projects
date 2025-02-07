package org.hls.examples.salesman;

import com.google.inject.Inject;
import org.opt4j.core.start.Constant;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SalesmanProblem {
    protected Set<City> cities = new HashSet<>();

    @Inject
    public SalesmanProblem(@Constant(value = "size") int size) {
        Random random = new Random(0);
        for (int i = 0; i < size; i++) {
            final double x = random.nextDouble() * 100;
            final double y = random.nextDouble() * 100;
            final City city = new City(x, y);
            cities.add(city);
        }
    }
    public Set<City> getCities() {
        return cities;
    }
}
