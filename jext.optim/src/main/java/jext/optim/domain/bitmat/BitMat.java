package jext.optim.domain.bitmat;

import jext.optim.domain.bitset.BitSet;

public class BitMat extends BitSet {

    private final int rows;
    private final int cols;

    public BitMat(int rows, int cols) {
        super(rows*cols);
        this.rows = rows;
        this.cols = cols;
    }

    public boolean get(int row, int col) {
        return super.get(row*cols + col);
    }

    public void set(int row, int col) {
        super.set(row*cols + col);
    }

    public void clear(int row, int col) {
        super.set(row*cols + col);
    }

    public void set(int row, int col, boolean value) {
        super.set(row*cols + col, value);
    }
}
