package jext.math;

public class Linalg {

    // ----------------------------------------------------------------------
    // Determinant
    // ----------------------------------------------------------------------

    public static double determinant(double[] v) {
        int n = v.length;
        double d = 1;
        for (int i=0; i<n; ++i)
            d *= v[i];
        return d;
    }

    public static double determinant(double[][] m) {
        return determinant(m, m.length);
    }

    private static double determinant(double[][] m, int n) {
        int D = 0; // Initialize result

        // Base case : if matrix
        // contains single element
        if (n == 1)
            return m[0][0];

        if (n == 2)
            return m[0][0] * m[1][1] - m[1][0] * m[0][1];

        if (n == 3)
            return m[0][0] * (m[1][1] * m[2][2] - m[1][2] * m[2][1])
                - m[0][1] * (m[1][0] * m[2][2] - m[1][2] * m[2][0])
                + m[0][2] * (m[1][0] * m[2][1] - m[1][1] * m[2][0]);

        // To store cofactors
        double temp[][] = new double[n][n];

        // To store sign multiplier
        int sign = 1;

        // Iterate for each element of first row
        for (int f = 0; f < n; f++) {
            // Getting Cofactor of mat[0][f]
            cofactor(m, temp, 0, f, n);
            D += sign * m[0][f]
                * determinant(temp, n - 1);

            // terms are to be added
            // with alternate sign
            sign = -sign;
        }

        return D;
    }

    // Function to get cofactor of
    // mat[p][q] in temp[][]. n is
    // current dimension of mat[][]
    private static void cofactor(double[][] m, double[][] t, int p, int q, int n) {
        int i = 0, j = 0;

        // Looping for each element
        // of the matrix
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                // Copying into temporary matrix
                // only those element which are
                // not in given row and column
                if (row != p && col != q) {
                    t[i][j++] = m[row][col];
                    // Row is filled, so increase
                    // row index and reset col index
                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    // ----------------------------------------------------------------------
    // Inverse
    // ----------------------------------------------------------------------

    public static double[] inverse(double[] v) {
        int n = v.length;
        double[] r = new double[n];
        for (int i=0; i<n; ++i)
            r[i] = 1/v[i];
        return r;
    }

    public static double[][] inverse(double[][] m) {
        int n = m.length;
        double[][] a = copy(m);
        double[][] x = new double[n][n];
        double[][] b = new double[n][n];
        int[] index = new int[n];
        for (int i = 0; i < n; ++i)
            b[i][i] = 1;

        // Transform the matrix into an upper triangle
        gaussian(a, index);

        // Update the matrix b[i][j] with the ratios stored
        for (int i = 0; i < n - 1; ++i)
            for (int j = i + 1; j < n; ++j)
                for (int k = 0; k < n; ++k)
                    b[index[j]][k] -= a[index[j]][i] * b[index[i]][k];

        // Perform backward substitutions
        for (int i = 0; i < n; ++i) {
            x[n - 1][i] = b[index[n - 1]][i] / a[index[n - 1]][n - 1];
            for (int j = n - 2; j >= 0; --j) {
                x[j][i] = b[index[j]][i];
                for (int k = j + 1; k < n; ++k)
                    x[j][i] -= a[index[j]][k] * x[k][i];
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }

    // Method to carry out the partial-pivoting Gaussian
    // elimination.  Here index[] stores pivoting order.

    private static void gaussian(double[][] m, int[] index) {
        int n = index.length;
        double c[] = new double[n];

        // Initialize the index
        for (int i = 0; i < n; ++i)
            index[i] = i;

        // Find the rescaling factors, one from each row
        for (int i = 0; i < n; ++i) {
            double c1 = 0;
            for (int j = 0; j < n; ++j) {
                double c0 = Math.abs(m[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }

        // Search the pivoting element from each column
        int k = 0;
        for (int j = 0; j < n - 1; ++j) {
            double pi1 = 0;
            for (int i = j; i < n; ++i) {
                double pi0 = Math.abs(m[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }

            // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i = j + 1; i < n; ++i) {
                double pj = m[index[i]][j] / m[index[j]][j];

                // Record pivoting ratios below the diagonal
                m[index[i]][j] = pj;

                // Modify other elements accordingly
                for (int l = j + 1; l < n; ++l)
                    m[index[i]][l] -= pj * m[index[j]][l];
            }
        }
    }

    // ----------------------------------------------------------------------
    // Copy
    // ----------------------------------------------------------------------

    private static double[][] copy(double[][] m) {
        int n = m.length;
        double[][] c = new double[n][n];
        for(int i=0; i<n; ++i)
            for(int j=0; j<n; ++j)
                c[i][j] = m[i][j];
        return c;
    }

    public static double[] copy(double x, int n) {
        double[] v = new double[n];
        for (int i=0; i<n; ++i)
            v[i] = x;
        return v;
    }

    // ----------------------------------------------------------------------
    // Copy
    // ----------------------------------------------------------------------

    private static double sq(double x) { return x*x; }

    public static double multiply(double[] x, double[] mean, double[] d) {
        int n = x.length;

        double s = 0;
        for (int i=0; i<n; ++i)
            s += sq( x[i] - mean[i])*d[i];
        return s;
    }

    public static double multiply(double[] x, double[] mean, double[][] mat) {
        int n = x.length;
        double[] t = new double[n];
        double[] r = new double[n];
        for (int i=0; i<n; ++i)
            t[i] = x[i] - mean[i];

        double s;
        for (int i=0; i<n; ++i) {
            s = 0;
            for (int j=0; j<n; ++j)
                s += mat[i][j]*t[j];
            r[i] = s;
        }

        s = 0;
        for (int i=0; i<n; ++i)
            s += t[i]*r[i];
        return s;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
