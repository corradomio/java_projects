package jext.sql;

import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Struct;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class ConnectionEx implements Connection {

    // static ConnectionEx of(java.sql.Connection c, Properties info) {
    //     return new ConnectionEx(c, info);
    // }

    // ----------------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------------

    private Properties info;
    java.sql.Connection c;
    private Map<String, String> queries = new HashMap<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    ConnectionEx(java.sql.Connection c, Properties info) {
        this.info = info;
        this.c = c;
    }

    // ----------------------------------------------------------------------
    // createStatement
    // ----------------------------------------------------------------------

    @Override
    public Statement createStatement() throws SQLException {
        // java.sql.Statement s = c.createStatement();
        // return StatementEx.of(this, s, "");
        return new StatementEx(this);
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        // java.sql.Statement s = c.createStatement(resultSetType, resultSetConcurrency);
        // return StatementEx.of(this, s, "");
        return new StatementEx(this, resultSetType, resultSetConcurrency);
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        // java.sql.Statement s = c.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
        // return StatementEx.of(this, s, "");
        return new StatementEx(this, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    // ----------------------------------------------------------------------

    String resolveNamedQuery(String sqlx) {
        return queries.getOrDefault(sqlx, sqlx);
    }

    // ----------------------------------------------------------------------
    // prepareCall
    // ----------------------------------------------------------------------

    @Override
    public CallableStatement prepareCall(String sqlx) throws SQLException {
        // java.sql.CallableStatement cs = c.prepareCall(sql);
        // return CallableStatementEx.of(this, cs, sql);

        sqlx = resolveNamedQuery(sqlx);

        return new CallableStatementEx(this, sqlx);
    }

    @Override
    public CallableStatement prepareCall(String sqlx, int resultSetType, int resultSetConcurrency) throws SQLException {
        // java.sql.CallableStatement cs = c.prepareCall(sql, resultSetType, resultSetConcurrency);
        // return CallableStatementEx.of(this, cs, sql);

        sqlx = resolveNamedQuery(sqlx);

        return new CallableStatementEx(this, sqlx, resultSetType, resultSetConcurrency);
    }

    @Override
    public CallableStatement prepareCall(String sqlx, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        // java.sql.CallableStatement cs = c.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        // return CallableStatementEx.of(this, cs, sql);

        sqlx = resolveNamedQuery(sqlx);

        return new CallableStatementEx(this, sqlx, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    // ----------------------------------------------------------------------
    // prepareStatement
    // ----------------------------------------------------------------------

    @Override
    public PreparedStatement prepareStatement(String sqlx) throws SQLException {
        // Map<String, int[]> names = new LinkedHashMap<>();
        // String sql = parseSqlx(sqlx, names);
        //
        // java.sql.PreparedStatement ps = c.prepareStatement(sql);
        // return PreparedStatementEx.of(this, ps, sqlx, names);

        sqlx = resolveNamedQuery(sqlx);

        return new PreparedStatementEx(this, sqlx);
    }

    @Override
    public PreparedStatement prepareStatement(String sqlx, int resultSetType, int resultSetConcurrency) throws SQLException {
        // Map<String, int[]> names = new LinkedHashMap<>();
        // String sql = parseSqlx(sqlx, names);
        //
        // java.sql.PreparedStatement ps = c.prepareStatement(sql, resultSetType, resultSetConcurrency);
        // return PreparedStatementEx.of(this, ps, sqlx, names);

        sqlx = resolveNamedQuery(sqlx);

        return new PreparedStatementEx(this, sqlx, resultSetType, resultSetConcurrency);
    }

    @Override
    public PreparedStatement prepareStatement(String sqlx, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        // Map<String, int[]> names = new LinkedHashMap<>();
        // String sql = parseSqlx(sqlx, names);
        //
        // java.sql.PreparedStatement ps = c.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        // return PreparedStatementEx.of(this, ps, sqlx, names);

        sqlx = resolveNamedQuery(sqlx);

        return new PreparedStatementEx(this, sqlx, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    @Override
    public PreparedStatement prepareStatement(String sqlx, int autoGeneratedKeys) throws SQLException {
        // Map<String, int[]> names = new LinkedHashMap<>();
        // String sql = parseSqlx(sqlx, names);
        //
        // java.sql.PreparedStatement ps = c.prepareStatement(sql, autoGeneratedKeys);
        // return PreparedStatementEx.of(this, ps, sqlx, names);

        sqlx = resolveNamedQuery(sqlx);

        return new PreparedStatementEx(this, sqlx, autoGeneratedKeys);
    }

    @Override
    public PreparedStatement prepareStatement(String sqlx, int[] columnIndexes) throws SQLException {
        // Map<String, int[]> names = new LinkedHashMap<>();
        // String sql = parseSqlx(sqlx, names);
        //
        // java.sql.PreparedStatement ps = c.prepareStatement(sql, columnIndexes);
        // return PreparedStatementEx.of(this, ps, sqlx, names);

        sqlx = resolveNamedQuery(sqlx);

        return new PreparedStatementEx(this, sqlx, columnIndexes);
    }

    @Override
    public PreparedStatement prepareStatement(String sqlx, String[] columnNames) throws SQLException {
        // Map<String, int[]> names = new LinkedHashMap<>();
        // String sql = parseSqlx(sqlx, names);
        //
        // java.sql.PreparedStatement ps = c.prepareStatement(sql, columnNames);
        // return PreparedStatementEx.of(this, ps, sqlx, names);

        sqlx = resolveNamedQuery(sqlx);

        return new PreparedStatementEx(this, sqlx, columnNames);
    }

    // ----------------------------------------------------------------------

    // ----------------------------------------------------------------------
    // Connection
    // ----------------------------------------------------------------------

    @Override
    public void commit() throws SQLException {
        c.commit();
    }

    @Override
    public void rollback() throws SQLException {
        c.rollback();
    }

    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        c.rollback(savepoint);
    }

    @Override
    public void close() throws SQLException {
        c.close();
    }

    @Override
    public boolean isClosed() throws SQLException {
        return c.isClosed();
    }

    @Override
    public void abort(Executor executor) throws SQLException {
        c.abort(executor);
    }

    // ----------------------------------------------------------------------
    // Getter/Setter
    // ----------------------------------------------------------------------

    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        c.setAutoCommit(autoCommit);
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        return c.getAutoCommit();
    }

    @Override
    public String nativeSQL(String sql) throws SQLException {
        return c.nativeSQL(sql);
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return c.getMetaData();
    }

    @Override
    public void setReadOnly(boolean readOnly) throws SQLException {
        c.setReadOnly(readOnly);
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        return c.isReadOnly();
    }

    @Override
    public void setCatalog(String catalog) throws SQLException {
        c.setCatalog(catalog);
    }

    @Override
    public String getCatalog() throws SQLException {
        return c.getCatalog();
    }

    @Override
    public void setTransactionIsolation(int level) throws SQLException {
        c.setTransactionIsolation(level);
    }

    @Override
    public int getTransactionIsolation() throws SQLException {
        return c.getTransactionIsolation();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return c.getWarnings();
    }

    @Override
    public void clearWarnings() throws SQLException {
        c.clearWarnings();
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return c.getTypeMap();
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        c.setTypeMap(map);
    }

    @Override
    public void setHoldability(int holdability) throws SQLException {
        c.setHoldability(holdability);
    }

    @Override
    public int getHoldability() throws SQLException {
        return c.getHoldability();
    }

    @Override
    public Clob createClob() throws SQLException {
        return c.createClob();
    }

    @Override
    public Blob createBlob() throws SQLException {
        return c.createBlob();
    }

    @Override
    public NClob createNClob() throws SQLException {
        return c.createNClob();
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        return c.createSQLXML();
    }

    @Override
    public boolean isValid(int timeout) throws SQLException {
        return c.isValid(timeout);
    }

    @Override
    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        c.setClientInfo(name, value);
    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        c.setClientInfo(properties);
    }

    @Override
    public String getClientInfo(String name) throws SQLException {
        return c.getClientInfo(name);
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        return c.getClientInfo();
    }

    @Override
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return c.createArrayOf(typeName, elements);
    }

    @Override
    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        return c.createStruct(typeName, attributes);
    }

    @Override
    public void setSchema(String schema) throws SQLException {
        c.setSchema(schema);
    }

    @Override
    public String getSchema() throws SQLException {
        return c.getSchema();
    }

    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        c.setNetworkTimeout(executor, milliseconds);
    }

    @Override
    public int getNetworkTimeout() throws SQLException {
        return c.getNetworkTimeout();
    }

    // ----------------------------------------------------------------------
    // Savepoint
    // ----------------------------------------------------------------------

    @Override
    public Savepoint setSavepoint() throws SQLException {
        return c.setSavepoint();
    }

    @Override
    public Savepoint setSavepoint(String name) throws SQLException {
        return c.setSavepoint(name);
    }

    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        c.releaseSavepoint(savepoint);
    }

    // ----------------------------------------------------------------------
    // Named queries
    // ----------------------------------------------------------------------

    public void registerNamedQuery(String name, String statement) throws SQLException {
        if (queries.containsKey(name))
            throw new jext.sql.SQLException("Duplicated named query", name);

        this.queries.put(name, statement);
    }

    // ----------------------------------------------------------------------
    // Wrap
    // ----------------------------------------------------------------------

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return c.unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return c.isWrapperFor(iface);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
