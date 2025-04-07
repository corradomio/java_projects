package jext.swing.map;

import de.fhpotsdam.unfolding.core.Coordinate;
import de.fhpotsdam.unfolding.providers.Google;
import jext.util.JSONUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GoogleLang {

    public static class GoogleMapProvider extends Google.GoogleProvider {

        private String lang;

        public GoogleMapProvider() {
            this("en");
        }

        public GoogleMapProvider(String lang) {
            this.lang = lang;
        }

        public String[] getTileUrls(Coordinate var1) {
            String var2 = "http://mt1.google.com/vt/lyrs=m@116&hl=" + this.lang + "&x=" + (int)var1.column + "&y=" + (int)var1.row + "&z=" + (int)var1.zoom + "&s=Galileo";
            return new String[]{var2};
        }
    }

    public static class GoogleTerrainProvider extends Google.GoogleProvider {

        private final String lang;

        public GoogleTerrainProvider() {
            this("en");
        }

        public GoogleTerrainProvider(String lang) {
            this.lang = lang;
        }

        public String[] getTileUrls(Coordinate var1) {
            String var2 = "http://mt1.google.com/vt/v=w2p.116&hl=" + this.lang + "&x=" + (int)var1.column + "&y=" + (int)var1.row + "&z=" + (int)var1.zoom + "&s=Galileo";
            return new String[]{var2};
        }
    }

    public static class GoogleApiMapProvider extends Google.GoogleProvider  {

        private final String lang;
        private final String apiKey;
        private String sessionKey;

        public GoogleApiMapProvider(String apiKey) {
            this(apiKey, "en");
        }

        public GoogleApiMapProvider(String apiKey, String lang) {
            this.apiKey = apiKey;
            this.lang = lang;
            this.sessionKey = createSessionKey();
        }

        private String createSessionKey() {
            String googleUrl = "https://tile.googleapis.com/v1/createSession?key=" + this.apiKey;
            try {
                URL url = new URL(googleUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);
                OutputStream os = conn.getOutputStream();
                os.write("{'mapType':'roadmap','language':'en-US','region':'US'}".getBytes());
                os.flush();
                os.close();

                int responseCode = conn.getResponseCode();
                System.out.println("POST Response Code :: " + responseCode);
                if (responseCode != HttpURLConnection.HTTP_OK)
                    throw new RuntimeException();

                String inputLine;
                StringBuffer response = new StringBuffer();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                System.out.println(response.toString());

                Map<String, String> map = JSONUtils.parse(response.toString(), HashMap.class);
                return map.get("session");

            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public String[] getTileUrls(Coordinate var1) {
            // https://tile.googleapis.com/v1/2dtiles/ZOOM_COL_ROW?session=YOUR_SESSION_TOKEN&key=YOUR_API_KEY&orientation=0_90_180_270

            String var2 = String.format("https://tile.googleapis.com/v1/2dtiles/%s?session=%s&key=%s&orientation=%d",
                this.getZoomString(var1),
                this.sessionKey,
                this.apiKey,
                0 // rotation
            );
            return new String[]{var2};
        }

        public String getZoomString(Coordinate var1) {
            return (int)var1.zoom + "/" + (int)var1.column + "/" + (int)var1.row;
        }

    }
}
