package jext.optim.domain.permutation;

import jext.optim.domain.CandidateNeighbours;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

public class TwoOptPermutationNeighbours implements CandidateNeighbours<Permutation> {

    @Override
    public List<Permutation> neighbours(Permutation candidate, int n, int distance, RandomGenerator rng) {
        List<Permutation> neighbours = new ArrayList<>();
        int length = candidate.length();
        for (int i = 0; i<n; ++i) {
            Permutation perm = candidate.clone();

            for (int j = 0; j < distance; ++j) {
                int s = rng.nextInt(length);
                int t = s+1; if (t >= length) t = 0;
                perm.swap(s, t);
            }
            neighbours.add(perm);
        }
        return neighbours;
    }

    @Override
    public List<Permutation> neighbours(Permutation candidate) {
        List<Permutation> neighbours = new ArrayList<>();
        int length = candidate.length();
        for (int s = 0; s<length; ++s) {
            Permutation perm = candidate.clone();

            int t = s+1; if (t >= length) t = 0;
            perm.swap(s, t);

            neighbours.add(perm);
        }
        return neighbours;
    }
}
