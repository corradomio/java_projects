package ae.ac.ebtic.sql.bt.btproxy;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

public class Driver  implements java.sql.Driver {

    static final String PROTOCOL = "jdbc:btproxy:";
    static final String PROTOCOL_HTTP = "jdbc:btproxy:http:";
    static final String PROTOCOL_HTTPS = "jdbc:btproxy:https:";

    private static final Logger PARENT_LOGGER = Logger.getLogger("ae.ac.ebtic.sql.bt.btproxy");
    private static final Logger LOGGER = Logger.getLogger("ae.ac.ebtic.sql.bt.btproxy.Driver");

    // ----------------------------------------------------------------------

    static final String LOGIN_SUFFIX = "login_optimiser";
    static final String QUERY_SUFFIX = "query_database";


    // ----------------------------------------------------------------------

    @Override
    public Connection connect(String jdbcUrl, Properties info) throws SQLException {
        if (jdbcUrl == null) {
            throw new SQLException("url is null");
        }

        if (!jdbcUrl.startsWith(PROTOCOL)) {
            // MUST NOT throw an exception, because the DriverManager uses this value to understand
            // that THIS driver is for the specified DBMS, and it tries another driver
            return null;
        }

        String user = info.getProperty("user");
        String password = info.getProperty("password");

        if (user == null || user.isEmpty())
            throw new SQLException("user is null");
        if (password == null || password.isEmpty())
            throw new SQLException("password is null");

        Properties defaults = getDefaultProperties();

        HttpClient client = new DefaultHttpClient();
        String httpUrl = URLUtils.parse(jdbcUrl);
        String token = tryConnect(client, httpUrl, info);

        return new BTConnection(client, httpUrl, token);
    }

    private String tryConnect(HttpClient client, String httpUrl, Properties info) throws SQLException {
        Map<String, Object> jresponse;
        StatusLine statusLine;

        String username = info.getProperty("user");
        String password = info.getProperty("password");

        try {
            HttpPost post = new HttpPost(String.format("%s/%s", httpUrl, LOGIN_SUFFIX));

            POSTUtils.setBody(post, MapUtils.asMap(
                "username", username,
                "password", password
            ));

            HttpResponse response = client.execute(post);

            statusLine = response.getStatusLine();
            jresponse = JSONUtils.parse(response.getEntity().getContent(), LinkedHashMap.class);
        }
        catch (Exception e) {
            throw new jext.sql.SQLException("Connection failed", e.getMessage());
        }

        if (statusLine.getStatusCode() >= 400) {
            throw new jext.sql.SQLException("Connection failed", statusLine.getReasonPhrase());
        }

        String token = MapUtils.get(jresponse, "token");

        return token;
    }

    private Properties getDefaultProperties() {
        Properties defaults = new Properties();

        return defaults;
    }

    @Override
    public boolean acceptsURL(String url) throws SQLException {
        return url.startsWith(PROTOCOL) ||
            url.startsWith(PROTOCOL_HTTP) ||
            url.startsWith(PROTOCOL_HTTPS);
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        return new DriverPropertyInfo[0];
    }

    @Override
    public int getMajorVersion() {
        return 1;
    }

    @Override
    public int getMinorVersion() {
        return 0;
    }

    @Override
    public boolean jdbcCompliant() {
        return false;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return PARENT_LOGGER;
    }

    // ----------------------------------------------------------------------

    static {
        try {
            // moved the registerDriver from the constructor to here
            // because some clients call the driver themselves (I know, as
            // my early jdbc work did - and that was based on other examples).
            // Placing it here, means that the driver is registered once only.
            register();
        } catch (SQLException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private static Driver registeredDriver;

    public static boolean isRegistered() {
        return registeredDriver != null;
    }

    public static void register() throws SQLException {
        if (isRegistered()) {
            throw new IllegalStateException(
                "Driver is already registered. It can only be registered once.");
        }
        Driver registeredDriver = new Driver();
        DriverManager.registerDriver(registeredDriver);
        Driver.registeredDriver = registeredDriver;
    }
}
