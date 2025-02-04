package org.hls.examples.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Coords {

    // ----------------------------------------------------------------------
    // Static methods
    // ----------------------------------------------------------------------

    public static double[][] getCoords(List<? extends Coords> list) {
        return getCoords(list, false);
    }

    public static double[][] getCoords(List<? extends Coords> list, boolean closed) {
        int i = 0;
        int n = list.size();
        double[] x = new double[n + (closed ? 1 : 0)];
        double[] y = new double[n + (closed ? 1 : 0)];

        for(Coords c : list) {
            x[i] = c.longitude;
            y[i] = c.latitude;

            ++i;
        }
        if (closed) {
            Coords c = list.get(0);
            x[n] = c.longitude;
            y[n] = c.latitude;
        }

        return new double[][]{ x, y };
    }

    public static Map<?, double[][]> getVehiclesPath(Map<?, ?> vpaths) {
        Map<Object, double[][]> mapPaths = new HashMap<>();

        for (Object obj : vpaths.values()) {
            Coords ci;
            List<? extends Coords> l = (List<? extends Coords>) obj;
            int n = l.size();
            double[] x = new double[n+1];
            double[] y = new double[n+1];

            // add the last point
            ci = l.get(n-1);
            x[n] = ci.longitude;
            y[n] = ci.latitude;

            for (int i=0; i<n; ++i) {
                ci = l.get(i);
                x[i] = ci.longitude;
                y[i] = ci.latitude;
            }

            mapPaths.put(obj, new double[][]{x,y});
        }

        return mapPaths;
    }

    // public static double[][] getPathCoords(Map<?, ?> vpaths) {
    //     // count the number of edges to generate:
    //     int k = 0;
    //     for (Object obj : vpaths.values()) {
    //         List<? extends Coords> l = (List<? extends Coords>) obj;
    //         k += l.size();
    //     }
    //
    //     double[] x = new double[2*k];
    //     double[] y = new double[2*k];
    //
    //     int h = 0;
    //     for (Object obj : vpaths.values()) {
    //         List<? extends Coords> l = (List<? extends Coords>) obj;
    //         int m = l.size();
    //         for (int j=0; j<m; ++j) {
    //             Coords c0 = l.get(j);
    //             Coords c1 = l.get((j+1)%m);
    //
    //             x[h] = c0.longitude;
    //             y[h] = c0.latitude;
    //             h++;
    //             x[h] = c1.longitude;
    //             y[h] = c1.latitude;
    //             h++;
    //         }
    //     }
    //
    //
    //     return new double[][]{ x, y};
    // }

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

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

}
