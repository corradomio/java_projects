package org.hls.check;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class App {

    public static void main(String[] args) throws SQLException {

        System.out.println("connect");
        try(Connection c = getConnection("jdbc:postgresql://localhost/osm", "osmuser", "osmuser")) {
            System.out.println("stmt");
            PreparedStatement s = c.prepareStatement("SELECT tid, uid, ilon, ilat FROM abu_dhabi_tracks ORDER BY tid, uid");
            System.out.println("exec");
            ResultSet r = s.executeQuery();
            r.close();
            s.close();
        }
        System.out.println("done");
    }
}
