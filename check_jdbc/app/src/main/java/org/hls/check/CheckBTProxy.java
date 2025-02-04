package org.hls.check;

import jext.sql.DriverManager;

import jext.sql.Connection;
import jext.sql.queries.JSONQueries;
import jext.sql.queries.NamedQueries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jext.util.JSONUtils;

import java.io.File;
import java.util.Map;

public class CheckBTProxy {

    public static void main(String[] args) throws Exception {
        Class.forName("ae.ac.ebtic.sql.bt.btproxy.Driver");

        ResultSet dataSet;
        PreparedStatement statement;
        Connection connection = DriverManager.getConnection("jdbc:btproxy://localhost:9002/spare-dimensioning-server", "ciccio", "pasticcio");

        Map<String, Object> queries = JSONUtils.load(new File("sparemanagement-queries.json"));
        NamedQueries jq = JSONQueries.of(queries);
        jq.registerTo(connection);


        statement = connection.prepareStatement("read-spare-distribution");
        statement.setString(1, "Exp_5_items");

        dataSet = statement.executeQuery();
    }

    public static void main2(String[] args) throws Exception {
        Class.forName("ae.ac.ebtic.sql.bt.btproxy.Driver");

        ResultSet dataSet;
        PreparedStatement statement;
        Connection connection = DriverManager.getConnection("jdbc:btproxy://localhost:9002/spare-dimensioning-server", "ciccio", "pasticcio");

        Map<String, Object> queries = JSONUtils.load(new File("sparemanagement-queries.json"));
        NamedQueries jq = JSONQueries.of(queries);
        jq.registerTo(connection);

        statement = connection.prepareStatement("read-node-list-for-item");
        statement.setString(-1, AppProp.TABLE_ITEM);
        statement.setString(-2, AppProp.TABLE_TRAVEL);
        statement.setString(-3, "count");
        statement.setString(-4, "count");

        statement.setString(1, "Generic");
        statement.setString(2, "000001");

        dataSet = statement.executeQuery();


        while (dataSet.next()) {
            double lat = dataSet.getDouble(1);
            double lon = dataSet.getDouble(2);
            double x = dataSet.getDouble(2);
            double y = dataSet.getDouble(1);
            String id_locus_key = dataSet.getString(3);
            String code = dataSet.getString(4);
            int count = dataSet.getInt(5);
            int sla_time = dataSet.getInt(6);
            int sla_mkr_count = dataSet.getInt(7);
            int sla_parm = dataSet.getInt(8);
            int bh_count = dataSet.getInt(9);
            int ah_count = dataSet.getInt(10);
            String sla_unit = dataSet.getString(11);
        }
    }

    public static void main1(String[] args) throws Exception {
        Class.forName("ae.ac.ebtic.sql.bt.btproxy.Driver");

        Connection c = DriverManager.getConnection("jdbc:btproxy://localhost:9002/spare-dimensioning-server", "ciccio", "pasticcio");

        Map<String, Object> queries = JSONUtils.load(new File("sparemanagement-queries.json"));
        NamedQueries jq = JSONQueries.of(queries);
        jq.registerTo(c);

        PreparedStatement ps = c.prepareStatement("test");
        ResultSet rset = ps.executeQuery();

        while (rset.next()) {
            System.out.println(rset.getString(1));
            System.out.println(rset.getString("scenario_name"));
        }
    }
}
