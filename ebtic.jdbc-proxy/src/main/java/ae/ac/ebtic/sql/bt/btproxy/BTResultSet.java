package ae.ac.ebtic.sql.bt.btproxy;

/*
{
    "query_id": "test",
    "results": {
        "rowCount": 19,
        "rows": [{
                "scenario_name": "2024-11-11 14:54"
            },
            ...
        ]
    }
} */

import jext.sql.base.BaseResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BTResultSet extends BaseResultSet {
    private final Map<String, Object> resultSet;
    private final int rowCount;
    private List<Map<String, Object>> rows;
    private int currIndex;
    private Map<String, Object> currRow;
    private List<String> columns;

    BTResultSet(Map<String, Object> resultSet) {
        this.resultSet = resultSet;
        this.rowCount = MapUtils.getInt(resultSet, "results", "rowCount");
        this.rows = MapUtils.get(resultSet, "results", "rows");
        this.currIndex = 0;
        this.currRow = null;
    }

    // ----------------------------------------------------------------------

    @Override
    public boolean next() {
        if (currIndex >= rowCount) {
            currRow = null;
            columns = null;
            return false;
        }

        currRow = rows.get(currIndex);
        columns = currRow.keySet().stream().toList();
        currIndex++;

        return this.currIndex <= this.rowCount;
    }

    @Override
    public void close() {

    }

    // ----------------------------------------------------------------------

    private void checkColumn(int columnIndex) throws java.sql.SQLException {
        if (columnIndex > columns.size() || columnIndex < 1)
            throw jext.sql.SQLException.of(String.format("The column index is out of range: 33, number of columns: 1.", currRow.size(), columnIndex));
    }

    private void checkColumn(String columName) throws java.sql.SQLException {
        if (!currRow.containsKey(columName))
            throw jext.sql.SQLException.of(String.format("The column name %s name was not found in this ResultSet.", columName));
    }

    // ----------------------------------------------------------------------
    // -- String

    @Override
    public String getString(int columIndex) throws SQLException {
        checkColumn(columIndex);
        return getString(columns.get(columIndex-1));
    }

    @Override
    public String getString(String columnName) throws SQLException {
        checkColumn(columnName);
        Object value = currRow.get(columnName);
        return value == null ? null : value.toString();
    }

    // -- bool

    @Override
    public boolean getBoolean(int columIndex) throws SQLException {
        checkColumn(columIndex);
        return getBoolean(columns.get(columIndex-1));
    }

    @Override
    public boolean getBoolean(String columnName) throws SQLException {
        checkColumn(columnName);
        Object value = currRow.get(columnName);
        if (value == null)
            return false;
        if (value instanceof Boolean)
            return ((Boolean)value).booleanValue();
        if (value instanceof Number)
            return ((Number)value).doubleValue() != 0;
        if (value instanceof String)
            return "true".equals(value.toString());
        throw jext.sql.SQLException.of(String.format("Unsupported boolean value %s", value));
    }

    // -- int

    @Override
    public int getInt(int columIndex) throws SQLException {
        checkColumn(columIndex);
        return getInt(columns.get(columIndex-1));
    }

    @Override
    public int getInt(String columnName) throws SQLException {
        checkColumn(columnName);
        Object value = currRow.get(columnName);
        if (value == null)
            return 0;
        if (value instanceof Number)
            return ((Number)value).intValue();
        if (value instanceof String)
            return Integer.valueOf((String) value);
        throw jext.sql.SQLException.of(String.format("Unsupported integer value %s", value));
    }

    // -- long

    @Override
    public long getLong(int columIndex) throws SQLException {
        checkColumn(columIndex);
        return getLong(columns.get(columIndex-1));
    }

    @Override
    public long getLong(String columnName) throws SQLException {
        checkColumn(columnName);
        Object value = currRow.get(columnName);
        if (value == null)
            return 0;
        if (value instanceof Number)
            return ((Number)value).longValue();
        if (value instanceof String)
            return Long.valueOf((String) value);
        throw jext.sql.SQLException.of(String.format("Unsupported integer value %s", value));
    }

    // -- float

    @Override
    public float getFloat(int columIndex) throws SQLException {
        checkColumn(columIndex);
        return getFloat(columns.get(columIndex-1));
    }

    @Override
    public float getFloat(String columnName) throws SQLException {
        checkColumn(columnName);
        Object value = currRow.get(columnName);
        if (value == null)
            return 0;
        if (value instanceof Number)
            return ((Number)value).floatValue();
        if (value instanceof String)
            return Float.valueOf((String) value);
        throw jext.sql.SQLException.of(String.format("Unsupported float value %s", value));
    }

    // -- double

    @Override
    public double getDouble(int columIndex) throws SQLException {
        checkColumn(columIndex);
        return getDouble(columns.get(columIndex-1));
    }

    @Override
    public double getDouble(String columnName) throws SQLException {
        checkColumn(columnName);
        Object value = currRow.get(columnName);
        if (value == null)
            return 0;
        if (value instanceof Number)
            return ((Number)value).doubleValue();
        if (value instanceof String)
            return Double.valueOf((String) value);
        throw jext.sql.SQLException.of(String.format("Unsupported double value %s", value));
    }

}
