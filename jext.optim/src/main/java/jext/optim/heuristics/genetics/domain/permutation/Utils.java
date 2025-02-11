package jext.optim.heuristics.genetics.domain.permutation;

import java.util.Arrays;
import java.util.Random;
import java.util.random.RandomGenerator;

public class Utils {

    private static final Random RANDOM = new Random();

    // ----------------------------------------------------------------------
    // create permutations
    // ----------------------------------------------------------------------

    /** permutation initalized with -1 */
    public static int[] empty(int n) {
        int[] perm = new int[n];
        Arrays.fill(perm, -1);
        return perm;
    }

    /** permutation [0, ..., n-1] */
    public static int[] defaultPerm(int n) {
        return defaultPerm(n, 0);
    }

    /** permutation [1, ..., n] */
    public static int[] defaultPerm(int n, int start) {
        int[] perm = new int[n];
        for (int i = 0; i < n; i++)
            perm[i] = start + i;
        return perm;
    }

    /** random permutation */
    public static int[] random(int n) {
        return random(n, 0);
    }

    /** random permutation */
    public static int[] random(int n, int start) {
        return random(n, start, RANDOM);
    }

    /** random permutation */
    public static int[] random(int n, RandomGenerator rng) {
        return random(n, 0, rng);
    }

    /** random permutation */
    public static int[] random(int n, int start, RandomGenerator rng) {
        int[] perm = defaultPerm(n, start);
        // shuffle_(perm, 0, n-1, rng);
        jext.util.Arrays.shuffle(perm, rng);
        return perm;
    }

    // ----------------------------------------------------------------------
    // mutate permutations
    // ----------------------------------------------------------------------

    /** move element i near to j or j near to i (i < j) */
    public static void insert(int[] perm, int i, int j) {
        boolean moveFirst = i < j;
        if (i > j) { int t = i; i = j; j = t; }

        if (moveFirst) {
            int t = perm[i];
            for (int k = i; k < j-1; k++)
                perm[k] = perm[k+1];
            perm[j-1] = t;
        }
        else {
            int t = perm[j];
            for (int k=j; k > i+1; --k)
                perm[k] = perm[k-1];
            perm[i+1] = t;
        }
    }

    // ----------------------------------------------------------------------
    // crossover permutations
    // ----------------------------------------------------------------------

    /** Partially Mapped Crossover */
    public static int[] pmx(int[] p1, int[] p2, int i, int j) {
        int n = p1.length;
        int[] nperm = empty(n);
        if (i > j) { int t = i; i = j; j = t; }

        for (int k = i; k <= j; k++)
            nperm[k] = p1[k];

        for (int k = i; k <= j; k++) {
            if (indexOf(nperm, p2[k]) != -1)
                continue;
            int h = findPos(nperm, p1, p2, k);
            nperm[h] = p2[k];
        }

        for (int k = 0; k < n; k++)
            if (nperm[k] == -1)
                nperm[k] = p2[k];

        // validate(nperm);

        return nperm;
    }

    private static int findPos(int[] nperm, int[] p1, int[] p2, int k) {
        int i = p2[k];
        int j = p1[k];
        int h = indexOf(p2, j);
        if (nperm[h] == -1)
            return h;
        else
            return findPos(nperm, p1, p2, h);
    }

    private static int indexOf(int[] perm, int t) {
        for(int i = 0; i < perm.length; i++)
            if (perm[i] == t) return i;
        return -1;
    }

    // ----------------------------------------------------------------------

    /** Ordered Crossover */
    public static int[] ordx(int[] p1, int[] p2, int i, int j) {
        int n = p1.length;
        int[] nperm = empty(n);
        int k, h;

        if (i > j) { int t = i; i = j; j = t; }

        for (k = i; k <= j; k++)
            nperm[k] = p1[k];

        k = next(j, n);
        h = k;
        for(int l = 0; l < n; l++) {
            int t = p2[h];
            if (indexOf(nperm, t) == -1) {
                nperm[k] = t;
                k = next(k, n);
            }
            h = next(h, n);
        }

        return nperm;
    }

    private static int next(int i, int n) {
        i++;
        return i < n ? i : 0;
    }

    // ----------------------------------------------------------------------
    // 1 2 3 4 5 6 7 8 9
    // 9 3 7 8 2 6 5 1 4
    //
    // 1 9 4 8 1
    // 2 3 7 5 2
    // 6

    /** Cyclic Crossover */
    public static int[] cycx(int[] p1, int[] p2) {
        int n = p1.length;
        int[] nperm = empty(n);
        int k, s, t;

        while (true) {
            k = findFree(nperm);
            if (k == -1) break;
            s = p1[k];
            while (true) {
                nperm[k] = p1[k];
                t = p2[k];
                k = indexOf(p1, t);
                if (k == -1) break;
                if (p1[k] == s) break;
            }

            { int[] tt = p1; p1 = p2; p2 = tt; }
        }

        // validate(nperm);

        return nperm;
    }

    private static int findFree(int[] perm) {
        int n = perm.length;
        for (int i=0; i<n; i++)
            if (perm[i] == -1)
                return i;
        return -1;
    }

    // ----------------------------------------------------------------------

    private static void validate(int[] perm) {
        int n = perm.length;
        int start = Integer.MAX_VALUE;
        for (int i=0; i<n; i++) {
            if (perm[i] < start)
                start = perm[i];
        }

        for (int i=0; i < n; i++) {
            if (indexOf(perm, start+i) == -1)
                throw new Error("Invalid permutation:" + Arrays.toString(perm));
        }
    }
}
