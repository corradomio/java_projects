package jext.optim.domain.reals;

import jext.optim.domain.CandidateNeighbours;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

public class VectorNeighbours implements CandidateNeighbours<Vector> {

    private final double delta;

    public VectorNeighbours(double delta) {
        this.delta = delta;
    }

    @Override
    public List<Vector> neighbours(Vector candidate, int n, int distance, RandomGenerator rng) {
        List<Vector> neighbours = new ArrayList<Vector>();
        int length = candidate.length();
        for (int i=0; i<n; ++i) {
            Vector v = candidate.clone();

            for (int j=0; j<distance; ++j) {
                int index = rng.nextInt(length);
                double step = rng.nextGaussian() * delta;
                v.add(index, step);
            }

            neighbours.add(v);
        }
        return neighbours;
    }

    @Override
    public List<Vector> neighbours(Vector candidate) {
        Vector v;
        List<Vector> neighbours = new ArrayList<Vector>();
        int length = candidate.length();

        for (int i=0; i<length; ++i) {
            v = candidate.clone();
            v.add(i, delta);
            neighbours.add(v);

            v = candidate.clone();
            v.add(i, -delta);
            neighbours.add(v);
        }
        return neighbours;
    }
}
