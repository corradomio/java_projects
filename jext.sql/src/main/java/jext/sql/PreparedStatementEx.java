package jext.sql;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.Ref;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

public class PreparedStatementEx extends StatementEx implements PreparedStatement {

    static PreparedStatementEx of(ConnectionEx cx, java.sql.PreparedStatement ps, String sqlx, Map<String, int[]> names) throws java.sql.SQLException {
        return new PreparedStatementEx(cx, ps, sqlx, names);
    }

    // ----------------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------------

    protected java.sql.PreparedStatement ps;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    protected PreparedStatementEx(ConnectionEx cx, java.sql.PreparedStatement ps, String sqlx, Map<String, int[]> names) throws java.sql.SQLException {
        super(cx, ps, sqlx);
        this.sql = sqlx;
        this.ps = ps;
        this.names = names;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    // String sql() { return sqlx; }
    // java.sql.PreparedStatement ps { return (java.sql.PreparedStatement) s; }

    // ----------------------------------------------------------------------
    // Execute
    // ----------------------------------------------------------------------

    @Override
    public ResultSet executeQuery() throws java.sql.SQLException {
        try {
            return ResultSetEx.of(this, ps.executeQuery());
        }
        catch (java.sql.SQLException e) {
            throw jext.sql.SQLException.of(e, sql, params);
        }
    }

    @Override
    public int executeUpdate() throws java.sql.SQLException {
        try {
            return ps.executeUpdate();
        } catch (java.sql.SQLException e) {
            throw jext.sql.SQLException.of(e, sql, params);
        }
    }

    @Override
    public boolean execute() throws java.sql.SQLException {
        try {
            return ps.execute();
        } catch (java.sql.SQLException e) {
            throw jext.sql.SQLException.of(e, sql, params);
        }
    }

    // ----------------------------------------------------------------------
    // Setter/Getter
    // ----------------------------------------------------------------------

    @Override
    public void setNull(int parameterIndex, int sqlType) throws java.sql.SQLException {
        params.put(parameterIndex, String.format("null[%d]", sqlType));
        ps.setNull(parameterIndex, sqlType);
    }

    @Override
    public void setBoolean(int parameterIndex, boolean x) throws java.sql.SQLException {
        params.put(parameterIndex, x);
        ps.setBoolean(parameterIndex, x);
    }

    @Override
    public void setByte(int parameterIndex, byte x) throws java.sql.SQLException {
        params.put(parameterIndex, x);
        ps.setByte(parameterIndex, x);
    }

    @Override
    public void setShort(int parameterIndex, short x) throws java.sql.SQLException {
        params.put(parameterIndex, x);
        ps.setShort(parameterIndex, x);
    }

    @Override
    public void setInt(int parameterIndex, int x) throws java.sql.SQLException {
        params.put(parameterIndex, x);
        ps.setInt(parameterIndex, x);
    }

    @Override
    public void setLong(int parameterIndex, long x) throws java.sql.SQLException {
        params.put(parameterIndex, x);
        ps.setLong(parameterIndex, x);
    }

    @Override
    public void setFloat(int parameterIndex, float x) throws java.sql.SQLException {
        params.put(parameterIndex, x);
        ps.setFloat(parameterIndex, x);
    }

    @Override
    public void setDouble(int parameterIndex, double x) throws java.sql.SQLException {
        params.put(parameterIndex, x);
        ps.setDouble(parameterIndex, x);
    }

    @Override
    public void setBigDecimal(int parameterIndex, BigDecimal x) throws java.sql.SQLException {
        params.put(parameterIndex, x);
        ps.setBigDecimal(parameterIndex, x);
    }

    @Override
    public void setString(int parameterIndex, String x) throws java.sql.SQLException {
        params.put(parameterIndex, x);
        ps.setString(parameterIndex, x);
    }

    @Override
    public void setBytes(int parameterIndex, byte[] x) throws java.sql.SQLException {
        params.put(parameterIndex, x);
        ps.setBytes(parameterIndex, x);
    }

    @Override
    public void setDate(int parameterIndex, Date x) throws java.sql.SQLException {
        params.put(parameterIndex, x);
        ps.setDate(parameterIndex, x);
    }

    @Override
    public void setTime(int parameterIndex, Time x) throws java.sql.SQLException {
        params.put(parameterIndex, x);
        ps.setTime(parameterIndex, x);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x) throws java.sql.SQLException {
        params.put(parameterIndex, x);
        ps.setTimestamp(parameterIndex, x);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, int length) throws java.sql.SQLException {
        params.put(parameterIndex, x);
        ps.setAsciiStream(parameterIndex, x, length);
    }

    @Override
    public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws java.sql.SQLException {
        params.put(parameterIndex, x);
        ps.setUnicodeStream(parameterIndex, x, length);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, int length) throws java.sql.SQLException {
        params.put(parameterIndex, x);
        ps.setBinaryStream(parameterIndex, x, length);
    }

    @Override
    public void clearParameters() throws java.sql.SQLException {
        params.clear();
        ps.clearParameters();
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType) throws java.sql.SQLException {
        params.put(parameterIndex, x);
        ps.setObject(parameterIndex, x, targetSqlType);
    }

    @Override
    public void setObject(int parameterIndex, Object x) throws java.sql.SQLException {
        params.put(parameterIndex, x);
        ps.setObject(parameterIndex, x);
    }

    @Override
    public void addBatch() throws java.sql.SQLException {
        ps.addBatch();
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, int length) throws java.sql.SQLException {
        params.put(parameterIndex, reader);
        ps.setCharacterStream(parameterIndex, reader, length);
    }

    @Override
    public void setRef(int parameterIndex, Ref x) throws java.sql.SQLException {
        params.put(parameterIndex, x);
        ps.setRef(parameterIndex, x);
    }

    @Override
    public void setBlob(int parameterIndex, Blob x) throws java.sql.SQLException {
        params.put(parameterIndex, x);
        ps.setBlob(parameterIndex, x);
    }

    @Override
    public void setClob(int parameterIndex, Clob x) throws java.sql.SQLException {
        params.put(parameterIndex, x);
        ps.setClob(parameterIndex, x);
    }

    @Override
    public void setArray(int parameterIndex, Array x) throws java.sql.SQLException {
        params.put(parameterIndex, x);
        ps.setArray(parameterIndex, x);
    }

    @Override
    public ResultSetMetaData getMetaData() throws java.sql.SQLException {
        return ps.getMetaData();
    }

    @Override
    public void setDate(int parameterIndex, Date x, Calendar cal) throws java.sql.SQLException {
        params.put(parameterIndex, x);
        ps.setDate(parameterIndex, x, cal);
    }

    @Override
    public void setTime(int parameterIndex, Time x, Calendar cal) throws java.sql.SQLException {
        params.put(parameterIndex, x);
        ps.setTime(parameterIndex, x, cal);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws java.sql.SQLException {
        params.put(parameterIndex, x);
        ps.setTimestamp(parameterIndex, x, cal);
    }

    @Override
    public void setNull(int parameterIndex, int sqlType, String typeName) throws java.sql.SQLException {
        params.put(parameterIndex, String.format("null[%d]", sqlType));
        ps.setNull(parameterIndex, sqlType, typeName);
    }

    @Override
    public void setURL(int parameterIndex, URL x) throws java.sql.SQLException {
        params.put(parameterIndex, x);
        ps.setURL(parameterIndex, x);
    }

    @Override
    public ParameterMetaData getParameterMetaData() throws java.sql.SQLException {
        return ps.getParameterMetaData();
    }

    @Override
    public void setRowId(int parameterIndex, RowId x) throws java.sql.SQLException {
        params.put(parameterIndex, x);
        ps.setRowId(parameterIndex, x);
    }

    @Override
    public void setNString(int parameterIndex, String value) throws java.sql.SQLException {
        params.put(parameterIndex, value);
        ps.setNString(parameterIndex, value);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value, long length) throws java.sql.SQLException {
        params.put(parameterIndex, value);
        ps.setNCharacterStream(parameterIndex, value, length);
    }

    @Override
    public void setNClob(int parameterIndex, NClob value) throws java.sql.SQLException {
        params.put(parameterIndex, value);
        ps.setNClob(parameterIndex, value);
    }

    @Override
    public void setClob(int parameterIndex, Reader reader, long length) throws java.sql.SQLException {
        params.put(parameterIndex, reader);
        ps.setClob(parameterIndex, reader, length);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream, long length) throws java.sql.SQLException {
        params.put(parameterIndex, inputStream);
        ps.setBlob(parameterIndex, inputStream, length);
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader, long length) throws java.sql.SQLException {
        params.put(parameterIndex, reader);
        ps.setNClob(parameterIndex, reader, length);
    }

    @Override
    public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws java.sql.SQLException {
        params.put(parameterIndex, xmlObject);
        ps.setSQLXML(parameterIndex, xmlObject);
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws java.sql.SQLException {
        params.put(parameterIndex, x);
        ps.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, long length) throws java.sql.SQLException {
        params.put(parameterIndex, x);
        ps.setAsciiStream(parameterIndex, x, length);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, long length) throws java.sql.SQLException {
        params.put(parameterIndex, x);
        ps.setBinaryStream(parameterIndex, x, length);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, long length) throws java.sql.SQLException {
        params.put(parameterIndex, reader);
        ps.setCharacterStream(parameterIndex, reader, length);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x) throws java.sql.SQLException {
        params.put(parameterIndex, x);
        ps.setAsciiStream(parameterIndex, x);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x) throws java.sql.SQLException {
        params.put(parameterIndex, x);
        ps.setBinaryStream(parameterIndex, x);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader) throws java.sql.SQLException {
        params.put(parameterIndex, reader);
        ps.setCharacterStream(parameterIndex, reader);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value) throws java.sql.SQLException {
        params.put(parameterIndex, value);
        ps.setNCharacterStream(parameterIndex, value);
    }

    @Override
    public void setClob(int parameterIndex, Reader reader) throws java.sql.SQLException {
        params.put(parameterIndex, reader);
        ps.setClob(parameterIndex, reader);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream) throws java.sql.SQLException {
        params.put(parameterIndex, inputStream);
        ps.setBlob(parameterIndex, inputStream);
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader) throws java.sql.SQLException {
        params.put(parameterIndex, reader);
        ps.setNClob(parameterIndex, reader);
    }

    // ----------------------------------------------------------------------
    // set by name
    // ----------------------------------------------------------------------

    private int[] getIndices(String parameterName) throws java.sql.SQLException {
        if (!names.containsKey(parameterName))
            throw SQLException.of("Invalid parameter name " + parameterName, sql, params);

        return names.get(parameterName);
    }

    @Override
    public void setNull(String parameterName, int sqlType) throws java.sql.SQLException {
        params.put(parameterName, String.format("null[%d]", sqlType));
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setNull(parameterIndex, sqlType);
        }
    }

    @Override
    public void setBoolean(String parameterName, boolean x) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setBoolean(parameterIndex, x);
        }
    }

    @Override
    public void setByte(String parameterName, byte x) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setByte(parameterIndex, x);
        }
    }

    @Override
    public void setShort(String parameterName, short x) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setShort(parameterIndex, x);
        }
    }

    @Override
    public void setInt(String parameterName, int x) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setInt(parameterIndex, x);
        }
    }

    @Override
    public void setLong(String parameterName, long x) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setLong(parameterIndex, x);
        }
    }

    @Override
    public void setFloat(String parameterName, float x) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setFloat(parameterIndex, x);
        }
    }

    @Override
    public void setDouble(String parameterName, double x) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setDouble(parameterIndex, x);
        }
    }

    @Override
    public void setBigDecimal(String parameterName, BigDecimal x) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setBigDecimal(parameterIndex, x);
        }
    }

    @Override
    public void setString(String parameterName, String x) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setString(parameterIndex, x);
        }
    }

    @Override
    public void setBytes(String parameterName, byte[] x) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setBytes(parameterIndex, x);
        }
    }

    @Override
    public void setDate(String parameterName, java.sql.Date x) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setDate(parameterIndex, x);
        }
    }

    @Override
    public void setTime(String parameterName, java.sql.Time x) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setTime(parameterIndex, x);
        }
    }

    @Override
    public void setTimestamp(String parameterName, java.sql.Timestamp x) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setTimestamp(parameterIndex, x);
        }
    }

    @Override
    public void setAsciiStream(String parameterName, java.io.InputStream x, int length) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setAsciiStream(parameterIndex, x, length);
        }
    }

    @Override
    public void setBinaryStream(String parameterName, java.io.InputStream x, int length) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setBinaryStream(parameterIndex, x, length);
        }
    }

    //----------------------------------------------------------------------
    // Advanced features:

    @Override
    public void setObject(String parameterName, Object x, int targetSqlType) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setObject(parameterIndex, x, targetSqlType);
        }
    }

    @Override
    public void setObject(String parameterName, Object x) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setObject(parameterIndex, x);
        }
    }

    @Override
    public void setCharacterStream(String parameterName, java.io.Reader x, int length) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setCharacterStream(parameterIndex, x, length);
        }
    }

    @Override
    public void setBlob(String parameterName, Blob x) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setBlob(parameterIndex, x);
        }
    }

    @Override
    public void setClob(String parameterName, Clob x) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setClob(parameterIndex, x);
        }
    }

    @Override
    public void setDate(String parameterName, java.sql.Date x, Calendar cal) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setDate(parameterIndex, x, cal);
        }
    }

    @Override
    public void setTime(String parameterName, java.sql.Time x, Calendar cal) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setTime(parameterIndex, x, cal);
        }
    }

    @Override
    public void setTimestamp(String parameterName, java.sql.Timestamp x, Calendar cal) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setTimestamp(parameterIndex, x, cal);
        }
    }

    @Override
    public void setNull(String parameterName, int sqlType, String typeName) throws java.sql.SQLException {
        params.put(parameterName, String.format("null[%d,%s]", sqlType, typeName));
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setNull(parameterIndex, sqlType, typeName);
        }
    }

    @Override
    public void setURL(String parameterName, java.net.URL x) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setURL(parameterIndex, x);
        }
    }

    //------------------------- JDBC 4.0 -----------------------------------

    @Override
    public void setRowId(String parameterName, RowId x) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setRowId(parameterIndex, x);
        }
    }

    @Override
    public void setNString(String parameterName, String x) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setNString(parameterIndex, x);
        }
    }

    @Override
    public void setNCharacterStream(String parameterName, Reader x, long length) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setNCharacterStream(parameterIndex, x, length);
        }
    }

    @Override
    public void setNClob(String parameterName, NClob x) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setNClob(parameterIndex, x);
        }
    }

    @Override
    public void setClob(String parameterName, Reader x, long length) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setClob(parameterIndex, x, length);
        }
    }

    @Override
    public void setBlob(String parameterName, InputStream x, long length) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setBlob(parameterIndex, x, length);
        }
    }

    @Override
    public void setNClob(String parameterName, Reader x, long length) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setNClob(parameterIndex, x, length);
        }
    }

    @Override
    public void setSQLXML(String parameterName, SQLXML x) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setSQLXML(parameterIndex, x);
        }
    }

    @Override
    public void setObject(String parameterName, Object x, int targetSqlType, int scaleOrLength) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
        }
    }

    @Override
    public void setAsciiStream(String parameterName, java.io.InputStream x, long length) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setAsciiStream(parameterIndex, x, length);
        }
    }

    @Override
    public void setBinaryStream(String parameterName, java.io.InputStream x, long length) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setBinaryStream(parameterIndex, x, length);
        }
    }

    @Override
    public void setCharacterStream(String parameterName, java.io.Reader x, long length) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setCharacterStream(parameterIndex, x, length);
        }
    }

    @Override
    public void setAsciiStream(String parameterName, java.io.InputStream x) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setAsciiStream(parameterIndex, x);
        }
    }

    @Override
    public void setBinaryStream(String parameterName, java.io.InputStream x) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setBinaryStream(parameterIndex, x);
        }
    }

    @Override
    public void setCharacterStream(String parameterName, java.io.Reader x) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setCharacterStream(parameterIndex, x);
        }
    }

    @Override
    public void setNCharacterStream(String parameterName, Reader x) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setNCharacterStream(parameterIndex, x);
        }
    }

    @Override
    public void setClob(String parameterName, Reader x) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setClob(parameterIndex, x);
        }
    }

    @Override
    public void setBlob(String parameterName, InputStream x) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setBlob(parameterIndex, x);
        }
    }

    @Override
    public void setNClob(String parameterName, Reader x) throws java.sql.SQLException {
        params.put(parameterName, x);
        int[] indices = getIndices(parameterName);
        for(int parameterIndex : indices) {
            ps.setNClob(parameterIndex, x);
        }
    }

    // ----------------------------------------------------------------------
    // end
    // ----------------------------------------------------------------------
}
