package jext.graph;

import jext.logging.Logger;
import jext.net.URL;

import java.io.InputStream;
import java.util.Map;
import java.util.HashMap;
import java.util.Properties;

public class GraphDatabases {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private static final String URL = "url";
    private static final String URI = "uri";
    private static Map<String, GraphDatabaseFactory> protocols = new HashMap<>();
    private static Logger logger = Logger.getLogger(GraphDatabases.class);

    static {
        try (InputStream inp = GraphDatabases.class.getResourceAsStream("/jext/graphdb/util/protocols.properties")) {
            Properties props = new Properties();
            props.load(inp);

            for(String proto: props.stringPropertyNames()) {
                String fclass = props.getProperty(proto);
                GraphDatabaseFactory factory = (GraphDatabaseFactory) Class.forName(fclass).newInstance();

                protocols.put(proto, factory);
            }
        }
        catch (Exception e) {
            logger.error(e, e);
        }
    }

    // ----------------------------------------------------------------------
    // Factory Methods
    // ----------------------------------------------------------------------

    public static GraphDatabase newGraphDatabase(Properties props) throws GraphDatabaseException {
        String surl = props.getProperty(URL);
        if (surl == null)
            surl = props.getProperty(URI);
        return newGraphDatabase(surl, props);
    }

    public static GraphDatabase newGraphDatabase(String surl, Properties props) throws GraphDatabaseException {
        jext.net.URL url = new URL(surl);
        if (!protocols.containsKey(url.getProtocol()))
            throw new GraphDatabaseException("Unsupported protocol " + url.getProtocol());

        GraphDatabaseFactory factory = protocols.get(url.getProtocol());
        Properties nprops = new Properties();
        nprops.putAll(props);
        nprops.putAll(url.getParameters());

        // compatibility between "user" & "username"
        if (props.containsKey("username"))
            nprops.put("user", props.get("username"));

        GraphDatabase graphdb = factory.newGraphDatabase(url, nprops);
        graphdb.initialize();
        return graphdb;
    }

}
