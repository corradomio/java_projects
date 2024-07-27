package jext.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

public class ResultSetIterator implements Iterator<Map<String, Object>> {

    public static ResultSetIterator of(ResultSet rset, String... columns) {
        return new ResultSetIterator(rset, columns);
    }

    private final ResultSet rset;
    private final String[] columns;
    private boolean hasNext;
    private Map<String, Object> record;

    public ResultSetIterator(ResultSet rset, String[] columns) {
        this.rset = rset;
        this.columns = columns;

        try {
            this.hasNext = rset.next();
        } catch (SQLException e) {
            this.hasNext = false;
        }
    }

    @Override
    public boolean hasNext() {
        return this.hasNext;
    }

    @Override
    public Map<String, Object> next() {
        for (String column : this.columns) {
            try {
                this.record.put(column, this.rset.getObject(column));
            } catch (SQLException e) {
                this.record.put(column, null);
            }
        }
        try {
            this.hasNext = rset.next();
        } catch (SQLException e) {
            this.hasNext = false;
        }

        return this.record;
    }
}
