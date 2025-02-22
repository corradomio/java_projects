package jext.optim.domain.permutation;

import jext.optim.domain.CandidateFactory;

import java.util.random.RandomGenerator;

public class PermutationFactory implements CandidateFactory<Permutation> {

    private int n;

    public PermutationFactory(int n) {
        this.n = n;
    }

    @Override
    public int length() {
        return n;
    }

    @Override
    public Permutation candidate(RandomGenerator rng) {
        int[] perm = PermUtils.random(n, rng);
        return new Permutation(perm);
    }
}
