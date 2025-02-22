package ae.ac.ebtic.sql.bt.btproxy;

import java.sql.SQLException;

public class URLUtils {

    private static final String DEFAULT_PROTOCOL = "http";
    private static final String PROTOCOL = Driver.PROTOCOL + "//";
    private static final String PROTOCOL_HTTP = Driver.PROTOCOL_HTTP + "//";
    private static final String PROTOCOL_HTTPS = Driver.PROTOCOL_HTTPS + "//";
    private static final String LOGIN_SUFFIX = Driver.LOGIN_SUFFIX;
    private static final String QUERY_SUFFIX = Driver.QUERY_SUFFIX;

    // ----------------------------------------------------------------------

    public static String parse(String url_) throws SQLException {
        // jdbc:btproxy://host:port/base
        // jdbc:btproxy:http://host:port/base
        // jdbc:btproxy:https://host:port/base

        String http  = DEFAULT_PROTOCOL;
        String url = url_;

        int len = url.length();
        if (url.endsWith(LOGIN_SUFFIX))
            // remove '/login_optimiser'
            url = url.substring(0, len - LOGIN_SUFFIX.length() - 1);
        if (url.endsWith(QUERY_SUFFIX))
            // remove '/query_database'
            url = url.substring(0, len - QUERY_SUFFIX.length() - 1);

        // Asserts.check(url.startsWith("jdbc:btproxy:"), "Invalid jdbc url");

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
            throw new jext.sql.SQLException("Unsupported jdbc url", url_);
        }

        String host, port, base;

        // host/base
        // host:port/base
        int c = url.indexOf(':');
        int s = url.indexOf('/');

        // host:port/base
        if (c == -1 && s != -1) {
            host = url.substring(0, s);
            base = url.substring(s+1);
            return String.format("%s://%s/%s", http, host, base);
        }
        // host/base
        else if (c != -1 && s != -1 ) {
            host = url.substring(0, c);
            port = url.substring(c+1, s);
            base = url.substring(s+1);
            return String.format("%s://%s:%s/%s", http, host, port, base);
        }
        // boh!
        else {
            throw new jext.sql.SQLException("Unsupported jdbc url", url_);
        }
    }

}
