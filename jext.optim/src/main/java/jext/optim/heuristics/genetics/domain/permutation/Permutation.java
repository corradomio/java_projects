package jext.optim.heuristics.genetics.domain.permutation;

import java.util.Arrays;

public class Permutation implements Cloneable {

    private final int[] perm;

    public Permutation(int n) {
        this.perm = Utils.defaultPerm(n);
    }

    public Permutation(int[] perm) {
        this.perm = perm;
    }

    public int[] permutation() {
        return perm;
    }

    @Override
    public Permutation clone() {
        return new Permutation(Utils.clone(perm));
    }

    @Override
    public String toString() {
        return Arrays.toString(perm);
    }
}
