package org.hls.example;


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

    public static double[][] getEdgeCoords(List<Edge> edges) {
        int i = 0;
        int n = edges.size();
        double[] x = new double[2*n];
        double[] y = new double[2*n];

        for(Edge e : edges) {
            x[i] = e.l1.longitude;
            y[i] = e.l1.latitude;
            ++i;
            x[i] = e.l2.longitude;
            y[i] = e.l2.latitude;
            ++i;
        }

        return new double[][]{ x, y };
    }

    // ----------------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------------

    protected double longitude;
    protected double latitude;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public Coords() {

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

    // public void setLongitude(double longitude) {
    //     this.longitude = longitude;
    // }

    public double getLatitude() {
        return latitude;
    }

    // public void setLatitude(double latitude) {
    //     this.latitude = latitude;
    // }

}
