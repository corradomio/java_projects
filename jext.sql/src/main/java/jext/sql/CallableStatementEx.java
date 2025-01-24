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
import java.sql.Ref;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

public class CallableStatementEx extends PreparedStatementEx implements CallableStatement {

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public CallableStatementEx(ConnectionEx c, String sqlx) {
        super(c, sqlx);
    }

    public CallableStatementEx(ConnectionEx c, String sqlx, int resultSetType, int resultSetConcurrency) {
        super(c, sqlx, resultSetType, resultSetConcurrency);
    }

    public CallableStatementEx(ConnectionEx c, String sqlx, int resultSetType, int resultSetConcurrency, int resultSetHoldability) {
        super(c, sqlx, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------
    
    private java.sql.CallableStatement cs() throws SQLException {
        if (this.stmt == null) {
            // replace '$s' and '$<index>$s' parameters
            if (this.sparams[0] != null)
                this.sql = String.format(this.sql, this.sparams);

            switch (this.mode) {
                case NONE:
                    this.stmt = this.conn().prepareCall(this.sql);
                    break;
                case RESULT_TYPE_CONCURRENCY:
                    this.stmt = this.conn().prepareCall(this.sql, this.resultSetType, this.resultSetConcurrency);
                    break;
                case RESULT_TYPE_CONCURRENCY_HOLDABILITY:
                    this.stmt = this.conn().prepareCall(this.sql, this.resultSetType, this.resultSetConcurrency, this.resultSetHoldability);
                    break;
                default:
                    this.stmt = this.conn().prepareCall(this.sql);
            }
        }
        return (java.sql.CallableStatement) this.stmt;
    }

    // ----------------------------------------------------------------------
    // Register In/Out Parameters
    // ----------------------------------------------------------------------

    @Override
    public void registerOutParameter(int parameterIndex, int sqlType) throws SQLException {
        this.cs().registerOutParameter(parameterIndex, sqlType);
    }

    @Override
    public void registerOutParameter(int parameterIndex, int sqlType, int scale) throws SQLException {
        this.cs().registerOutParameter(parameterIndex, sqlType, scale);
    }

    @Override
    public void registerOutParameter(int parameterIndex, int sqlType, String typeName) throws SQLException {
        this.cs().registerOutParameter(parameterIndex, sqlType, typeName);
    }

    @Override
    public void registerOutParameter(String parameterName, int sqlType) throws SQLException {
        this.cs().registerOutParameter(parameterName, sqlType);
    }

    @Override
    public void registerOutParameter(String parameterName, int sqlType, int scale) throws SQLException {
        this.cs().registerOutParameter(parameterName, sqlType, scale);
    }

    @Override
    public void registerOutParameter(String parameterName, int sqlType, String typeName) throws SQLException {
        this.cs().registerOutParameter(parameterName, sqlType, typeName);
    }

    @Override
    public boolean wasNull() throws SQLException {
        return this.cs().wasNull();
    }

    // ----------------------------------------------------------------------
    // get... index
    // ----------------------------------------------------------------------

    @Override
    public String getString(int parameterIndex) throws SQLException {
        return this.cs().getString(parameterIndex);
    }

    @Override
    public boolean getBoolean(int parameterIndex) throws SQLException {
        return this.cs().getBoolean(parameterIndex);
    }

    @Override
    public byte getByte(int parameterIndex) throws SQLException {
        return this.cs().getByte(parameterIndex);
    }

    @Override
    public short getShort(int parameterIndex) throws SQLException {
        return this.cs().getShort(parameterIndex);
    }

    @Override
    public int getInt(int parameterIndex) throws SQLException {
        return this.cs().getInt(parameterIndex);
    }

    @Override
    public long getLong(int parameterIndex) throws SQLException {
        return this.cs().getLong(parameterIndex);
    }

    @Override
    public float getFloat(int parameterIndex) throws SQLException {
        return this.cs().getFloat(parameterIndex);
    }

    @Override
    public double getDouble(int parameterIndex) throws SQLException {
        return this.cs().getDouble(parameterIndex);
    }

    @Override
    public BigDecimal getBigDecimal(int parameterIndex, int scale) throws SQLException {
        return this.cs().getBigDecimal(parameterIndex, scale);
    }

    @Override
    public byte[] getBytes(int parameterIndex) throws SQLException {
        return this.cs().getBytes(parameterIndex);
    }

    @Override
    public Date getDate(int parameterIndex) throws SQLException {
        return this.cs().getDate(parameterIndex);
    }

    @Override
    public Time getTime(int parameterIndex) throws SQLException {
        return this.cs().getTime(parameterIndex);
    }

    @Override
    public Timestamp getTimestamp(int parameterIndex) throws SQLException {
        return this.cs().getTimestamp(parameterIndex);
    }

    @Override
    public Object getObject(int parameterIndex) throws SQLException {
        return this.cs().getObject(parameterIndex);
    }

    @Override
    public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
        return this.cs().getBigDecimal(parameterIndex);
    }

    @Override
    public Object getObject(int parameterIndex, Map<String, Class<?>> map) throws SQLException {
        return this.cs().getObject(parameterIndex, map);
    }

    @Override
    public Ref getRef(int parameterIndex) throws SQLException {
        return this.cs().getRef(parameterIndex);
    }

    @Override
    public Blob getBlob(int parameterIndex) throws SQLException {
        return this.cs().getBlob(parameterIndex);
    }

    @Override
    public Clob getClob(int parameterIndex) throws SQLException {
        return this.cs().getClob(parameterIndex);
    }

    @Override
    public Array getArray(int parameterIndex) throws SQLException {
        return this.cs().getArray(parameterIndex);
    }

    @Override
    public Date getDate(int parameterIndex, Calendar cal) throws SQLException {
        return this.cs().getDate(parameterIndex, cal);
    }

    @Override
    public Time getTime(int parameterIndex, Calendar cal) throws SQLException {
        return this.cs().getTime(parameterIndex, cal);
    }

    @Override
    public Timestamp getTimestamp(int parameterIndex, Calendar cal) throws SQLException {
        return this.cs().getTimestamp(parameterIndex, cal);
    }

    @Override
    public URL getURL(int parameterIndex) throws SQLException {
        return this.cs().getURL(parameterIndex);
    }

    @Override
    public RowId getRowId(int parameterIndex) throws SQLException {
        return this.cs().getRowId(parameterIndex);
    }

    @Override
    public NClob getNClob(int parameterIndex) throws SQLException {
        return this.cs().getNClob(parameterIndex);
    }

    @Override
    public SQLXML getSQLXML(int parameterIndex) throws SQLException {
        return this.cs().getSQLXML(parameterIndex);
    }

    @Override
    public String getNString(int parameterIndex) throws SQLException {
        return this.cs().getNString(parameterIndex);
    }

    @Override
    public Reader getNCharacterStream(int parameterIndex) throws SQLException {
        return this.cs().getNCharacterStream(parameterIndex);
    }

    @Override
    public Reader getCharacterStream(int parameterIndex) throws SQLException {
        return this.cs().getCharacterStream(parameterIndex);
    }

    // ----------------------------------------------------------------------
    // set... name
    // ----------------------------------------------------------------------

    @Override
    public void setURL(String parameterName, URL val) throws SQLException {
        this.cs().setURL(parameterName, val);
    }

    @Override
    public void setNull(String parameterName, int sqlType) throws SQLException {
        this.cs().setNull(parameterName, sqlType);
    }

    @Override
    public void setBoolean(String parameterName, boolean x) throws SQLException {
        this.cs().setBoolean(parameterName, x);
    }

    @Override
    public void setByte(String parameterName, byte x) throws SQLException {
        this.cs().setByte(parameterName, x);
    }

    @Override
    public void setShort(String parameterName, short x) throws SQLException {
        this.cs().setShort(parameterName, x);
    }

    @Override
    public void setInt(String parameterName, int x) throws SQLException {
        this.cs().setInt(parameterName, x);
    }

    @Override
    public void setLong(String parameterName, long x) throws SQLException {
        this.cs().setLong(parameterName, x);
    }

    @Override
    public void setFloat(String parameterName, float x) throws SQLException {
        this.cs().setFloat(parameterName, x);
    }

    @Override
    public void setDouble(String parameterName, double x) throws SQLException {
        this.cs().setDouble(parameterName, x);
    }

    @Override
    public void setBigDecimal(String parameterName, BigDecimal x) throws SQLException {
        this.cs().setBigDecimal(parameterName, x);
    }

    @Override
    public void setString(String parameterName, String x) throws SQLException {
        this.cs().setString(parameterName, x);
    }

    @Override
    public void setBytes(String parameterName, byte[] x) throws SQLException {
        this.cs().setBytes(parameterName, x);
    }

    @Override
    public void setDate(String parameterName, Date x) throws SQLException {
        this.cs().setDate(parameterName, x);
    }

    @Override
    public void setTime(String parameterName, Time x) throws SQLException {
        this.cs().setTime(parameterName, x);
    }

    @Override
    public void setTimestamp(String parameterName, Timestamp x) throws SQLException {
        this.cs().setTimestamp(parameterName, x);
    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x, int length) throws SQLException {
        this.cs().setAsciiStream(parameterName, x, length);
    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x, int length) throws SQLException {
        this.cs().setBinaryStream(parameterName, x, length);
    }

    @Override
    public void setObject(String parameterName, Object x, int targetSqlType, int scale) throws SQLException {
        this.cs().setObject(parameterName, x, targetSqlType, scale);
    }

    @Override
    public void setObject(String parameterName, Object x, int targetSqlType) throws SQLException {
        this.cs().setObject(parameterName, x, targetSqlType);
    }

    @Override
    public void setObject(String parameterName, Object x) throws SQLException {
        this.cs().setObject(parameterName, x);
    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader, int length) throws SQLException {
        this.cs().setCharacterStream(parameterName, reader, length);
    }

    @Override
    public void setDate(String parameterName, Date x, Calendar cal) throws SQLException {
        this.cs().setDate(parameterName, x, cal);
    }

    @Override
    public void setTime(String parameterName, Time x, Calendar cal) throws SQLException {
        this.cs().setTime(parameterName, x, cal);
    }

    @Override
    public void setTimestamp(String parameterName, Timestamp x, Calendar cal) throws SQLException {
        this.cs().setTimestamp(parameterName, x, cal);
    }

    @Override
    public void setNull(String parameterName, int sqlType, String typeName) throws SQLException {
        this.cs().setNull(parameterName, sqlType, typeName);
    }

    @Override
    public void setRowId(String parameterName, RowId x) throws SQLException {
        this.cs().setRowId(parameterName, x);
    }

    @Override
    public void setNString(String parameterName, String value) throws SQLException {
        this.cs().setNString(parameterName, value);
    }

    @Override
    public void setNCharacterStream(String parameterName, Reader value, long length) throws SQLException {
        this.cs().setNCharacterStream(parameterName, value, length);
    }

    @Override
    public void setNClob(String parameterName, NClob value) throws SQLException {
        this.cs().setNClob(parameterName, value);
    }

    @Override
    public void setClob(String parameterName, Reader reader, long length) throws SQLException {
        this.cs().setClob(parameterName, reader, length);
    }

    @Override
    public void setBlob(String parameterName, InputStream inputStream, long length) throws SQLException {
        this.cs().setBlob(parameterName, inputStream, length);
    }

    @Override
    public void setNClob(String parameterName, Reader reader, long length) throws SQLException {
        this.cs().setNClob(parameterName, reader, length);
    }

    @Override
    public void setSQLXML(String parameterName, SQLXML xmlObject) throws SQLException {
        this.cs().setSQLXML(parameterName, xmlObject);
    }

    @Override
    public void setBlob(String parameterName, Blob x) throws SQLException {
        this.cs().setBlob(parameterName, x);
    }

    @Override
    public void setClob(String parameterName, Clob x) throws SQLException {
        this.cs().setClob(parameterName, x);
    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x, long length) throws SQLException {
        this.cs().setAsciiStream(parameterName, x, length);
    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x, long length) throws SQLException {
        this.cs().setBinaryStream(parameterName, x, length);
    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader, long length) throws SQLException {
        this.cs().setCharacterStream(parameterName, reader, length);
    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x) throws SQLException {
        this.cs().setAsciiStream(parameterName, x);
    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x) throws SQLException {
        this.cs().setBinaryStream(parameterName, x);
    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader) throws SQLException {
        this.cs().setCharacterStream(parameterName, reader);
    }

    @Override
    public void setNCharacterStream(String parameterName, Reader value) throws SQLException {
        this.cs().setNCharacterStream(parameterName, value);
    }

    @Override
    public void setClob(String parameterName, Reader reader) throws SQLException {
        this.cs().setClob(parameterName, reader);
    }

    @Override
    public void setBlob(String parameterName, InputStream inputStream) throws SQLException {
        this.cs().setBlob(parameterName, inputStream);
    }

    @Override
    public void setNClob(String parameterName, Reader reader) throws SQLException {
        this.cs().setNClob(parameterName, reader);
    }

    // ----------------------------------------------------------------------
    // get... name
    // ----------------------------------------------------------------------

    @Override
    public String getString(String parameterName) throws SQLException {
        return this.cs().getString(parameterName);
    }

    @Override
    public boolean getBoolean(String parameterName) throws SQLException {
        return this.cs().getBoolean(parameterName);
    }

    @Override
    public byte getByte(String parameterName) throws SQLException {
        return this.cs().getByte(parameterName);
    }

    @Override
    public short getShort(String parameterName) throws SQLException {
        return this.cs().getShort(parameterName);
    }

    @Override
    public int getInt(String parameterName) throws SQLException {
        return this.cs().getInt(parameterName);
    }

    @Override
    public long getLong(String parameterName) throws SQLException {
        return this.cs().getLong(parameterName);
    }

    @Override
    public float getFloat(String parameterName) throws SQLException {
        return this.cs().getFloat(parameterName);
    }

    @Override
    public double getDouble(String parameterName) throws SQLException {
        return this.cs().getDouble(parameterName);
    }

    @Override
    public byte[] getBytes(String parameterName) throws SQLException {
        return this.cs().getBytes(parameterName);
    }

    @Override
    public Date getDate(String parameterName) throws SQLException {
        return this.cs().getDate(parameterName);
    }

    @Override
    public Time getTime(String parameterName) throws SQLException {
        return this.cs().getTime(parameterName);
    }

    @Override
    public Timestamp getTimestamp(String parameterName) throws SQLException {
        return this.cs().getTimestamp(parameterName);
    }

    @Override
    public Object getObject(String parameterName) throws SQLException {
        return this.cs().getObject(parameterName);
    }

    @Override
    public BigDecimal getBigDecimal(String parameterName) throws SQLException {
        return this.cs().getBigDecimal(parameterName);
    }

    @Override
    public Object getObject(String parameterName, Map<String, Class<?>> map) throws SQLException {
        return this.cs().getObject(parameterName, map);
    }

    @Override
    public Ref getRef(String parameterName) throws SQLException {
        return this.cs().getRef(parameterName);
    }

    @Override
    public Blob getBlob(String parameterName) throws SQLException {
        return this.cs().getBlob(parameterName);
    }

    @Override
    public Clob getClob(String parameterName) throws SQLException {
        return this.cs().getClob(parameterName);
    }

    @Override
    public Array getArray(String parameterName) throws SQLException {
        return this.cs().getArray(parameterName);
    }

    @Override
    public Date getDate(String parameterName, Calendar cal) throws SQLException {
        return this.cs().getDate(parameterName, cal);
    }

    @Override
    public Time getTime(String parameterName, Calendar cal) throws SQLException {
        return this.cs().getTime(parameterName, cal);
    }

    @Override
    public Timestamp getTimestamp(String parameterName, Calendar cal) throws SQLException {
        return this.cs().getTimestamp(parameterName, cal);
    }

    @Override
    public URL getURL(String parameterName) throws SQLException {
        return this.cs().getURL(parameterName);
    }

    @Override
    public RowId getRowId(String parameterName) throws SQLException {
        return this.cs().getRowId(parameterName);
    }

    @Override
    public NClob getNClob(String parameterName) throws SQLException {
        return this.cs().getNClob(parameterName);
    }

    @Override
    public SQLXML getSQLXML(String parameterName) throws SQLException {
        return this.cs().getSQLXML(parameterName);
    }

    @Override
    public String getNString(String parameterName) throws SQLException {
        return this.cs().getNString(parameterName);
    }

    @Override
    public Reader getNCharacterStream(String parameterName) throws SQLException {
        return this.cs().getNCharacterStream(parameterName);
    }

    @Override
    public Reader getCharacterStream(String parameterName) throws SQLException {
        return this.cs().getCharacterStream(parameterName);
    }

    @Override
    public <T> T getObject(int parameterIndex, Class<T> type) throws SQLException {
        return this.cs().getObject(parameterIndex, type);
    }

    @Override
    public <T> T getObject(String parameterName, Class<T> type) throws SQLException {
        return this.cs().getObject(parameterName, type);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
