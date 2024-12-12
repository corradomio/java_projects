package jext.sql;

import java.sql.SQLException;
import java.util.Properties;

public class DriverManager {

    public static jext.sql.Connection getConnection(String url, Properties info_) throws SQLException {
        Properties info = new Properties(info_);
        info.put("url", url);
        return ConnectionEx.of(java.sql.DriverManager.getConnection(url, info_), info);
    }

    public static jext.sql.Connection getConnection(String url, String user, String password) throws SQLException {
        Properties info = new Properties();
        info.put("url", url);
        info.put("user", user);
        info.put("password", password);
        return getConnection(url, info);
    }

    public static jext.sql.Connection getConnection(String url) throws SQLException {
        Properties info = new Properties();
        info.put("url", url);
        return getConnection(url, info);
    }

    public static jext.sql.Connection getConnection(Properties info) throws SQLException {
        String url = (String) info.get("url");
        return getConnection(url, info);
    }

}
