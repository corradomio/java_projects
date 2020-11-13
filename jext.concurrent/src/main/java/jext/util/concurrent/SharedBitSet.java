package jext.util.concurrent;

public class SharedBitSet {
    private boolean[] bits;

    public SharedBitSet(int nbits) {
        bits = new boolean[nbits];
    }

    public int size() {
        return bits.length;
    }

    public void clear() {
        int nbits = bits.length;
        for(int i=0; i < nbits; ++i)
            bits[i] = false;
    }

    public void set(SharedBitSet other) {
        int nbits = bits.length;
        for(int i=0; i < nbits; ++i)
            bits[i]= other.bits[i];
    }

    public void clear(int position) {
        set(position, false);
    }

    public void clear(int startIndex, int excludeIndex) {
        set(startIndex, excludeIndex, false);
    }

    public void set(int position) {
        set(position, true);
    }

    public void set(int startIndex, int excludeIndex) {
        set(startIndex, excludeIndex, true);
    }

    private void set(int position, boolean value) {
        bits[position] = value;
    }
    private void set(int startIndex, int excludeIndex, boolean value) {
        for(int i = startIndex; i < excludeIndex; ++i)
            bits[i] = value;
    }

    public boolean get(int index) {
        return bits[index];
    }

    public int maxSetBit() {
        int nbits = bits.length;
        for (int i = nbits-1; i >= 0; --i)
            if (bits[i])
                return i;
        return -1;
    }

    public int cardinality() {
        int card = 0;
        int nbits = bits.length;
        for (int i = 0; i < nbits; ++i)
            if (bits[i])
                ++card;
        return card;
    }

    @Override
    public String toString() {
        if (cardinality() == 0)
            return "{}";
        StringBuffer sb = new StringBuffer("{");
        int nbits = bits.length;
        for (int i=0;i < nbits; ++i) {
            if (sb.length()> 1)
                sb.append(", ");
            sb.append(i);
        }
        return sb.append("}").toString();
    }
}
