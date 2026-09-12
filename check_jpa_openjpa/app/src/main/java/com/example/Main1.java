package com.example;

import jext.sql.ResultSetIterator;
import jext.util.PropertiesUtils;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

public class Main1 {
    public static void main(String[] args) throws SQLException {
        System.out.println("Hello world!");

        Properties dbprops = PropertiesUtils.load(new File("dbms.properties"));

        // try(Connection conn = DriverManager.getConnection(
        //     "jdbc:postgresql://10.193.20.14:5432/spare-management",
        //     "postgres",
        //     "p0stgres"
        //     ))
        // {
        //     System.out.println("connected");
        // }

        String url = dbprops.getProperty("url");
        try(Connection conn = DriverManager.getConnection(url, dbprops))
        {
            System.out.println("connected");

            ResultSet rset = conn.prepareStatement("select scenario_name from scenarios").executeQuery();
            while(rset.next()) {
                System.out.println(rset.getString("scenario_name"));
            }

            System.out.println("---");

            ResultSetIterator rit = ResultSetIterator.of(
                conn.prepareStatement("select scenario_name from scenarios").executeQuery(),
                "scenario_name");
            while(rit.hasNext()) {
                Map<String, Object> record = rit.next();
                System.out.println(record.get("scenario_name"));
            }
        }

        System.out.println("---");

        System.out.println("Done!");
    }
}