package org.hls.check;

import jext.sql.DriverManager;

import jext.sql.Connection;
import jext.sql.queries.JSONQueries;
import jext.sql.queries.NamedQueries;
import jext.sql.Statement;
import jext.sql.PreparedStatement;
import jext.sql.ResultSet;
import jext.util.JSONUtils;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {
        Class.forName("ae.ac.ebtic.sql.bt.btproxy.Driver");

        Connection c = DriverManager.getConnection("jdbc:btproxy://localhost:9002/spare-dimensioning-server", "ciccio", "pasticcio");

        Map<String, Object> queries = JSONUtils.load(new File("sparemanagement-queries.json"));
        NamedQueries jq = JSONQueries.of(queries);
        jq.registerTo(c);

        PreparedStatement ps = c.prepareStatement("test");
        ps.executeQuery();

    }

    public static void main5(String[] args) throws IOException {
        try(Connection c = DriverManager.getConnection(
            // "jdbc:postgresql://localhost:5432/spare-management",
            "jdbc:postgresql://10.193.20.14:5432/spare-management",
            // "jdbc:postgresql://192.168.101.131:5432/spare-management",
            // "jdbc:postgresql://10.10.103.35:5432/spare-management",
            "postgres",
            // "p@stgres"
            "p0stgres"
        )) {

            Map<String, Object> queries = JSONUtils.load(new File("sparemanagement-queries.json"));
            NamedQueries jq = JSONQueries.of(queries);
            jq.registerTo(c);

            // c.registerNamedQuery("test", "SELECT scenario_name FROM scenarios");

            Statement s = c.createStatement();
            System.out.println("execute statement");

            ResultSet rs = s.executeQuery("SELECT scenario_name FROM scenarios");
            while(rs.next()) {
                System.out.printf("... %s\n", rs.getString(1));
            }

            System.out.println("--------------------");
            System.out.println("execute named statement");

            PreparedStatement ps = c.prepareStatement("test");
            rs = ps.executeQuery("SELECT scenario_name FROM scenarios");
            while(rs.next()) {
                System.out.printf("... %s\n", rs.getString(1));
            }

            System.out.println("done");

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main4(String[] args) {

        long count;
        System.out.println("Connect ...");
        try(Connection c = DriverManager.getConnection(
            // "jdbc:postgresql://localhost:5432/spare-management",
            "jdbc:postgresql://10.193.20.14:5432/spare-management",
            // "jdbc:postgresql://192.168.101.131:5432/spare-management",
            // "jdbc:postgresql://10.10.103.35:5432/spare-management",
            "postgres",
            // "p@stgres"
            "p0stgres"
        )) {

            c.registerNamedQuery("test", "SELECT scenario_name FROM scenarios");

            Statement s = c.createStatement();
            System.out.println("execute statement");

            ResultSet rs = s.executeQuery("SELECT scenario_name FROM scenarios");
            while(rs.next()) {
                System.out.printf("... %s\n", rs.getString(1));
            }

            System.out.println("--------------------");
            System.out.println("execute named statement");

            PreparedStatement ps = c.prepareStatement("test");
            rs = ps.executeQuery("SELECT scenario_name FROM scenarios");
            while(rs.next()) {
                System.out.printf("... %s\n", rs.getString(1));
            }

            System.out.println("done");

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main3(String[] args) throws Exception {

        Map<String, Object> data = JSONUtils.load(new File("sparemanagement-queries.json"), LinkedHashMap.class);

        NamedQueries nq = JSONQueries.of(data);

        String sql = nq.getQuery("ae.ebtic.tools.sparemanagement", "read-item-codes-filtered").getStatement();

        nq.forEach(q -> {
            System.out.println(q.name);
        });

        System.out.println(sql);
    }

    public static void main2(String[] args) throws SQLException {

        long count;
        System.out.println("Connect ...");
        try(Connection c = DriverManager.getConnection(
            // "jdbc:postgresql://localhost:5432/spare-management",
            "jdbc:postgresql://10.193.20.14:5432/spare-management",
            // "jdbc:postgresql://192.168.101.131:5432/spare-management",
            // "jdbc:postgresql://10.10.103.35:5432/spare-management",
            "postgres",
            // "p@stgres"
            "p0stgres"
        )) {

            PreparedStatement s = c.prepareStatement("SELECT scenario_name FROM %1$s WHERE total_equip = ?total_equip");
            s.setString(-1, "scenarios");

            s.setInt("total_equip", 1);

            System.out.println("... execute statement");
            ResultSet rs = s.executeQuery();
            while(rs.next()) {
                System.out.printf("... %s\n", rs.getString(1));
            }
            System.out.println("done");

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main1(String[] args) throws SQLException {

        long count;
        System.out.println("Connect ...");
        try(Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/osm",
            "osmuser", "osmuser")) {

            PreparedStatement s = c.prepareStatement("SELECT uid,tid,ilon,ilat FROM abu_dhabi_tracks WHERE uid BETWEEN ? AND ?");
            PreparedStatement i = c.prepareStatement("INSERT INTO abu_dhabi_tracks_2 (uid,tid,ilon,ilat) VALUES (?, ?, ?, ?)");
            System.out.println("... execute statement");
            s.setInt(1, 1);
            s.setInt(2, 100);
            ResultSet rs = s.executeQuery();
            System.out.println("... read result");
            rs.next();
            System.out.println("done");
            count = rs.getLong(1);
        }

        System.out.println(count);

    }
}
