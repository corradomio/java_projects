package ae.ac.ebtic.tools.vr.data;


import jext.optim.problems.Coordinates;

public class LonLat implements Coordinates {

    // ----------------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------------

    protected final float longitude;
    protected final float latitude;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public LonLat() {
        longitude = 0;
        latitude = 0;
    }

    public LonLat(float longitude, float latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public float getX() {
        return longitude;
    }

    @Override
    public float getY() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }

}
