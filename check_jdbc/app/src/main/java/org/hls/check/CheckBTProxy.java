package org.hls.check;

import ae.ac.ebtic.sql.bt.btproxy.BTResultSet;
import jext.sql.DriverManager;

import jext.sql.Connection;
import jext.sql.queries.JSONQueries;
import jext.sql.queries.NamedQueries;
import jext.sql.PreparedStatement;

import java.math.BigDecimal;
import java.sql.ResultSet;
import jext.util.JSONUtils;
import jext.util.logging.LogManager;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

public class CheckBTProxy {

    public static void main(String[] args) throws Exception {
        LogManager.configure(new File("logging.properties"));

        Class.forName("ae.ac.ebtic.sql.bt.btproxy.Driver");


        PreparedStatement statement;
        Connection connection = DriverManager.getConnection(
            // "jdbc:btproxy://localhost:9002/spare-dimensioning-server",
            "jdbc:btproxy://localhost:9001/spare-dimensioning-server",
            "ciccio", "pasticcio");

        Map<String, Object> queries = JSONUtils.load(new File("sparemanagement-queries.json"));
        NamedQueries jq = JSONQueries.of(queries);
        jq.registerTo(connection);

        Date now = new Date();

        statement = connection.prepareStatement("test_batch");

        for (int i=0; i<10; ++i) {
            // (str, flt, dbl, bol, i2, i4, i8, bi, d, t, dt)"
            statement.setString(1, "Ciccio Pasticcio");
            statement.setFloat(2, 1.2f);
            statement.setDouble(3, 3.4);
            statement.setBoolean(4, true);
            statement.setShort(5, (short)123);
            statement.setInt(6, 456789);
            statement.setLong(7, 123456789L);
            statement.setBigDecimal(8, new BigDecimal(1000+i));
            statement.setDate(9, new java.sql.Date(now.getTime()));
            statement.setTime(10, new java.sql.Time(now.getTime()));
            statement.setTimestamp(11, new java.sql.Timestamp(now.getTime()));

            // statement.addBatch();
        }
        // int[] indices = statement.executeBatch();
        // System.out.println(Arrays.toString(indices));

        int index = statement.executeUpdate();
        System.out.println(index);

    }

    public static void main4(String[] args) throws Exception {
        Class.forName("ae.ac.ebtic.sql.bt.btproxy.Driver");

        PreparedStatement statement;
        Connection connection = DriverManager.getConnection("jdbc:btproxy://localhost:9002/spare-dimensioning-server", "ciccio", "pasticcio");

        Map<String, Object> queries = JSONUtils.load(new File("sparemanagement-queries.json"));
        NamedQueries jq = JSONQueries.of(queries);
        jq.registerTo(connection);


        statement = connection.prepareStatement("select_fields");
        // statement.setString(1, "Exp_5_items");

        BTResultSet dataSet = (BTResultSet) statement.executeQuery();

        // dataSet.serialize(System.out);
        dataSet.next();

        System.out.println(dataSet.getDate("d"));
        System.out.println(dataSet.getTime("t"));
        System.out.println(dataSet.getTimestamp("dt"));

        System.out.println(dataSet.getTimestamp("d"));
        System.out.println(dataSet.getTimestamp("t"));

        System.out.println(dataSet.getTime("d"));
        System.out.println(dataSet.getTime("dt"));

    }


    public static void main3(String[] args) throws Exception {
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
