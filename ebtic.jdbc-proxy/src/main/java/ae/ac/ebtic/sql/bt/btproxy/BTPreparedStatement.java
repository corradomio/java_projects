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
import java.util.Map;

import static ae.ac.ebtic.sql.bt.btproxy.Driver.QUERY_SUFFIX;

public class BTPreparedStatement extends BaseStatement {

    private String name;
    private String token;
    private String httpUrl;
    private HttpClient client;
    private BTConnection conn;
    private ArrayList<String> stmtParams = new ArrayList<>();
    private ArrayList<Object> queryParams = new ArrayList<>();

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

    // @Override
    // public void setStatementParameter(int index, String value) {
    //     // index starts from 1
    //     stmtParams.set(index-1, value);
    // }

    // ----------------------------------------------------------------------

    @Override
    public void setNull(int parameterIndex, int sqlType) throws SQLException {
        ArrayUtils.set(queryParams, parameterIndex-1, null);
    }

    @Override
    public void setBoolean(int parameterIndex, boolean x) throws SQLException {
        ArrayUtils.set(queryParams, parameterIndex-1, x);
    }

    @Override
    public void setByte(int parameterIndex, byte x) throws SQLException {
        ArrayUtils.set(queryParams, parameterIndex-1, x);
    }

    @Override
    public void setShort(int parameterIndex, short x) throws SQLException {
        ArrayUtils.set(queryParams, parameterIndex-1, x);
    }

    @Override
    public void setInt(int parameterIndex, int x) throws SQLException {
        ArrayUtils.set(queryParams, parameterIndex-1, x);
    }

    @Override
    public void setLong(int parameterIndex, long x) throws SQLException {
        ArrayUtils.set(queryParams, parameterIndex-1, x);
    }

    @Override
    public void setFloat(int parameterIndex, float x) throws SQLException {
        ArrayUtils.set(queryParams, parameterIndex-1, x);
    }

    @Override
    public void setDouble(int parameterIndex, double x) throws SQLException {
        ArrayUtils.set(queryParams, parameterIndex-1, x);
    }

    @Override
    public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
        ArrayUtils.set(queryParams, parameterIndex-1, x);
    }

    @Override
    public void setString(int parameterIndex, String x) {
        if (parameterIndex < 0)
            ArrayUtils.set(stmtParams, -parameterIndex-1, x);
        else
            ArrayUtils.set(queryParams, parameterIndex-1, x);
    }

    @Override
    public void setDate(int parameterIndex, Date x) throws SQLException {
        ArrayUtils.set(queryParams, parameterIndex-1, x);
    }

    @Override
    public void setTime(int parameterIndex, Time x) throws SQLException {
        ArrayUtils.set(queryParams, parameterIndex-1, x);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
        ArrayUtils.set(queryParams, parameterIndex-1, x);
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
        ArrayUtils.set(queryParams, parameterIndex-1, x);
    }

    @Override
    public void setObject(int parameterIndex, Object x) throws SQLException {
        ArrayUtils.set(queryParams, parameterIndex-1, x);
    }

    @Override
    public void clearParameters() throws SQLException {
        stmtParams.clear();
        queryParams.clear();
    }

    // ----------------------------------------------------------------------

    @Override
    public boolean execute() throws SQLException {
        int count = executeUpdate();
        return count > 0;
    }

    @Override
    public int executeUpdate() throws SQLException {
        Map<String, Object> result = tryExecute();
        return 0;
    }

    // ----------------------------------------------------------------------

    @Override
    public ResultSet executeQuery() throws SQLException {
        Map<String, Object> resultSet = tryExecute();
        return new BTResultSet(resultSet);
    }

    private Map<String, Object> tryExecute() throws SQLException {
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
            throw jext.sql.SQLException.of("Connection failed", e.getMessage());
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
        throw jext.sql.SQLException.of(errorMessage, name, MapUtils.asMap(
            "structure_parameters", stmtParams,
            "query_parameters", queryParams
        ));
    }

    // ----------------------------------------------------------------------


}
