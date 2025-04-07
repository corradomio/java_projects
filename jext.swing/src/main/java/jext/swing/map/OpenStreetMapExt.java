

package jext.swing.map;

import de.fhpotsdam.unfolding.core.Coordinate;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;

public class OpenStreetMapExt extends OpenStreetMap {

    public static class OpenStreetMapProviderExt extends OpenStreetMapProvider {
        public OpenStreetMapProviderExt() {
        }

        public String[] getTileUrls(Coordinate var1) {
            String var2 = "https://tile.openstreetmap.de/" + this.getZoomString(var1) + ".png";
            return new String[]{var2};
        }
    }
}
