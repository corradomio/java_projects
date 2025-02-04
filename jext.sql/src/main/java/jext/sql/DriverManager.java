package jext.sql;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.Properties;

public class DriverManager {

    public static Connection getConnection(String url, String username, String password) throws SQLException {
        Properties info = new Properties();
        info.put("url", url);
        info.put("user", username);
        info.put("password", password);
        return getConnection(url, info);
    }

    public static Connection getConnection(String url) throws SQLException {
        Properties info = new Properties();
        info.put("url", url);
        return getConnection(url, info);
    }

    public static Connection getConnection(Properties info) throws SQLException {
        String url = (String) info.get("url");
        return getConnection(url, info);
    }

    public static Connection getConnection(File connectionProperties) throws SQLException {
        Properties info = new Properties();
        try(Reader reader = new FileReader(connectionProperties)) {
            info.load(reader);
        } catch (IOException e) {
            throw new SQLException(e);
        }
        return getConnection(info);
    }

    public static Connection getConnection(String url, Properties info_) throws SQLException {
        Properties info = new Properties();
        info.putAll(info_);
        info.put("url", url);
        java.sql.Connection con = java.sql.DriverManager.getConnection(url, info);

        if (Connection.class.isAssignableFrom(con.getClass()))
            return (Connection) con;
        else
            return new ConnectionEx(con, info);
    }

}
