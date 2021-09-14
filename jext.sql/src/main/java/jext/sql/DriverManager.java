package jext.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DriverManager {

    public static Connection getConnection(String url, Properties info_) throws SQLException {
        Properties info = new Properties(info_);
        info.put("url", url);
        return ConnectionWrapper.of(java.sql.DriverManager.getConnection(url, info_), info);
    }

    public static Connection getConnection(String url, String user, String password) throws SQLException {
        Properties info = new Properties();
        info.put("url", url);
        info.put("user", user);
        info.put("password", password);
        return ConnectionWrapper.of(java.sql.DriverManager.getConnection(url, user, password), info);
    }

    public static Connection getConnection(String url) throws SQLException {
        Properties info = new Properties();
        info.put("url", url);
        return ConnectionWrapper.of(java.sql.DriverManager.getConnection(url), info);
    }

}
