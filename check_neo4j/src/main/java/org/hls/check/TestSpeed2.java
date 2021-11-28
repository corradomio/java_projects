package org.hls.check;

import org.json.JSONObject;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TestSpeed2 {

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

    private static Driver connect(Properties props) {
        String uri = props.getProperty("neo4j.uri");
        String username = props.getProperty("neo4j.username");
        String password = props.getProperty("neo4j.password");

        return GraphDatabase.driver( uri, AuthTokens.basic( username, password ) );
    }

    public static void main(String[] args) throws IOException {

        Properties props = loadProps();
        long maxNodes = Long.parseLong(props.getProperty("max-nodes", "0"));
        long count = 0;
        if (maxNodes == 0)
            maxNodes = Long.MAX_VALUE;

        try(Driver conn = connect(props)) {

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

    private static void createNode(Driver conn, JSONObject jobject) {
        String label = jobject.getJSONArray("labels").getString(0);
        String name  = jobject.getJSONObject("properties").getString("name");
        long nid,oid = Long.parseLong(jobject.getString("id"));

        String s = String.format("CREATE (n:%s {refId:'test', name:'%s'}) RETURN id(n) AS nid", label, name);

        try(Session sess = conn.session()) {
            Result r = sess.run(s);
            nid = (Long)(r.single().asMap().get("nid"));
        }

        nodeMap.put(oid, nid);

        if (nodeMap.size() %1000 == 0)
            System.out.printf("... created %d nodes\n", nodeMap.size());
    }

    private static void createEdge(Driver conn, JSONObject jobject) {
        String label = (String) jobject.get("label");
        long nid, oid = Long.parseLong(jobject.getString("id"));

        long osource = Long.parseLong(jobject.getJSONObject("start").getString("id"));
        long otarget = Long.parseLong(jobject.getJSONObject("end").getString("id"));

        if (!nodeMap.containsKey(osource) || !nodeMap.containsKey(otarget))
            return;

        long nsource = nodeMap.get(osource);
        long ntarget = nodeMap.get(otarget);

        String s = String.format("MATCH (s),(t) WHERE id(s)=%d AND id(t)=%d CREATE (s)-[e:%s]->(t) RETURN id(e) AS eid",
            nsource,
            ntarget,
            label);

        try(Session sess = conn.session()) {
            Result r = sess.run(s);
            nid = (Long)(r.single().asMap().get("eid"));
        }

        edgeMap.put(oid, nid);

        if (edgeMap.size() % 1000 == 0)
            System.out.printf("... created %d edges\n", edgeMap.size());
    }

    private static void unknownType(Driver conn, JSONObject jobject) {

    }

    private static void deleteAll(Driver conn) {
        long start = System.currentTimeMillis();
        long deleted = 1;
        long count = 0;

        System.out.print("Deleting ...\n");

        String s = "MATCH (n {refId:'test'}) WITH n LIMIT 10000 DETACH DELETE n RETURN count(n)";
        while(deleted > 0) {
            try(Session sess = conn.session()) {
                Result r = sess.run(s);
                deleted = r.consume().counters().nodesDeleted();
                count += deleted;
            }
        }

        System.out.printf("Deleted %d nodes in %d s\n\n", count, (System.currentTimeMillis()-start)/1000);
    }
}
