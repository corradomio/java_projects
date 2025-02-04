package jext.sql;

import java.util.Collections;
import java.util.Map;

public class SQLException extends java.sql.SQLException {

    public static SQLException of(String reason) {
        return new SQLException(reason, "", Collections.emptyMap());
    }

    public static SQLException of(String reason, String sql) {
        return new SQLException(reason, sql, Collections.emptyMap());
    }

    public static SQLException of(String reason, String sql, Map<Object, Object> params) {
        return new SQLException(reason, sql, params);
    }

    public static SQLException of(java.sql.SQLException e, String sql) {
        return new SQLException(e, sql, Collections.emptyMap());
    }

    public static SQLException of(java.sql.SQLException e, String sql, Map<Object, Object> params) {
        return new SQLException(e, sql, params);
    }

    private String sql = "";
    private Map<Object, Object> params = Collections.emptyMap();

    // ----------------------------------------------------------------------

    public SQLException(String reason) {
        super(reason);
    }

    public SQLException(String reason, String sql, Map<Object, Object> params) {
        super(reason);
        this.sql = sql;
        this.params = params;
    }

    public SQLException(java.sql.SQLException e, String sql, Map<Object, Object> params) {
        super(e);
        this.sql = sql;
        this.params = params;
    }

    @Override
    public String getMessage() {
        String message = super.getMessage();
        return String.format("%s\n     sql: %s\n  params: %s", message, sql, params.toString());
    }

}
