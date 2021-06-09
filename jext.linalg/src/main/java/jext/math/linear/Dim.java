package jext.math.linear;

import java.util.Arrays;

public class Dim {
    public int[] dims;

    public Dim(int n) {
        dims = new int[]{n};
    }

    public Dim(int n, int m) {
        dims = new int[]{n, m};
    }

    public int rank() {
        return dims.length;
    }

    // public int dim(int idim) {
    //     return dims[idim];
    // }

    @Override
    public boolean equals(Object obj) {
        Dim that = (Dim)obj;
        int k = rank();
        // same rank
        if (k != that.rank())
            return false;
        // same dimensions
        for (int i=0; i<k; ++i)
            if (this.dims[i] != that.dims[i])
                return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(dims);
    }

    public boolean conform(Dim that) {
        if (rank() == 1)
            // vector
            return this.dims[0]  == that.dims[0];
        else
            // matrix
            return this.dims[1]  == that.dims[0];
    }
}
