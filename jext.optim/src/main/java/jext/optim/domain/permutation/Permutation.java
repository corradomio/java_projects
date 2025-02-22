package jext.optim.domain.permutation;

import jext.util.ArrayUtils;

import java.util.Arrays;
import java.util.random.RandomGenerator;

public class Permutation implements Cloneable {

    private final int[] perm;

    // ----------------------------------------------------------------------

    public Permutation(int n) {
        this.perm = PermUtils.defaultPerm(n);
    }

    public Permutation(int[] perm) {
        this.perm = perm;
    }

    // ----------------------------------------------------------------------

    public int[] permutation() {
        return perm;
    }

    public int length() {
        return perm.length;
    }

    // ----------------------------------------------------------------------

    public void flip(int i) {
        int j = i+1; if (j == perm.length) j = 0;
        swap(i, j);
    }

    public void swap(int i, int j) {
        ArrayUtils.swap(perm, i, j);
    }

    public void shuffle(int i, int j, RandomGenerator rng) {
        ArrayUtils.shuffle(perm, i, j, rng);
    }

    public void insert(int i, int j) {
        PermUtils.insert(perm, i, j);
    }

    public void reverse(int i, int j) {
        ArrayUtils.reverse(perm, i, j);
    }

    // ----------------------------------------------------------------------


    public Permutation cycx(Permutation that) {
        int[] perm = PermUtils.cycx(this.perm, that.perm);
        return new Permutation(perm);
    }

    public Permutation pmx(Permutation that, int i, int j) {
        int[] perm = PermUtils.pmx(this.perm, that.perm, i, j);
        return new Permutation(perm);
    }

    public Permutation ordx(Permutation that, int i, int j) {
        int[] perm = PermUtils.ordx(this.perm, that.perm, i, j);
        return new Permutation(perm);
    }

    // ----------------------------------------------------------------------

    @Override
    public int hashCode() {
        return Arrays.hashCode(perm);
    }

    @Override
    public boolean equals(Object obj) {
        Permutation that = (Permutation) obj;
        return Arrays.equals(perm, that.perm);
    }

    @Override
    public Permutation clone() {
        int[] perm = Arrays.copyOf(this.perm, this.perm.length);
        return new Permutation(perm);
    }

    @Override
    public String toString() {
        return Arrays.toString(perm);
    }
}
