package jext.versioning;

import jext.logging.Logger;
import jext.net.URL;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public abstract class VersioningSystems {

    private static Logger logger = Logger.getLogger(VersioningSystems.class);

    public static final String URL = "url";

    private static Map<String, Class<VersioningSystem>> protocols = new HashMap<>();
    static {
        loadProtocols();
    }

    public static VersioningSystem newInstance(String url, Properties properties) {
        Properties nprops = new Properties();
        nprops.putAll(properties);
        nprops.put(URL, url);
        return newInstance(nprops);
    }

    public static VersioningSystem newInstance(Properties properties) {
        String surl = properties.getProperty(URL);
        URL url = new URL(surl);
        String protocol = url.getProtocol();
        if (!protocols.containsKey(protocol))
            throw new VersioningSystemException("Unsupported protocol in " + surl);
        Class<VersioningSystem> vsclass = protocols.get(protocol);
        try {
            return vsclass.getDeclaredConstructor(Properties.class).newInstance(properties);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new VersioningSystemException("Unable to create an istance of " + vsclass.getName(), e);
        }
    }

    private static void loadProtocols() {
        try (InputStream stream = VersioningSystems.class.getResource("/jext/versioning/protocols.properties").openStream()) {
            Properties configuration = new Properties();
            configuration.load(stream);

            for (String protocol : configuration.stringPropertyNames()) {
                String className = configuration.getProperty(protocol);

                try {
                    Class<VersioningSystem> vsclass = (Class<VersioningSystem>) Class.forName(className);
                    protocols.put(protocol, vsclass);
                }
                catch (ClassNotFoundException e) {
                    logger.error(e);
                }
            }
        }
        catch (IOException e) {
            logger.error(e);
        }
    }
}
