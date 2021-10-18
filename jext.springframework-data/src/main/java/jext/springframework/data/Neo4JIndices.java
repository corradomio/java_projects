package jext.springframework.data;

import jext.logging.Logger;
import jext.springframework.data.indices.IndexCollection;
import jext.springframework.data.indices.IndexDescriptor;
import jext.util.PropertiesUtils;
import jext.xml.XPathUtils;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.w3c.dom.Element;

import java.io.File;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Neo4JIndices {

    private Logger logger = Logger.getLogger(Neo4JIndices.class);

    private SessionFactory sessionFactory;
    private String dbversion;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------
    /*
        <indices>
            <drop name=":splproject(refId)" version="3.">
                DROP INDEX ON :splproject(refId)
            </drop>
            <drop name=":project(refId)" version="3.">
                DROP INDEX ON :project(refId)
            </drop>
            <index name="spl_fulltext_name_index">
                CALL db.index.fulltext.createNodeIndex("${name}",
                    ["project","module","type","comment","method","library","component","feature"],
                    ["name"],
                    {analyzer: "standard", eventually_consistent: "true"}
                )
            </index>
        </indices>
     */

    public Neo4JIndices() {

    }

    public void composeIndices(SessionFactory sessionFactory, File configurationFile) {
        this.sessionFactory = sessionFactory;
        try {
            Element elt = XPathUtils.parse(configurationFile).getDocumentElement();

            this.dbversion = getVersion();
            IndexCollection indexes = getIndexes();

            dropIndices(elt, indexes);
            createIndices(elt, indexes);
        }
        catch (Throwable t) {
            logger.errorf("Unable to parse %s: %s", configurationFile, t);
        }
    }

    private void dropIndices(Element elt, IndexCollection indices) {
        Properties props = new Properties();

        // drop indices
        XPathUtils.selectElements(elt, "/configuration/system/graphdb/indices/drop")
            .forEach(din -> {
                String name = din.getAttribute("name");
                String version = din.getAttribute("version");

                // the index is for a specific Neo4J version
                if (!version.isEmpty() && !dbversion.startsWith(version))
                    return;

                props.setProperty("name", name);
                String stmt = XPathUtils.getValue(din, "text()").trim();
                stmt = PropertiesUtils.resolveValue(stmt, props);

                dropIndex(name, stmt, indices);
            });
    }

    private void dropIndex(String name, String stmt, IndexCollection indices) {
        if (!indices.containsIndex(name)) return;
        if (stmt.isEmpty()) return;

        logger.warnf("Drop index %s: %s", name, stmt);

        try {
            Session session = sessionFactory.openSession();
            session.query(stmt, Collections.emptyMap());
        }
        catch (Throwable t) {
            logger.errorf("Unable to execute '%s': %s", stmt, t);
        }
    }

    private void createIndices(Element elt, IndexCollection indices) {
        Properties props = new Properties();

        // create indices
        XPathUtils.selectElements(elt, "/configuration/system/graphdb/indices/index")
            .forEach(din -> {
                String name = din.getAttribute("name");
                String version = din.getAttribute("version");

                // the index is for a specific Neo4J version
                if (!version.isEmpty() && !dbversion.startsWith(version))
                    return;

                props.setProperty("name", name);
                String stmt = XPathUtils.getValue(din, "text()").trim();
                stmt = PropertiesUtils.resolveValue(stmt, props);

                createIndex(name, stmt, indices);
            });
    }

    private void createIndex(String name, String stmt, IndexCollection indices) {
        if (indices.containsIndex(name)) return;
        if (stmt.isEmpty()) return;

        logger.warnf("Create index %s: %s", name, stmt);

        try {
            Session session = sessionFactory.openSession();
            session.query(stmt, Collections.emptyMap());
        }
        catch (Throwable t) {
            logger.errorf("Unable to execute '%s': %s", stmt, t);
        }
    }

    // ----------------------------------------------------------------------
    // Operations (Neo4J 3.5)
    // ----------------------------------------------------------------------
    // Note: Neo4J v3.5 has different syntax than Neo4J v4.x
    //
    //      CREATE INDEX ON :<label>(<property>)            single property
    //      DROP   INDEX ON :<label>(<property>)
    //      CREATE INDEX ON :<label>(<property>, ...)       multiple properties
    //      DROP   INDEX ON :<label>(<property>, ...)
    //
    //      CALL db.indexes
    //      CALL db.indexes()
    //
    //      CALL db.index.fulltext.createNodeIndex(<name>, [<label>,...], [<property>, ...], <config>)
    //      CALL db.index.fulltext.createRelationshipIndex(...)
    //      CALL db.index.fulltext.drop(<name>)
    //
    //      CALL db.index.fulltext.queryNodes(...)
    //      db.index.fulltext.queryRelationships
    //
    //      CALL db.index.fulltext.listAvailableAnalyzers()
    //
    // -- Neo4j 4.2
    //
    //      CREATE [BTREE] INDEX [index_name] [IF NOT EXISTS] FOR (n:LabelName) ON (n.propertyName)
    //             [OPTIONS "{" option: value[, ...] "}"]
    //      CREATE [BTREE] INDEX [index_name] [IF NOT EXISTS] FOR (n:LabelName) ON (n.propertyName_1, ...)
    //             [OPTIONS "{" option: value[, ...] "}"]
    //
    //      DROP INDEX index_name [IF EXISTS]
    //
    //      SHOW [ALL|BTREE] INDEX[ES] [BRIEF|VERBOSE [OUTPUT]]
    //
    //      DROP INDEX ON :LabelName(propertyName)                  -- deprecated
    //      DROP INDEX ON :LabelName (n.propertyName_1, ...)        -- deprecated
    //
    //  Note: fulltext indices are the same
    //

    // single property index
    //
    // {
    //      provider={version=1.0, key=native-btree},
    //      indexName='Unnamed index',
    //      description='INDEX ON :type(refId)',
    //      progress=100.0,
    //      tokenNames=['component'],
    //      state=ONLINE,
    //      id=14,
    //      type='node_label_property',
    //      failureMessage='',
    //      properties=['refId']
    // }
    //
    // full text index
    //
    // {
    //      provider={version=1.0, key=fulltext},
    //      indexName='spl_fulltext_index',
    //      description='INDEX ON NODE:project, module, source, type, comment, method, field, library, component, feature(name)',
    //      progress=100.0,
    //      tokenNames=['project', 'module', 'source', 'type', 'comment', 'method', 'field', 'library', 'component', 'feature'],
    //      state=ONLINE,
    //      id=7,
    //      type='node_fulltext',
    //      failureMessage='',
    //      properties=['name']
    // }
    //

    /**
     * Retrieve the Neo4J version.
     * @return the version number (for example '3.5.23')
     */
    public String getVersion() {
        String s = "CALL dbms.components() YIELD versions, edition UNWIND versions AS version RETURN version, edition;";

        Session session = sessionFactory.openSession();

        Result result = session.query(s, Collections.emptyMap());
        return (String) result.iterator().next().get("version");
    }

    public IndexCollection getIndexes() {
        IndexCollection indices = new IndexCollection();
        Session session = sessionFactory.openSession();
        Result  result = session.query("CALL db.indexes", Collections.emptyMap());
        Iterator<Map<String, Object>> it = result.iterator();
        while (it.hasNext()) {
            Map<String, Object> rec = it.next();

            IndexDescriptor idesc = new IndexDescriptor(rec);

            indices.add(idesc);
        }
        return indices;
    }

    public void createIndex(String indexStatement) {

    }

    public void createSinglePropertyIndex(List<String> labels, String property) {
        labels.forEach(label ->
            createSinglePropertyIndex(label, property));
    }

    public void dropSinglePropertyIndex(List<String> labels, String property) {
        labels.forEach(label ->
            dropSinglePropertyIndex(label, property));
    }

    public void createSinglePropertyIndex(String label, String property) {
        String s = String.format("CREATE INDEX ON :%s(%s)", label, property);
        Session session = sessionFactory.openSession();
        session.query(s, Collections.emptyMap());
    }

    public void dropSinglePropertyIndex(String label, String property) {
        String s = String.format("DROP INDEX ON :%s(%s)", label, property);

        Session session = sessionFactory.openSession();
        session.query(s, Collections.emptyMap());
    }

    public void createFulltextIndex(String indexName, List<String> labels, List<String> properties) {
        String s = String.format("CALL db.index.fulltext.createNodeIndex(%s, %s, %s, {analyzer: \"standard\", eventually_consistent: \"true\"})",
            indexName,
            toArray(labels),
            toArray(properties));

        Session session = sessionFactory.openSession();
        session.query(s, Collections.emptyMap());
    }


    public void dropIndex(String indexName) {
        // if the name is :<label>(<property, ...)
        //      drop the standard index
        // else
        //      drop a fulltext index

        String s;
        if (indexName.startsWith(":") && indexName.contains("("))
            s = String.format("DROP INDEX ON %s", indexName);
        else
            s = String.format("CALL db.index.fulltext.drop(%s)", indexName);

        Session session = sessionFactory.openSession();
        session.query(s, Collections.emptyMap());
    }

    public void dropFulltextIndex(String indexName) {
        String s = String.format("CALL db.index.fulltext.drop(%s)", indexName);

        Session session = sessionFactory.openSession();
        session.query(s, Collections.emptyMap());
    }

    private static String toArray(List<String> list) {
        Iterator<String> it = list.iterator();
        StringBuilder sb = new StringBuilder("[").append(it.next());
        while (it.hasNext())
            sb.append(",").append(it.next());
        return sb.append("]").toString();
    }

}
