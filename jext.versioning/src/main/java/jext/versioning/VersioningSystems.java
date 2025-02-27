package jext.versioning;

import jext.util.logging.Logger;
import jext.net.URL;
import jext.util.FileUtils;
import org.eclipse.jgit.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public abstract class VersioningSystems {

    private static Logger logger = Logger.getLogger(VersioningSystems.class);

    public static final String URL = "url";

    private static Map<String, VersioningSystemFactory> factories = new HashMap<>();
    static {
        loadProtocols();
    }

    public static VersioningSystem newInstance(Properties properties, File localDirectory) {
        String surl = properties.getProperty(URL);
        return newInstance(surl, properties, localDirectory);
    }

    public static VersioningSystem newInstance(String surl, Properties properties, File localDirectory) {
        if (StringUtils.isEmptyOrNull(surl))
            surl = "novs:///" + FileUtils.getAbsolutePath(localDirectory);

        // convert 'http:///.../.../project.git' in 'git+http:///...'
        if (surl.endsWith(".git") && !surl.startsWith("git+"))
            surl = "git+"+ surl;

        URL url = new URL(surl);
        String protocol = url.getProtocol();
        if (!factories.containsKey(protocol))
            throw new VersioningSystemException("Unsupported protocol in " + surl);
        VersioningSystemFactory vsfactory = factories.get(protocol);
        return vsfactory.newInstance(surl, properties, localDirectory);
    }

    private static void loadProtocols() {
        try (InputStream stream = VersioningSystems.class.getResource("/jext/versioning/protocols.properties").openStream()) {
            Properties configuration = new Properties();
            configuration.load(stream);

            for (String protocol : configuration.stringPropertyNames()) {
                String className = configuration.getProperty(protocol);

                try {
                    Class<VersioningSystemFactory> vsclass = (Class<VersioningSystemFactory>) Class.forName(className);
                    factories.put(protocol, vsclass.getDeclaredConstructor().newInstance());
                }
                catch (Exception e) {
                    logger.error(e);
                }
            }
        }
        catch (IOException e) {
            logger.error(e);
        }
    }
}
