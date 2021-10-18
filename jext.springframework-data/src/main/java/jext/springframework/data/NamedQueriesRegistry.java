package jext.springframework.data;

import jext.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NamedQueriesRegistry {

    private static Logger logger = LogManager.getLogger(NamedQueriesRegistry.class);

    /*
            <?xml version="1.0" encoding="UTF-8"?>
            <configuration>
                <system>
                    <graphdb>
                        <namedqueries>
                            <query name="findTypeClosure">
                                MATCH (f:type {projectId: $projectId}) -[:uses*]-> (n:type {projectId: $projectId}
                            </query>
                            ...
                        </namedqueries>
                    </graphdb>
                </system
            </configuration>
     */

    private static final String QUERIES = "/configuration/system/graphdb/namedqueries/query";
    private static final String NAME = "name";
    private static final long MIN_CHECK_TIMEOUT = 60000; // 60 seconds = 1 minute

    private File namedQueriesFile;
    private long lastModified;
    private long timestamp;
    private Map<String, String> namedQueries = new HashMap<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public NamedQueriesRegistry() {

    }

    // ----------------------------------------------------------------------
    // Configure
    // ----------------------------------------------------------------------

    public void configure(File namedQueriesFile){
        if (!namedQueriesFile.exists())
            throw new RuntimeException(String.format("NamedQueries configuration file %s not found",
                    namedQueriesFile.getAbsolutePath()));

        this.namedQueriesFile = namedQueriesFile;
        check();
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public String get(String name) {
        check();
        if (!namedQueries.containsKey(name))
            throw new IllegalArgumentException(String.format("Named query %s not existent", name));
        return namedQueries.get(name);
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private synchronized void check() {
        File namedQueriesFile = this.namedQueriesFile;
        long now = System.currentTimeMillis();
        if ((now - timestamp) < MIN_CHECK_TIMEOUT)
            return;

        this.timestamp = now;
        long lastModified = this.namedQueriesFile.lastModified();
        if (this.lastModified == lastModified)
            return;

        this.lastModified = lastModified;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(false);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(namedQueriesFile);

            XPath xPath = XPathFactory.newInstance().newXPath();
            NodeList nodeList = (NodeList) xPath.compile(QUERIES).evaluate(doc, XPathConstants.NODESET);

            logger.info(String.format("configure [%s]", namedQueriesFile.getAbsolutePath()));

            this.namedQueries.clear();

            for (int i = 0; i < nodeList.getLength(); ++i) {
                Element query = (Element) nodeList.item(i);
                String name = query.getAttribute(NAME);
                String body = StringUtils.trimnl(query.getTextContent());

                this.namedQueries.put(name, body);

                logger.info(String.format("   %s", name));
            }

            logger.info("done");
        }
        catch (Exception e) {
            logger.error(String.format("Unable to parse %s: %s", namedQueriesFile.toString(), e.toString()));
        }
    }

}
