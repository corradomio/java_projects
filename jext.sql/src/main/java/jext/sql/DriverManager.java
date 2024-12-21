package jext.sql;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.Properties;

public class DriverManager {

    public static jext.sql.Connection getConnection(String url, Properties info_) throws SQLException {
        Properties info = new Properties(info_);
        info.put("url", url);
        java.sql.Connection con = DriverManager.getConnection(url, info);
        return ConnectionEx.of(con, info);
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

    public static jext.sql.Connection getConnection(File connectionProperties) throws SQLException {
        Properties info = new Properties();
        try(Reader reader = new FileReader(connectionProperties)) {
            info.load(reader);
        } catch (IOException e) {
            throw new java.sql.SQLException(e);
        }
        return getConnection(info);
    }

}
