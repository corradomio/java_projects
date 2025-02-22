package ae.ac.ebtic.sql.bt.btproxy;

import jext.sql.ResultSet;
import jext.sql.base.BaseStatement;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ae.ac.ebtic.sql.bt.btproxy.Driver.QUERY_SUFFIX;

public class BTPreparedStatement extends BaseStatement {

    private static final Logger logger = Logger.getLogger(BTPreparedStatement.class.getCanonicalName());

    private final String name;
    private final String token;
    private final String httpUrl;
    private final HttpClient client;
    private final BTConnection conn;

    private final ArrayList<String> stmtParams = new ArrayList<>();
    private final ArrayList<Object> queryParams = new ArrayList<>();
    private final ArrayList<List<Object>> batchParameters = new ArrayList<>();

    // ----------------------------------------------------------------------

    BTPreparedStatement(BTConnection conn, String name) {
        this.conn = conn;
        this.name = name;
        this.token = conn.token;
        this.httpUrl = conn.httpUrl;
        this.client = conn.client;
    }

    @Override
    public void close() {

    }

    // ----------------------------------------------------------------------
    // Special case

    @Override
    public void setString(int parameterIndex, String x) {
        if (parameterIndex < 0)
            set(stmtParams, -parameterIndex-1, x);
        else
            set(queryParams, parameterIndex-1, x);
    }

    // ----------------------------------------------------------------------

    @Override
    public void setNull(int parameterIndex, int sqlType) {
        set(queryParams, parameterIndex-1, null);
    }

    @Override
    public void setBoolean(int parameterIndex, boolean x) {
        set(queryParams, parameterIndex-1, x);
    }

    @Override
    public void setByte(int parameterIndex, byte x) {
        set(queryParams, parameterIndex-1, x);
    }

    @Override
    public void setShort(int parameterIndex, short x) {
        set(queryParams, parameterIndex-1, x);
    }

    @Override
    public void setInt(int parameterIndex, int x) {
        set(queryParams, parameterIndex-1, x);
    }

    @Override
    public void setLong(int parameterIndex, long x) {
        set(queryParams, parameterIndex-1, x);
    }

    @Override
    public void setBigDecimal(int parameterIndex, BigDecimal x) {
        set(queryParams, parameterIndex-1, x);
    }

    @Override
    public void setFloat(int parameterIndex, float x) {
        set(queryParams, parameterIndex-1, x);
    }

    @Override
    public void setDouble(int parameterIndex, double x) {
        set(queryParams, parameterIndex-1, x);
    }

    @Override
    public void setDate(int parameterIndex, Date x) {
        set(queryParams, parameterIndex-1, ISO8601DateFormat.formatDate(x));
    }

    @Override
    public void setTime(int parameterIndex, Time x) {
        set(queryParams, parameterIndex-1, ISO8601DateFormat.formatTime(x));
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x) {
        set(queryParams, parameterIndex-1, ISO8601DateFormat.formatTimestamp(x));
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType) {
        // set(queryParams, parameterIndex-1, ISO8601DateFormat.format(x));
        setObject(parameterIndex, x);
    }

    @Override
    public void setObject(int parameterIndex, Object x) {
        if (x instanceof Time)
            // java.sql.'Time: HH:mm:ss'
            set(queryParams, parameterIndex-1, ISO8601DateFormat.formatTime((java.util.Date)x));
        else if (x instanceof Date)
            // java.sql.Date
            // java.sql.Timestamp
            set(queryParams, parameterIndex-1, ISO8601DateFormat.formatDate((java.util.Date)x));
        else if (x instanceof Timestamp)
            // java.sql.Date
            // java.sql.Timestamp
            set(queryParams, parameterIndex-1, ISO8601DateFormat.formatTimestamp((java.util.Date)x));
        else
            set(queryParams, parameterIndex-1, x);
    }

    // ----------------------------------------------------------------------
    // Batch support
    // ----------------------------------------------------------------------
    // For now, the batch is implemented as multiple statements

    @Override
    public void addBatch() throws SQLException {
        // int ret = executeUpdate();
        batchParameters.add(new ArrayList<>(queryParams));
        queryParams.clear();
    }

    @Override
    public int[] executeBatch() throws SQLException {
        prepareBatch();

        Map<String, Object> result = tryExecute();

        clearBatch();

        return toIndices(result);
    }

    private static int[] toIndices(Map<String, Object> result) {

        /*
            {
              "query_id" : "test_batch",
              "status" : "SUCCESS",
              "results" : {
                "rowCount" : 1,
                "rows" : [ 5 ]
              }
            }
         */

        List<Integer> indexList = MapUtils.get(result, "results", "rows");
        int n = indexList.size();
        int[] indices = new int[n];
        for (int i=0; i<n; ++i)
            indices[i] = indexList.get(i);
        return indices;
    }

    private void prepareBatch() {
        if (!batchParameters.isEmpty()) {
            queryParams.clear();
            queryParams.addAll(batchParameters);
            batchParameters.clear();
        }
    }

    @Override
    public void clearBatch() {
        batchParameters.clear();
        queryParams.clear();
    }

    // ----------------------------------------------------------------------

    @Override
    public void clearParameters() {
        stmtParams.clear();
        queryParams.clear();
    }

    // ----------------------------------------------------------------------

    private static <T> void set(ArrayList<T> array, int index, T value) {
        checkIndex(array, index);
        array.set(index, value);
    }

    private static <T> void checkIndex(ArrayList<T> array, int index) {
        while (index >= array.size())
            array.add(null);
    }

    // ----------------------------------------------------------------------

    @Override
    public boolean execute() throws SQLException {
        trickForInsert();

        Map<String, Object> result = tryExecute();
        int rowCount = MapUtils.get(result, "results", "rowCount");
        return rowCount > 0;
    }

    @Override
    public int executeUpdate() throws SQLException {
        trickForInsert();

        Map<String, Object> result = tryExecute();
        int[] indices = toIndices(result);
        return indices.length > 0 ? indices[0] : -1;
    }

    // ----------------------------------------------------------------------

    @Override
    public ResultSet executeQuery() throws SQLException {
        Map<String, Object> resultSet = tryExecute();
        return new BTResultSet(resultSet);
    }

    private void trickForInsert() throws SQLException {
        String statementType = this.conn.getStatementType(this.name);
        if ("INSERT".equals(statementType)) {
            addBatch();
            prepareBatch();
        }
    }

    private Map<String, Object> tryExecute() throws SQLException {
        logQuery();
        try {
            Map<String, Object> postBody = MapUtils.asMap(
                "query_id", name,
                "query_type", conn.getStatementType(name),
                "parameters", stmtParams,
                "data", queryParams
            );

            HttpPost post = new HttpPost(String.format("%s/%s", httpUrl, QUERY_SUFFIX));

            POSTUtils.setBody(post, postBody);
            POSTUtils.setToken(post, token);

            HttpResponse response = client.execute(post);
            StatusLine statusLine = response.getStatusLine();
            Map<String, Object> jresponse = JSONUtils.parse(response.getEntity().getContent(), LinkedHashMap.class);

            if (statusLine.getStatusCode() > 300)
                raiseException(statusLine, jresponse);

            return jresponse;
        }
        catch (Exception e) {
            throw new jext.sql.SQLException("Connection failed", e.getMessage());
        }
    }

    private void raiseException(StatusLine statusLine, Map<String, Object> response) throws SQLException {
        /*
        {
            "status": "ERROR",
            "errorMessage": "..."
        }
         */
        String errorMessage = MapUtils.get(response, "errorMessage");
        throw new jext.sql.SQLException(errorMessage, name, MapUtils.asMap(
            "name", name,
            "structure_parameters", stmtParams,
            "query_parameters", queryParams
        ));
    }

    private void logQuery() {
        if (!logger.isLoggable(Level.FINE))
            return;

        logger.fine(String.format(
                """
                name: %s
                %s
                queryParams: %s
                stmtParams : %s
                """,
            this.name,
            conn.getStatement(this.name),
            stmtParams,
            queryParams
        ));
    }

    // ----------------------------------------------------------------------


}
