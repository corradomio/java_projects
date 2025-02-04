package jext.geo;

public class TestSlippyUtils {

    public static void main(String[] args) {

        int zoom = 12;
        int[] xytile = TileUtils.toTile(13.530542150803688, 45.807664370358005, zoom);
        // int[] xytile = TileUtils.toTile(139.7006793, 35.6590699, zoom);

        // https://tile.openstreetmap.org/{zoom}/{x}/{y}.png
        System.out.printf("https://tile.openstreetmap.org/%d/%d/%d.png", zoom, xytile[0], xytile[1]);

        double[] coords = TileUtils.toCoordinate(xytile[0], xytile[1], 12);
        System.out.println(coords[0]);
        System.out.println(coords[1]);

    }
}
