package ae.ac.ebtic.sql.bt.btproxy;

import org.apache.http.util.Asserts;

import java.sql.SQLException;

public class URLUtils {

    private static final String PROTOCOL = ae.ac.ebtic.sql.bt.btproxy.Driver.PROTOCOL + "//";
    private static final String PROTOCOL_HTTP = ae.ac.ebtic.sql.bt.btproxy.Driver.PROTOCOL_HTTP + "//";
    private static final String PROTOCOL_HTTPS = ae.ac.ebtic.sql.bt.btproxy.Driver.PROTOCOL_HTTPS + "//";

    public static String parse(String url_) throws SQLException {
        String http  = "http";
        String url = url_;

        // jdbc:btproxy://host:port/base
        // jdbc:btproxy:http://host:port/base
        // jdbc:btproxy:https://host:port/base

        Asserts.check(url.startsWith("jdbc:btproxy:"), "Invalid jdbc url");

        if (url.startsWith(PROTOCOL_HTTPS)) {
            http = "https";
            url = url.substring(PROTOCOL_HTTPS.length());
        }
        else if (url.startsWith(PROTOCOL_HTTP)) {
            url = url.substring(PROTOCOL_HTTP.length());
        }
        else if (url.startsWith(PROTOCOL)) {
            url = url.substring(PROTOCOL.length());
        }
        else {
            throw jext.sql.SQLException.of("Unsupported jdbc url", url_);
        }

        // host:port/base
        int c = url.indexOf(':');
        int s = url.indexOf('/');
        if (c == -1 || s == -1)
            throw jext.sql.SQLException.of("Unsupported jdbc url", url_);

        String host = url.substring(0, c);
        String port = url.substring(c+1, s);
        String base = url.substring(s+1);

        return String.format("%s://%s:%s/%s", http, host, port, base);
    }

}
