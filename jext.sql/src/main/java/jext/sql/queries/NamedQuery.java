package jext.sql.queries;

import java.util.List;

public class NamedQuery {

    public final String name;
    public final String statement;
    public final List<NamedParameter> structuredParameters;
    public final List<NamedParameter> queryParameters;

    public NamedQuery(String namespace, String queryId, String statement, List<NamedParameter> structuredParameters, List<NamedParameter> queryParameters) {
        this.name = compose(namespace, queryId);
        this.statement = statement;
        this.structuredParameters = structuredParameters;
        this.queryParameters = queryParameters;
    }

    public String getStatement() {
        return this.statement;
    }

    static String compose(String namespace, String queryId) {
        if (namespace == null || namespace.isEmpty())
            return queryId;
        else
            return String.format("%s.%s", namespace, queryId);
    }
}
