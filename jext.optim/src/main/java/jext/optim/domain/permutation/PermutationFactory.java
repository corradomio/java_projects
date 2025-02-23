package jext.optim.domain.permutation;

import jext.optim.domain.CandidateFactory;

import java.util.random.RandomGenerator;

public class PermutationFactory implements CandidateFactory<Permutation> {

    private int length;

    public PermutationFactory(int length) {
        this.length = length;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public Permutation candidate(RandomGenerator rng) {
        int[] perm = PermUtils.random(length, rng);
        return new Permutation(perm);
    }
}
