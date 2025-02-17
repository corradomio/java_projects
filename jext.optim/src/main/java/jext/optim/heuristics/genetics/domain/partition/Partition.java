package jext.optim.heuristics.genetics.domain.partition;

import jext.optim.heuristics.genetics.domain.bitset.BitSet;

import java.util.Arrays;

public class Partition implements Cloneable {

    private final int nparts;
    private final int [] elements;

    // local caches (to avoid multiple evaluations)
    private BitSet[] sets = null;
    private int[][] partitions = null;
    private String toString = null;

    // ----------------------------------------------------------------------

    public Partition(final int nelts, final int nparts) {
        this.nparts = nparts;
        this.elements = new int[nelts];
    }

    public Partition(final int [] elements, int nparts) {
        this.nparts = nparts;
        this.elements = elements;
    }

    public int length() {
        return elements.length;
    }

    public int nparts() {
        return nparts;
    }
    
    // ----------------------------------------------------------------------

    public void set(int elt, int part) {
        elements[elt] = part;
        clear();
    }

    public void set(int start, Partition that, int length) {
        System.arraycopy(that.elements, start, this.elements, start, length);
        clone();
    }

    private void clear() {
        // clear the caches
        partitions = null;
        sets = null;
        toString = null;
    }

    public int get(int elt) {
        return elements[elt];
    }

    public int[] elements() {
        return elements;
    }

    public BitSet[] sets() {
        if (sets != null)
            return sets;

        int n = elements.length;
        sets = new BitSet[nparts];
        for (int i = 0; i < nparts; i++)
            sets[i] = new BitSet(n);

        for (int i = 0; i < elements.length; i++)
            sets[elements[i]].set(i);

        return sets;
    }

    public int[][] partitions() {
        if (partitions != null)
            return partitions;

        int n = elements.length;

        // matrix (nparts, nelts)
        // large enough to fill all elements in the same partition
        int[][] tparts = new int[nparts][n];
        // coun the elements in each partition
        int[] tcount = new int[nparts];

        // scan elements and add each element in its partition
        for (int i=0; i<n ; ++i) {
            int part = elements[i];
            int p = tcount[part];
            tparts[part][p] = i;
            tcount[part] = p+1;
        }

        // recompose the array os arrays in such way
        // each partition contains ONLY its elements,
        // that is, the length of the vector is the number
        // of elements in partition
        partitions = new int[nparts][];
        for (int i=0; i<nparts; ++i) {
            partitions[i] = Arrays.copyOf(tparts[i], tcount[i]);
        }

        return partitions;
    }

    // ----------------------------------------------------------------------

    @Override
    public int hashCode() {
        return Arrays.hashCode(elements);
    }

    @Override
    public boolean equals(Object obj) {
        Partition that = (Partition) obj;
        return Arrays.equals(elements, that.elements);
    }

    @Override
    public Partition clone() {
        int[] elts = Arrays.copyOf(elements, elements.length);
        return new Partition(elts, nparts);
    }

    @Override
    public String toString() {
        if (toString != null)
            return toString;

        BitSet[] partitions = this.sets();

        StringBuilder sb = new StringBuilder("[").append(partitions[0]);
        for(int i=1; i<nparts; i++)
            sb.append(", ").append(partitions[i]);
        sb.append("]");
        toString = sb.toString();

        return toString;
    }
}
