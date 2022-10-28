package jext.graph.named;

import jext.util.StringUtils;
import org.apache.commons.digester3.Digester;
import org.neo4j.driver.exceptions.DatabaseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NamedQueries {

    public static NamedQueries load(File nqueriesFile) throws ParserConfigurationException, IOException, SAXException {
        Digester d = new Digester();

        NamedQueries nqueries = new NamedQueries();

        addRules(d, nqueries);

        d.push(nqueries);

        d.parse(nqueriesFile);

        return nqueries;
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    public static final String NO_VERSION = "";

    private Map<String/*version*/, Map<String, NamedQuery>> namedQueries = new HashMap<>();
    private List<NamedQuery> queries = new ArrayList<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public NamedQueries() {

    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public String getQuery(String qname, String dbversion) {
        Map<String, NamedQuery> nqueries;

        // check if is defined in specific version
        for(String version : namedQueries.keySet()) {
            nqueries = namedQueries.get(version);

            // skip version ""
            if (version.isEmpty()) continue;
            // skip wrong version
            if (!dbversion.startsWith(version)) continue;
            // skip if not defined
            if (!nqueries.containsKey(qname)) continue;

            return StringUtils.trim(nqueries.get(qname).getBody());
        }

        // check if it is defined in version ""
        nqueries = namedQueries.get(NO_VERSION);
        if (nqueries.containsKey(qname))
            return StringUtils.trim(nqueries.get(qname).getBody());

        throw new DatabaseException("USER", String.format("Named query '%s' not defined", qname));
    }

    public void registerQuery(String qname, String body) {
        NamedQuery nquery = new NamedQuery();
        nquery.setName(qname);
        nquery.setBody(body);
        addNamedQuery(nquery);
    }

    public void registerQueries(Map<String/*name*/, String/*body*/> namedQueries) {
        for (String qname : namedQueries.keySet())
            registerQuery(qname, namedQueries.get(qname));
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private static void addRules(Digester d, NamedQueries nqueries) {
        d.addObjectCreate("graphdb/namedqueries/query", NamedQuery.class);
        d.addSetProperties("graphdb/namedqueries/query");
        d.addCallMethod("graphdb/namedqueries/query", "setBody", 1);
        d.addCallParam("graphdb/namedqueries/query", 0);
        d.addSetNext("graphdb/namedqueries/query", "addNamedQuery");

    }

    public void addNamedQuery(NamedQuery nquery) {
        String version = nquery.getVersion();
        if (!namedQueries.containsKey(version))
            namedQueries.put(version, new HashMap<>());
        namedQueries.get(version).put(nquery.getName(), nquery);
        queries.add(nquery);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
