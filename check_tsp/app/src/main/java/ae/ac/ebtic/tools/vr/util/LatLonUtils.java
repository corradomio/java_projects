package ae.ac.ebtic.tools.vr.util;

import static java.lang.Math.*;
import static java.lang.Math.sqrt;

public class LatLonUtils {

    public static double sq(double x) {
        return x*x;
    }

    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        return distance(lat1, lon1, lat2, lon2, 'K');
    }

    public static double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
        // double theta = lon1 - lon2;
        // double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        // dist = Math.acos(dist);
        // dist = rad2deg(dist);
        // dist = dist * 60 * 1.1515;
        // if (unit == 'K') {
        //     dist = dist * 1.609344;  // 1 mile == 1.609344 km
        // } else if (unit == 'N') {
        //     dist = dist * 0.8684;    // 1 mile == 0.8684 nautical miles
        // }
        // return (dist);
        double dlat, dlon, a, c, R;

        R = 6371.0088; // Km
        lat1 = deg2rad(lat1);
        lon1 = deg2rad(lon1);
        lat2 = deg2rad(lat2);
        lon2 = deg2rad(lon2);
        dlon = lon2 - lon1;
        dlat = lat2 - lat1;

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

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts decimal degrees to radians             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    public static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts radians to decimal degrees             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    public static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
