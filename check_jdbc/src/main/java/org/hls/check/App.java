package org.hls.check;

import jext.sql.DriverManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {

    public static void main(String[] args) throws SQLException {

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
