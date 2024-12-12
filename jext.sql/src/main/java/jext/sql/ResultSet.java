package jext.sql;

import java.sql.SQLException;

public interface ResultSet extends java.sql.ResultSet {
    boolean hasNext() throws SQLException;
}
