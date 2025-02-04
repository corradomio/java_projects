package jext.geo;

import static java.lang.Math.cos;
import static java.lang.Math.tan;
import static java.lang.Math.log;
import static java.lang.Math.sqrt;
import static java.lang.Math.atan;
import static java.lang.Math.sinh;
import static java.lang.Math.PI;
import static java.lang.Math.toDegrees;
import static java.lang.Math.toRadians;

/*
    https://wiki.openstreetmap.org/wiki/Slippy_map_tilenames
    x = longitude
        Positive is east, negative is west.
    y = latitude
        Positive is north, negative is south.

     	https://tile.openstreetmap.org/{zoom}/{x}/{y}.png
     	http://[abc].tile.thunderforest.com/cycle/{zoom}/{x}/{y}.png
     	https://maptiles.p.rapidapi.com/local/osm/v1/{zoom}/{x}/{y}.png?rapidapi-key=YOUR-KEY


    https://wiki.openstreetmap.org/wiki/Zoom_levels

    World Geodetic System 1984, used in GPS - EPSG:4326 == WGS84
    Web Mercator projection                 - EPSG:3857


    WARNING:            latitudine (y)      longitudine (x)
        Monfalcone:     45.80743542469213,  13.530127101604732
 */

public class TileUtils {

    private static int[] pow2 = new int[]{
        1,     2,        4,         8,        16,        32,         64,     128,      256,
             512,     1024,      2048,      4096,      8192,      16384,   32768,    65536,
          131072,   262144,    524288,   1048576,   2097152,    4194304, 8388608, 16777216,
        33554432, 67108864, 134217728, 268435456, 536870912, 1073741824
    };

    private static double sec(double x) { return 1./cos(x); }

    public static int[] toTile(double longitude, double latitude, int zoom) {
        return toTile(longitude, latitude, zoom, 256);
    }

    public static int[] toTile(double longitude, double latitude, int zoom, int dimension) {
        int n = pow2[zoom];
        double lon_rad = toRadians(longitude);
        double lat_rad = toRadians(latitude);
        double x = lon_rad;
        double y = log(tan(lat_rad) + sec(lat_rad));

        x = n*(1 + x/PI)/2;
        y = n*(1 - y/PI)/2;

        int xtile = (int)(x);
        int ytile = (int)(y);
        int xpixel = (int)((x - xtile)*dimension);
        int ypixel = (int)((y - ytile)*dimension);

        return new int[] {xtile, ytile, xpixel, ypixel};
    }

    public static double[] toCoordinate(int xtile, int ytile, int zoom) {
        int n = pow2[zoom];
        double longitude = (360.0*xtile) / n - 180.0;
        double lat_rad = atan(sinh(PI * (1.0 - 2.0 * ytile / n)));
        double latitude = toDegrees(lat_rad);

        return new double[] {longitude, latitude};
    }

}
