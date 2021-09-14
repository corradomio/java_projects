package jext.sql;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

public class SQLExceptionWrapper extends SQLException {

    private String sql;
    private Map<Object, Object> params;

    public SQLExceptionWrapper(SQLException e) {
        this(e, "", Collections.emptyMap());
    }

    public SQLExceptionWrapper(SQLException e, String sql) {
        this(e, sql, Collections.emptyMap());
    }

    public SQLExceptionWrapper(SQLException e, String sql, Map<Object, Object> params) {
        super(e);
        this.sql = sql;
        this.params = params;
    }

}
