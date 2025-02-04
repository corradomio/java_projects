package org.hls.check;

import jext.sql.Connection;
import jext.sql.DriverManager;
import jext.sql.queries.JSONQueries;
import jext.sql.queries.NamedQueries;
import jext.util.JSONUtils;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Map;

public class CheckJDBC {

    public static void main(String[] args) throws Exception {
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

            PreparedStatement ps = c.prepareStatement("test");
            ResultSet rset = ps.executeQuery();

            while (rset.next()) {
                System.out.println(rset.getString(1));
                System.out.println(rset.getString("scenario name"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
