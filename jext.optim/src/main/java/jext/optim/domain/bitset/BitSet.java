package jext.optim.domain.bitset;

import java.util.Arrays;

public class BitSet implements Cloneable {

    private static final int WORD_SHIFT = 6;
    private static final int WORD_MASK = 0x3F;

    private static final int[] BIT_COUNTS = new int[]{
        0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4, 1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5, 1, 2, 2, 3, 2, 3,
        3, 4, 2, 3, 3, 4, 3, 4, 4, 5, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4,
        3, 4, 4, 5, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 3, 4,
        4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7, 1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5, 2, 3, 3, 4, 3, 4, 4, 5,
        3, 4, 4, 5, 4, 5, 5, 6, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6,
        6, 7, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7, 3, 4, 4, 5,
        4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7, 4, 5, 5, 6, 5, 6, 6, 7, 5, 6, 6, 7, 6, 7, 7, 8
    };

    private final int nbits;
    private final long[] words;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public BitSet(int nbits) {
        this.nbits = nbits;
        this.words = new long[(nbits >> WORD_SHIFT) + 1];
    }

    private BitSet(int nbits, long[] words) {
        this.nbits = nbits;
        this.words = words;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    /// Number of bits
    public int length() {
        return nbits;
    }

    /// Number of bits with value 1
    public int size() {
        int n = words.length;
        int size = 0;
        for (int i=0; i<n; ++i) {
            long bits = words[i];
            while (bits != 0) {
                size += BIT_COUNTS[(int)(bits & 0xFF)];
                bits >>= 8;
            }
        }
        return size;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public boolean get(int bitIndex) {
        int wi = bitIndex >> WORD_SHIFT;
        int bi = bitIndex & WORD_MASK;
        return (words[wi] & (1L << bi)) != 0;
    }

    public void set(int bitIndex, boolean value){
        if (value)
            set(bitIndex);
        else
            clear(bitIndex);
    }

    public void set(int start, BitSet that, int len) {
        if (start == 0 && len == nbits) {
            System.arraycopy(that.words, 0, this.words, 0, words.length);
        }
        else {
            for (int i=0; i<len; ++i)
                this.set(start+i, that.get(start+i));
        }
    }

    // public void set(int offset, BitSet that, int start, int len) {
    //     if (offset == 0 && start == 0 && len == nbits) {
    //         System.arraycopy(that.words, 0, this.words, 0, words.length);
    //     }
    //     else {
    //         for (int i=0; i<len; ++i)
    //             this.set(offset+i, that.get(start+i));
    //     }
    // }

    public void set(int bitIndex) {
        int wi = bitIndex >> WORD_SHIFT;
        int bi = bitIndex & WORD_MASK;
        words[wi] |= 1L << bi;
    }

    public void clear() {
        Arrays.fill(words, 0);
    }

    public void clear(int bitIndex) {
        int wi = bitIndex >> WORD_SHIFT;
        int bi = bitIndex & WORD_MASK;
        words[wi] &= ~(1L << bi);
    }

    public void flip(int bitIndex) {
        int wi = bitIndex >> WORD_SHIFT;
        int bi = bitIndex & WORD_MASK;
        words[wi] ^= 1L << bi;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public BitSet union(BitSet that) {
        int n = this.words.length;
        long[] uwords = Arrays.copyOf(this.words, n);
        for(int i=0; i<n; ++i)
            uwords[i] |= that.words[i];

        return new BitSet(nbits, uwords);
    }

    public BitSet intersection(BitSet that) {
        int n = this.words.length;
        long[] uwords = Arrays.copyOf(this.words, n);
        for(int i=0; i<n; ++i)
            uwords[i] &= that.words[i];

        return new BitSet(nbits, uwords);
    }

    public BitSet difference(BitSet that) {
        int n = this.words.length;
        long[] uwords = Arrays.copyOf(this.words, n);
        for(int i=0; i<n; ++i)
            uwords[i] &= ~that.words[i];

        return new BitSet(nbits, uwords);
    }

    public BitSet symdiff(BitSet that) {
        int n = this.words.length;
        long[] uwords = new long[n];
        for(int i=0; i<n; ++i)
            uwords[i] = (this.words[i] & ~that.words[i]) | (~this.words[i] & that.words[i]);

        return new BitSet(nbits, uwords);
    }

    // ----------------------------------------------------------------------

    @Override
    public int hashCode() {
        return Arrays.hashCode(words);
    }

    @Override
    public boolean equals(Object obj) {
        BitSet that = (BitSet) obj;
        return Arrays.equals(words, that.words);
    }

    @Override
    public BitSet clone() {
        int n = words.length;
        return new BitSet(nbits, Arrays.copyOf(words, n));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for(int i=0; i<nbits; ++i) {
            if (this.get(i)) {
                if (sb.length() > 1) sb.append(", ");
                sb.append(i);
            }
        }
        sb.append("]");
        return sb.toString();
    }

}

