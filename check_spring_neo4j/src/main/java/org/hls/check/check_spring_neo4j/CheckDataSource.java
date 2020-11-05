package org.hls.check.check_spring_neo4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CheckDataSource {

    public static void main(String[] args) throws Exception {

        // Connecting
        try (Connection con = DriverManager.getConnection("jdbc:neo4j:bolt://localhost:7687", "neo4j", "password")) {

            // Querying
            String query = "MATCH (u:project) RETURN u.name";
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                // stmt.setString(1,"John");

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        System.out.println("Friend: "+rs.getString("u.name"));
                    }
                }
            }
        }
    }
}
