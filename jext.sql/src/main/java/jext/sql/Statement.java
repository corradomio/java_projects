package jext.sql;

import java.sql.SQLException;

public interface Statement extends java.sql.Statement {

    ResultSet executeQuery(String sql) throws SQLException;
}
