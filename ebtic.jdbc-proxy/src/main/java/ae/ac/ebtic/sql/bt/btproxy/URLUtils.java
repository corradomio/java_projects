package ae.ac.ebtic.sql.bt.btproxy;

import java.sql.SQLException;

public class URLUtils {

    private static final String DEFAULT_PROTOCOL = "http";
    private static final String PROTOCOL = Driver.PROTOCOL + "//";
    private static final String PROTOCOL_HTTP = Driver.PROTOCOL_HTTP + "//";
    private static final String PROTOCOL_HTTPS = Driver.PROTOCOL_HTTPS + "//";

    // ----------------------------------------------------------------------

    public static String parse(String url_) throws SQLException {
        // jdbc:btproxy://host:port/base
        // jdbc:btproxy:http://host:port/base
        // jdbc:btproxy:https://host:port/base

        String http  = DEFAULT_PROTOCOL;
        String url = url_;

        // Asserts.check(url.startsWith("jdbc:btproxy:"), "Invalid jdbc url");

        if (url.startsWith(PROTOCOL_HTTPS)) {
            http = DEFAULT_PROTOCOL;
            url = url.substring(PROTOCOL_HTTPS.length());
        }
        else if (url.startsWith(PROTOCOL_HTTP)) {
            url = url.substring(PROTOCOL_HTTP.length());
        }
        else if (url.startsWith(PROTOCOL)) {
            url = url.substring(PROTOCOL.length());
        }
        else {
            throw new jext.sql.SQLException("Unsupported jdbc url", url_);
        }

        // host:port/base
        int c = url.indexOf(':');
        int s = url.indexOf('/');
        if (c == -1 || s == -1)
            throw new jext.sql.SQLException("Unsupported jdbc url", url_);

        String host = url.substring(0, c);
        String port = url.substring(c+1, s);
        String base = url.substring(s+1);

        return String.format("%s://%s:%s/%s", http, host, port, base);
    }

}
