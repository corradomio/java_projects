package org.hls.check.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Connecting
        try (Connection con = DriverManager.getConnection("jdbc:neo4j:bolt://localhost", "neo4j", "password")) {

            // Querying
            String query = "MATCH (p:project)--(m:module) WHERE p.name = {1} RETURN p.name, m.name";
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setString(1,"spl26");

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        System.out.println("Module: '"+rs.getString("p.name")+"' <-- '"+rs.getString("m.name")+"'");
                    }
                }
            }
        }
    }
}
