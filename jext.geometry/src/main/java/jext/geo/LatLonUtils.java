package jext.geo;

import static java.lang.Math.atan2;
import static java.lang.Math.atan;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.tan;
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static java.lang.Math.toRadians;

/*
    https://www.baeldung.com/java-find-distance-between-points
 */

public class LatLonUtils {

    public static final double EARTH_RADIUS = 6371.0088; // Km
    public static final double SEMI_MAJOR_AXIS_MT = 6378137;
    public static final double SEMI_MINOR_AXIS_MT = 6356752.314245;
    public static final double FLATTENING = 1 / 298.257223563;
    public static final double ERROR_TOLERANCE = 1e-12;

    private static double sq(double x) {
        return x*x;
    }

    // The GPS coordinates are specified as (latitude, longitude)
    //
    //
    // latitude     -> y
    // longitude    -> x
    //
    // https://www.baeldung.com/java-find-distance-between-points
    //
    //      EquirectangularApproximation.calculateDistance(lat1, lon1, lat2, lon2);
    //      HaversineDistance.calculateDistance(lat1, lon1, lat2, lon2);
    //      VincentyDistance.calculateDistance(lat1, lon1, lat2, lon2);
    //

    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        return distance(lat1, lon1, lat2, lon2, 'K');
    }

    public static double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
        // double theta = lon1 - lon2;
        // double dist = sin(deg2rad(lat1)) * sin(deg2rad(lat2)) + cos(deg2rad(lat1)) * cos(deg2rad(lat2)) * cos(deg2rad(theta));
        // dist = acos(dist);
        // dist = rad2deg(dist);
        // dist = dist * 60 * 1.1515;
        // if (unit == 'K') {
        //     dist = dist * 1.609344;  // 1 mile == 1.609344 km
        // } else if (unit == 'N') {
        //     dist = dist * 0.8684;    // 1 mile == 0.8684 nautical miles
        // }
        // return (dist);
        double dlat, dlon, a, c, R;

        R = EARTH_RADIUS;
        lat1 = toRadians(lat1);
        lon1 = toRadians(lon1);
        lat2 = toRadians(lat2);
        lon2 = toRadians(lon2);
        dlon = lon2 - lon1;
        dlat = lat2 - lat1;

        // haversine(dlat) := sq(sin(dlat/2))
        a = sq(sin(dlat/2)) + cos(lat1) * cos(lat2) * sq(sin(dlon/2));
        c = 2 * atan2(sqrt(a), sqrt(1-a));

        if (unit == 'K')
            // in km
            return R * c;
        else if (unit == 'N')
            // km -> nautical miles
            return R * c * 0.539957;
        else
            // km -> miles
            return R * c * 0.621371;
    }

    public static double angularDistance(double lat1, double lon1, double lat2, double lon2) {
        return sqrt(sq(lat1 - lat2) + sq(lon1 - lon2));
    }

    public static double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        return distance(lat1, lon1, lat2, lon2);
    }

    public static double equirectangularDistance(double lat1, double lon1, double lat2, double lon2) {
        double lat1Rad = toRadians(lat1);
        double lat2Rad = toRadians(lat2);
        double lon1Rad = toRadians(lon1);
        double lon2Rad = toRadians(lon2);

        double x = (lon2Rad - lon1Rad) * cos((lat1Rad + lat2Rad) / 2);
        double y = (lat2Rad - lat1Rad);
        double distance = sqrt(sq(x) + sq(y)) * EARTH_RADIUS;

        return distance;
    }

    public static double vicentyDistance(double lat1, double lon1, double lat2, double lon2) {
        double U1 = atan((1 - FLATTENING) * tan(toRadians(lat1)));
        double U2 = atan((1 - FLATTENING) * tan(toRadians(lat2)));

        double sinU1 = sin(U1);
        double cosU1 = cos(U1);
        double sinU2 = sin(U2);
        double cosU2 = cos(U2);

        double longitudeDifference = toRadians(lon1 - lon2);
        double previousLongitudeDifference;

        double sinSigma, cosSigma, sigma, sinAlpha, cosSqAlpha, cos2SigmaM;

        do {
            sinSigma = sqrt(sq(cosU2 * sin(longitudeDifference)) +
                sq(cosU1 * sinU2 - sinU1 * cosU2 * cos(longitudeDifference)));
            cosSigma = sinU1 * sinU2 + cosU1 * cosU2 * cos(longitudeDifference);
            sigma = atan2(sinSigma, cosSigma);
            sinAlpha = cosU1 * cosU2 * sin(longitudeDifference) / sinSigma;
            cosSqAlpha = 1 - sq(sinAlpha);
            cos2SigmaM = cosSigma - 2 * sinU1 * sinU2 / cosSqAlpha;
            if (Double.isNaN(cos2SigmaM)) {
                cos2SigmaM = 0;
            }
            previousLongitudeDifference = longitudeDifference;
            double C = FLATTENING / 16 * cosSqAlpha * (4 + FLATTENING * (4 - 3 * cosSqAlpha));
            longitudeDifference = toRadians(lon2 - lon1) + (1 - C) * FLATTENING * sinAlpha *
                (sigma + C * sinSigma * (cos2SigmaM + C * cosSigma * (-1 + 2 * sq(cos2SigmaM))));
        } while (abs(longitudeDifference - previousLongitudeDifference) > ERROR_TOLERANCE);

        double uSq = cosSqAlpha * (sq(SEMI_MAJOR_AXIS_MT) - sq(SEMI_MINOR_AXIS_MT)) / sq(SEMI_MINOR_AXIS_MT);

        double A = 1 + uSq / 16384 * (4096 + uSq * (-768 + uSq * (320 - 175 * uSq)));
        double B = uSq / 1024 * (256 + uSq * (-128 + uSq * (74 - 47 * uSq)));

        double deltaSigma = B * sinSigma * (cos2SigmaM + B / 4 * (cosSigma * (-1 + 2 * sq(cos2SigmaM))
            - B / 6 * cos2SigmaM * (-3 + 4 * sq(sinSigma)) * (-3 + 4 * sq(cos2SigmaM))));

        double distanceMt = SEMI_MINOR_AXIS_MT * A * (sigma - deltaSigma);
        return distanceMt / 1000;
    }

}
