package ae.ac.ebtic.tools.vr.data;


import jext.problems.Coords;

public class LonLat implements Coords {

    // ----------------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------------

    protected final double longitude;
    protected final double latitude;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public LonLat() {
        longitude = 0;
        latitude = 0;
    }

    public LonLat(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public double getX() {
        return longitude;
    }

    @Override
    public double getY() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

}
