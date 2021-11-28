package org.hls.check;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TestSpeed {

    private static Map<Long, Long> nodeMap = new HashMap<>();
    private static Map<Long, Long> edgeMap = new HashMap<>();

    private static Properties loadProps() throws IOException {
        Properties props = new Properties();
        try(InputStream s = new FileInputStream(new File("config/application.properties"))) {
            props.load(s);
        }
        return props;
    }

    private static LineNumberReader openJson() throws FileNotFoundException {
        return new LineNumberReader(new FileReader(new File("config/hibernate.json")));
    }

    private static Connection connect(Properties props) throws SQLException {
        String jdbcurl = props.getProperty("neo4j.jdbc-url");
        return DriverManager.getConnection(jdbcurl);
    }

    public static void main(String[] args) throws IOException, SQLException {

        Properties props = loadProps();
        long maxNodes = Long.parseLong(props.getProperty("max-nodes", "0"));
        long count = 0;
        if (maxNodes == 0)
            maxNodes = Long.MAX_VALUE;

        try(Connection conn = connect(props)) {

            deleteAll(conn);

            System.out.print("Creating ...\n");
            long start = System.currentTimeMillis();
            try (LineNumberReader rdr = openJson()) {
                for (String line = rdr.readLine(); line != null; line = rdr.readLine()) {
                    JSONObject jobject = new JSONObject(line);
                    String type = (String) jobject.get("type");

                    if ("node".equals(type)) {
                        if (nodeMap.size() < maxNodes)
                            createNode(conn, jobject);
                    }
                    else if ("relationship".equals(type)) {
                        createEdge(conn, jobject);
                    }
                    else
                        unknownType(conn, jobject);
                }
            }

            System.out.printf("End: created %d nodes, %d edges in %d s\n",
                nodeMap.size(),
                edgeMap.size(),
                (System.currentTimeMillis() - start)/1000);
        };
    }

    private static void deleteAll(Connection conn) throws SQLException {
        long start = System.currentTimeMillis();
        Statement stmt = conn.createStatement();
        long deleted = 1;
        long count = 0;

        System.out.print("Deleting ...\n");

        String s = "MATCH (n {refId:'test'}) WITH n LIMIT 10000 DETACH DELETE n RETURN count(n)";
        while(deleted > 0) {
            ResultSet rset = stmt.executeQuery(s);
            rset.next();
            deleted = rset.getLong(1);
            count += deleted;
        }

        System.out.printf("Deleted %d nodes in %d s\n\n", count, (System.currentTimeMillis()-start)/1000);
    }

    private static void unknownType(Connection conn, JSONObject jobject) {
        System.out.printf("UNKNOWN: %s \n", jobject.toString());
    }

    private static void createNode(Connection conn, JSONObject jobject) throws SQLException {
        String label = jobject.getJSONArray("labels").getString(0);
        String name  = jobject.getJSONObject("properties").getString("name");
        long oid = Long.parseLong(jobject.getString("id"));

        String s = String.format("CREATE (n:%s {refId:'test', name:'%s'}) RETURN id(n)", label, name);

        Statement stmt = conn.createStatement();
        ResultSet rset = stmt.executeQuery(s);
        rset.next();
        long nid = rset.getLong(1);

        nodeMap.put(oid, nid);

        if (nodeMap.size() %1000 == 0)
            System.out.printf("... created %d nodes\n", nodeMap.size());
    }

    // {"id":"294973","type":"relationship","label":"ofType",
    //      "properties":{"inRevision":[true],"type":"return"},
    //      "start":{"id":"118161","labels":["method"]},
    //      "end":{"id":"44108","labels":["type"]}}
    private static void createEdge(Connection conn, JSONObject jobject) throws SQLException {
        String label = (String) jobject.get("label");
        long oid = Long.parseLong(jobject.getString("id"));

        long osource = Long.parseLong(jobject.getJSONObject("start").getString("id"));
        long otarget = Long.parseLong(jobject.getJSONObject("end").getString("id"));

        if (!nodeMap.containsKey(osource) || !nodeMap.containsKey(otarget))
            return;

        long nsource = nodeMap.get(osource);
        long ntarget = nodeMap.get(otarget);

        String s = String.format("MATCH (s),(t) WHERE id(s)=%d AND id(t)=%d CREATE (s)-[e:%s]->(t) RETURN id(e)",
            nsource,
            ntarget,
            label);
        Statement stmt = conn.createStatement();
        ResultSet rset = stmt.executeQuery(s);
        rset.next();
        long nid = rset.getLong(1);

        edgeMap.put(oid, nid);

        if (edgeMap.size() % 1000 == 0)
            System.out.printf("... created %d edges\n", edgeMap.size());
    }

}
