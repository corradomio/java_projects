package jext.swing.map;

import de.fhpotsdam.unfolding.core.Coordinate;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;

public class CustomMap {
    public static class CustomMapProvider extends OpenStreetMap.OpenStreetMapProvider {

        private String customUrl;
        public CustomMapProvider(String url) {
            this.customUrl = url;
        }

        public String[] getTileUrls(Coordinate var1) {
            String zxy = this.getZoomString(var1);
            String var2 = this.customUrl.replace("{z}/{x}/{y}", zxy);
            return new String[]{var2};
        }
    }
}
