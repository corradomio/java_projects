package org.hls.check;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class App {

    public static void main(String[] args) throws Exception {

        Connection conn = DriverManager.getConnection("jdbc:neo4j:bolt://localhost:7787/?username=neo4j,password=password");
        PreparedStatement stmt = conn.prepareStatement("MATCH (n:component) RETURN n LIMIT 10");
        ResultSet rset = stmt.executeQuery();

        System.out.println(conn.getClass());

        while(rset.next()) {
            System.out.println(rset.getObject(1));
        }

        conn.close();

    }
}
