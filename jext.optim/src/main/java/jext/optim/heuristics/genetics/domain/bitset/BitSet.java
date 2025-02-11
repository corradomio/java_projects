package jext.optim.heuristics.genetics.domain.bitset;

import java.util.Arrays;

public class BitSet implements Cloneable {

    private static final int WORD_SHIFT = 6;
    private static final int WORD_MASK = 0x3F;

    private final int nbits;
    private final long[] words;

    public BitSet(int nbits) {
        this.nbits = nbits;
        this.words = new long[(nbits >> WORD_SHIFT) + 1];
    }

    private BitSet(int nbits, long[] words) {
        this.nbits = nbits;
        this.words = words;
    }

    public int length() {
        return nbits;
    }

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

    public void set(int offset, BitSet that, int start, int len) {
        if (offset == 0 && start == 0 && len == nbits) {
            System.arraycopy(that.words, 0, this.words, 0, words.length);
        }
        else {
            for (int i=0; i<len; ++i)
                this.set(offset+1, that.get(start+i));
        }
    }

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

    @Override
    public BitSet clone() {
        return new BitSet(nbits, Arrays.copyOf(words, words.length));
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(words);
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


// public class BitSet extends java.util.BitSet {
//     private final int nbits;
//
//     public BitSet(int nbits) {
//         super(nbits);
//         this.nbits = nbits;
//     }
//
//     public BitSet(BitSet bs) {
//         super(bs.nbits);
//         this.nbits = bs.nbits;
//         for(int i = 0; i < nbits; i++)
//             set(i, bs.get(i));
//     }
//
//     public int nbits() {
//         return nbits;
//     }
//
//     public void set(int bitIndex) {
//         if (bitIndex > nbits)
//             throw new IndexOutOfBoundsException("bitIndex > " + nbits + ": " + bitIndex);
//         super.set(bitIndex);
//     }
//
//     public void clear(int bitIndex) {
//         if (bitIndex > nbits)
//             throw new IndexOutOfBoundsException("bitIndex > " + nbits + ": " + bitIndex);
//         super.set(bitIndex);
//     }
//
//
//     public BitSet set(int offset, BitSet bs, int start) {
//         int length = Math.min(nbits-offset, bs.nbits - start);
//         return set(offset, bs, start, length);
//     }
//
//     public BitSet set(int offset, BitSet bs, int start, int length) {
//         for(int i=0; i<length; i++) {
//             set(offset+i, bs.get(start+i));
//         }
//         return this;
//     }
//
//     public BitSet union(BitSet that) {
//         BitSet br = new BitSet(nbits);
//         br.or(this);
//         br.or(that);
//         return br;
//     }
//
//     public BitSet intersection(BitSet that) {
//         BitSet br = new BitSet(nbits);
//         br.or(this);
//         br.and(that);
//         return br;
//     }
//
//     public BitSet difference(BitSet that) {
//         BitSet br = new BitSet(nbits);
//         br.or(this);
//         br.andNot(that);
//         return br;
//     }
//
//     public BitSet symdiff(BitSet that) {
//         BitSet b1 = this.difference(that);
//         BitSet b2 = that.difference(this);
//         return b1.union(b2);
//     }
//
//     @Override
//     public BitSet clone() {
//         BitSet br = new BitSet(nbits);
//         br.or(this);
//         return br;
//     }
//
// }
