package org.hls.check;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    static Driver driver;

    static void checkMySQL() throws SQLException {
        // com.mysql.jdbc.Driver        5
        // com.mysql.cj.jdbc.Driver     8
        //      com.mysql.jdbc.Driver   8 backward compatibility
        String url = "jdbc:mysql://localhost:3306/summer";
        try(Connection c = DriverManager.getConnection(url, "root", "password");
            PreparedStatement ps = c.prepareStatement("SELECT * FROM gowalla LIMIT 10");
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                System.out.println(rs.getLong(5));
            }
        }
    }

    static void checkNeo4J() throws SQLException {
        // org.neo4j.jdbc.Driver
        //      org.neo4j.jdbc.http.HttpDriver
        //      org.neo4j.jdbc.bolt.BoltDriver
        //      org.neo4j.jdbc.bolt.BoltRoutingNeo4jDriver
        String url = "jdbc:neo4j:bolt://neo4j:password@localhost:7687/";
        try(Connection c = DriverManager.getConnection(url, "neo4j", "password");
            PreparedStatement ps = c.prepareStatement("MATCH (p:project) RETURN id(p),p.name");
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                System.out.printf("%d, %s\n", rs.getLong(1), rs.getString(2));
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        checkMySQL();
        checkNeo4J();
    }
}
