package org.hls.check;

import jext.sql.DriverManager;

import jext.sql.Connection;
import jext.sql.PreparedStatement;
import jext.sql.ResultSet;
import java.sql.SQLException;

public class App {

    public static void main(String[] args) throws SQLException {

        long count;
        System.out.println("Connect ...");
        try(Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/spare-management",
            "postgres", "p@stgres")) {

            PreparedStatement s = c.prepareStatement("SELECT scenario_name FROM scenarios where total_equip = ?totalequip");
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

    public static void mainOld(String[] args) throws SQLException {

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
