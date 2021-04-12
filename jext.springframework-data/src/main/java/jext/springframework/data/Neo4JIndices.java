package jext.springframework.data;

import jext.springframework.data.indices.IndexCollection;
import jext.springframework.data.indices.IndexDescriptor;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class Neo4JIndices {

    @Autowired
    private SessionFactory sessionFactory;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public Neo4JIndices() {

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

    public IndexCollection getIndexes() {
        IndexCollection indices = new IndexCollection();
        Session session = sessionFactory.openSession();
        Result  result = session.query("CALL db.indexes", Collections.emptyMap());
        Iterator<Map<String, Object>> it = result.iterator();
        while (it.hasNext()) {
            Map<String, Object> rec = it.next();

            IndexDescriptor idesc = new IndexDescriptor();
            idesc.name = (String) rec.get("indexName");
            idesc.description = (String) rec.get("description");
            idesc.type = (String) rec.get("type");

            // tokenNames: String[]
            idesc.labels.addAll(Arrays.asList((String[])rec.get("tokenNames")));
            // properties: String[]
            idesc.properties.addAll(Arrays.asList((String[])rec.get("properties")));

            indices.add(idesc);
        }
        return indices;
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
