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

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class BTResultSet extends BaseResultSet {
    private final Map<String, Object> response;
    private final int rowCount;

    private List<Map<String, Object>> rows;
    private int currIndex;
    private Map<String, Object> currRow;
    private List<String> columns;

    BTResultSet(Map<String, Object> response) {
        this.response = response;
        this.rowCount = MapUtils.getInt(response, "results", "rowCount");
        this.rows = MapUtils.get(response, "results", "rows");
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

    public Map<String, Object> response() {
        return response;
    }

    public void serialize(Writer writer) throws IOException {
        JSONUtils.serialize(writer, response);
    }

    public void serialize(OutputStream ostream) throws IOException {
        JSONUtils.serialize(new OutputStreamWriter(ostream), response);
    }

    // ----------------------------------------------------------------------

    private void checkColumn(int columnIndex) throws SQLException {
        if (columns == null)
            throw new jext.sql.SQLException("ResultSet not positioned properly, perhaps you need to call next.");
        if (columnIndex > columns.size() || columnIndex < 1)
            throw new jext.sql.SQLException(String.format("The column index is out of range: %d, number of columns: %d.",
                currRow.size(), columnIndex));
    }

    private void checkColumn(String columName) throws SQLException {
        if (currRow == null)
            throw new jext.sql.SQLException("ResultSet not positioned properly, perhaps you need to call next.");
        if (!currRow.containsKey(columName))
            throw new jext.sql.SQLException(String.format("The column name %s name was not found in this ResultSet.",
                columName));
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
            return (Boolean) value;
        if (value instanceof Number)
            return ((Number)value).doubleValue() != 0;
        if (value instanceof String)
            return "true".equals(value.toString());
        throw new jext.sql.SQLException(String.format("Unsupported boolean value %s", value));
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
            return Integer.parseInt((String) value);
        throw new jext.sql.SQLException(String.format("Unsupported integer value %s", value));
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
            return Long.parseLong((String) value);
        throw new jext.sql.SQLException(String.format("Unsupported integer value %s", value));
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
            return Float.parseFloat((String) value);
        throw new jext.sql.SQLException(String.format("Unsupported float value %s", value));
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
            return Double.parseDouble((String) value);
        throw new jext.sql.SQLException(String.format("Unsupported double value %s", value));
    }

    // -- Date

    @Override
    public Date getDate(int columIndex) throws SQLException {
        checkColumn(columIndex);
        return getDate(columns.get(columIndex-1));
    }

    @Override
    public Date getDate(String columnName) throws SQLException {
        checkColumn(columnName);
        Object value = currRow.get(columnName);
        if (value == null)
            return null;
        else if (value instanceof String)
            return new Date(ISO8601DateFormat.parse((String)value).getTime());
        else
            throw new UnsupportedOperationException("Unsupported date type " + value.getClass());
    }

    // -- Time

    @Override
    public Time getTime(int columIndex) throws SQLException {
        checkColumn(columIndex);
        return getTime(columns.get(columIndex-1));
    }

    @Override
    public Time getTime(String columnName) throws SQLException {
        checkColumn(columnName);
        Object value = currRow.get(columnName);
        if (value == null)
            return null;
        else if (value instanceof String)
            return new Time(ISO8601DateFormat.parse((String)value).getTime());
        else
            throw new UnsupportedOperationException("Unsupported date type " + value.getClass());
    }

    // -- Timestamp

    @Override
    public Timestamp getTimestamp(int columIndex) throws SQLException {
        checkColumn(columIndex);
        return getTimestamp(columns.get(columIndex-1));
    }

    @Override
    public Timestamp getTimestamp(String columnName) throws SQLException {
        checkColumn(columnName);
        Object value = currRow.get(columnName);
        if (value == null)
            return null;
        else if (value instanceof String)
            return new Timestamp(ISO8601DateFormat.parse((String)value).getTime());
        else
            throw new UnsupportedOperationException("Unsupported date type " + value.getClass());
    }


}
