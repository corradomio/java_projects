package jext.sql.queries;

import jext.sql.SQLException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class JSONQueries implements NamedQueries {

    public static NamedQueries of(Map<String, Object> data) throws IOException {

        // Map<String, Object> data = JSONUtils.load(jsonFile, LinkedHashMap.class);

        String namespace = get(data, "api_query_endpoint", "namespace");
        String version   = get(data, "api_query_endpoint", "version");
        String language  = get(data, "api_query_endpoint", "language");

        JSONQueries jq = new JSONQueries(namespace, version, language);

        List<Map> queries = get(data, "api_query_endpoint", "query");

        for(Map<String, Object> query : queries) {
            String queryId = get(query, "query_id");
            String statement = getStatement(query);
            List<NamedParameter> structuredParameters = getParameters(query, "structure_parameters");
            List<NamedParameter> queryParameters = getParameters(query, "query_parameters");

            NamedQuery nquery = new NamedQuery(
                namespace,
                queryId,
                statement,
                structuredParameters,
                queryParameters
            );

            jq.addQuery(nquery);
        }

        return jq;
    }

    // ----------------------------------------------------------------------
    // Utilities
    // ----------------------------------------------------------------------

    /**
     * Parse the statement
     * @param query query dictionary
     * @return statement as string
     */
    private static String getStatement(Map<String, Object> query) {
        Object object = get(query, "statement");
        if (object instanceof String)
            return (String) object;

        List<String> lines = (List<String>) object;
        StringBuilder sb = new StringBuilder();
        for(String line : lines)
            sb.append(line).append("\n");
        return sb.toString();
    }

    /**
     * Parse the parameters.
     * 'structuredParameter' and 'queryParameter' can be a list of a map
     *
     * @param query query dictionary
     * @param whichParameters which parameters to analyze
     * @return list o parameters
     */
    private static List<NamedParameter> getParameters(Map<String, Object> query, String whichParameters) {
        List<NamedParameter> parameters = new ArrayList<>();

        List<Map<String, Object>> sparams = (List<Map<String, Object>>) query.getOrDefault(whichParameters, Collections.emptyList());

        int index = 1;
        for(Map<String, Object> sparam : sparams) {
            int pindex = ((Integer)sparam.getOrDefault("index", index)).intValue();
            String name = (String) sparam.get("name");
            String type = (String) sparam.getOrDefault("type", "string");
            boolean nullable = ((Boolean) sparam.getOrDefault("nullable", true)).booleanValue();
            boolean scalar = ((Boolean) sparam.getOrDefault("scalar", true)).booleanValue();

            parameters.add(new NamedParameter(
                name,
                pindex,
                type,
                nullable,
                scalar
            ));


            ++pindex;
        }

        return parameters;
    }

    // ----------------------------------------------------------------------
    // Utilities
    // ----------------------------------------------------------------------

    private static <T> T get(Map<String, Object> data, String... keys) {
        Map<String, Object> current;
        Object value = data;
        for (String key : keys) {
            current = (Map<String, Object>) value;
            value = current.getOrDefault(key, null);
            if (value == null) break;
        }
        return (T) value;
    }

    // ----------------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------------

    private final String namespace;
    private final String version;
    private final String language;

    private Map<String, NamedQuery> queries = new HashMap<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private JSONQueries(String namespace, String version, String language) {
        this.namespace = namespace;
        this.version = version;
        this.language = language;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    private void addQuery(NamedQuery query) {
        queries.put(query.name, query);
    }

    public NamedQuery getQuery(String namespace, String queryId) throws SQLException {
        String name = NamedQuery.compose(namespace, queryId);
        return getQuery(name);
    }

    public NamedQuery getQuery(String name) throws SQLException {
        String qname = name;
        if (!qname.startsWith(namespace))
            qname = String.format("%s.%s", namespace, name);

        NamedQuery query = queries.getOrDefault(qname, null);
        if (query == null)
            throw SQLException.of("Unknown named query", name, Collections.emptyMap());
        return query;
    }

    public Iterator<NamedQuery> iterator() {
        return queries.values().iterator();
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
