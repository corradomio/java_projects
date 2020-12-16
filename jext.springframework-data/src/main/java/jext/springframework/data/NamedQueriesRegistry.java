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

    private Map<String, String> namedQueries = new HashMap<>();

    // /configuration/system/graphdb/namedqueries/query
    private String queries = "/configuration/system/graphdb/namedqueries/query";
    // name
    private String name = "name";

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public void setQueriesPath(String xpath) {
        this.queries = xpath;
    }

    public void setQueryName(String name) {
        this.name = name;
    }

    // ----------------------------------------------------------------------
    // Load Queries
    // ----------------------------------------------------------------------

    public void loadQueries(String definitionQueries) {
        loadQueries(new File(definitionQueries));
    }

    public void loadQueries(File definitionQueries){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(false);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(definitionQueries);

            XPath xPath = XPathFactory.newInstance().newXPath();
            NodeList nodeList = (NodeList) xPath.compile(queries).evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nodeList.getLength(); ++i) {
                Element query = (Element) nodeList.item(i);
                String name = query.getAttribute(this.name);
                String body = trim(query.getTextContent());

                this.namedQueries.put(name, body);
            }
        }
        catch (Exception e) {
            logger.error(String.format("Unable to parse %s: %s", definitionQueries.toString(), e.toString()));
        }
    }

    private static String trim(String s) {
        StringBuilder sb = new StringBuilder();
        Arrays.asList(s.split("\n"))
            .stream()
            .map(part -> part.trim())
            .forEach(part -> {
                if (sb.length() > 0)
                    sb.append("\n");
                sb.append(part);
            });
        return sb.toString().trim();
    }

    public String get(String name) {
        return namedQueries.get(name);
    }

}
