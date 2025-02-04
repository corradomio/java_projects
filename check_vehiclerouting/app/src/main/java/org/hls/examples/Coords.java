package org.hls.examples;


import java.util.List;


public class Coords {

    // ----------------------------------------------------------------------
    // Static methods
    // ----------------------------------------------------------------------

    public static double[][] getCoords(List<? extends Coords> list) {
        int i = 0;
        int n = list.size();
        double[] x = new double[n];
        double[] y = new double[n];

        for(Coords c : list) {
            x[i] = c.longitude;
            y[i] = c.latitude;

            ++i;
        }

        return new double[][]{ x, y };
    }

    public static Double[][] getEdgesCoords(List<Edge> edges) {
        int i = 0;
        int n = edges.size();
        Double[] x = new Double[3*n];
        Double[] y = new Double[3*n];

        for(Edge e : edges) {
            x[i] = e.l1.longitude;
            y[i] = e.l1.latitude;
            ++i;
            x[i] = e.l2.longitude;
            y[i] = e.l2.latitude;
            ++i;
            x[i] = null;
            y[i] = null;
            i++;
        }

        return new Double[][]{ x, y };
    }

    public static double[][] getPathsCoords(List<Location>[] vpaths) {
        int m = vpaths.length;
        // 1) count the number of 'edges' to create
        int k = 0;
        for(int j=0; j<m; ++j)
            k += vpaths[j].size();  // (lj0-lj1),...(lj[n-1]-lj0)

        // fill the coordinates
        double[] x = new double[2*k];
        double[] y = new double[2*k];

        int h=0;
        for(int j=0; j<m; ++j) {
            Location l0, l1;
            List<Location> toVisit = vpaths[j];
            int l = toVisit.size()-1;
            for(int i=0; i < l; ++i) {
                l0 = toVisit.get(i);
                l1 = toVisit.get(i+1);

                x[h] = l0.longitude;
                y[h] = l0.latitude;
                h++;
                x[h] = l1.longitude;
                y[h] = l1.latitude;
                h++;
            }
            // add last edge
            {
                l0 = toVisit.get(l);
                l1 = toVisit.get(0);

                x[h] = l0.longitude;
                y[h] = l0.latitude;
                h++;
                x[h] = l1.longitude;
                y[h] = l1.latitude;
                h++;
            }
        }

        return new double[][]{ x, y };
    }

    // ----------------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------------

    protected final double longitude;
    protected final double latitude;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public Coords() {
        longitude = 0;
        latitude = 0;
    }

    public Coords(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------


}
