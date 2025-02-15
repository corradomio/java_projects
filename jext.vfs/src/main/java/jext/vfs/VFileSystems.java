package jext.vfs;

import jext.util.logging.Logger;
import jext.net.URL;

import java.io.File;
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
    private static Map<String, String> websites = new HashMap<>();
    private static Logger logger = Logger.getLogger(VFileSystems.class);

    static {
        try (InputStream inp = VFileSystems.class.getResourceAsStream("/jext/vfs/util/protocols.properties")) {
            Properties props = new Properties();
            props.load(inp);

            for(String proto: props.stringPropertyNames()) {
                String fclass = props.getProperty(proto);
                try {
                    VFileSystemFactory factory = (VFileSystemFactory) Class.forName(fclass).newInstance();
                    protocols.put(proto, factory);
                }
                catch (Exception e) {
                    websites.put(proto, fclass);
                }
            }
        }
        catch (Exception e) {
            logger.error(e, e);
        }
    }

    // ----------------------------------------------------------------------
    // Factory Methods
    // ----------------------------------------------------------------------

    public static void registerProtocol(String protocol, VFileSystemFactory factory) {
        protocols.put(protocol, factory);
    }
    
    public static void registerWebsite(String website, String protocol) {
        websites.put(website.toLowerCase(), protocol);
    }

    // ----------------------------------------------------------------------

    public static VFileSystem newFileSystem(File compressedFile) throws VFileSystemException {
        String protocol = extOf(compressedFile);
        String surl = String.format("%s:///%s", protocol, compressedFile.getAbsolutePath().replace('\\', '/'));
        return newFileSystem(surl);
    }

    private static String extOf(File file) {
        String name = file.getName();
        if (name.endsWith(".tar.tgz"))
            return "tgz";
        int p = name.lastIndexOf('.');
        return name.substring(p+1);
    }

    public static VFileSystem newFileSystem(String surl) throws VFileSystemException {
        Properties props = new Properties();
        return newFileSystem(surl, props);
    }

    public static VFileSystem newFileSystem(Properties props) throws VFileSystemException {
        String surl = props.getProperty(URL);
        return newFileSystem(surl, props);
    }

    /**
     * Select the correct VFileSystem based on the URL.
     *
     * @param surl URL of filesystem's home.
     * @param props properties used to access the filesystem. For example, user & password
     *              for FTP, Git, ...
     * @return a virtual file system object
     * @throws VFileSystemException
     */
    public static VFileSystem newFileSystem(String surl, Properties props) throws VFileSystemException {
        if (surl.endsWith(".git") && !surl.startsWith("git+"))
            surl = "git+" + surl;

        URL url = new URL(surl);
        String protocol;

        // check for 'protocol://<host>:<port>  ->  'new-protocol'
        String website = url.getProtocolHostPort().toLowerCase();
        if (websites.containsKey(website))
            protocol = websites.get(website);
        else
            protocol = url.getProtocol();

        if (!protocols.containsKey(protocol))
            throw new VFileSystemException("Unsupported protocol for " + url.toString());

        VFileSystemFactory factory = protocols.get(protocol);

        Properties nprops = new Properties();
        nprops.putAll(props);
        nprops.putAll(url.getParameters());

        VFileSystem vfs = factory.newFileSysytem(url, nprops);
        vfs.connect();

        return vfs;
    }

}
