package jext.sql;

import java.sql.SQLException;

public interface Statement extends java.sql.Statement {

    void setStatementParameter(int index, String param);

    jext.sql.ResultSet executeQuery(String sql) throws SQLException;
}
