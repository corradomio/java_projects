package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/*
    JDBC is available ONLY in ENTERPRISE databases
 */

// TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        // TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        // System.out.printf("Hello and welcome!");
        //
        // for (int i = 1; i <= 5; i++) {
        //     // TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
        //     // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
        //     System.out.println("i = " + i);
        // }

        // Connection details (replace with your MongoDB instance details)
        String url = "jdbc:mongodb://localhost:27017/testdb?authSource=admin";
        // String url = "mongodb://localhost:27017/testdb?authSource=admin";
        Properties props = new Properties();
        // Uncomment the lines below if authentication is required
        // props.put("user", "myUser");
        // props.put("password", "myPassword");

        // Ensure the driver is registered (often automatic with JDBC 4.0+)
        try {
            Class.forName("com.mongodb.jdbc.MongoDriver");
            // Class.forName("mongodb.jdbc.MongoDriver");
        } catch (ClassNotFoundException e) {
            System.err.println("MongoDB JDBC Driver not found: " + e.getMessage());
            return;
        }

        System.out.println("Connecting to database...");

        // Use try-with-resources to ensure resources are closed automatically
        try (Connection conn = DriverManager.getConnection(url, props);
             Statement stmt = conn.createStatement();
             // The BI connector allows SQL queries against MongoDB collections
             ResultSet rs = stmt.executeQuery("SELECT * FROM myCollection LIMIT 5")) {

            System.out.println("Connection successful.");
            System.out.println("++++++ Showing contents for testdb.myCollection ++++++++");

            while (rs.next()) {
                // Retrieve by column name, similar to relational databases
                System.out.println(rs.getString(1));
            }

        } catch (SQLException e) {
            System.err.println("SQL Exception during connection or query: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
