package jext.math.linear;

public class Dim {
    private int[] dims;

    public Dim(int n) {
        dims = new int[]{n};
    }

    public Dim(int n, int m) {
        dims = new int[]{n, m};
    }

    public int rank() {
        return dims.length;
    }

    public int dim(int idim) {
        return dims[idim];
    }

    public boolean equals(Object obj) {
        Dim that = (Dim)obj;
        int k = rank();
        // same rank
        if (k != that.rank())
            return false;
        // same dimensions
        for (int i=0; i<k; ++i)
            if (this.dim(i) != that.dim(i))
                return false;
        return true;
    }

    public boolean conform(Dim that) {
        if (rank() == 1)
            // vector
            return this.dim(0)  == that.dim(0);
        else
            // matrix
            return this.dim(1)  == that.dim(0);
    }
}
