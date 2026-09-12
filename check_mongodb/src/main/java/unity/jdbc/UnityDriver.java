package unity.jdbc;

import de.schlichtherle.license.CipherParam;
import de.schlichtherle.license.KeyStoreParam;
import de.schlichtherle.license.LicenseContent;
import de.schlichtherle.license.LicenseManager;
import de.schlichtherle.license.LicenseParam;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.logging.Logger;
import unity.annotation.GlobalSchema;
import unity.cache.Cache;
import unity.io.FileManager;
import unity.mapping.DatabaseMapping;
import unity.util.CipherParamImpl;
import unity.util.LicenseParamImpl;
import unity.util.PublicKeyStoreParamImpl;
import unity.util.StringFunc;
import unity.util.UsageTracker;

public class UnityDriver implements Driver {
    private static PrintWriter out;

    public static final Locale locale = Locale.getDefault();

    public static ResourceBundle i18n = ResourceBundle.getBundle("resources/unity", locale);

    public static final int _MAJORVERSION = 4;

    public static final int _MINORVERSION = 4;

    public static final int _BUILD = 146;

    public static final boolean NO_TRIAL_EXCEPTION = true;

    private static boolean TRIAL = true;

    private static boolean EXPIRED = true;

    private static Date EXPIRY_DATE;

    private static boolean CLIENT_VERSION = false;

    private static final boolean FREE_VERSION = true;

    private static final int CONNECTION_LIMIT = 3;

    private static final int MAX_RESULTS = 100;

    public static final String DRIVER_NAME = "UnityJDBC";

    public static boolean DEBUG = false;

    private static Cache cache = null;

    private static DatabaseMapping mapper = null;

    public static final String VIRTUAL_NAME = "virtual";

    public static final String SOURCES_PROPERTY = "sources";

    public static final String JDBC_URL = "jdbc:unity://";

    public static ClassLoader classLoader;

    private static boolean LICENSE_PROCESSED;

    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

    static {
        try {
            DriverManager.registerDriver(new UnityDriver());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to register UnityJDBC Driver: " + e);
        }
    }

    public UnityDriver() throws SQLException {
        String email = "testuser@unityjdbc.com";
        InputStream in2 = null;
        debug("In UnityDriver constructor");
        if (!LICENSE_PROCESSED) {
            try {
                PublicKeyStoreParamImpl publicKeyStoreParamImpl = new PublicKeyStoreParamImpl();
                CipherParamImpl cipherParamImpl = new CipherParamImpl();
                LicenseParamImpl licenseParamImpl = new LicenseParamImpl((KeyStoreParam)publicKeyStoreParamImpl, (CipherParam)cipherParamImpl);
                LicenseManager lm = new LicenseManager((LicenseParam)licenseParamImpl);
                in2 = getLicenseStream();
                LicenseContent lc = null;
                if (in2 != null && !LICENSE_PROCESSED) {
                    lm.install(in2);
                    lc = lm.verify();
                    Boolean[] b = (Boolean[])lc.getExtra();
                    if (b != null) {
                        CLIENT_VERSION = b[1].booleanValue();
                        EXPIRY_DATE = lc.getNotAfter();
                        Date issueDate = lc.getIssued();
                        if (EXPIRY_DATE != null) {
                            debug("License Issued date: " + issueDate);
                            debug("Trial End date:      " + EXPIRY_DATE);
                        }
                        Date currentDate = new Date();
                        TRIAL = b[0].booleanValue();
                        if (TRIAL)
                            if (EXPIRY_DATE == null) {
                                EXPIRED = true;
                            } else {
                                EXPIRED = currentDate.after(EXPIRY_DATE);
                            }
                        String uinfo = lc.getHolder().toString();
                        int idx = uinfo.indexOf("EMAILADDRESS=");
                        if (idx > 0)
                            email = uinfo.substring(idx + 13);
                    }
                    LICENSE_PROCESSED = true;
                }
            } catch (Exception e) {
                if (e.toString().contains("ClassCastException")) {
                    TRIAL = false;
                } else {
                    TRIAL = false;
                }
            } catch (Error e) {
                if (e.toString().contains("ClassCastException")) {
                    TRIAL = false;
                } else {
                    TRIAL = false;
                }
            }
            FileManager.closeStream(in2);
        }
        debug("Loading function mapping");
        if (mapper == null) {
            BufferedReader reader = null;
            try {
                InputStream in = getClass().getClassLoader().getResourceAsStream("unity/mapping/.mapping");
                if (in == null)
                    throw new SQLException(i18n.getString("UnityDriver.MapFileNotFound"));
                classLoader = getClass().getClassLoader();
                reader = new BufferedReader(new InputStreamReader(FileManager.getDecryptedStream(in, "UnityDriver.class", "PBKDF2WithHmacSHA1", "AES", 65536, 128, "AES/CBC/PKCS5Padding"), Charset.forName("UTF-8")));
                mapper = new DatabaseMapping();
                DatabaseMapping.load(reader);
            } catch (IOException e) {
                throw new SQLException(i18n.getString("UnityDriver.ErrorLoadMapFile"));
            } finally {
                try {
                    if (reader != null)
                        reader.close();
                } catch (Exception exception) {}
            }
            reader = null;
            try {
                InputStream in = getClass().getClassLoader().getResourceAsStream("mapping.xml");
                if (in != null) {
                    reader = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
                    DatabaseMapping.load(reader);
                    debug(i18n.getString("UnityDriver.ReadUserMapFile"));
                }
            } catch (Exception exception) {
                try {
                    if (reader != null)
                        reader.close();
                } catch (Exception exception1) {}
            } finally {
                try {
                    if (reader != null)
                        reader.close();
                } catch (Exception exception) {}
            }
        }
        debug("Functions loaded");
        UsageTracker tracker = new UsageTracker(email);
        tracker.start();
        debug("Driver constructor finished.");
    }

    private InputStream getLicenseStream() {
        InputStream in2 = getClass().getClassLoader().getResourceAsStream("license.lic");
        if (in2 == null)
            try {
                in2 = FileManager.openInputFile("license.lic");
            } catch (IOException iOException) {}
        if (in2 == null)
            debug("Unable to read license");
        if (in2 == null) {
            in2 = getClass().getResourceAsStream("license.lic");
            if (in2 != null)
                debug("B");
        }
        if (in2 == null) {
            in2 = getClass().getResourceAsStream("/license.lic");
            if (in2 != null)
                debug("C");
        }
        return in2;
    }

    public boolean acceptsURL(String url) throws SQLException {
        if (url == null)
            return false;
        int position = url.toLowerCase().indexOf("jdbc:unity://");
        if (position < 0 || position > 0)
            return false;
        return true;
    }

    public Connection connect(String sourcesurl, Properties info) throws SQLException {
        String url = sourcesurl;
        if (!acceptsURL(url))
            return null;
        boolean generateNew = false;
        int paramPos = url.indexOf("?");
        if (paramPos > 0) {
            String params = url.substring(paramPos + 1);
            StringTokenizer myTokenizer = new StringTokenizer(params, "=&;");
            while (myTokenizer.hasMoreTokens()) {
                String val = myTokenizer.nextToken();
                if (val.equalsIgnoreCase("debug")) {
                    if (myTokenizer.hasMoreTokens()) {
                        val = myTokenizer.nextToken();
                        if (val.equalsIgnoreCase("true"))
                            DEBUG = true;
                    }
                    continue;
                }
                if (val.equalsIgnoreCase("new")) {
                    if (myTokenizer.hasMoreTokens()) {
                        val = myTokenizer.nextToken();
                        info.setProperty("new", val);
                    } else {
                        info.setProperty("new", "true");
                    }
                    generateNew = true;
                    continue;
                }
                if (val.equalsIgnoreCase("sort_buffer_size")) {
                    if (myTokenizer.hasMoreTokens()) {
                        val = myTokenizer.nextToken();
                        info.setProperty("sort_buffer_size", val);
                    }
                    continue;
                }
                if (val.equalsIgnoreCase("join_buffer_size")) {
                    if (myTokenizer.hasMoreTokens()) {
                        val = myTokenizer.nextToken();
                        info.setProperty("join_buffer_size", val);
                    }
                    continue;
                }
                if (val.equalsIgnoreCase("log")) {
                    if (myTokenizer.hasMoreTokens()) {
                        val = myTokenizer.nextToken();
                        info.setProperty("log", val);
                    }
                    continue;
                }
                String key = val;
                if (myTokenizer.hasMoreTokens()) {
                    val = myTokenizer.nextToken();
                    info.setProperty(key, val);
                }
            }
            url = url.substring(0, paramPos);
        }
        String log = info.getProperty("log");
        if (log != null && !log.equals(""))
            initializeLog(log);
        int position = url.toLowerCase().indexOf("jdbc:unity://");
        String fileName = url.substring(position + 13);
        BufferedReader reader = null;
        // if (DEBUG) {
        //     debug("UnityJDBC Version " + getMajorVersion() + "." + getMinorVersion() + "." + ');
        //     debug("Sources file: " + fileName);
        // }
        GlobalSchema gs = new GlobalSchema();
        if (fileName.equals("virtual")) {
            String sources = info.getProperty("sources");
            gs.parseSources(sources, info);
        } else {
            boolean encrypted = false;
            try {
                String password = info.getProperty("password");
                if (password != null && !password.equals("")) {
                    encrypted = true;
                    InputStream is = FileManager.getDecryptedStream(fileName, password, info);
                    reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                } else {
                    reader = new BufferedReader(new InputStreamReader(FileManager.getStream(fileName), Charset.forName("UTF-8")));
                }
            } catch (IOException e) {
                if (generateNew) {
                    try {
                        gs.exportSources(fileName);
                        reader = new BufferedReader(new InputStreamReader(FileManager.getStream(fileName), Charset.forName("UTF-8")));
                    } catch (IOException e2) {
                        throw new SQLException("Unable to create new connection information at: " + fileName);
                    }
                } else {
                    throw new SQLException("Unable to retrieve connection information at: " + fileName + " Encrypted: " + encrypted + " Error: " + e);
                }
            }
            gs.parseSourcesFile(reader, url, info);
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException iOException) {}
        }
        UnityConnection unityconn = new UnityConnection(gs, info);
        unityconn.setCache(cache);
        debug("UnityConnection created.");
        return unityconn;
    }

    public int getMajorVersion() {
        return 4;
    }

    public int getMinorVersion() {
        return 4;
    }

    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        String[] keys = { "debug", "new", "user", "password", "encoding", "log", "join_buffer_size", "sort_buffer_size", "mappings", "cache" };
        String[] values = { "false", "false", "", "", "utf-8", "", "20000000", "20000000", "", "" };
        String[][] choices = { { "true", "false" }, { "true", "false" }, {}, {}, { "utf-8" }, {}, { "A positive integer >= 1000000" }, { "A positive integer >= 1000000" }, {}, {} };
        String[] description = { "The debug property will cause the driver to print out debug information to the console during its operation.", "If true, create a new virtual source.", "User name for connection.", "Password for connection.", "Character encoding used.", "File location of log file to store debugging information if debug is true.", "Size to use in bytes for join operations.", "Size to use in bytes for sort operations.", "JSON Array encoding of mapping rules that allow for resolving table naming conflicts (same table in two or more sources) without prefixing with source name.Each rule has the form: {{\"tableName\":\"<yourTableName>\",\"databaseName\":\"<yourDatabaseName>\",\"op\":\"One of: SELECT, WRITE, INSERT, UPDATE, DELETE\"}", "JSON Array encoding of caching rules that allow for caching query results at the driver-level for improved performance.Each rule has the form: {{\"pattern\":\"<Java regular expression to match SQL query against>\",\"time\":<cache time-to-live in milliseconds>\"}" };
        boolean[] required = { false, false, false, false, false, false, false, false, false, false };
        DriverPropertyInfo[] dpinfo = new DriverPropertyInfo[keys.length];
        for (int i = 0; i < keys.length; i++) {
            dpinfo[i] = new DriverPropertyInfo(keys[i], values[i]);
            if (info != null && info.containsKey(keys[i]))
                (dpinfo[i]).value = info.getProperty(keys[i]);
            (dpinfo[i]).description = description[i];
            (dpinfo[i]).choices = choices[i];
            (dpinfo[i]).required = required[i];
        }
        return dpinfo;
    }

    public boolean jdbcCompliant() {
        return false;
    }

    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new SQLFeatureNotSupportedException(i18n.getString("UnityDriver.NoLogging"));
    }

    public static int getConnectionLimit() {
        return Integer.MAX_VALUE;
    }

    public static boolean isClientVersion() {
        return CLIENT_VERSION;
    }

    public static boolean isFreeVersion() {
        return true;
    }

    public static int getMaxResults() {
        return 100;
    }

    public static synchronized void createCache() {
        if (cache == null) {
            cache = new Cache();
            debug("Created query cache.");
        }
    }

    public static synchronized void closeCache() {
        if (cache != null) {
            cache.close();
            cache = null;
            debug("Cache closed.");
        }
    }

    public static synchronized void clearCache() {
        if (cache != null)
            cache.clear();
    }

    public static String getVersion() {
        String version = "UnityJDBC";
        if (TRIAL) {
            version = version + " Trial ";
        } else {
            version = version + " Full ";
        }
        if (CLIENT_VERSION) {
            version = version + "Client ";
        } else {
            version = version + "Server ";
        }
        version = "UnityJDBC Free ";
        version = version + "4.4.146";
        return version;
    }

    public static void logVersion() {
        if (DEBUG) {
            String version = "UnityJDBC";
            if (TRIAL) {
                version = version + " Trial ";
            } else {
                version = version + " Full ";
            }
            if (CLIENT_VERSION) {
                version = version + "Client ";
            } else {
                version = version + "Server ";
            }
            version = version + "4.4.146";
            debug(version);
        }
    }

    public String getLicense() {
        String license = "No license";
        PublicKeyStoreParamImpl publicKeyStoreParamImpl = new PublicKeyStoreParamImpl();
        CipherParamImpl cipherParamImpl = new CipherParamImpl();
        LicenseParamImpl licenseParamImpl = new LicenseParamImpl((KeyStoreParam)publicKeyStoreParamImpl, (CipherParam)cipherParamImpl);
        LicenseManager lm = new LicenseManager((LicenseParam)licenseParamImpl);
        InputStream in2 = getLicenseStream();
        LicenseContent lc = null;
        String email = "testuser@unityjdbc.com";
        String name = "Trial User";
        String org = "UnityJDBC";
        if (in2 != null)
            try {
                lm.install(in2);
                lc = lm.verify();
                Boolean[] b = (Boolean[])lc.getExtra();
                if (b != null) {
                    CLIENT_VERSION = b[1].booleanValue();
                    TRIAL = b[0].booleanValue();
                    String uinfo = lc.getHolder().toString();
                    StringTokenizer tokenizer = new StringTokenizer(uinfo, ",=");
                    while (tokenizer.hasMoreTokens()) {
                        String st = tokenizer.nextToken().trim();
                        if (st.equals("EMAILADDRESS")) {
                            email = tokenizer.nextToken();
                            continue;
                        }
                        if (st.equals("CN")) {
                            name = tokenizer.nextToken();
                            continue;
                        }
                        if (st.equals("O"))
                            org = tokenizer.nextToken();
                    }
                    license = "Licensed to: \n" + name + "\n" + org + "\n" + email;
                    String expiryDate = getTrialExpiryDate();
                    if (!expiryDate.equals("NO EXPIRY"))
                        license = license + "\nTrial license expires: " + expiryDate;
                }
            } catch (Exception exception) {

            } finally {
                FileManager.closeStream(in2);
            }
        return license;
    }

    public static ResultSet translate(String sql) {
        return null;
    }

    public static String getURLFilePath(String url) {
        int paramLoc = url.lastIndexOf('?');
        if (paramLoc >= 0)
            return url.substring(url.toLowerCase().indexOf("jdbc:unity://") + 13, paramLoc);
        return url.substring(url.toLowerCase().indexOf("jdbc:unity://") + 13);
    }

    public static boolean isTrial() {
        return TRIAL;
    }

    public static boolean isExpiredTrial() {
        return (TRIAL && EXPIRED);
    }

    public static String getTrialExpiryDate() {
        if (EXPIRY_DATE != null)
            return StringFunc.formatDate(EXPIRY_DATE, "yyyy-MM-dd");
        return "NO EXPIRY";
    }

    public static void initializeLog(String log) {
        try {
            if (out == null)
                out = new PrintWriter(new FileWriter(log, true));
        } catch (IOException e) {
            debugException(e);
        }
    }

    public static synchronized void debug(String message) {
        if (DEBUG) {
            Date date = new Date();
            String timestamp = df.format(date);
            try {
                StringBuffer buf = new StringBuffer();
                buf.append("[");
                buf.append(timestamp);
                buf.append("] ");
                buf.append(message);
                String output = buf.toString();
                if (out != null) {
                    out.println(output);
                    out.println();
                    out.flush();
                }
            } catch (Exception exception) {}
        }
    }

    public static synchronized void debugException(Exception ex) {
        if (DEBUG) {
            StringWriter writer = new StringWriter();
            PrintWriter printWriter = new PrintWriter(writer);
            ex.printStackTrace(printWriter);
            Date date = new Date();
            String timestamp = df.format(date);
            try {
                StringBuffer buf = new StringBuffer();
                buf.append("[");
                buf.append(timestamp);
                buf.append("] ");
                buf.append(writer.toString());
                String output = buf.toString();
                if (out != null) {
                    out.println(output);
                    out.println();
                    out.flush();
                }
            } catch (Exception exception) {}
        }
    }

    public static synchronized void debugTrace() {
        if (DEBUG) {
            Date date = new Date();
            String timestamp = df.format(date);
            try {
                StringBuffer buf = new StringBuffer();
                buf.append("[");
                buf.append(timestamp);
                buf.append("] Stack: ");
                StackTraceElement[] arrayOfStackTraceElement = Thread.currentThread().getStackTrace();
                buf.append(Arrays.toString(Arrays.copyOfRange((Object[])arrayOfStackTraceElement, 2, arrayOfStackTraceElement.length)));
                String output = buf.toString();
                if (out != null) {
                    out.println(output);
                    out.println();
                    out.flush();
                }
            } catch (Exception exception) {}
        }
    }
}
