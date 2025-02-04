package com.example.providers;

import de.fhpotsdam.unfolding.core.Coordinate;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;

public class Local {

    public static class LocalProvider extends OpenStreetMap.GenericOpenStreetMapProvider {

        private String url = "http://localhost:8080/styles/basic-preview/512/";

        public LocalProvider() {

        }

        public LocalProvider(String url) {
            this.url = url;
            if (!this.url.endsWith("/"))
                this.url = this.url + "/";
        }

        @Override
        public String[] getTileUrls(Coordinate coordinate) {
            String var2 = this.url + this.getZoomString(coordinate) + ".png";
            return new String[]{var2};
        }
    }
}
