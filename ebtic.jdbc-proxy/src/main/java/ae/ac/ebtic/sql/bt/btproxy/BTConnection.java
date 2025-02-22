package ae.ac.ebtic.sql.bt.btproxy;

import jext.sql.PreparedStatement;
import jext.sql.base.BaseConnection;
import org.apache.http.client.HttpClient;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BTConnection extends BaseConnection {

    String httpUrl;
    String token;
    HttpClient client;

    private final Map<String, String> queries = new HashMap<>();

    // ----------------------------------------------------------------------
    // BTConnection
    // ----------------------------------------------------------------------

    BTConnection(HttpClient client, String httpUrl, String token) {
        this.httpUrl = httpUrl;
        this.token = token;
        this.client = client;
    }

    @Override
    public void close() {

    }

    // ----------------------------------------------------------------------
    // NamedQueries
    // ----------------------------------------------------------------------

    @Override
    public void registerNamedQuery(String name, String statement) throws SQLException {
        if (queries.containsKey(name))
            throw new jext.sql.SQLException("Duplicated named query", name);

        this.queries.put(name, statement.trim());
    }

    @Override
    public PreparedStatement prepareStatement(String sqlx) throws SQLException {
        if (this.queries.containsKey(sqlx))
            return new BTPreparedStatement(this, sqlx);
        else
            return super.prepareStatement(sqlx);
    }

    // @Override
    // public PreparedStatement namedStatement(String name) throws SQLException {
    //     if (!this.queries.containsKey(name))
    //         throw jext.sql.SQLException.of("Unknown named query", name);
    //
    //     String sqlx = this.queries.get(name);
    //     return new BTPreparedStatement(this, name);
    // }

    // ----------------------------------------------------------------------
    // NamedQueries
    // ----------------------------------------------------------------------

    private static final List<String> SUPPORTED_STATEMENT_TYPES = Arrays.asList("SELECT", "INSERT", "UPDATE", "DELETE");

    String getStatement(String name) {
        return this.queries.getOrDefault(name, name);
    }

    String getStatementType(String name) throws jext.sql.SQLException {
        // it must handle:
        //      SELECT ...
        //      SELECT\n...
        String statement = this.queries.getOrDefault(name, name);
        int spos = statement.indexOf(' ');
        int npos = statement.indexOf('\n');
        int pos = Math.max(spos, npos);
        if (spos > 0 && spos < pos) pos = spos;
        if (npos > 0 && npos < pos) pos = npos;

        String type = statement.substring(0, pos).toUpperCase();

        if (!SUPPORTED_STATEMENT_TYPES.contains(type))
            throw new jext.sql.SQLException("Unsupported statement type", type, Collections.emptyMap());

        return type;
    }

}
