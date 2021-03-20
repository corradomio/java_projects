package jext.math.linalg;

public class Dim {

    private int[] dims;

    public Dim() {
        dims = new int[0];
    }

    public Dim(int n) {
        dims = new int[1];
        dims[0] = n;
    }

    public Dim(int r, int c) {
        dims = new int[2];
        dims[0] = r;
        dims[1] = c;
    }

    public int rank() {
        return dims.length;
    }

    public int dim(int idim) {
        return dims[idim];
    }
}
