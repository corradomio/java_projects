package jext.math.linear.sparse;

public class Data {
    // idx? must be ordered
    int[] idx0;     // used for vectors and rows in matrices
    int[] idx1;     // used for columns in matrices. Can be null
    float[] data;


    public Data(int[] idxs, float[] data) {
        this.idx0 = idxs;
        this.data = data;
    }

    public Data(int[] rows, int[] cols, float[] data) {
        this.idx0 = rows;
        this.idx1 = cols;
        this.data = data;
    }

    public int length() {
        return data.length;
    }
}
