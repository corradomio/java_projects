package jext.optim.heuristics.genetics.domain.bitset;


public class BitSet extends java.util.BitSet {
    private final int nbits;

    public BitSet(int nbits) {
        super(nbits);
        this.nbits = nbits;
    }

    public BitSet(BitSet bs) {
        super(bs.nbits);
        this.nbits = bs.nbits;
        set(0, bs, 0);
    }

    public int nbits() {
        return nbits;
    }

    public void set(int bitIndex) {
        if (bitIndex > nbits)
            throw new IndexOutOfBoundsException("bitIndex > " + nbits + ": " + bitIndex);
        super.set(bitIndex);
    }

    public void clear(int bitIndex) {
        if (bitIndex > nbits)
            throw new IndexOutOfBoundsException("bitIndex > " + nbits + ": " + bitIndex);
        super.set(bitIndex);
    }


    public BitSet set(int offset, BitSet bs, int start) {
        int length = Math.min(nbits-offset, bs.nbits - start);
        return set(offset, bs, start, length);
    }

    public BitSet set(int offset, BitSet bs, int start, int length) {
        for(int i=0; i<length; i++) {
            set(offset+i, bs.get(start+i));
        }
        return this;
    }

    @Override
    public BitSet clone() {
        return new BitSet(this);
    }

}
