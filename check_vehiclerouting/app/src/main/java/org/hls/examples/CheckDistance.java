package org.hls.examples;

import org.hls.examples.utils.LatLonUtils;

public class CheckDistance {
    public static void main(String[] args) {

        double lat1 = 40.714268; // New York
        double lon1 = -74.005974;
        double lat2 = 34.0522; // Los Angeles
        double lon2 = -118.2437;
        double expectedDistance = 3944;

        // 500 km
        System.out.println(LatLonUtils.distance(24.25267,     51.58714, 26.00484,     56.17143));

        // System.out.println(LatLonUtils.distance(lat1, lon1, lat2, lon2));
        //
        // System.out.println(LatLonUtils.haversineDistance(lat1, lon1, lat2, lon2));
        //
        // System.out.println(LatLonUtils.vicentyDistance(lat1, lon1, lat2, lon2));
        //
        // System.out.println(LatLonUtils.equirectangularDistance(lat1, lon1, lat2, lon2));


        System.out.println(LatLonUtils.distance(24.470624404934032, 54.336533750506355, 24.478082773327873, 54.34530722925839));

        System.out.println(LatLonUtils.distance(24.5, 54.5, 25.5, 55.5));
        System.out.println(LatLonUtils.distance(24.5, 54.5, 25.0, 55.0));

        System.out.println(LatLonUtils.distance(24.5, 54.5, 25.0, 54.5));
        System.out.println(LatLonUtils.distance(24.5, 54.5, 24.5, 55.0));

        System.out.println(LatLonUtils.distance(24.5, 54.5, 24.6, 54.6));
    }
}
