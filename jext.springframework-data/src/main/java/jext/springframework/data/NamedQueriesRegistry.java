package jext.springframework.data;

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

    private File namedQueriesFile;
    private long nqfTimestamp;

    private Map<String, String> namedQueries = new HashMap<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public NamedQueriesRegistry(File namedQueriesFile) {
        this.namedQueriesFile = namedQueriesFile;
        this.nqfTimestamp = 0;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public String get(String name) {
        if (this.nqfTimestamp < namedQueriesFile.lastModified())
            loadQueries();

        return namedQueries.get(name);
    }

    // ----------------------------------------------------------------------
    // Load Queries
    // ----------------------------------------------------------------------

    private void loadQueries(){
        File definitionQueries = this.namedQueriesFile;
        if (!definitionQueries.exists()) {
            logger.error(String.format("Named queries file %s not found", definitionQueries.getAbsolutePath()));
            this.nqfTimestamp = Long.MAX_VALUE;
            return;
        }

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(false);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(definitionQueries);

            XPath xPath = XPathFactory.newInstance().newXPath();
            NodeList nodeList = (NodeList) xPath.compile(QUERIES).evaluate(doc, XPathConstants.NODESET);

            logger.info(String.format("configure [%s]", definitionQueries.getAbsolutePath()));

            this.namedQueries.clear();

            for (int i = 0; i < nodeList.getLength(); ++i) {
                Element query = (Element) nodeList.item(i);
                String name = query.getAttribute(NAME);
                String body = trim(query.getTextContent());

                this.namedQueries.put(name, body);

                logger.info(String.format("   %s", name));
            }
            logger.info("done");

            this.nqfTimestamp = definitionQueries.lastModified();
        }
        catch (Exception e) {
            logger.error(String.format("Unable to parse %s: %s", definitionQueries.toString(), e.toString()));
        }
    }

    private static String trim(String s) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(s.split("\n"))
            .map(String::trim)
            .forEach(part -> {
                if (sb.length() > 0)
                    sb.append("\n");
                sb.append(part);
            });
        return sb.toString().trim();
    }

}
