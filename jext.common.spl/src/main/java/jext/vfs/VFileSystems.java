package jext.vfs;

import jext.logging.Logger;
import jext.net.URL;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class VFileSystems {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private static final String URL = "url";
    private static Map<String, VFileSystemFactory> protocols = new HashMap<>();
    private static Logger logger = Logger.getLogger(VFileSystems.class);

    static {
        try (InputStream inp = VFileSystems.class.getResourceAsStream("/jext/vfs/util/protocols.properties")) {
            Properties props = new Properties();
            props.load(inp);

            for(String proto: props.stringPropertyNames()) {
                String fclass = props.getProperty(proto);
                VFileSystemFactory factory = (VFileSystemFactory) Class.forName(fclass).newInstance();

                protocols.put(proto, factory);
            }
        } catch (Exception e) {
            logger.error(e, e);
        }
    }

    // ----------------------------------------------------------------------
    // Factory Methods
    // ----------------------------------------------------------------------

    public static VFileSystem newFileSystem(String surl) throws VFileSystemException {
        Properties props = new Properties();
        return newFileSystem(surl, props);
    }

    public static VFileSystem newFileSystem(Properties props) throws VFileSystemException {
        String surl = props.getProperty(URL);
        return newFileSystem(surl, props);
    }

    public static VFileSystem newFileSystem(String surl, Properties props) throws VFileSystemException {
        URL url = new URL(surl);
        if (!protocols.containsKey(url.getProtocol()))
            throw new VFileSystemException("Unsupported protocol " + url.getProtocol());

        VFileSystemFactory factory = protocols.get(url.getProtocol());
        Properties nprops = new Properties();
        nprops.putAll(props);
        nprops.putAll(url.getParameters());

        return factory.newFileSysytem(url, nprops);
    }

}
