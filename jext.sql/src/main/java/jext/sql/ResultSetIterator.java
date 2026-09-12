package jext.sql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ResultSetIterator implements Iterator {

    public static ResultSetIterator of(ResultSet rs, String dummy) {
        return new ResultSetIterator(rs);
    }

    private final ResultSet rs;

    private ResultSetIterator(ResultSet rs) {
        this.rs = rs;
    }

    @Override
    public boolean hasNext() {
        try {
            return this.rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, Object> next() {
        Map<String, Object> rec = new HashMap<>();
        try {
            ResultSetMetaData rsmd = this.rs.getMetaData();
            int ncols = rsmd.getColumnCount();
            for (int c=1; c<=ncols; ++c) {
                String name = rsmd.getColumnName(c);
                Object value = rs.getObject(c);
                rec.put(name, value);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rec;
    }
}
